package com.rcx.materialis.modifiers;

import com.rcx.materialis.util.TinkerToolFluxed;

import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class FluxburnerModifier extends CapacitorModifier {

	private static final int ENERGY_COST = 100;

	public FluxburnerModifier() {
		super(0xFF8800);
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (isEffective && !tool.isBroken() && TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, true, false)) {
			event.setNewSpeed(event.getNewSpeed() + 2.5f * level * tool.getModifier(ToolStats.MINING_SPEED));
		}
	}

	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
		if (context.isAOE() && context.isEffective() && !tool.isBroken()) {
			TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, false, false); //only eat the energy if the block is actually broken
		}
	}

	public int getCapacity() {
		return 5000;
	}
}