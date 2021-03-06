package com.rcx.materialis.modifiers;

import java.util.Random;

import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.contraptions.wrench.IWrenchable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
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
				if (context.getPlayer().isSecondaryUseActive() && AllTags.AllBlockTags.WRENCH_PICKUP.matches(state)) {
					ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
					return onItemUseOnOther(world, pos, state, context);
				}
				return ActionResultType.PASS;
			}
			IWrenchable actor = (IWrenchable) block;
			ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
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
		AllSoundEvents.WRENCH_REMOVE.playOnServer(world, pos, 1, rand.nextFloat() * .5f + .5f);
		return ActionResultType.SUCCESS;
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (context.getTarget() instanceof AbstractMinecartEntity) {
			DamageSource source;
			PlayerEntity player = context.getPlayerAttacker();
			if (player != null) {
				source = DamageSource.playerAttack(player);
			} else {
				source = DamageSource.mobAttack(context.getAttacker());
			}
			context.getTarget().hurt(source, 100);
		}
		return 0;
	}
}