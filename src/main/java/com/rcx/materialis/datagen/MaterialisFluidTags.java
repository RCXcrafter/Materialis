package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MaterialisFluidTags extends FluidTagsProvider {

	public MaterialisFluidTags(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, Materialis.modID, existingFileHelper);
	}

	@Override
	public void addTags() {
		//okay nevermind then
	}
}
