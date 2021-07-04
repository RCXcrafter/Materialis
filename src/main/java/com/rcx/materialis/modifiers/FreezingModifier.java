package com.rcx.materialis.modifiers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.helper.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class FreezingModifier extends Modifier {

	public FreezingModifier() {
		super(0x95BDE3);
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		LivingEntity target = context.getLivingTarget();
		if (target != null && context.isFullyCharged() && target.isAlive()) {
			target.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 150 * level, Math.min(level, 2)));
		}
		return 0;
	}
}