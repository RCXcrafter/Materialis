package com.rcx.materialis.modifiers;

import net.minecraft.item.Item;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class ShortSightedModifier extends Modifier {

	public ShortSightedModifier() {
		super(0xB78FD2);
	}

	@Override
	public void addToolStats(Item item, ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, IModDataReadOnly volatileData, int level, ModifierStatsBuilder builder) {
		ToolStats.REACH.multiply(builder, Math.max(1.0 - (0.2 * level), 0.0));
	}
}