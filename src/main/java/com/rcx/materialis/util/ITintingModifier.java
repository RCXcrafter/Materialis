package com.rcx.materialis.util;

import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public interface ITintingModifier {

	default int getTint(IModifierToolStack tool) {
		return -1;
	};

	default boolean doesGlow(IModifierToolStack tool) {
		return false;
	};
}
