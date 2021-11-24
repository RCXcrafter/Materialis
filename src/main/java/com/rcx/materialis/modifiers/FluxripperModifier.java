package com.rcx.materialis.modifiers;

import com.rcx.materialis.util.TinkerToolFluxed;

import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class FluxripperModifier extends CapacitorModifier {

	private static final int ENERGY_COST = 100;

	public FluxripperModifier() {
		super(0xFF8800);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (!tool.isBroken() && TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, false, false)) {
			return damage + 1.5f * level;
		}
		return damage;
	}

	public int getCapacity() {
		return 5000;
	}
}