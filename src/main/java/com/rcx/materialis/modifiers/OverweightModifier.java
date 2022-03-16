package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

public class OverweightModifier extends Modifier {

	@Override
	public int getPriority() {
		return 80; //after overcast
	}

	@Override
	public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
		OverslimeModifier overslime = TinkerModifiers.overslime.get();
		overslime.setFriend(volatileData);

		float multiplier = 0.1f * level;
		float speedEaten = context.getBaseStats().get(ToolStats.ATTACK_DAMAGE) * 2.0f * multiplier + context.getBaseStats().get(ToolStats.MINING_SPEED) * multiplier;

		overslime.addCapacity(volatileData, (int) (speedEaten * 300.0f));
	}

	@Override
	public void addToolStats(ToolRebuildContext context, int level, ModifierStatsBuilder builder) {
		float multiplier = 1.0f - 0.1f * level;
		ToolStats.ATTACK_DAMAGE.multiply(builder, multiplier);
		ToolStats.MINING_SPEED.multiply(builder, multiplier);
	}
}