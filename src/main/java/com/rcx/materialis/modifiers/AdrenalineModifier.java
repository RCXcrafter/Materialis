package com.rcx.materialis.modifiers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class AdrenalineModifier extends Modifier {

	public AdrenalineModifier() {
		super(0xCDE8FD);
	}

	@Override
	public float applyLivingDamage(IModifierToolStack tool, int level, LivingEntity attacker, Hand hand, LivingEntity target, float baseDamage, float damage, boolean isCritical, boolean fullyCharged, boolean isExtraAttack) {
		float multiplier = level * (attacker.getMaxHealth() - attacker.getHealth()) / attacker.getMaxHealth() / 2.0f + 0.5f;
		return super.applyLivingDamage(tool, level, attacker, hand, target, baseDamage, damage, isCritical, fullyCharged, isExtraAttack) * multiplier;
	}
}