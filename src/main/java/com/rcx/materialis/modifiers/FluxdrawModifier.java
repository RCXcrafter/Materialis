package com.rcx.materialis.modifiers;

import java.util.List;

import com.rcx.materialis.util.TinkerToolFluxed;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap.Builder;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class FluxdrawModifier extends CapacitorModifier implements ConditionalStatModifierHook, ProjectileLaunchModifierHook {

	private static final int ENERGY_COST = 100;

	@Override
	protected void registerHooks(Builder hookBuilder) {
		hookBuilder.addHook(this, TinkerHooks.CONDITIONAL_STAT, TinkerHooks.PROJECTILE_LAUNCH);
	}

	@Override
	public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
		if (stat == ToolStats.DRAW_SPEED && TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * modifier.getLevel(), true, false)) {
			return baseValue + 0.15f * multiplier * modifier.getLevel();
		}
		return baseValue;
	}

	@Override
	public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, AbstractArrow arrow, NamespacedNBT persistentData, boolean primary) {
		if (primary) {
			TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * modifier.getLevel(), false, false); //only eat the energy if the projectile is actually fired
		}
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		super.addInformation(tool, level, player, tooltip, key, flag);
		float bonus = 0;
		if (TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, true, false))
			bonus = 0.15f * level;
		addStatTooltip(tool, ToolStats.DRAW_SPEED, TinkerTags.Items.RANGED, bonus, tooltip);
	}

	@Override
	public int getCapacity() {
		return 5000;
	}
}