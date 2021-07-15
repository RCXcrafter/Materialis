package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class PolishedModifier extends Modifier {

	public PolishedModifier() {
		super(0xFFD359);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		float total = tool.getDamage() + tool.getCurrentDurability();
		float extra = level * tool.getCurrentDurability() / total;
		return damage + extra;
	}
}