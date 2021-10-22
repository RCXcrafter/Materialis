package com.rcx.materialis.modifiers;

import net.minecraft.item.Item;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class FeatherweightModifier extends Modifier {

	public FeatherweightModifier() {
		super(0xE6B7BF);
	}

	@Override
	public int getPriority() {
		return 80; //after most damage adding modifiers
	}

	@Override
	public void addToolStats(Item item, ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, IModDataReadOnly volatileData, int level, ModifierStatsBuilder builder) {
		//ToolStats.ATTACK_DAMAGE.multiply(builder, 1 - (0.1f * level));
		ToolStats.ATTACK_SPEED.multiply(builder, 1 + (0.15f * level));
	}
	
	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		return damage * (1 - (0.1f * level));
	}
}