package com.rcx.materialis.util;

import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public interface ITintingModifier {

	default int getTint(IToolStackView tool) {
		return -1;
	};

	default int getLuminosity(IToolStackView tool) {
		return 0;
	};
}
