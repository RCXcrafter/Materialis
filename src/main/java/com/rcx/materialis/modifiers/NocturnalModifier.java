package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipKey;

public class NocturnalModifier extends Modifier {

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
		addStatTooltip(tool, ToolStats.MINING_SPEED, TinkerTags.Items.HARVEST, 2 * level, tooltip);
	}
}