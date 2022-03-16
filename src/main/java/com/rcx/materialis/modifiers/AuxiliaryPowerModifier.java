package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class AuxiliaryPowerModifier extends Modifier {

	@Override
	public void addToolStats(ToolRebuildContext context, int level, ModifierStatsBuilder builder) {
		ToolStats.ATTACK_SPEED.add(builder, context.getBaseStats().get(ToolStats.MINING_SPEED) * level / 80.0f);
	}
}