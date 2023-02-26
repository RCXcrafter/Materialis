package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap.Builder;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class PolishedModifier extends Modifier implements ConditionalStatModifierHook {

	@Override
	protected void registerHooks(Builder hookBuilder) {
		hookBuilder.addHook(this, TinkerHooks.CONDITIONAL_STAT);
	}

	@Override
	public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
		if (stat == ToolStats.ATTACK_DAMAGE) {
			return baseValue + 1.5f * multiplier * modifier.getLevel() * tool.getCurrentDurability() / (tool.getDamage() + tool.getCurrentDurability());
		}
		if (stat == ToolStats.VELOCITY) {
			return baseValue + 0.1f * multiplier * modifier.getLevel() * tool.getCurrentDurability() / (tool.getDamage() + tool.getCurrentDurability());
		}
		return baseValue;
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		float extra = level * tool.getCurrentDurability() / (tool.getDamage() + tool.getCurrentDurability());
		addStatTooltip(tool, ToolStats.ATTACK_DAMAGE, TinkerTags.Items.MELEE, 1.5f * extra, tooltip);
		addStatTooltip(tool, ToolStats.VELOCITY, TinkerTags.Items.RANGED, 0.1f * extra, tooltip);
	}
}