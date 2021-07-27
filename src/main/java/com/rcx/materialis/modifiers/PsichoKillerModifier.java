package com.rcx.materialis.modifiers;

import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
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
			return damage + level * data.getAvailablePsi() * 1.0f / ((float) data.getTotalPsi());
		}
		return damage;
	}
}