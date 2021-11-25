package com.rcx.materialis.modifiers;

import java.util.List;
import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces.IConfigurableSides;
import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces.IDirectionalTile;
import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces.IHammerInteraction;
import blusunrize.immersiveengineering.common.util.ItemNBTHelper;
import blusunrize.immersiveengineering.common.util.advancements.IEAdvancements;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class ImmersiveWrenchingModifier extends SingleUseModifier {

	boolean enabled = ModList.get().isLoaded("immersiveengineering");

	public ImmersiveWrenchingModifier() {
		super(0x8F5034);
	}

	//mostly copied from immersive engineering HammerItem.java
	@Override
	public ActionResultType beforeBlockUse(IModifierToolStack tool, int level, ItemUseContext context) {
		if (enabled && !tool.isBroken() && context.getPlayer() != null) {
			World world = context.getLevel();
			BlockPos pos = context.getClickedPos();
			PlayerEntity player = context.getPlayer();
			Direction side = context.getClickedFace();
			ItemStack stack = context.getPlayer().getMainHandItem();
			if (tool instanceof ToolStack)
				stack = ((ToolStack) tool).createStack();

			/*
				Multiblock Handling
			 */
			List<ResourceLocation> permittedMultiblocks = null;
			List<ResourceLocation> interdictedMultiblocks = null;
			if (ItemNBTHelper.hasKey(stack, "multiblockPermission")) {
				ListNBT list = stack.getOrCreateTag().getList("multiblockPermission", NBT.TAG_STRING);
				Optional<List<ResourceLocation>> permittedMultiblocksResult = parseUserDefinedRLs(list, player, "permission");
				if(!permittedMultiblocksResult.isPresent())
					return ActionResultType.FAIL;
				else
					permittedMultiblocks = permittedMultiblocksResult.get();
			}
			if (ItemNBTHelper.hasKey(stack, "multiblockInterdiction")) {
				ListNBT list = stack.getOrCreateTag().getList("multiblockInterdiction", NBT.TAG_STRING);
				Optional<List<ResourceLocation>> interdictedMultiblocksResult = parseUserDefinedRLs(list, player, "interdiction");
				if (!interdictedMultiblocksResult.isPresent())
					return ActionResultType.FAIL;
				else
					interdictedMultiblocks = interdictedMultiblocksResult.get();
			}
			final Direction multiblockSide;
			if (side.getAxis() == Axis.Y && player != null)
				multiblockSide = Direction.fromYRot(player.yRot).getOpposite();
			else
				multiblockSide = side;
			for (MultiblockHandler.IMultiblock mb : MultiblockHandler.getMultiblocks())
				if (mb.isBlockTrigger(world.getBlockState(pos), multiblockSide, world)) {
					boolean isAllowed;
					if (permittedMultiblocks!=null)
						isAllowed = permittedMultiblocks.contains(mb.getUniqueName());
					else if (interdictedMultiblocks!=null)
						isAllowed = !interdictedMultiblocks.contains(mb.getUniqueName());
					else
						isAllowed = true;
					if (!isAllowed)
						continue;
					if (MultiblockHandler.postMultiblockFormationEvent(player, mb, pos, stack).isCanceled())
						continue;
					if (mb.createStructure(world, pos, multiblockSide, player)) {
						if(player instanceof ServerPlayerEntity)
							IEAdvancements.TRIGGER_MULTIBLOCK.trigger((ServerPlayerEntity)player, mb, stack);
						ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
						return ActionResultType.SUCCESS;
					}
				}

			/*
				Side Configs & Rotation Handling
			 */
			TileEntity tile = world.getBlockEntity(pos);
			if (tile instanceof IConfigurableSides) {
				Direction activeSide = ((player!=null)&&player.isShiftKeyDown())?side.getOpposite(): side;
				if(((IConfigurableSides)tile).toggleSide(activeSide, player)) {
					ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
					return ActionResultType.SUCCESS;
				} else
					return ActionResultType.FAIL;
			} else {
				boolean rotate = !(tile instanceof IDirectionalTile) && !(tile instanceof IHammerInteraction);
				if (!rotate && tile instanceof IDirectionalTile)
					rotate = ((IDirectionalTile) tile).canHammerRotate(side, context.getClickLocation().subtract(Vector3d.atLowerCornerOf(pos)), player);
				else if (!rotate && tile instanceof IHammerInteraction) {
					if (((IHammerInteraction) tile).hammerUseSide(side, player, context.getHand(), context.getClickLocation())) {
						ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
						return ActionResultType.SUCCESS;
					}
				}
			}
		}
		return ActionResultType.PASS;
	}

	private static Optional<List<ResourceLocation>> parseUserDefinedRLs(ListNBT data, PlayerEntity player, String prefix) {
		DataResult<List<ResourceLocation>> result = parseUserDefinedRLs(data);
		return result.resultOrPartial(err -> {
			if(player != null && !player.getCommandSenderWorld().isClientSide)
				player.displayClientMessage(new StringTextComponent("Invalid " + prefix + " entry: " + err), false);
		});
	}

	private static DataResult<List<ResourceLocation>> parseUserDefinedRLs(ListNBT data) {
		return Codec.list(ResourceLocation.CODEC).parse(NBTDynamicOps.INSTANCE, data);
	}
}