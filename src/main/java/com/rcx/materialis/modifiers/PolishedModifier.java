package com.rcx.materialis.modifiers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class PolishedModifier extends Modifier {

	public PolishedModifier() {
		super(0xFFD359);
	}

	@Override
	public float applyLivingDamage(IModifierToolStack tool, int level, LivingEntity attacker, Hand hand, LivingEntity target, float baseDamage, float damage, boolean isCritical, boolean fullyCharged, boolean isExtraAttack) {
		float total = tool.getDamage() + tool.getCurrentDurability();
		float extra = level * tool.getCurrentDurability() / total;

		return super.applyLivingDamage(tool, level, attacker, hand, target, baseDamage, damage, isCritical, fullyCharged, isExtraAttack) + extra;
	}
}