package com.rcx.materialis.resources;

import com.rcx.materialis.Materialis;

import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.fluid.FluidMolten;

public class FluidCustom extends FluidMolten {

	public FluidCustom(String fluidName) {
		super(fluidName, 0xFFFFFFFF, new ResourceLocation(Materialis.ID, "blocks/fluids/molten_" + fluidName + "_still"), new ResourceLocation(Materialis.ID, "blocks/fluids/molten_"  + fluidName + "_flow"));
	}
}
