package com.rcx.materialis.modifiers;

import cofh.core.init.CoreMobEffects;
import cofh.lib.api.block.IDismantleable;
import cofh.lib.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
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

public class ThermalWrenchingModifier extends NoLevelsModifier implements BlockInteractionModifierHook {

	boolean enabled = ModList.get().isLoaded("cofh_core");

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
			if (context.getPlayer().isSecondaryUseActive() && block.canEntityDestroy(state, world, pos, context.getPlayer()) && block instanceof IDismantleable && ((IDismantleable) block).canDismantle(world, pos, state, context.getPlayer())) {
				if (Utils.isServerWorld(world)) {
					BlockHitResult target = new BlockHitResult(context.getClickLocation(), context.getClickedFace(), context.getClickedPos(), context.isInside());
					((IDismantleable) block).dismantleBlock(world, pos, state, target, context.getPlayer(), false);
				}
				ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		if (enabled) {
			LivingEntity target = context.getLivingTarget();
			if (target != null && context.isFullyCharged() && target.isAlive()) {
				target.addEffect(new MobEffectInstance(CoreMobEffects.WRENCHED.get(), 60, 0, false, false));
			}
		}
		return 0;
	}
}