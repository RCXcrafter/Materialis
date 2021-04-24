package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class MaterialisItemModels extends ItemModelProvider {

	public MaterialisItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Materialis.modID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		itemWithModel(MaterialisResources.FAIRY_INGOT, "item/generated");
		itemWithModel(MaterialisResources.FAIRY_NUGGET, "item/generated");
		bucketModel(MaterialisResources.FAIRY_BUCKET);

		//create fluids
		bucketModel(MaterialisResources.REFINED_RADIANCE_BUCKET);
		bucketModel(MaterialisResources.SHADOW_STEEL_BUCKET);
	}

	public void itemWithModel(RegistryObject<? extends Item> registryObject, String model) {
		ResourceLocation id = registryObject.getId();
		ResourceLocation textureLocation = new ResourceLocation(id.getNamespace(), "item/" + id.getPath());
		singleTexture(id.getPath(), new ResourceLocation(model), "layer0", textureLocation);
	}

	public void bucketModel(RegistryObject<? extends BucketItem> registryObject) {
		ResourceLocation id = registryObject.getId();
		ResourceLocation textureLocation = new ResourceLocation(id.getNamespace(), "item/" + id.getPath());
		getBuilder(id.getPath()).parent(getExistingFile(new ResourceLocation("item/generated"))).texture("layer0", id.getNamespace() + ":item/" + registryObject.get().getFluid().getRegistryName().getPath() + "_bucket");

		//wip code for forge bucket models, won't finish
		/*ResourceLocation id = registryObject.getId();
		ResourceLocation textureLocation = new ResourceLocation(id.getNamespace(), "item/" + id.getPath());
		//singleTexture(id.getPath(), new ResourceLocation(model), "layer0", textureLocation);
		ModelBuilder builder = getBuilder(id.getPath()).parent(getExistingFile(new ResourceLocation("forge:item/bucket_drip")));
		//.texture("loader", "forge:bucket").texture("fluid", registryObject.get().getFluid().getRegistryName().toString());
		builder.customLoader(new BiFunction<ModelBuilder, ExistingFileHelper, CustomLoaderBuilder>() {

			@Override
			public CustomLoaderBuilder apply(ModelBuilder t, ExistingFileHelper u) {
				return new CustomLoaderBuilder(t.getLocation(), t, u) {
					public JsonObject toJson(JsonObject json)
				    {
				        json.addProperty("loader", loaderId.toString());


				        return json;
				    }
				};
			}

		});*/
	}
}
