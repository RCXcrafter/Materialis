package com.rcx.materialis.datagen;

import java.util.function.Supplier;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.MaterialisResources.FluidWithBlockNBucket;
import com.rcx.materialis.MaterialisResources.IngotWithBlockNNugget;

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
		for (FluidWithBlockNBucket fluid : MaterialisResources.fluidList) {
			addBlock(fluid.FLUID_BLOCK, fluid.localizedName);
			addFluid(fluid.FLUID, fluid.localizedName);
			addItem(fluid.FLUID_BUCKET, fluid.localizedName + " Bucket");
		}

		for (IngotWithBlockNNugget material : MaterialisResources.materialList) {
			addBlock(material.BLOCK, material.localizedName + " Block");
			addItem(material.INGOT, material.localizedName + " Ingot");
			addItem(material.NUGGET, material.localizedName + " Nugget");
		}

		add("material.materialis.fairy", "Fairy");

		//custom casts
		addCast(MaterialisResources.INLAY_CAST, "Inlay");

		//general oredict materials
		add("material.materialis.brass", "Brass");
		add("material.materialis.aluminum", "Aluminum");
		//add("material.materialis.nickel", "Nickel");
		//add("material.materialis.platinum", "Platinum");
		//add("material.materialis.tin", "Tin");
		//add("material.materialis.zinc", "Zinc");
		add("material.materialis.uranium", "Uranium");
		//add("material.materialis.osmium", "Osmium");
		add("material.materialis.tungsten", "Tungsten");
		//add("material.materialis.invar", "Invar");

		//create stuff
		add("material.materialis.rose_quartz", "Rose Quartz");
		add("material.materialis.refined_radiance", "Refined Radiance");
		add("material.materialis.refined_radiance.format", "Radiant %s");
		add("material.materialis.shadow_steel", "Shadow Steel");

		//eidolon stuff
		add("material.materialis.pewter", "Pewter");
		add("material.materialis.arcane_gold", "Arcane Gold");

		//aquaculture stuff
		add("material.materialis.neptunium", "Neptunium");

		//mystical world stuff
		add("material.materialis.quicksilver", "Quicksilver");

		//astral sorcery stuff
		add("material.materialis.starmetal", "Starmetal");

		//industrial foregoing stuff
		add("material.materialis.plastic", "Plastic");
		add("material.materialis.pink_slime", "Pink Slime");
		addItem(MaterialisResources.PINK_SLIME_CRYSTAL, "Pink Slime Crystal");

		//undergarden stuff
		add("material.materialis.cloggrum", "Cloggrum");
		add("material.materialis.froststeel", "Froststeel");
		add("material.materialis.utherium", "Utherium");
		add("material.materialis.forgotten_metal", "Forgotten Metal");
		add("material.materialis.forgotten_metal.format", "Forgotten %s");

		//modifiers
		add("modifier.materialis.enhanced_quartz", "Enhanced");
		add("modifier.materialis.enhanced_quartz.flavor", "Shiny!");
		add("modifier.materialis.enhanced_quartz.description", "Rose quartz goes great with a bonus upgrade!");
		add("modifier.materialis.voiding", "Voiding");
		add("modifier.materialis.voiding.flavor", "Forged in the void");
		add("modifier.materialis.voiding.description", "Voids block and mob drops, drops extra experience for killing mobs");
		add("modifier.materialis.residual_light", "Residual Light");
		add("modifier.materialis.residual_light.flavor", "Forged from light");
		add("modifier.materialis.residual_light.description", "Leaves behind pieces of light (photons) when hitting mobs leaving them glowing or when breaking blocks leaving the air glowing for some time");
		add("modifier.materialis.inertia", "Inertia");
		add("modifier.materialis.inertia.flavor", "A property of matter");
		add("modifier.materialis.inertia.description", "Decreases attack speed but increases attack damage");
		add("modifier.materialis.featherweight", "Featherweight");
		add("modifier.materialis.featherweight.flavor", "Beep beep");
		add("modifier.materialis.featherweight.description", "Decreases attack damage but increases attack speed");
		add("modifier.materialis.arcane", "Arcane");
		add("modifier.materialis.arcane.flavor", "Magic *snort*");
		add("modifier.materialis.arcane.description", "Deals a small amount of extra magic damage to attacked targets");
		add("modifier.materialis.reaping", "Reaping");
		add("modifier.materialis.reaping.flavor", "Harvester of souls");
		add("modifier.materialis.reaping.description", "Killing undead mobs will destroy their bodies and crystallize their souls");
		add("modifier.materialis.neptunes_blessing", "Neptune's Blessing");
		add("modifier.materialis.neptunes_blessing.flavor", "Stay hydrated");
		add("modifier.materialis.neptunes_blessing.description", "Increases mining speed underwater and damage to wet mobs");
		add("modifier.materialis.half_life", "Half-Life");
		add("modifier.materialis.half_life.3", "Half-Life II: Episode I");
		add("modifier.materialis.half_life.4", "Half-Life II: Episode II");
		add("modifier.materialis.half_life.flavor", "Smell the ashes");
		add("modifier.materialis.half_life.description", "Your tool loses durability faster when it has high durability and loses durability slower when it has low durability");
		add("modifier.materialis.work_hardened", "Work Hardened");
		add("modifier.materialis.work_hardened.flavor", "Play hardeneder");
		add("modifier.materialis.work_hardened.description", "Your tool loses durability slower when it has low durability");
		add("modifier.materialis.daredevil", "Daredevil");
		add("modifier.materialis.daredevil.flavor", "Living on the edge");
		add("modifier.materialis.daredevil.description", "Boosts mining speed when your health is low");
		add("modifier.materialis.polished", "Polished");
		add("modifier.materialis.polished.flavor", "I can see my face in it!");
		add("modifier.materialis.polished.description", "Deals more damage at a higher durability");
		add("modifier.materialis.decay", "Decay");
		add("modifier.materialis.decay.flavor", "Disintegrates into chalk");
		add("modifier.materialis.decay.description", "Your tool doesn't use durability on use but instead decays over time");
		add("modifier.materialis.nocturnal", "Nocturnal");
		add("modifier.materialis.nocturnal.flavor", "Stay up past bedtime!");
		add("modifier.materialis.nocturnal.description", "gives bonus mining speed during the night, most powerful at midnight");
		add("modifier.materialis.feeble", "Feeble");
		add("modifier.materialis.feeble.flavor", "Handle with care");
		add("modifier.materialis.feeble.description", "Decreases knockback on attacked mobs");
		add("modifier.materialis.overweight", "Overweight");
		add("modifier.materialis.overweight.flavor", "Supersized!");
		add("modifier.materialis.overweight.description", "Eats some mining speed and attack speed to gain more overslime capacity");
		add("modifier.materialis.economical", "Economical");
		add("modifier.materialis.economical.flavor", "Waste not, want not");
		add("modifier.materialis.economical.description", "Repairing your tool is cheaper when your tool has low durability");
		add("modifier.materialis.old_timer", "Old-Timer");
		add("modifier.materialis.old_timer.flavor", "Rust Bucket!");
		add("modifier.materialis.old_timer.description", "Repairing your tool is more expensive when your tool has high durability");
		add("modifier.materialis.freezing", "Freezing");
		add("modifier.materialis.freezing.flavor", "As cold as ice!");
		add("modifier.materialis.freezing.description", "Slows down hit mobs");
		add("modifier.materialis.cleansing", "Cleansing");
		add("modifier.materialis.cleansing.flavor", "Begone foul beast!");
		add("modifier.materialis.cleansing.description", "Deals more damage to rotspawn");
		add("modifier.materialis.underlord", "Underlord");
		add("modifier.materialis.underlord.flavor", "Master of the Undergarden");
		add("modifier.materialis.underlord.description", "Deals more damage to non-boss undergarden mobs");
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
