package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipKey;

public class PsichoKillerModifier extends Modifier {

	boolean enabled = ModList.get().isLoaded("psi");

	@Override
	public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		/*if (enabled && !tool.isBroken() && context.getPlayerAttacker() != null) {
			PlayerData data = PlayerDataHandler.get(context.getPlayerAttacker());
			return damage + level * tool.getModifier(ToolStats.ATTACK_DAMAGE) * data.getAvailablePsi() * 1.0f / ((float) data.getTotalPsi());
		}*/
		return damage;
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		addStatTooltip(tool, ToolStats.ATTACK_DAMAGE, TinkerTags.Items.MELEE, level, tooltip);
	}
}