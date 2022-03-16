package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipKey;

public class ArcaneModifier extends Modifier {

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		DamageSource source;
		Player player = context.getPlayerAttacker();
		if (player != null) {
			source = DamageSource.playerAttack(player);
		} else {
			source = DamageSource.mobAttack(context.getAttacker());
		}
		ToolAttackUtil.attackEntitySecondary(source.setMagic().bypassArmor(), level * tool.getMultiplier(ToolStats.ATTACK_DAMAGE), context.getTarget(), context.getLivingTarget(), true);
		return 0;
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		addStatTooltip(tool, ToolStats.ATTACK_DAMAGE, TinkerTags.Items.MELEE, level, tooltip);
	}
}