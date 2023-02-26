package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
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

public class NocturnalModifier extends Modifier implements ConditionalStatModifierHook {

	@Override
	protected void registerHooks(Builder hookBuilder) {
		hookBuilder.addHook(this, TinkerHooks.CONDITIONAL_STAT);
	}

	@Override
	public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
		int time = (int) (living.level.getDayTime() % 24000l);
		if (time > 12000) {
			float bonus = (float) (-Math.sin(time * Math.PI / 12000.0f) * modifier.getLevel()) * multiplier;
			if (stat == ToolStats.MINING_SPEED) {
				return baseValue + bonus * 2.0f;
			}
			if (stat == ToolStats.DRAW_SPEED) {
				return baseValue + bonus * 0.1f;
			}
		}
		return baseValue;
	}

	@Override
	public void onBreakSpeed(IToolStackView tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		int time = (int) (event.getEntityLiving().level.getDayTime() % 24000l);
		if (time > 12000) {
			float bonus = (float) (-Math.sin(time * Math.PI / 12000.0f) * 2 * level);
			event.setNewSpeed(event.getNewSpeed() + bonus * tool.getMultiplier(ToolStats.MINING_SPEED));
		}
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		int time = (int) (player.level.getDayTime() % 24000l);
		float bonus = 0;
		if (time > 12000) {
			bonus = (float) (-Math.sin(time * Math.PI / 12000.0f) * level);
		}
		addStatTooltip(tool, ToolStats.MINING_SPEED, TinkerTags.Items.HARVEST, bonus * 2.0f, tooltip);
		addStatTooltip(tool, ToolStats.DRAW_SPEED, TinkerTags.Items.RANGED, bonus * 0.1f, tooltip);
	}
}