package com.rcx.materialis.datagen;

import java.util.function.Supplier;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class MaterialisLang extends LanguageProvider {

	public MaterialisLang(DataGenerator gen) {
		super(gen, Materialis.modID, "en_us");
	}

	@Override
	protected void addTranslations() {
		add("material.materialis.fairy", "Fairy");
		addBlock(MaterialisResources.FAIRY_BLOCK, "Fairy Block");
		addBlock(MaterialisResources.MOLTEN_FAIRY, "Molten Fairy");
		addItem(MaterialisResources.FAIRY_INGOT, "Fairy Ingot");
		addItem(MaterialisResources.FAIRY_NUGGET, "Fairy Nugget");
		addFluid(MaterialisResources.FAIRY_FLUID, "Molten Fairy");
		addItem(MaterialisResources.FAIRY_BUCKET, "Molten Fairy Bucket");

		//create stuff
		add("material.materialis.brass", "Brass");
		add("material.materialis.rose_quartz", "Rose Quartz");
		add("material.materialis.refined_radiance", "Refined Radiance");
		add("material.materialis.refined_radiance.format", "Radiant %s");
		addBlock(MaterialisResources.MOLTEN_REFINED_RADIANCE, "Liquified Radiance");
		addFluid(MaterialisResources.REFINED_RADIANCE_FLUID, "Liquified Radiance");
		addItem(MaterialisResources.REFINED_RADIANCE_BUCKET, "Liquified Radiance Bucket");
		add("material.materialis.shadow_steel", "Shadow Steel");
		addBlock(MaterialisResources.MOLTEN_SHADOW_STEEL, "Molten Shadow Steel");
		addFluid(MaterialisResources.SHADOW_STEEL_FLUID, "Molten Shadow Steel");
		addItem(MaterialisResources.SHADOW_STEEL_BUCKET, "Molten Shadow Steel Bucket");
		
		//modifiers
		add("modifier.materialis.voiding", "Voiding");
		add("modifier.materialis.voiding.flavor", "Forged in the void");
		add("modifier.materialis.voiding.description", "Voids block and mob drops, drops extra experience for killing mobs");
		add("modifier.materialis.residual_light", "Residual Light");
		add("modifier.materialis.residual_light.flavor", "Forged from light");
		add("modifier.materialis.residual_light.description", "Leaves behind pieces of light (photons) when hitting mobs leaving them glowing or when breaking blocks leaving the air glowing for some time");
	}

	public void addFluid(Supplier<? extends ForgeFlowingFluid> key, String name) {
		ResourceLocation id = key.get().getRegistryName();
		add("fluid." + id.getNamespace() + "." + id.getPath(), name);
	}
}
