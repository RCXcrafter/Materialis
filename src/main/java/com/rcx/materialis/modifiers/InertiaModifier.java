package com.rcx.materialis.modifiers;

import net.minecraft.item.Item;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class InertiaModifier extends Modifier {

	public InertiaModifier() {
		super(0xA1A097);
	}

	@Override
	public void addToolStats(Item item, ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, IModDataReadOnly volatileData, int level, ModifierStatsBuilder builder) {
		ToolStats.ATTACK_DAMAGE.multiply(builder, 1 + (0.2f * level));
		ToolStats.ATTACK_SPEED.multiply(builder, 1 - (0.1f * level));
	}
}