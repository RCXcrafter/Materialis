package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class BrittleModifier extends Modifier {

	public BrittleModifier() {
		super(0xA1FFFF);
	}

	@Override
	public int getPriority() {
		return 160; //before overslime
	}

	@Override
	public int onDamageTool(IModifierToolStack toolStack, int level, int amount) {
		return (int) (amount * Math.pow(2, level));
	}
}