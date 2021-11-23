package com.rcx.materialis.modifiers;

import com.rcx.materialis.util.TinkerToolFluxed;

import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class FluxburnerModifier extends CapacitorModifier {

	private static final int ENERGY_COST = 100;

	public FluxburnerModifier() {
		super(0xB51212);
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (isEffective && !tool.isBroken() && TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, true)) {
			event.setNewSpeed(event.getNewSpeed() + 2.5f * level);
		}
	}

	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
		if (context.isAOE() && context.isEffective() && !tool.isBroken()) {
			TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, false); //only eat the energy if the block is actually broken
		}
	}

	public int getCapacity() {
		return 5000;
	}
}