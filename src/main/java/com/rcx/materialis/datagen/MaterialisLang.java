package com.rcx.materialis.datagen;

import java.util.function.Supplier;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.MaterialisResources.FluidWithBlockNBucket;
import com.rcx.materialis.MaterialisResources.IngotWithBlockNNugget;
import com.rcx.materialis.util.TinkerToolFluxed;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import slimeknights.tconstruct.tools.item.ArmorSlotType;

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

		addMaterial(MaterialisMaterials.fairy, "Fairy", "From the fairy depths of hell.", "Adds the percentage of health lost multiplied by the level to your mining speed and accuracy");
		add(TinkerToolFluxed.STORED_ENERGY_KEY, "Energy: %s / %s RF");

		//custom casts
		addCast(MaterialisResources.INLAY_CAST, "Inlay");
		addCast(MaterialisResources.WRENCH_HEAD_CAST, "Wrench Head");

		//arrows
		addItem(MaterialisResources.MANASHOT, "Manashot");
		addEntityType(MaterialisResources.MANASHOT_ENTITY, "Manashot");
		addItem(MaterialisResources.HEAVENSHOT, "Heavenshot");

		//wrench stuff
		addItem(MaterialisResources.WRENCH_HEAD, "Wrench Head");
		add("pattern.materialis.wrench_head", "Wrench Head");
		addItem(MaterialisResources.WRENCH, "Wrench");
		add("item.materialis.wrench.description", "The Wrench is a common tool among engineers. It can efficiently break machines and has room for many abilities.\nRight Click: Rotate block");
		addItem(MaterialisResources.BATTLEWRENCH, "Battlewrench");
		add("recipe.materialis.modifier.thermal_wrenching_requirements", "Thermal Wrenching requires Wrenching to apply");
		add("recipe.materialis.modifier.create_wrenching_requirements", "Mechanical Wrenching requires Wrenching to apply");
		add("recipe.materialis.modifier.immersive_wrenching_requirements", "Immersive Hammering requires Wrenching to apply");
		add("recipe.materialis.modifier.pipe_wrenching_requirements", "Pipe Wrenching requires Wrenching to apply");

		//general oredict materials
		addMaterial(MaterialisMaterials.brass, "Brass", "Ya like brass?", "Adds +1.5 attack damage and +0.1 velocity per level that degrades as durability goes down");
		addMaterial(MaterialisMaterials.aluminum, "Aluminum", "", "-10% attack damage, +15% attack speed per level");
		addMaterial(MaterialisMaterials.nickel, "Nickel", "", "Adds half a second of burning per level to targets that are on fire");
		//addMaterial(MaterialisMaterials.platinum, "Platinum", "", "Tool takes double durability damage for every level");
		addMaterial(MaterialisMaterials.uranium, "Uranium", "", "Tool takes up to twice as much durability damage when above a quarter durability and increasingly less damage below a quarter durability");
		//addMaterial(MaterialisMaterials.osmium, "Osmium", "", "Adds 0.5 attack damage per level multiplied by the percentage of health lost");
		//addMaterial(MaterialisMaterials.tungsten, "Tungsten", "", "As the tool loses durability the chance of taking durability damage gradually decreases to 1%");
		//addMaterial(MaterialisMaterials.invar, "Invar", "", "Puts out attacked mobs and deals 1 extra damage for every 3 seconds of fire multiplied by the level");

		//create stuff
		addMaterial(MaterialisMaterials.roseQuartz, "Rose Quartz", "Disclaimer: Not actually made with roses.", "Grants 1 extra upgrade slot per part");
		addMaterial(MaterialisMaterials.refinedRadiance, "Refined Radiance", "", "Applies 20 seconds of glowing to attacked mobs and leaves a glowing air block when a block is mined that despawns eventually");
		add("material.materialis.refined_radiance.format", "Radiant %s");
		addMaterial(MaterialisMaterials.shadowSteel, "Shadow Steel", "", "Voids all dropped items from mob kills and broken blocks, drops bonus xp instead");

		//eidolon stuff
		addMaterial(MaterialisMaterials.pewter, "Pewter", "", "-5% attack speed, +10% attack damage per level");
		addMaterial(MaterialisMaterials.arcaneGold, "Arcane Gold", "", "+1 magic damage per level");

		//aquaculture stuff
		addMaterial(MaterialisMaterials.neptunium, "Neptunium", "", "1.25x damage to submerged mobs, no mining speed penalty when underwater");

		//mystical world stuff
		addMaterial(MaterialisMaterials.quicksilver, "Quicksilver", "", "Tool no longer takes durability damage from usages but has a 1% chance of losing durability every second");

		//astral sorcery stuff
		addMaterial(MaterialisMaterials.starmetal, "Starmetal", "Not to be confused with star cutting metal.", "Tool gains 2 mining speed and 0.1 draw speed per level at midnight");

		//industrial foregoing stuff
		addMaterial(MaterialisMaterials.plastic, "Plastic", "", "-20% knockback per level");
		addMaterial(MaterialisMaterials.pinkSlime, "Pink Slime", "Do you have any idea where that's been?", "Overslime can be eaten to restore 1 hunger and 0.1 saturation per level");
		addMaterial(MaterialisMaterials.pinkSlimeball, "Pink Slime", "", "");

		//undergarden stuff
		addMaterial(MaterialisMaterials.cloggrum, "Cloggrum", "", "Repairing the tool costs up to half as much the higher the durability the tool has");
		addMaterial(MaterialisMaterials.froststeel, "Froststeel", "", "Applies 7.5 seconds of slowness to attacked mobs per level");
		addMaterial(MaterialisMaterials.utherium, "Utherium", "", "Deals 25% extra damage to rotspawn per level");
		addMaterial(MaterialisMaterials.regalium, "Regalium", "", "");
		addMaterial(MaterialisMaterials.forgottenMetal, "Forgotten Metal", "It's the uhhh umm, y- I uhhhh ermmmm...", "Deals 50% extra damage to undergarden mobs per level, +25% mining speed on undergarden blocks per level");
		add("material.materialis.forgotten_metal.format", "Forgotten %s");

		//mekanism stuff
		addMaterial(MaterialisMaterials.refinedObsidian, "Refined Obsidian", "", "-20% reach");
		addMaterial(MaterialisMaterials.refinedGlowstone, "Refined Glowstone", "", "Adds the mining speed, multiplied by the level, divided by 80, to the attack speed");

		//psi stuff
		addMaterial(MaterialisMaterials.psimetal, "Psimetal", "", "Casts the selected spell on block break for tools and on attack for weapons, also adds one spell socket");
		addMaterial(MaterialisMaterials.ebonyPsimetal, "Ebony Psimetal", "", "Adds up to 1 extra attack damage that scales with the amount of psi you have per level");
		addMaterial(MaterialisMaterials.ivoryPsimetal, "Ivory Psimetal", "", "Adds up to 1 extra mining speed that scales with the amount of psi you have per level");
		add("recipe.materialis.remove_modifier.spell_slot_not_empty", "This change removes a socket that currently contains a spell");
		add("recipe.materialis.add_modifier.too_many_spell_slots", "This change adds too many sockets to the tool");
		add("recipe.materialis.modifier.casting_requirements", "Casting requires Psionizing Radiation or Psi Decay to apply");
		add("stat.tconstruct.slot.display.sensor", "sensor");
		add("stat.tconstruct.slot.prefix.sensor", "Sensor Slots: ");
		add("item.tconstruct.creative_slot.sensor", "Creative Sensor Slot");
		addItem(() -> MaterialisResources.PSIMETAL_EXOSUIT.get(ArmorSlotType.BOOTS), "Tinkers Exosuit Boots");
		addItem(() -> MaterialisResources.PSIMETAL_EXOSUIT.get(ArmorSlotType.LEGGINGS), "Tinkers Exosuit Leggings");
		addItem(() -> MaterialisResources.PSIMETAL_EXOSUIT.get(ArmorSlotType.CHESTPLATE), "Tinkers Exosuit Chestplate");
		addItem(() -> MaterialisResources.PSIMETAL_EXOSUIT.get(ArmorSlotType.HELMET), "Tinkers Exosuit Helmet");

		//quark stuff
		add("recipe.materialis.modifier.runed_requirements", "Runed requires Shiny to apply");

		//occultism stuff
		addMaterial(MaterialisMaterials.iesnium, "Iesnium", "", "Allows the tool to harvest otherworld blocks");

		//botania stuff
		addMaterial(MaterialisMaterials.livingwood, "Livingwood", "", "Increases attack damage with 1.5 per level, mana cost increases with level");
		addMaterial(MaterialisMaterials.dreamwood, "Dreamwood", "", "Increases mining speed with 2.5 per level, mana cost increases with level");
		addMaterial(MaterialisMaterials.manasteel, "Manasteel", "", "Has a 10% chance of using mana instead of durability per level");
		addMaterial(MaterialisMaterials.elementium, "Elementium", "", "Adds a 3% chance to spawn a pixie while holding the tool per level");
		addMaterial(MaterialisMaterials.terrasteel, "Terrasteel", "", "Has a 40% chance per level to fire a beam, this costs mana or durability if you have no mana");
		//add("material.materialis.terrasteel.format", "Terra %s");
		addMaterial(MaterialisMaterials.manastring, "Mana Infused String", "", "Conjures arrows out of thin air at the cost of mana");
		add("material.materialis.manastring.format", "Mana Infused %s");

		//mythicbotany stuff
		addMaterial(MaterialisMaterials.alfsteel, "Alfsteel", "", "Has a 40% chance per level to fire a beam, this costs mana or durability if you have no mana");

		//draconic evolution stuff
		addMaterial(MaterialisMaterials.draconium, "Draconium", "", "Has a 10% chance of using 200 RF instead of durability per level");
		add("material.materialis.draconium.format", "Wyvern %s");
		addMaterial(MaterialisMaterials.awakenedDraconium, "Awakened Draconium", "", "Increases mining speed with 2.5 and attack damage with 1.5 per level, costs 100 RF per level");
		add("material.materialis.draconium_awakened.format", "Draconic %s");

		//redstone arsenal stuff
		addMaterial(MaterialisMaterials.fluxInfused, "Flux-Infused", "", "Increases mining speed with 2.5 and attack damage with 1.5 per level, costs 100 RF per level");

		//avaritia stuff
		addMaterial(MaterialisMaterials.crystalMatrix, "Crystal Matrix", "", "Adds 50% mining speed and attack damage per level");
		addMaterial(MaterialisMaterials.neutronium, "Neutronium", "", "Adds 150% knockback per level");
		addMaterial(MaterialisMaterials.infinity, "Infinity", "", "");
		addMaterial(MaterialisMaterials.infinityEmbellishment, "Infinity", "", "");
		add("stat.tconstruct.harvest_tier.materialis.infinity", "Infinity");

		//modifiers
		addModifier(MaterialisModifiers.enhancedQuartzModifier, "Enhanced",
				"Shiny!",
				"Rose quartz goes great with a bonus upgrade!");
		addModifier(MaterialisModifiers.voidingModifier, "Voiding",
				"Forged in the void",
				"Voids block and mob drops, drops extra experience instead");
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
		add("modifier.materialis.arcane.attack_damage", "Magic Damage");
		addModifier(MaterialisModifiers.reapingModifier, "Reaping",
				"Harvester of souls",
				"Killing undead mobs will destroy their bodies and crystallize their souls, seems to have an affection towards scythes");
		addModifier(MaterialisModifiers.neptunesBlessingModifier, "Neptune's Blessing",
				"Stay hydrated",
				"Increases damage to submerged mobs and allows you to mine normally while swimming");
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
				"Boosts mining speed and accuracy when your health is low");
		addModifier(MaterialisModifiers.polishedModifier, "Polished",
				"I can see my face in it!",
				"Boosts damage and velocity at a higher durability");
		add("modifier.materialis.polished.attack_damage", "Polished Attack Damage");
		add("modifier.materialis.polished.velocity", "Polished Velocity");
		addModifier(MaterialisModifiers.decayModifier, "Decay",
				"Also melts in your hands",
				"Your tool doesn't use durability on use but instead decays over time");
		addModifier(MaterialisModifiers.nocturnalModifier, "Nocturnal",
				"Stay up past bedtime!",
				"gives bonus mining speed during the night, most powerful at midnight");
		add("modifier.materialis.nocturnal.mining_speed", "Nocturnal Mining Speed");
		add("modifier.materialis.nocturnal.draw_speed", "Nocturnal Draw Speed");
		addModifier(MaterialisModifiers.feebleModifier, "Feeble",
				"Handle with care",
				"Decreases knockback on attacked mobs");
		addModifier(MaterialisModifiers.overweightModifier, "Overweight",
				"Supersized!",
				"Eats some mining speed and attack damage to gain more overslime capacity");
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
		addModifier(MaterialisModifiers.psionizingRadiationModifierBreakBlock, "Psi Decay (Break Block)",
				"You can feel it glowing",
				"Enables the tool to cast spells when breaking blocks");
		addModifier(MaterialisModifiers.psionizingRadiationModifierAttack, "Psi Decay (Attack)",
				"You can feel it glowing",
				"Enables the tool to cast spells when attacking");
		addModifier(MaterialisModifiers.psionizingRadiationModifierDamage, "Psi Decay (Damage)",
				"You can feel it glowing",
				"Enables the tool to cast spells when taking damage");
		addModifier(MaterialisModifiers.psionizingRadiationModifierTick, "Psi Decay (Tick)",
				"You can feel it glowing",
				"Enables the tool to cast spells every tick");
		addModifier(MaterialisModifiers.psionizingRadiationModifierJump, "Psi Decay (Jump)",
				"You can feel it glowing",
				"Enables the tool to cast spells when jumping");
		addModifier(MaterialisModifiers.psionizingRadiationModifierFire, "Psi Decay (On Fire)",
				"You can feel it glowing",
				"Enables the tool to cast spells when taking fire damage");
		addModifier(MaterialisModifiers.psionizingRadiationModifierHealth, "Psi Decay (Low Health)",
				"You can feel it glowing",
				"Enables the tool to cast spells when your health is low");
		addModifier(MaterialisModifiers.psionizingRadiationModifierLight, "Psi Decay (Low Light)",
				"You can feel it glowing",
				"Enables the tool to cast spells when entering darkness");
		addModifier(MaterialisModifiers.psionizingRadiationModifierWater, "Psi Decay (Underwater)",
				"You can feel it glowing",
				"Enables the tool to cast spells when underwater");
		addModifier(MaterialisModifiers.psionizingRadiationModifierDetonate, "Psi Decay (Detonation)",
				"You can feel it glowing",
				"Enables the tool to cast spells when a spell is detonated nearby");
		addModifier(MaterialisModifiers.lesserPsionizingRadiationModifier, "Psi Decay",
				"You can feel it glowing",
				"Your tool is highly conductive to psions and is suitable for casting spells");
		addModifier(MaterialisModifiers.spellSocketModifier, "Socket",
				"More durable than lime sockets!",
				"Adds one psi spell socket to your tool");
		addModifier(MaterialisModifiers.spellCastingModifier, "Casting",
				"Doesn't involve molten metal",
				"Adds the ability to cast spells on right-click but removes the ability to cast spells on block breaking and attacking, +1 spell socket");
		addModifier(MaterialisModifiers.lesserSpellCastingModifier, "Casting",
				"Doesn't involve molten metal",
				"Adds the ability to cast spells on right-click but removes the ability to cast spells on block breaking and attacking");
		addModifier(MaterialisModifiers.colorizedModifier, "Colorized",
				"Colormatic!",
				"Adds a decorative colorizer to your tool");
		addModifier(MaterialisModifiers.psionizingRadiationModifierSensor, "Sensor",
				"6th sense!",
				"Enables the tool to cast spells when the selected sensor is triggered");
		/*addModifier(MaterialisModifiers.processor, "Processor",
				"Is this processed?",
				"Allows your tool to process input from sensors for casting spells");*/
		addModifier(MaterialisModifiers.psichoKillerModifier, "Psicho Killer",
				"Qu'est-ce que c'est?",
				"Deals bonus attack damage depending on how much psi you currently have");
		add("modifier.materialis.psicho_killer.attack_damage", "Attack Damage at full Psi");
		addModifier(MaterialisModifiers.psichoDiggerModifier, "Psicho Digger",
				"Run run run away",
				"Adds bonus mining speed depending on how much psi you currently have");
		add("modifier.materialis.psicho_digger.mining_speed", "Mining Speed at full Psi");
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
		addModifier(MaterialisModifiers.immersiveWrenchingModifier, "Immersive Hammering",
				"Pootis spencer here!",
				"Tool can assemble multiblocks and configure sides of certain blocks");
		addModifier(MaterialisModifiers.pipeWrenchingModifier, "Pipe Wrenching",
				"Pretty!",
				"Tool can configure pretty pipes");
		addModifier(MaterialisModifiers.overeatingModifier, "Overeating",
				"5 second rule!",
				"That overslime buffer on your tool suddenly looks very appealing");
		addModifier(MaterialisModifiers.psishieldModifier, "Psishield",
				"Psichological!",
				"Has a chance of protecting your tool from damage at the cost of psi");
		addModifier(MaterialisModifiers.manaripperModifier, "Manaripper",
				"Rip and tear",
				"Boosts attack damage at the cost of mana");
		add("modifier.materialis.manaripper.attack_damage", "Attack Damage with Mana");
		addModifier(MaterialisModifiers.manaburnerModifier, "Manaburner",
				"burn through the mana",
				"Boosts mining speed at the cost of mana");
		add("modifier.materialis.manaburner.mining_speed", "Mining Speed with Mana");
		addModifier(MaterialisModifiers.manashieldModifier, "Manashield",
				"Botanical!",
				"Has a chance of protecting your tool from damage at the cost of mana");
		addModifier(MaterialisModifiers.manadrawModifier, "Manadraw",
				"Manaical!",
				"Boosts draw speed at the cost of mana");
		add("modifier.materialis.manadraw.draw_speed", "Draw Speed with Mana");
		addModifier(MaterialisModifiers.manaboltModifier, "Manabolt",
				"You better make like a nut, and bolt",
				"Boosts projectile velocity at the cost of mana");
		add("modifier.materialis.manashot.velocity", "Velocity with Mana");
		addModifier(MaterialisModifiers.manashotModifier, "Manashot",
				"Sick airshot!",
				"Creates arrows out of mana");
		addModifier(MaterialisModifiers.pixiecallerModifier, "Pixiecaller",
				"Pix or it didn't happen",
				"Has a chance of spawning a pixie when you take damage while holding the tool");
		addModifier(MaterialisModifiers.terrabeamModifier, "Terrabeam",
				"Beam me up!",
				"When attacking with your tool you have a chance of firing a beam of mana");
		addModifier(MaterialisModifiers.elvenBeamModifier, "Elven Beam",
				"Science cannot yet explain how it works",
				"When attacking with your tool you have a chance of firing a beam of mana");
		addModifier(MaterialisModifiers.capacitorModifier, "Capacitor",
				"This is what makes time travel possible, the flux capacitor",
				"Increases the energy capacity of the tool by 10000");
		addModifier(MaterialisModifiers.fluxripperModifier, "Fluxripper",
				"64 cores!",
				"Boosts attack damage at the cost of energy");
		add("modifier.materialis.fluxripper.attack_damage", "Fluxed Attack Damage");
		addModifier(MaterialisModifiers.fluxburnerModifier, "Fluxburner",
				"Empowered!",
				"Boosts mining speed at the cost of energy");
		add("modifier.materialis.fluxburner.mining_speed", "Fluxed Mining Speed");
		addModifier(MaterialisModifiers.fluxshieldModifier, "Fluxshield",
				"Energized!",
				"Has a chance of protecting your tool from damage at the cost of energy");
		addModifier(MaterialisModifiers.fluxdrawModifier, "Fluxdraw",
				"Talk about drawing energy",
				"Boosts draw speed at the cost of flux");
		add("modifier.materialis.fluxdraw.draw_speed", "Fluxed Draw Speed");
		addModifier(MaterialisModifiers.fluxboltModifier, "Fluxbolt",
				"Railgun!",
				"Boosts projectile velocity at the cost of flux");
		add("modifier.materialis.fluxshot.velocity", "Fluxed Velocity");
		addModifier(MaterialisModifiers.powerHungryModifier, "Power Hungry",
				"Megalomaniac!",
				"Using the tool costs 100 RF per durability per level, the tool doesn't seem to work so well if it's not powered");
		addModifier(MaterialisModifiers.engineersGogglesModifier, "Engineers Goggles",
				"Mind-goggling",
				"Shows additional information when looking at kinetic devices");
		addModifier(MaterialisModifiers.otherworldGogglesModifier, "Otherworld Goggles",
				"Open your third eye",
				"Allows you to see blocks from the other place, can be toggled with sneak + helmet interact");
		addModifier(MaterialisModifiers.reactiveModifier, "Reactive",
				"Not to be confused with radioactive",
				"Has a chance to cast the inscribed spell on use");
		add("recipe.materialis.modifier.reactive_requirements", "Reactive requires a different recipe for every level");
		addModifier(MaterialisModifiers.crystallineModifier, "Crystalline",
				"Refractive!",
				"Adds 50% extra mining speed and attack damage");
		addModifier(MaterialisModifiers.supermassiveModifier, "Supermassive",
				"You set my soul alight",
				"Greatly increases the amount of knockback dealt to enemies");
		addModifier(MaterialisModifiers.instamineModifier, "Instamine",
				"Talk about speedmining",
				"Blocks instantly break when hit");
		addModifier(MaterialisModifiers.instakillModifier, "Instakill",
				"Headshot!",
				"Enemies instantly die when attacked");
		addModifier(MaterialisModifiers.cosmicLuckModifier, "Luck X",
				"Flamboyant!",
				"Gives you many more nice things when mining or killing mobs!");
		addModifier(MaterialisModifiers.cosmicUnbreakableModifier, "Unbreakable",
				"Frozen state!",
				"Tool no longer takes damage from normal usage");
		addModifier(MaterialisModifiers.bedrockBreakerModifier, "Bedrock Breaker",
				"Break the unbreakable",
				"Allows the tool to harvest bedrock");
		addModifier(MaterialisModifiers.cataclysmicModifier, "Cataclysmic",
				"Absolutely ludicrous!",
				"Adds 5 extra upgrades and 1 ability");
		addModifier(MaterialisModifiers.skullfireModifire, "Skullfire",
				"Turn the lights out",
				"Beheads skeletons and scorches them black");
		add("recipe.materialis.modifier.skullfire_requirements", "Skullfire requires Severing V to apply");
		addModifier(MaterialisModifiers.heavenshotModifier, "Heavenshot",
				"Holy Shot!",
				"Creates arrows out of thin air that chase targets and explode on impact");
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

	public void addModifier(StaticModifier<? extends Modifier> modifier, String name, String flavour, String desc) {
		String id = modifier.getId().getPath();
		add("modifier.materialis." + id, name);
		add("modifier.materialis." + id + ".flavor", flavour);
		add("modifier.materialis." + id + ".description", desc);
	}

	public void addModifier(ModifierId modifier, String name, String flavour, String desc) {
		String id = modifier.getPath();
		add("modifier.materialis." + id, name);
		add("modifier.materialis." + id + ".flavor", flavour);
		add("modifier.materialis." + id + ".description", desc);
	}
}
