package com.rcx.materialis.util;

import java.util.Locale;
import java.util.function.Supplier;

import com.rcx.materialis.MaterialisResources;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.world.level.material.Fluid;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.IByproduct;
import slimeknights.tconstruct.library.recipe.FluidValues;

@RequiredArgsConstructor
public enum MaterialisByproduct implements IByproduct {
	// base mod
	COPPER    (true, TinkerFluids.moltenCopper),
	IRON      (true, TinkerFluids.moltenIron),
	GOLD      (true, TinkerFluids.moltenGold),
	SMALL_GOLD("gold", true, TinkerFluids.moltenGold, FluidValues.NUGGET),
	COBALT    (true, TinkerFluids.moltenCobalt),
	// compat
	TIN     (false, TinkerFluids.moltenTin),
	ZINC    (false, TinkerFluids.moltenZinc),
	SILVER  (false, TinkerFluids.moltenSilver),
	NICKEL  (false, TinkerFluids.moltenNickel),
	LEAD    (false, TinkerFluids.moltenLead),
	PLATINUM(false, TinkerFluids.moltenPlatinum),
	SMALL_PLATINUM("platinum", false, TinkerFluids.moltenPlatinum, FluidValues.NUGGET),
	// undergarden
	CLOGGRUM    (true, MaterialisResources.CLOGGRUM_FLUID.FLUID),
	FROSTSTEEL  (true, MaterialisResources.FROSTSTEEL_FLUID.FLUID),
	UTHERIUM    ("utherium", true, MaterialisResources.UTHERIUM_FLUID.FLUID, FluidValues.NUGGET),
	REGALIUM    (true, MaterialisResources.REGALIUM_FLUID.FLUID);

	@Getter
	private final String name;
	@Getter
	private final boolean alwaysPresent;
	private final Supplier<? extends Fluid> fluidSupplier;
	@Getter
	private final int amount;

	MaterialisByproduct(boolean alwaysPresent, Supplier<? extends Fluid> fluidSupplier) {
		this.name = name().toLowerCase(Locale.ROOT);
		this.alwaysPresent = alwaysPresent;
		this.fluidSupplier = fluidSupplier;
		this.amount = FluidValues.NUGGET * 3;
	}

	/** Gets the fluid of this byproduct */
	public Fluid getFluid() {
		return fluidSupplier.get();
	}
}
