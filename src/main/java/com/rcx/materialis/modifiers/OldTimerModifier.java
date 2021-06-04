package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class OldTimerModifier extends Modifier {

	public OldTimerModifier() {
		super(0x6CD7AA);
	}

	@Override
	public float getRepairFactor(IModifierToolStack toolStack, int level, float factor) {
		float half = (toolStack.getDamage() + toolStack.getCurrentDurability()) / 2;
		if (toolStack.getCurrentDurability() < half)
			return factor;
		float strength = level / 2.0f;
		return factor + strength - strength * toolStack.getCurrentDurability() / half;
	}
}