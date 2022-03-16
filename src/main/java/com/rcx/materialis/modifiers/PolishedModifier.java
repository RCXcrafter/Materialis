package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipKey;

public class PolishedModifier extends Modifier {

	@Override
	public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		float total = tool.getDamage() + tool.getCurrentDurability();
		float extra = 1.5f * level * tool.getCurrentDurability() / total;
		return damage + extra * tool.getMultiplier(ToolStats.ATTACK_DAMAGE);
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		float total = tool.getDamage() + tool.getCurrentDurability();
		float extra = 1.5f * level * tool.getCurrentDurability() / total;
		addStatTooltip(tool, ToolStats.ATTACK_DAMAGE, TinkerTags.Items.MELEE, extra, tooltip);
	}
}