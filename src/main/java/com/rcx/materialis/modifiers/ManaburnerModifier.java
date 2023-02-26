package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.fml.ModList;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import vazkii.botania.api.mana.ManaItemHandler;

public class ManaburnerModifier extends Modifier {

	boolean enabled = ModList.get().isLoaded("botania");
	private static final int MANA_COST = 80;

	@Override
	public void onBreakSpeed(IToolStackView tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (enabled && isEffective && !tool.isBroken() && event.getEntityLiving() instanceof Player) {
			ItemStack toolStack = event.getEntityLiving().getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();

			if (ManaItemHandler.instance().requestManaExactForTool(toolStack, (Player) event.getEntityLiving(), MANA_COST * level, false))
				event.setNewSpeed(event.getNewSpeed() + 2.5f * level * tool.getMultiplier(ToolStats.MINING_SPEED));
		}
	}

	public void afterBlockBreak(IToolStackView tool, int level, ToolHarvestContext context) {
		if (enabled && !context.isAOE() && context.isEffective() && !tool.isBroken() && context.getPlayer() != null) {
			ItemStack toolStack = context.getPlayer().getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();

			ManaItemHandler.instance().requestManaExactForTool(toolStack, context.getPlayer(), MANA_COST * level, true); //only eat the mana if the block is actually broken
		}
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		addStatTooltip(tool, ToolStats.MINING_SPEED, TinkerTags.Items.HARVEST, 2.5f * level, tooltip);
	}
}