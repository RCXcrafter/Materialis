package com.rcx.materialis.modifiers;

import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap.Builder;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

public class UnderlordModifier extends Modifier implements ProjectileHitModifierHook {

	@Override
	protected void registerHooks(Builder hookBuilder) {
		hookBuilder.addHook(this, TinkerHooks.PROJECTILE_HIT);
	}

	@Override
	public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
		if (target.getType().getRegistryName().getNamespace().equals("undergarden") && target.canChangeDimensions()) {
			ToolAttackUtil.attackEntitySecondary(new IndirectEntityDamageSource("arrow", projectile, attacker).setProjectile(), (float) (projectile.getDeltaMovement().length() * 0.5 * modifier.getLevel()), target, target, true);
		}
		return false;
	}

	@Override
	public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (context.getTarget().getType().getRegistryName().getNamespace().equals("undergarden") && context.getTarget().canChangeDimensions())
			return damage * (1.0f + 0.5f * level);
		return damage;
	}

	@Override
	public void onBreakSpeed(IToolStackView tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		BlockState state = event.getState();
		if (isEffective && state != null && state.getBlock().getRegistryName().getNamespace().equals("undergarden"))
			event.setNewSpeed(event.getNewSpeed() * (1.0f + 0.25f * level));
	}
}