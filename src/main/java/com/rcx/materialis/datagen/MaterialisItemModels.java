package com.rcx.materialis.datagen;

import java.util.function.BiFunction;

import com.google.gson.JsonObject;
import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.tconstruct.common.registration.CastItemObject;

public class MaterialisItemModels extends ItemModelProvider {

	public MaterialisItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Materialis.modID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		itemWithModel(MaterialisResources.FAIRY_INGOT, "item/generated");
		itemWithModel(MaterialisResources.FAIRY_NUGGET, "item/generated");
		bucketModel(MaterialisResources.FAIRY_BUCKET);

		//custom casts
		castModels(MaterialisResources.INLAY_CAST);

		//create fluids
		bucketModel(MaterialisResources.REFINED_RADIANCE_BUCKET);
		bucketModel(MaterialisResources.SHADOW_STEEL_BUCKET);

		//eidolon fluids
		bucketModel(MaterialisResources.ARCANE_GOLD_BUCKET);

		//aquaculture fluids
		bucketModel(MaterialisResources.NEPTUNIUM_BUCKET);

		//mystical world fluids
		bucketModel(MaterialisResources.QUICKSILVER_BUCKET);

		//astral sorcery fluids
		bucketModel(MaterialisResources.STARMETAL_BUCKET);

		//industrial foregoing stuff
		bucketModel(MaterialisResources.PINK_SLIME_BUCKET);
		itemWithModel(MaterialisResources.PINK_SLIME_CRYSTAL, "item/generated");
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
