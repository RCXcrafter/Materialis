package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.impl.SingleLevelModifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class CrystallineModifier extends SingleLevelModifier {

	@Override
	public void addToolStats(ToolRebuildContext context, int level, ModifierStatsBuilder builder) {
		ToolStats.ATTACK_DAMAGE.multiply(builder, 1 + 0.5 * level);
		ToolStats.MINING_SPEED.multiply(builder, 1 + 0.5 * level);
	}
}