package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MaterialisFluidTags extends FluidTagsProvider {

	public static final INamedTag<Fluid> LIQUID_PINK_SLIME = FluidTags.bind(Materialis.modID + ":pink_slime");

	public MaterialisFluidTags(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, Materialis.modID, existingFileHelper);
	}

	@Override
	public void addTags() {
		tag(LIQUID_PINK_SLIME).addOptional(new ResourceLocation("industrialforegoing", "pink_slime"));
	}
}
