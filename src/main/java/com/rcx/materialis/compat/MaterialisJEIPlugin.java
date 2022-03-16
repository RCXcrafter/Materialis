package com.rcx.materialis.compat;

import java.util.Collections;
import java.util.stream.Collectors;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.MaterialisResources.FluidWithBlockNBucket;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.SerializationTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.common.registration.CastItemObject;

@JeiPlugin
public class MaterialisJEIPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(Materialis.modID, "jei_plugin");
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		IIngredientManager manager = jeiRuntime.getIngredientManager();

		// hide compat that is not present
		for (FluidWithBlockNBucket fluid : MaterialisResources.fluidList) {
			Tag<Item> ingot = SerializationTags.getInstance().getOrEmpty(Registry.ITEM_REGISTRY).getTag(new ResourceLocation("forge", "ingots/" + fluid.name.replace("molten_", "")));
			if (ingot == null || ingot.getValues().isEmpty()) {
				removeFluid(manager, fluid.FLUID.get(), fluid.FLUID_BUCKET.get());
			}
		}

		optionalCast(manager, MaterialisResources.INLAY_CAST);
	}

	/**
	 * Removes a fluid from JEI
	 * @param manager  Manager
	 * @param fluid    Fluid to remove
	 * @param bucket   Fluid bucket to remove
	 */
	public static void removeFluid(IIngredientManager manager, Fluid fluid, Item bucket) {
		manager.removeIngredientsAtRuntime(VanillaTypes.FLUID, Collections.singleton(new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME)));
		manager.removeIngredientsAtRuntime(VanillaTypes.ITEM, Collections.singleton(new ItemStack(bucket)));
	}

	/**
	 * Hides casts if the related tag is empty
	 * @param manager  Ingredient manager
	 * @param cast     Cast instance
	 */
	public static void optionalCast(IIngredientManager manager, CastItemObject cast) {
		Tag<Item> tag = SerializationTags.getInstance().getOrEmpty(Registry.ITEM_REGISTRY).getTag(new ResourceLocation("forge", cast.getName().getPath() + "s"));
		if (tag == null || tag.getValues().isEmpty()) {
			manager.removeIngredientsAtRuntime(VanillaTypes.ITEM, cast.values().stream().map(ItemStack::new).collect(Collectors.toList()));
		}
	}
}
