package com.rcx.materialis.modifiers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class FeebleModifier extends Modifier {

	public FeebleModifier() {
		super(0xD9D9D9);
	}

	@Override
	public float beforeLivingHit(IModifierToolStack tool, int level, LivingEntity attacker, Hand hand, LivingEntity target, float damage, float baseKnockback, float knockback, boolean isCritical, boolean fullyCharged, boolean isExtraAttack) {
		return knockback / (1.7f * level);
	}
}