package com.rcx.materialis.datagen;

import java.util.function.Supplier;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.MaterialisResources.FluidWithBlockNBucket;
import com.rcx.materialis.MaterialisResources.IngotWithBlockNNugget;
import com.rcx.materialis.modifiers.MaterialisModifiers;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.Modifier;

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

		addMaterial(MaterialisMaterials.fairy, "Fairy", "From the fairy depths of hell.", "Adds the percentage of health lost multiplied by the level to your mining speed");

		//custom casts
		addCast(MaterialisResources.INLAY_CAST, "Inlay");
		addCast(MaterialisResources.WRENCH_HEAD_CAST, "Wrench Head");

		//wrench stuff
		addItem(MaterialisResources.WRENCH_HEAD, "Wrench Head");
		add("pattern.materialis.wrench_head", "Wrench Head");
		addItem(MaterialisResources.WRENCH, "Wrench");
		add("item.materialis.wrench.description", "The Wrench is a common tool among engineers. It can efficiently break machines and has room for many abilities.\nRight Click: Rotate block");
		addItem(MaterialisResources.BATTLEWRENCH, "Battlewrench");
		add("recipe.materialis.modifier.thermal_wrenching_requirements", "Thermal Wrenching requires Wrenching to apply");
		add("recipe.materialis.modifier.create_wrenching_requirements", "Mechanical Wrenching requires Wrenching to apply");

		//general oredict materials
		addMaterial(MaterialisMaterials.brass, "Brass", "Ya like brass?", "Adds +1 attack damage per level that degrades as durability goes down");
		addMaterial(MaterialisMaterials.aluminum, "Aluminum", "", "-10% attack damage, +20% attack speed per level");
		addMaterial(MaterialisMaterials.nickel, "Nickel", "", "Adds half a second of burning per level to targets that are on fire");
		addMaterial(MaterialisMaterials.platinum, "Platinum", "", "Tool takes double durability damage for every level");
		addMaterial(MaterialisMaterials.uranium, "Uranium", "", "Tool takes up to twice as much durability damage when above half durability and increasingly less damage below half durability");
		addMaterial(MaterialisMaterials.osmium, "Osmium", "", "Adds 0.5 attack damage per level multiplied by the percentage of health lost");
		addMaterial(MaterialisMaterials.tungsten, "Tungsten", "", "As the tool loses durability the chance of taking durability damage gradually decreases to 1%");
		addMaterial(MaterialisMaterials.invar, "Invar", "", "Puts out attacked mobs and deals 1 extra damage for every 3 seconds of fire multiplied by the level");

		//create stuff
		addMaterial(MaterialisMaterials.roseQuartz, "Rose Quartz", "Disclaimer: Not actually made with roses.", "Grants 1 extra upgrade slot per part");
		addMaterial(MaterialisMaterials.refinedRadiance, "Refined Radiance", "", "Applies 20 seconds of glowing to attacked mobs and leaves a glowing air block when a block is mined that despawns eventually");
		add("material.materialis.refined_radiance.format", "Radiant %s");
		addMaterial(MaterialisMaterials.shadowSteel, "Shadow Steel", "", "Voids all dropped items from mob kills and broken blocks, drops bonus xp when killing mobs");

		//eidolon stuff
		addMaterial(MaterialisMaterials.pewter, "Pewter", "", "-10% attack speed, +20% attack damage per level");
		addMaterial(MaterialisMaterials.arcaneGold, "Arcane Gold", "", "+1 magic damage per level");

		//aquaculture stuff
		addMaterial(MaterialisMaterials.neptunium, "Neptunium", "", "1.5x damage to submerged mobs, 5x mining speed when submerged");

		//mystical world stuff
		addMaterial(MaterialisMaterials.quicksilver, "Quicksilver", "", "Tool no longer takes durability damage from usages but has a 1% chance of losing durability every second");

		//astral sorcery stuff
		addMaterial(MaterialisMaterials.starmetal, "Starmetal", "Not to be confused with star cutting metal.", "Tool gains 2 mining speed per level at midnight");

		//industrial foregoing stuff
		addMaterial(MaterialisMaterials.plastic, "Plastic", "", "-20% knockback per level");
		addMaterial(MaterialisMaterials.pinkSlime, "Pink Slime", "", "Takes 10% of mining speed and attack speed per level, 300 multiplied by the stats that were taken is added as overslime capacity");
		addItem(MaterialisResources.PINK_SLIME_CRYSTAL, "Pink Slime Crystal");

		//undergarden stuff
		addMaterial(MaterialisMaterials.cloggrum, "Cloggrum", "", "Repairing the tool costs up to half as much the higher the durability the tool has");
		addMaterial(MaterialisMaterials.froststeel, "Froststeel", "", "Applies 7.5 seconds of slowness to attacked mobs per level");
		addMaterial(MaterialisMaterials.utherium, "Utherium", "", "Deals 25% extra damage to rotspawn per level");
		addMaterial(MaterialisMaterials.forgottenMetal, "Forgotten Metal", "It's the uhhh umm, y- I uhhhh ermmmm...", "Deals 50% extra damage to undergarden mobs per level, +25% mining speed on undergarden blocks per level");
		add("material.materialis.forgotten_metal.format", "Forgotten %s");

		//mekanism stuff
		addMaterial(MaterialisMaterials.refinedObsidian, "Refined Obsidian", "", "-20% reach");
		addMaterial(MaterialisMaterials.refinedGlowstone, "Refined Glowstone", "", "Adds the mining speed, multiplied by the level, divided by 20, to the attack speed");

		//psi stuff
		addMaterial(MaterialisMaterials.psimetal, "Psimetal", "", "Casts the selected spell on block break for tools and on attack for weapons, also adds one spell socket");
		addMaterial(MaterialisMaterials.ebonyPsimetal, "Ebony Psimetal", "", "Adds up to 1 extra attack damage that scales with the amount of psi you have per level");
		addMaterial(MaterialisMaterials.ivoryPsimetal, "Ivory Psimetal", "", "Adds up to 1 extra mining speed that scales with the amount of psi you have per level");
		add("recipe.materialis.remove_modifier.spell_slot_not_empty", "This change removes a socket that currently contains a spell");
		add("recipe.materialis.add_modifier.too_many_spell_slots", "This change adds too many sockets to the tool");
		add("recipe.materialis.modifier.casting_requirements", "Casting requires Psionizing Radiation to apply");

		//quark stuff
		add("recipe.materialis.modifier.runed_requirements", "Runed requires Shiny to apply");

		//occultism stuff
		addMaterial(MaterialisMaterials.iesnium, "Iesnium", "", "Allows the tool to harvest otherworld blocks");

		//modifiers
		addModifier(MaterialisModifiers.enhancedQuartzModifier, "Enhanced",
				"Shiny!",
				"Rose quartz goes great with a bonus upgrade!");
		addModifier(MaterialisModifiers.voidingModifier, "Voiding",
				"Forged in the void",
				"Voids block and mob drops, drops extra experience for killing mobs");
		addModifier(MaterialisModifiers.residualLightModifier, "Residual Light",
				"Forged from light",
				"Leaves behind pieces of light (photons) when hitting mobs leaving them glowing or when breaking blocks leaving the air glowing for some time");
		addModifier(MaterialisModifiers.inertiaModifier, "Inertia",
				"A property of matter",
				"Decreases attack speed but increases attack damage");
		addModifier(MaterialisModifiers.featherweightModifier, "Featherweight",
				"Beep beep",
				"Decreases attack damage but increases attack speed");
		addModifier(MaterialisModifiers.arcaneModifier, "Arcane",
				"Magic *snort*",
				"Deals a small amount of extra magic damage to attacked targets");
		addModifier(MaterialisModifiers.reapingModifier, "Reaping",
				"Harvester of souls",
				"Killing undead mobs will destroy their bodies and crystallize their souls, seems to have an affection towards scythes");
		addModifier(MaterialisModifiers.neptunesBlessingModifier, "Neptune's Blessing",
				"Stay hydrated",
				"Increases mining speed underwater and damage to wet mobs");
		addModifier(MaterialisModifiers.halfLifeModifier, "Half-Life",
				"Smell the ashes",
				"Your tool loses durability faster when it has high durability and loses durability slower when it has low durability");
		add("modifier.materialis.half_life.3", "Half-Life II: Episode I");
		add("modifier.materialis.half_life.4", "Half-Life II: Episode II");
		addModifier(MaterialisModifiers.workHardenedModifier, "Work Hardened",
				"Play hardeneder",
				"Your tool loses durability slower when it has low durability");
		addModifier(MaterialisModifiers.daredevilModifier, "Daredevil",
				"Living on the edge",
				"Boosts mining speed when your health is low");
		addModifier(MaterialisModifiers.polishedModifier, "Polished",
				"I can see my face in it!",
				"Deals more damage at a higher durability");
		addModifier(MaterialisModifiers.decayModifier, "Decay",
				"Also melts in your hands",
				"Your tool doesn't use durability on use but instead decays over time");
		addModifier(MaterialisModifiers.nocturnalModifier, "Nocturnal",
				"Stay up past bedtime!",
				"gives bonus mining speed during the night, most powerful at midnight");
		addModifier(MaterialisModifiers.feebleModifier, "Feeble",
				"Handle with care",
				"Decreases knockback on attacked mobs");
		addModifier(MaterialisModifiers.overweightModifier, "Overweight",
				"Supersized!",
				"Eats some mining speed and attack speed to gain more overslime capacity");
		addModifier(MaterialisModifiers.economicalModifier, "Economical",
				"Waste not, want not",
				"Repairing your tool is cheaper when your tool has low durability");
		addModifier(MaterialisModifiers.oldTimerModifier, "Old-Timer",
				"Rust bucket!",
				"Repairing your tool is more expensive when your tool has high durability");
		addModifier(MaterialisModifiers.freezingModifier, "Freezing",
				"As cold as ice!",
				"Slows down hit mobs");
		addModifier(MaterialisModifiers.cleansingModifier, "Cleansing",
				"Begone foul beast!",
				"Deals more damage to rotspawn");
		addModifier(MaterialisModifiers.underlordModifier, "Underlord",
				"Master of the Undergarden",
				"Deals more damage to non-boss undergarden mobs and mines undergarden blocks faster");
		addModifier(MaterialisModifiers.shortSightedModifier, "Short-Sighted",
				"Do now, think later",
				"Decreases your reach distance");
		addModifier(MaterialisModifiers.auxiliaryPowerModifier, "Auxiliary Power",
				"Extra juice!",
				"Boosts your attack speed based on your mining speed");
		addModifier(MaterialisModifiers.adrenalineModifier, "Adrenaline",
				"Fight or flight!",
				"Boosts attack damage when your health is low");
		addModifier(MaterialisModifiers.psionizingRadiationModifier, "Psionizing Radiation",
				"Feels kinda funny",
				"Your tool is highly conductive to psions and is suitable for casting spells, +1 spell socket");
		addModifier(MaterialisModifiers.spellSocketModifier, "Socket",
				"More durable than lime sockets!",
				"Adds one psi spell socket to your tool");
		addModifier(MaterialisModifiers.spellCastingModifier, "Casting",
				"Doesn't involve molten metal",
				"Adds the ability to cast spells on right-click but removes the ability to cast spells on block breaking and attacking, +1 spell socket");
		addModifier(MaterialisModifiers.colorizedModifier, "Colorized",
				"Colormatic!",
				"Adds a decorative colorizer to your tool");
		addModifier(MaterialisModifiers.psichoKillerModifier, "Psicho Killer",
				"Qu'est-ce que c'est?",
				"Deals bonus attack damage depending on how much psi you currently have");
		addModifier(MaterialisModifiers.psichoDiggerModifier, "Psicho Digger",
				"Run run run away",
				"Adds bonus mining speed depending on how much psi you currently have");
		addModifier(MaterialisModifiers.runedModifier, "Runed",
				"Runic energy flows!",
				"Tool glows with a colored enchantment glint");
		addModifier(MaterialisModifiers.brittleModifier, "Brittle",
				"Disintegrates into chalk",
				"Tool takes much more durability damage");
		addModifier(MaterialisModifiers.refuelingModifier, "Refueling",
				"Feed the fire",
				"Attacked mobs that are on fire will burn for longer");
		addModifier(MaterialisModifiers.quenchingModifier, "Quenching",
				"Uninflammable!",
				"Extinguishes mobs and deals bonus damage depending on how much longer the mob would have burned");
		add("modifier.materialis.otherworldly", "Otherworldly");
		add("modifier.materialis.otherworldly.flavor", "From another plane of existence!");
		add("modifier.materialis.otherworldly.description", "Allows the tool to harvest blocks from The Other Place");
		addModifier(MaterialisModifiers.wrenchingModifier, "Wrenching",
				"Wretched!",
				"Tool can rotate blocks by right clicking");
		addModifier(MaterialisModifiers.galvanizedModifier, "Galvanized",
				"Stainless!",
				"Trades two upgrade slots for an ability slot");
		addModifier(MaterialisModifiers.thermalWrenchingModifier, "Thermal Wrenching",
				"Crescent!",
				"Tool can dismantle thermal machines by sneak-right clicking");
		addModifier(MaterialisModifiers.createWrenchingModifier, "Mechanical Wrenching",
				"Creative!",
				"Tool can dismantle kinetic components by sneak-right clicking");
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

	public void addMaterial(MaterialId material, String name, String flavour, String desc) {
		String id = material.getPath();
		add("material.materialis." + id, name);
		if (!flavour.equals(""))
			add("material.materialis." + id + ".flavor", flavour);
		if (!desc.equals(""))
			add("material.materialis." + id + ".encyclopedia", desc);
	}

	public void addModifier(RegistryObject<Modifier> modifier, String name, String flavour, String desc) {
		String id = modifier.getId().getPath();
		add("modifier.materialis." + id, name);
		add("modifier.materialis." + id + ".flavor", flavour);
		add("modifier.materialis." + id + ".description", desc);
	}
}
