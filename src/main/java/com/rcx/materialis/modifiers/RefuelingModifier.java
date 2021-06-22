package com.rcx.materialis.modifiers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class RefuelingModifier extends Modifier {

	public RefuelingModifier() {
		super(0xF9EA98);
	}

	@Override
	public int getPriority() {
		return 90; //after modifiers that set mobs on fire
	}

	@Override
	public float applyLivingDamage(IModifierToolStack tool, int level, LivingEntity attacker, Hand hand, LivingEntity target, float baseDamage, float damage, boolean isCritical, boolean fullyCharged, boolean isExtraAttack) {
		if (target.isAlive() && target.isOnFire()) {
			target.setRemainingFireTicks(target.getRemainingFireTicks() + 10 * level);
		}
		return 0;
	}
}