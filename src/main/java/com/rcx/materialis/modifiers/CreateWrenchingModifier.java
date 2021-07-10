package com.rcx.materialis.modifiers;

import java.util.Random;

import com.rcx.materialis.datagen.MaterialisBlockTags;
import com.simibubi.create.content.contraptions.wrench.IWrenchable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.helper.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class CreateWrenchingModifier extends SingleUseModifier {

	boolean enabled = ModList.get().isLoaded("create");
	Random rand = new Random();

	public CreateWrenchingModifier() {
		super(0xD1A958);
	}

	@Override
	public ActionResultType beforeBlockUse(IModifierToolStack tool, int level, ItemUseContext context) {
		if (enabled && !tool.isBroken() && context.getPlayer() != null) {
			World world = context.getLevel();
			BlockPos pos = context.getClickedPos();
			BlockState state = context.getLevel().getBlockState(context.getClickedPos());
			Block block = state.getBlock();
			if (!(block instanceof IWrenchable)) {
				if (MaterialisBlockTags.CREATE_WRENCH_PICKUP.contains(block))
					return onItemUseOnOther(world, pos, state, context);
				return ActionResultType.PASS;
			}

			IWrenchable actor = (IWrenchable) block;
			if (context.getPlayer().isSecondaryUseActive())
				return actor.onSneakWrenched(state, context);
			return actor.onWrenched(state, context);
		}
		return ActionResultType.PASS;
	}

	//mostly copied from create WrenchItem.java
	private ActionResultType onItemUseOnOther(World world, BlockPos pos, BlockState state, ItemUseContext context) {
		PlayerEntity player = context.getPlayer();
		if (!(world instanceof ServerWorld))
			return ActionResultType.SUCCESS;
		if (player != null && !player.isCreative())
			Block.getDrops(state, (ServerWorld) world, pos, world.getBlockEntity(pos), player, context.getItemInHand())
			.forEach(itemStack -> player.inventory.placeItemBackInInventory(world, itemStack));
		state.spawnAfterBreak((ServerWorld) world, pos, ItemStack.EMPTY);
		world.destroyBlock(pos, false);
		//TODO: uncomment this when new create version is released
		//AllSoundEvents.WRENCH_REMOVE.playOnServer(world, pos, 1, rand.nextFloat() * .5f + .5f);
		return ActionResultType.SUCCESS;
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (context.getTarget() instanceof AbstractMinecartEntity)
			return damage + 100;
		return damage;
	}
}