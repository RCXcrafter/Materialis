package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipKey;

public class PsichoDiggerModifier extends Modifier {

	boolean enabled = ModList.get().isLoaded("psi");

	@Override
	public void onBreakSpeed(IToolStackView tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		/*if (enabled && !tool.isBroken() && event.getEntityLiving() instanceof PlayerEntity) {
			PlayerData data = PlayerDataHandler.get((PlayerEntity) event.getEntityLiving());
			event.setNewSpeed(event.getNewSpeed() + level * tool.getModifier(ToolStats.MINING_SPEED) * data.getAvailablePsi() / ((float) data.getTotalPsi()));
		}*/
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		addStatTooltip(tool, ToolStats.MINING_SPEED, TinkerTags.Items.HARVEST, level, tooltip);
	}
}
