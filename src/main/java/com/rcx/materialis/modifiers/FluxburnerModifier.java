package com.rcx.materialis.modifiers;

import java.util.List;

import com.rcx.materialis.util.TinkerToolFluxed;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class FluxburnerModifier extends CapacitorModifier {

	private static final int ENERGY_COST = 100;

	@Override
	public void onBreakSpeed(IToolStackView tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (isEffective && !tool.isBroken() && TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, true, false)) {
			event.setNewSpeed(event.getNewSpeed() + 2.5f * level * tool.getMultiplier(ToolStats.MINING_SPEED));
		}
	}

	@Override
	public void afterBlockBreak(IToolStackView tool, int level, ToolHarvestContext context) {
		if (context.isAOE() && context.isEffective() && !tool.isBroken()) {
			TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, false, false); //only eat the energy if the block is actually broken
		}
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		super.addInformation(tool, level, player, tooltip, key, flag);
		float bonus = 0;
		if (TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, true, false))
			bonus = 2.5f * level;
		addStatTooltip(tool, ToolStats.MINING_SPEED, TinkerTags.Items.HARVEST, bonus, tooltip);
	}

	@Override
	public int getCapacity() {
		return 5000;
	}
}