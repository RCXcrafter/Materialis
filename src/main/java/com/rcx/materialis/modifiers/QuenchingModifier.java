package com.rcx.materialis.modifiers;

import javax.annotation.Nullable;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
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
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class QuenchingModifier extends Modifier implements ProjectileHitModifierHook {

	@Override
	public int getPriority() {
		return 85; //after modifiers that set mobs on fire and after refueling
	}

	@Override
	protected void registerHooks(Builder hookBuilder) {
		hookBuilder.addHook(this, TinkerHooks.PROJECTILE_HIT);
	}

	@Override
	public boolean onProjectileHitEntity(ModifierNBT modifiers, NamespacedNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
		if (target != null && target.isAlive() && target.isOnFire()) {
			float bonus = 0.0f;
			bonus = (target.getRemainingFireTicks() * modifier.getLevel()) / 60.0f;
			target.clearFire();

			ToolAttackUtil.attackEntitySecondary(new IndirectEntityDamageSource("arrow", projectile, attacker).setProjectile(), bonus, target, target, true);
		}
		return false;
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		if (context.getTarget().isAlive() && context.getTarget().isOnFire()) {
			float bonus = 0.0f;
			bonus = (context.getTarget().getRemainingFireTicks() * level) / 60.0f;
			context.getTarget().clearFire();

			DamageSource source;
			Player player = context.getPlayerAttacker();
			if (player != null) {
				source = DamageSource.playerAttack(player);
			} else {
				source = DamageSource.mobAttack(context.getAttacker());
			}
			ToolAttackUtil.attackEntitySecondary(source, bonus * tool.getMultiplier(ToolStats.ATTACK_DAMAGE), context.getTarget(), context.getLivingTarget(), true);
		}
		return 0;
	}
}