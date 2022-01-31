package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.util.text.ITextComponent;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipFlag;

public class PolishedModifier extends Modifier {

	public PolishedModifier() {
		super(0xFFD359);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		float total = tool.getDamage() + tool.getCurrentDurability();
		float extra = 1.5f * level * tool.getCurrentDurability() / total;
		return damage + extra * tool.getModifier(ToolStats.ATTACK_DAMAGE);
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, TooltipFlag flag) {
		float total = tool.getDamage() + tool.getCurrentDurability();
		float extra = 1.5f * level * tool.getCurrentDurability() / total;
		addStatTooltip(tool, ToolStats.ATTACK_DAMAGE, TinkerTags.Items.MELEE, extra, tooltip);
	}
}