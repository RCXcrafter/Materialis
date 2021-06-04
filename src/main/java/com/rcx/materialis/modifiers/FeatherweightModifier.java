package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class FeatherweightModifier extends Modifier {

	public FeatherweightModifier() {
		super(0xE6B7BF);
	}

	@Override
	public void addToolStats(ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, IModDataReadOnly volatileData, int level, ModifierStatsBuilder builder) {
		ToolStats.ATTACK_DAMAGE.multiply(builder, 1 - (0.1f * level));
		ToolStats.ATTACK_SPEED.multiply(builder, 1 + (0.2f * level));
	}
}