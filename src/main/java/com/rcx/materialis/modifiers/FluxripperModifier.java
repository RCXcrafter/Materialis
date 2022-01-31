package com.rcx.materialis.modifiers;

import java.util.List;

import com.rcx.materialis.util.TinkerToolFluxed;

import net.minecraft.util.text.ITextComponent;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipFlag;

public class FluxripperModifier extends CapacitorModifier {

	private static final int ENERGY_COST = 100;

	public FluxripperModifier() {
		super(0xFF8800);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (!tool.isBroken() && TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, false, false)) {
			return damage + 1.5f * level * tool.getModifier(ToolStats.ATTACK_DAMAGE);
		}
		return damage;
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, TooltipFlag flag) {
		super.addInformation(tool, level, tooltip, flag);
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