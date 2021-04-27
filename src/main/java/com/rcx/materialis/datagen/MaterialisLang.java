package com.rcx.materialis.datagen;

import java.util.function.Supplier;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.tconstruct.common.registration.CastItemObject;

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

		//custom casts
		addCast(MaterialisResources.INLAY_CAST, "Inlay");

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

		//eidolon stuff
		add("material.materialis.pewter", "Pewter");
		add("material.materialis.arcane_gold", "Arcane Gold");
		addBlock(MaterialisResources.MOLTEN_ARCANE_GOLD, "Molten Arcane Gold");
		addFluid(MaterialisResources.ARCANE_GOLD_FLUID, "Molten Arcane Gold");
		addItem(MaterialisResources.ARCANE_GOLD_BUCKET, "Molten Arcane Gold Bucket");

		//aquaculture stuff
		add("material.materialis.neptunium", "Neptunium");
		addBlock(MaterialisResources.MOLTEN_NEPTUNIUM, "Molten Neptunium");
		addFluid(MaterialisResources.NEPTUNIUM_FLUID, "Molten Neptunium");
		addItem(MaterialisResources.NEPTUNIUM_BUCKET, "Molten Neptunium Bucket");

		//modifiers
		add("modifier.materialis.voiding", "Voiding");
		add("modifier.materialis.voiding.flavor", "Forged in the void");
		add("modifier.materialis.voiding.description", "Voids block and mob drops, drops extra experience for killing mobs");
		add("modifier.materialis.residual_light", "Residual Light");
		add("modifier.materialis.residual_light.flavor", "Forged from light");
		add("modifier.materialis.residual_light.description", "Leaves behind pieces of light (photons) when hitting mobs leaving them glowing or when breaking blocks leaving the air glowing for some time");
		add("modifier.materialis.inertia", "Inertia");
		add("modifier.materialis.inertia.flavor", "A property of matter");
		add("modifier.materialis.inertia.description", "Decreases attack speed but increases attack damage");
		add("modifier.materialis.arcane", "Arcane");
		add("modifier.materialis.arcane.flavor", "Magic *snort*");
		add("modifier.materialis.arcane.description", "Deals a small amount of extra magic damage to attacked targets");
		add("modifier.materialis.reaping", "Reaping");
		add("modifier.materialis.reaping.flavor", "Harvester of souls");
		add("modifier.materialis.reaping.description", "Killing undead mobs will destroy their bodies and crystallize their souls");
		add("modifier.materialis.neptunes_blessing", "Neptune's Blessing");
		add("modifier.materialis.neptunes_blessing.flavor", "Stay hydrated");
		add("modifier.materialis.neptunes_blessing.description", "Increases mining speed underwater and damage to wet mobs");
	}

	public void addFluid(Supplier<? extends ForgeFlowingFluid> key, String name) {
		ResourceLocation id = key.get().getRegistryName();
		add("fluid." + id.getNamespace() + "." + id.getPath(), name);
	}

	public void addCast(CastItemObject cast, String name) {
		ResourceLocation id = cast.get().getRegistryName();
		add("item." + id.getNamespace() + "." + id.getPath(), name + " Gold Cast");
		ResourceLocation idSand = cast.getSand().getRegistryName();
		add("item." + idSand.getNamespace() + "." + idSand.getPath(), name + " Sand Cast");
		ResourceLocation idRedSand = cast.getRedSand().getRegistryName();
		add("item." + idRedSand.getNamespace() + "." + idRedSand.getPath(), name + " Red Sand Cast");
	}
}
