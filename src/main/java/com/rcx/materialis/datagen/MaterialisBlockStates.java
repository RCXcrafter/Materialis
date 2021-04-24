package com.rcx.materialis.datagen;

import com.google.common.collect.ObjectArrays;
import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class MaterialisBlockStates extends BlockStateProvider {

	public MaterialisBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, Materialis.modID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		blockWithItem(MaterialisResources.FAIRY_BLOCK);
	}

	public void blockWithItem(RegistryObject<? extends Block> registryObject) {
		//block model
		simpleBlock(registryObject.get(), model -> ObjectArrays.concat(
				ConfiguredModel.allYRotations(model, 0, false),
				ConfiguredModel.allYRotations(model, 180, false),
				ConfiguredModel.class));
		//itemblock model
		ResourceLocation id = registryObject.getId();
		ResourceLocation textureLocation = new ResourceLocation(id.getNamespace(), "block/" + id.getPath());
		itemModels().cubeAll(id.getPath(), textureLocation);
	}
}
