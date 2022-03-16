package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class AdrenalineModifier extends Modifier {

	@Override
	public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		float multiplier = level * (context.getAttacker().getMaxHealth() - context.getAttacker().getHealth()) / context.getAttacker().getMaxHealth() / 2.0f + 0.5f;
		return damage * multiplier;
	}
}