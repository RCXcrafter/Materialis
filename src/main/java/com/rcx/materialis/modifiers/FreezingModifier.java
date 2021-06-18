package com.rcx.materialis.modifiers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class FreezingModifier extends Modifier {

	public FreezingModifier() {
		super(0x95BDE3);
	}

	@Override
	public int afterLivingHit(IModifierToolStack tool, int level, LivingEntity attacker, LivingEntity target, float damageDealt, boolean isCritical, float cooldown) {
		if (target.isAlive()) {
			// set entity so the potion is attributed as a player kill
			target.setLastHurtByMob(attacker);
			target.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 150 * level, Math.min(level, 2)));
		}
		return 0;
	}
}