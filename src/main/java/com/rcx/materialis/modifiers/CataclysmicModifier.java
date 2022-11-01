package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public class CataclysmicModifier extends Modifier {

	@Override
	public int getPriority() {
		return 905; //cosmic modifier priority
	}

	@Override
	public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
		volatileData.addSlots(SlotType.UPGRADE, level * 5);
		volatileData.addSlots(SlotType.ABILITY, level);
	}
}