package com.rcx.materialis.modifiers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class QuenchingModifier extends Modifier {

	public QuenchingModifier() {
		super(0xBAE6D5);
	}

	@Override
	public int getPriority() {
		return 85; //after modifiers that set mobs on fire and after refueling
	}

	@Override
	public float applyLivingDamage(IModifierToolStack tool, int level, LivingEntity attacker, Hand hand, LivingEntity target, float baseDamage, float damage, boolean isCritical, boolean fullyCharged, boolean isExtraAttack) {
		float bonus = 0.0f;
		if (target.isAlive() && target.isOnFire()) {
			bonus = (target.getRemainingFireTicks() * level) / 30.0f;
			target.clearFire();
		}
		return super.applyLivingDamage(tool, level, attacker, hand, target, baseDamage, damage, isCritical, fullyCharged, isExtraAttack) + bonus;
	}
}