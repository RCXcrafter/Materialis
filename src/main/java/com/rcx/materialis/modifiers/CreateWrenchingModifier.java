package com.rcx.materialis.modifiers;

import java.util.Random;

import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.contraptions.wrench.IWrenchable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.BlockInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap.Builder;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class CreateWrenchingModifier extends NoLevelsModifier implements BlockInteractionModifierHook {

	boolean enabled = ModList.get().isLoaded("create");
	Random rand = new Random();

	@Override
	protected void registerHooks(Builder hookBuilder) {
		hookBuilder.addHook(this, TinkerHooks.BLOCK_INTERACT);
	}

	@Override
	public InteractionResult beforeBlockUse(IToolStackView tool, ModifierEntry modifier, UseOnContext context, InteractionSource source) {
		if (enabled && !tool.isBroken() && context.getPlayer() != null) {
			Level world = context.getLevel();
			BlockPos pos = context.getClickedPos();
			BlockState state = context.getLevel().getBlockState(context.getClickedPos());
			Block block = state.getBlock();
			if (!(block instanceof IWrenchable)) {
				if (context.getPlayer().isSecondaryUseActive() && AllTags.AllBlockTags.WRENCH_PICKUP.matches(state)) {
					ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
					return onItemUseOnOther(world, pos, state, context);
				}
				return InteractionResult.PASS;
			}
			IWrenchable actor = (IWrenchable) block;
			ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
			if (context.getPlayer().isSecondaryUseActive())
				return actor.onSneakWrenched(state, context);
			return actor.onWrenched(state, context);
		}
		return InteractionResult.PASS;
	}

	//mostly copied from create WrenchItem.java
	private InteractionResult onItemUseOnOther(Level world, BlockPos pos, BlockState state, UseOnContext context) {
		Player player = context.getPlayer();
		if (!(world instanceof ServerLevel))
			return InteractionResult.SUCCESS;
		if (player != null && !player.isCreative())
			Block.getDrops(state, (ServerLevel) world, pos, world.getBlockEntity(pos), player, context.getItemInHand())
			.forEach(itemStack -> player.getInventory().placeItemBackInInventory(itemStack));
		state.spawnAfterBreak((ServerLevel) world, pos, ItemStack.EMPTY);
		world.destroyBlock(pos, false);
		AllSoundEvents.WRENCH_REMOVE.playOnServer(world, pos, 1, rand.nextFloat() * .5f + .5f);
		return InteractionResult.SUCCESS;
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		if (context.getTarget() instanceof AbstractMinecart) {
			DamageSource source;
			Player player = context.getPlayerAttacker();
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