package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;

public class FeatherweightModifier extends Modifier {

	public FeatherweightModifier() {
		super(0xE6B7BF);
	}

	@Override
	public void addToolStats(ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, IModDataReadOnly volatileData, int level, ModifierStatsBuilder builder) {
		builder.multiplyAttackDamage(1 - (0.1f * level));
		builder.multiplyAttackSpeed(1 + (0.2f * level));
	}
}