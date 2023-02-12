package com.rcx.materialis.modifiers;

import java.util.List;

import com.rcx.materialis.util.TinkerToolFluxed;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class FluxripperModifier extends CapacitorModifier {

	private static final int ENERGY_COST = 100;

	@Override
	public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (!tool.isBroken() && TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, false, false)) {
			return damage + 1.5f * level * tool.getMultiplier(ToolStats.ATTACK_DAMAGE);
		}
		return damage;
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		super.addInformation(tool, level, player, tooltip, key, flag);
		float bonus = 0;
		if (TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, true, false))
			bonus = 1.5f * level;
		addStatTooltip(tool, ToolStats.ATTACK_DAMAGE, TinkerTags.Items.MELEE, bonus, tooltip);
	}

	@Override
	public int getCapacity() {
		return 5000;
	}
}