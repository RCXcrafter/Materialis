package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipFlag;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.core.handler.PlayerDataHandler.PlayerData;

public class PsichoKillerModifier extends Modifier {

	boolean enabled = ModList.get().isLoaded("psi");

	public PsichoKillerModifier() {
		super(0x3D3838);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (enabled && !tool.isBroken() && context.getPlayerAttacker() != null) {
			PlayerData data = PlayerDataHandler.get(context.getPlayerAttacker());
			return damage + level * tool.getModifier(ToolStats.ATTACK_DAMAGE) * data.getAvailablePsi() * 1.0f / ((float) data.getTotalPsi());
		}
		return damage;
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, TooltipFlag flag) {
		addStatTooltip(tool, ToolStats.ATTACK_DAMAGE, TinkerTags.Items.MELEE, level, tooltip);
	}
}