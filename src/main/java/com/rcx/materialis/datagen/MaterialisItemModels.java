package com.rcx.materialis.datagen;

import com.google.gson.JsonObject;
import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.MaterialisResources.FluidWithBlockNBucket;
import com.rcx.materialis.MaterialisResources.IngotWithBlockNNugget;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.registration.CastItemObject;

public class MaterialisItemModels extends ItemModelProvider {

	public MaterialisItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Materialis.modID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		for (FluidWithBlockNBucket fluid : MaterialisResources.fluidList) {
			bucketModel(fluid.FLUID_BUCKET);
		}

		for (IngotWithBlockNNugget material : MaterialisResources.materialList) {
			itemWithModel(material.INGOT, "item/generated");
			itemWithModel(material.NUGGET, "item/generated");
		}
		
		//arrows
		itemWithModel(MaterialisResources.MANASHOT, "item/generated");
		itemWithModel(MaterialisResources.HEAVENSHOT, "item/generated");

		//custom casts
		castModels(MaterialisResources.INLAY_CAST);
		castModels(MaterialisResources.WRENCH_HEAD_CAST);
	}

	public void itemWithModel(RegistryObject<? extends Item> registryObject, String model) {
		ResourceLocation id = registryObject.getId();
		ResourceLocation textureLocation = new ResourceLocation(id.getNamespace(), "item/" + id.getPath());
		singleTexture(id.getPath(), new ResourceLocation(model), "layer0", textureLocation);
	}

	public void castModels(CastItemObject cast) {
		ResourceLocation idGold = cast.get().getRegistryName();
		ResourceLocation textureLocationGold = new ResourceLocation(idGold.getNamespace(), "item/" + idGold.getPath());
		singleTexture(idGold.getPath(), new ResourceLocation("item/generated"), "layer0", textureLocationGold);
		ResourceLocation idSand = cast.getSand().getRegistryName();
		ResourceLocation textureLocationSand = new ResourceLocation(idSand.getNamespace(), "item/" + idSand.getPath());
		singleTexture(idSand.getPath(), new ResourceLocation("item/generated"), "layer0", textureLocationSand);
		ResourceLocation idSandRed = cast.getRedSand().getRegistryName();
		ResourceLocation textureLocationSandRed = new ResourceLocation(idSandRed.getNamespace(), "item/" + idSandRed.getPath());
		singleTexture(idSandRed.getPath(), new ResourceLocation("item/generated"), "layer0", textureLocationSandRed);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void bucketModel(RegistryObject<? extends BucketItem> registryObject) {
		ModelBuilder builder = getBuilder(registryObject.getId().getPath()).parent(getExistingFile(new ResourceLocation(Materialis.modID, "item/bucket_fluid")));

		//I'm not sure how this works but it works
		builder.customLoader((t, u) ->  new CustomLoaderBuilder(((ModelBuilder) t).getLocation(), (ModelBuilder) t, (ExistingFileHelper) u) {
			public JsonObject toJson(JsonObject json) {
				json.addProperty("loader", "forge:bucket");
				json.addProperty("fluid", registryObject.get().getFluid().getRegistryName().toString());
				return json;
			}
		});
	}
}
