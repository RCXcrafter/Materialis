package com.rcx.materialis.modifiers;

import javax.annotation.Nullable;

import com.rcx.materialis.util.TinkerToolFluxed;

import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap.Builder;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class PowerHungryModifier extends CapacitorModifier implements ConditionalStatModifierHook {

	private static final int ENERGY_COST = 100;

	@Override
	public int getPriority() {
		return 160; //before overslime >:)
	}

	@Override
	protected void registerHooks(Builder hookBuilder) {
		hookBuilder.addHook(this, TinkerHooks.CONDITIONAL_STAT);
	}

	@Override
	public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
		if ((stat == ToolStats.VELOCITY || stat == ToolStats.MINING_SPEED || stat == ToolStats.ATTACK_DAMAGE) && !TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * modifier.getLevel(), true, false)) {
			return baseValue / 1.5f;
		}
		return baseValue;
	}

	@Override
	public int onDamageTool(IToolStackView tool, int level, int amount, @Nullable LivingEntity holder) {
		TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, false, true);
		return amount;
	}

	@Override
	public int getCapacity() {
		return 10000;
	}
}