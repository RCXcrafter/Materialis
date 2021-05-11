package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.MaterialisResources.FluidWithBlockNBucket;
import com.rcx.materialis.MaterialisResources.IngotWithBlockNNugget;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class MaterialisBlockStates extends BlockStateProvider {

	public MaterialisBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, Materialis.modID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		for (IngotWithBlockNNugget material : MaterialisResources.materialList) {
			blockWithItem(material.BLOCK);
		}

		//this is just to give them proper particles
		for (FluidWithBlockNBucket fluid : MaterialisResources.fluidList) {
			fluid(fluid.FLUID_BLOCK);
		}
	}

	public void blockWithItem(RegistryObject<? extends Block> registryObject) {
		//block model
		simpleBlock(registryObject.get());
		//itemblock model
		ResourceLocation id = registryObject.getId();
		ResourceLocation textureLocation = new ResourceLocation(id.getNamespace(), "block/" + id.getPath());
		itemModels().cubeAll(id.getPath(), textureLocation);
	}

	public void fluid(RegistryObject<? extends Block> fluid) {
		ResourceLocation name = fluid.get().getRegistryName();
		simpleBlock(fluid.get(), models().cubeAll(name.getPath(), new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/fluid/" + name.getPath().replace("block", "still"))));
	}
}
