package com.rcx.materialis.modifiers;

import javax.annotation.Nullable;

import com.rcx.materialis.util.TinkerToolFluxed;

import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class FluxshieldModifier extends CapacitorModifier {

	private static final int ENERGY_COST = 200;

	@Override
	public int getPriority() {
		return 110; //before reinforced
	}

	@Override
	public int onDamageTool(IToolStackView tool, int level, int amount, @Nullable LivingEntity holder) {
		if (!tool.isBroken()) {
			int dealt = 0;
			for (int i = 0; i < amount; i++) {
				if (RANDOM.nextFloat() >= 0.1f * level) {
					dealt++;
				} else {
					if (!TinkerToolFluxed.removeEnergy(tool, ENERGY_COST, false, false))
						dealt++;
				}
			}
			return dealt;
		}
		return amount;
	}

	@Override
	public int getCapacity() {
		return 5000;
	}
}