package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class FeatherweightModifier extends Modifier {

	@Override
	public int getPriority() {
		return 80; //after most damage adding modifiers
	}

	@Override
	public void addToolStats(ToolRebuildContext context, int level, ModifierStatsBuilder builder) {
		//ToolStats.ATTACK_DAMAGE.multiply(builder, 1 - (0.1f * level));
		ToolStats.ATTACK_SPEED.multiply(builder, 1 + (0.15f * level));
	}

	@Override
	public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		return damage * (1 - (0.1f * level));
	}
}