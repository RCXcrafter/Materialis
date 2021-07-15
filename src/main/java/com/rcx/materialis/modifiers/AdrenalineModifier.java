package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class AdrenalineModifier extends Modifier {

	public AdrenalineModifier() {
		super(0xCDE8FD);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		float multiplier = level * (context.getAttacker().getMaxHealth() - context.getAttacker().getHealth()) / context.getAttacker().getMaxHealth() / 2.0f + 0.5f;
		return damage * multiplier;
	}
}