package com.rcx.materialis.modifiers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class ArcaneModifier extends Modifier {

	public ArcaneModifier() {
		super(0xFFC069);
	}

	@Override
	public int afterLivingHit(IModifierToolStack tool, int level, LivingEntity attacker, LivingEntity target, float damageDealt, boolean isCritical, boolean fullyCharged) {
		DamageSource source;
		if (attacker instanceof PlayerEntity) {
			source = DamageSource.playerAttack((PlayerEntity)attacker);
		} else {
			source = DamageSource.mobAttack(attacker);
		}
		target.hurtTime = 0;
		attackEntitySecondary(source.setMagic(), level, target, false);
		return 0;
	}
}