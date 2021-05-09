package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;

public class OverclockedModifier extends Modifier {

	public OverclockedModifier() {
		super(0xA1A097);
	}

	@Override
	public void addToolStats(ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, IModDataReadOnly volatileData, int level, ModifierStatsBuilder builder) {
		builder.multiplyAttackDamage(1 + (0.2f * level));
		builder.multiplyMiningSpeed(1 + (0.2f * level));
		builder.multiplyDurability(1 - (0.4f * level));
	}
}