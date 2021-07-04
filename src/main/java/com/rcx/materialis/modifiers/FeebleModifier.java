package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.helper.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class FeebleModifier extends Modifier {

	public FeebleModifier() {
		super(0xD9D9D9);
	}

	@Override
	public float beforeEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damage, float baseKnockback, float knockback) {
		return knockback / (1.5f * level);
	}
}