package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipFlag;

public class ArcaneModifier extends Modifier {

	public ArcaneModifier() {
		super(0xFFC069);
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		DamageSource source;
		PlayerEntity player = context.getPlayerAttacker();
		if (player != null) {
			source = DamageSource.playerAttack(player);
		} else {
			source = DamageSource.mobAttack(context.getAttacker());
		}
		ToolAttackUtil.attackEntitySecondary(source.setMagic().bypassArmor(), level * tool.getModifier(ToolStats.ATTACK_DAMAGE), context.getTarget(), context.getLivingTarget(), true);
		return 0;
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, TooltipFlag flag) {
		addStatTooltip(tool, ToolStats.ATTACK_DAMAGE, TinkerTags.Items.MELEE, level, tooltip);
	}
}