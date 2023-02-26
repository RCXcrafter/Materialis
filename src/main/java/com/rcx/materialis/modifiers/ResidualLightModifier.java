package com.rcx.materialis.modifiers;

import javax.annotation.Nullable;

import com.rcx.materialis.MaterialisResources;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap.Builder;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

public class ResidualLightModifier extends NoLevelsModifier implements ProjectileHitModifierHook {

	@Override
	public int getPriority() {
		return Short.MIN_VALUE - 30; //after exchanging
	}

	@Override
	protected void registerHooks(Builder hookBuilder) {
		hookBuilder.addHook(this, TinkerHooks.PROJECTILE_HIT);
	}

	@Override
	public void afterBlockBreak(IToolStackView tool, int level, ToolHarvestContext context) {
		if (context.getWorld().getBlockState(context.getPos()).isAir())
			context.getWorld().setBlock(context.getPos(), MaterialisResources.LIGHT_RESIDUE.get().defaultBlockState(), 3 | 64);
		else if (context.getWorld().getBlockState(context.getPos()).equals(Blocks.WATER.defaultBlockState()))
			context.getWorld().setBlock(context.getPos(), MaterialisResources.LIGHT_RESIDUE.get().defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true), 3 | 64);
	}

	public boolean onProjectileHitBlock(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, BlockHitResult hit, @Nullable LivingEntity attacker) {
		BlockPos pos = hit.getBlockPos().relative(hit.getDirection());
		BlockState state = projectile.level.getBlockState(pos);

		if (state.isAir())
			projectile.level.setBlock(pos, MaterialisResources.LIGHT_RESIDUE.get().defaultBlockState(), 3 | 64);
		else if (state.equals(Blocks.WATER.defaultBlockState()))
			projectile.level.setBlock(pos, MaterialisResources.LIGHT_RESIDUE.get().defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true), 3 | 64);
		return false;
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		LivingEntity target = context.getLivingTarget();
		if (target != null && context.isFullyCharged() && target.isAlive()) {
			target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 400, 0, false, true));
		}
		return 0;
	}
}