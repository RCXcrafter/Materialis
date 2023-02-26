package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.util.InfinityTier;
import com.rcx.materialis.util.MaterialisConfigCondition;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.crafting.conditions.AndCondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.OrCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import slimeknights.tconstruct.common.json.ConfigEnabledCondition;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.stats.ExtraMaterialStats;
import slimeknights.tconstruct.tools.stats.GripMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;
import slimeknights.tconstruct.tools.stats.LimbMaterialStats;

public class MaterialisMaterials extends AbstractMaterialDataProvider {

	public static final MaterialId fairy = createMaterial("fairy");

	//general oredict materials
	public static final MaterialId brass = createMaterial("brass");
	public static final MaterialId aluminum = createMaterial("aluminum");
	public static final MaterialId nickel = createMaterial("nickel");
	public static final MaterialId uranium = createMaterial("uranium");

	//create materials
	public static final MaterialId roseQuartz = createMaterial("rose_quartz");
	public static final MaterialId refinedRadiance = createMaterial("refined_radiance");
	public static final MaterialId shadowSteel = createMaterial("shadow_steel");

	//eidolon materials
	public static final MaterialId pewter = createMaterial("pewter");
	public static final MaterialId arcaneGold = createMaterial("arcane_gold");

	//aquaculture materials
	public static final MaterialId neptunium = createMaterial("neptunium");

	//mystical world materials
	public static final MaterialId quicksilver = createMaterial("quicksilver");

	//astral sorcery materials
	public static final MaterialId starmetal = createMaterial("starmetal");

	//industrial foregoing materials
	public static final MaterialId plastic = createMaterial("plastic");
	public static final MaterialId pinkSlime = createMaterial("pink_slime");
	public static final MaterialId pinkSlimeball = createMaterial("pink_slimeball");

	//undergarden materials
	public static final MaterialId cloggrum = createMaterial("cloggrum");
	public static final MaterialId froststeel = createMaterial("froststeel");
	public static final MaterialId utherium = createMaterial("utherium");
	public static final MaterialId regalium = createMaterial("regalium");
	public static final MaterialId forgottenMetal = createMaterial("forgotten_metal");

	//mekanism materials
	public static final MaterialId refinedObsidian = createMaterial("refined_obsidian");
	public static final MaterialId refinedGlowstone = createMaterial("refined_glowstone");

	//psi materials
	public static final MaterialId psimetal = createMaterial("psimetal");
	public static final MaterialId ebonyPsimetal = createMaterial("ebony_psimetal");
	public static final MaterialId ivoryPsimetal = createMaterial("ivory_psimetal");

	//occultism materials
	public static final MaterialId iesnium = createMaterial("iesnium");

	//botania materials
	public static final MaterialId livingwood = createMaterial("livingwood");
	public static final MaterialId dreamwood = createMaterial("dreamwood");
	public static final MaterialId manasteel = createMaterial("manasteel");
	public static final MaterialId elementium = createMaterial("elementium");
	public static final MaterialId terrasteel = createMaterial("terrasteel");

	//mythicbotany materials
	public static final MaterialId alfsteel = createMaterial("alfsteel");

	//draconic evolution materials
	public static final MaterialId draconium = createMaterial("draconium");
	public static final MaterialId awakenedDraconium = createMaterial("draconium_awakened");

	//redstone arsenal materials
	public static final MaterialId fluxInfused = createMaterial("flux_infused");

	//avaritia materials
	public static final MaterialId crystalMatrix = createMaterial("crystal_matrix");
	public static final MaterialId neutronium = createMaterial("neutronium");
	public static final MaterialId infinity = createMaterial("infinity");
	public static final MaterialId infinityEmbellishment = createMaterial("infinity_embellishment");

	public MaterialisMaterials(DataGenerator gen) {
		super(gen);
	}

	@Override
	public String getName() {
		return "Materialis Materials";
	}

	@Override
	protected void addMaterials() {
		addMaterial(fairy, 3, ORDER_NETHER + ORDER_COMPAT, false);
		//general oredict materials
		addCompatMetalMaterial(brass, 3, ORDER_WEAPON + ORDER_COMPAT);
		addCompatMetalMaterial(aluminum, 2, ORDER_HARVEST + ORDER_COMPAT);
		addCompatMetalMaterial(nickel, 2, ORDER_HARVEST + ORDER_COMPAT);
		addCompatMetalMaterial(uranium, 2, ORDER_HARVEST + ORDER_COMPAT);
		//create materials
		addMaterial(roseQuartz, 3, ORDER_NETHER + ORDER_COMPAT, true, false, new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new NotCondition(new TagEmptyCondition(MaterialisItemTags.ROSE_QUARTZ_MATERIAL.location()))));
		addMaterial(refinedRadiance, 4, ORDER_SPECIAL + ORDER_COMPAT, false, false, new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new AndCondition(new NotCondition(new TagEmptyCondition(MaterialisItemTags.REFINED_RADIANCE_INGOT.location())), MaterialisConfigCondition.CHROMATIC_MATERIALS)));
		addMaterial(shadowSteel, 4, ORDER_SPECIAL + ORDER_COMPAT, false, false, new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new AndCondition(new NotCondition(new TagEmptyCondition(MaterialisItemTags.SHADOW_STEEL_INGOT.location())), MaterialisConfigCondition.CHROMATIC_MATERIALS)));
		//eidolon materials
		addCompatMetalMaterial(pewter, 3, ORDER_HARVEST + ORDER_COMPAT);
		addCompatMetalMaterial(arcaneGold, 3, ORDER_WEAPON + ORDER_COMPAT);
		//aquaculture materials
		addCompatMetalMaterial(neptunium, 3, ORDER_GENERAL + ORDER_COMPAT);
		//mystical world materials
		addCompatMetalMaterial(quicksilver, 2, ORDER_HARVEST + ORDER_COMPAT);
		//astral sorcery materials
		addCompatMetalMaterial(starmetal, 3, ORDER_HARVEST + ORDER_COMPAT);
		//industrial foregoing materials
		addMaterial(plastic, 2, ORDER_HARVEST + ORDER_COMPAT, true, false, new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new NotCondition(new TagEmptyCondition(MaterialisItemTags.PLASTIC_MATERIAL.location()))));
		addCompatMetalMaterial(pinkSlime, 3, ORDER_GENERAL + ORDER_COMPAT);
		addMaterial(pinkSlimeball, 6, 9, true);
		//undergarden materials
		addCompatMetalMaterial(cloggrum, 2, ORDER_HARVEST + ORDER_COMPAT);
		addCompatMetalMaterial(froststeel, 2, ORDER_WEAPON + ORDER_COMPAT);
		addCompatMetalMaterial(utherium, 3, ORDER_WEAPON + ORDER_COMPAT);
		addCompatMetalMaterial(regalium, 6, 9);
		addMaterial(forgottenMetal, 3, ORDER_GENERAL + ORDER_COMPAT, false, false, new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new NotCondition(new TagEmptyCondition("forge:ingots/forgotten_metal"))));
		//mekanism materials
		addCompatMetalMaterial(refinedObsidian, 3, ORDER_HARVEST + ORDER_COMPAT);
		addCompatMetalMaterial(refinedGlowstone, 3, ORDER_WEAPON + ORDER_COMPAT);
		//psi materials
		addCompatMetalMaterial(psimetal, 2, ORDER_SPECIAL + ORDER_COMPAT);
		addCompatMetalMaterial(ebonyPsimetal, 3, ORDER_SPECIAL + ORDER_COMPAT);
		addCompatMetalMaterial(ivoryPsimetal, 3, ORDER_SPECIAL + ORDER_COMPAT);
		//occultism materials
		addCompatMetalMaterial(iesnium, 4, ORDER_HARVEST + ORDER_COMPAT);
		//botania materials
		addMaterial(livingwood, 1, ORDER_WEAPON + ORDER_COMPAT, true, false, new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new ModLoadedCondition("botania")));
		addMaterial(dreamwood, 2, ORDER_HARVEST + ORDER_COMPAT, true, false, new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new ModLoadedCondition("botania")));
		addCompatMetalMaterial(manasteel, 2, ORDER_HARVEST + ORDER_COMPAT);
		addCompatMetalMaterial(elementium, 3, ORDER_WEAPON + ORDER_COMPAT);
		addCompatMetalMaterial(terrasteel, 4, ORDER_WEAPON + ORDER_COMPAT);
		//mythicbotany materials
		addCompatMetalMaterial(alfsteel, 4, ORDER_WEAPON + ORDER_COMPAT);
		//draconic evolution materials
		addCompatMetalMaterial(draconium, 4, ORDER_GENERAL + ORDER_COMPAT);
		addCompatMetalMaterial(awakenedDraconium, 4, ORDER_GENERAL + ORDER_COMPAT);
		//redstone arsenal materials
		addCompatMetalMaterial(fluxInfused, 3, ORDER_GENERAL + ORDER_COMPAT);
		//avaritia materials
		addMaterial(crystalMatrix, 4, ORDER_GENERAL + ORDER_BINDING, true, false, new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new NotCondition(new TagEmptyCondition("forge:ingots/crystal_matrix"))));
		addMaterial(neutronium, 4, ORDER_WEAPON + ORDER_COMPAT, true, false, new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new NotCondition(new TagEmptyCondition("forge:ingots/neutronium"))));
		addMaterial(infinity, 4, ORDER_SPECIAL + ORDER_COMPAT, true, false, new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new NotCondition(new TagEmptyCondition("forge:ingots/infinity"))));
		addMaterial(infinityEmbellishment, 4, ORDER_SPECIAL + ORDER_COMPAT, false, true, new OrCondition(ConfigEnabledCondition.FORCE_INTEGRATION_MATERIALS, new NotCondition(new TagEmptyCondition("forge:ingots/infinity"))));
	}

	private static MaterialId createMaterial(String name) {
		return new MaterialId(new ResourceLocation(Materialis.modID, name));
	}

	public static class MaterialisMaterialTraits extends AbstractMaterialTraitDataProvider {

		public MaterialisMaterialTraits(DataGenerator gen, AbstractMaterialDataProvider materials) {
			super(gen, materials);
		}

		@Override
		public String getName() {
			return "Materialis Material Traits";
		}

		@Override
		protected void addMaterialTraits() {
			addDefaultTraits(fairy, MaterialisModifiers.daredevilModifier);
			//general oredict materials
			addDefaultTraits(brass, MaterialisModifiers.polishedModifier);
			addDefaultTraits(aluminum, MaterialisModifiers.featherweightModifier);
			addDefaultTraits(nickel, MaterialisModifiers.refuelingModifier);
			addDefaultTraits(uranium, MaterialisModifiers.halfLifeModifier);
			//create materials
			addDefaultTraits(roseQuartz, MaterialisModifiers.enhancedQuartzModifier);
			addDefaultTraits(refinedRadiance, MaterialisModifiers.residualLightModifier);
			addDefaultTraits(shadowSteel, MaterialisModifiers.voidingModifier);
			//eidolon materials
			addDefaultTraits(pewter, MaterialisModifiers.inertiaModifier);
			addDefaultTraits(arcaneGold, MaterialisModifiers.arcaneModifier);
			//aquaculture materials
			addDefaultTraits(neptunium, MaterialisModifiers.neptunesBlessingModifier);
			//mystical world materials
			addDefaultTraits(quicksilver, MaterialisModifiers.decayModifier);
			//astral sorcery materials
			addDefaultTraits(starmetal, MaterialisModifiers.nocturnalModifier);
			//industrial foregoing materials
			addDefaultTraits(plastic, MaterialisModifiers.feebleModifier);
			addDefaultTraits(pinkSlime, MaterialisModifiers.overeatingModifier, TinkerModifiers.overslime);
			noTraits(pinkSlimeball);
			//undergarden materials
			addDefaultTraits(cloggrum, MaterialisModifiers.economicalModifier);
			addDefaultTraits(froststeel, MaterialisModifiers.freezingModifier);
			addDefaultTraits(utherium, MaterialisModifiers.cleansingModifier);
			noTraits(regalium);
			addDefaultTraits(forgottenMetal, MaterialisModifiers.oldTimerModifier, MaterialisModifiers.underlordModifier);
			//mekanism materials
			addDefaultTraits(refinedObsidian, MaterialisModifiers.shortSightedModifier);
			addDefaultTraits(refinedGlowstone, MaterialisModifiers.auxiliaryPowerModifier);
			//psi materials
			addDefaultTraits(psimetal, MaterialisModifiers.psionizingRadiationModifier);
			addDefaultTraits(ebonyPsimetal, MaterialisModifiers.lesserPsionizingRadiationModifier, MaterialisModifiers.psichoKillerModifier);
			addDefaultTraits(ivoryPsimetal, MaterialisModifiers.lesserPsionizingRadiationModifier, MaterialisModifiers.psichoDiggerModifier);
			//occultism materials
			addDefaultTraits(iesnium, MaterialisModifiers.otherworldly2Modifier);
			//botania materials
			addDefaultTraits(livingwood, MaterialisModifiers.manaripperModifier);
			addTraits(livingwood, LimbMaterialStats.ID, MaterialisModifiers.manadrawModifier);
			addTraits(livingwood, GripMaterialStats.ID, MaterialisModifiers.manadrawModifier);
			addDefaultTraits(dreamwood, MaterialisModifiers.manaburnerModifier);
			addTraits(dreamwood, LimbMaterialStats.ID, MaterialisModifiers.manashotModifier);
			addTraits(dreamwood, GripMaterialStats.ID, MaterialisModifiers.manashotModifier);
			addDefaultTraits(manasteel, new ModifierEntry(MaterialisModifiers.manashieldModifier, 2));
			addDefaultTraits(elementium, MaterialisModifiers.manashieldModifier, MaterialisModifiers.pixiecallerModifier);
			addDefaultTraits(terrasteel, MaterialisModifiers.manashieldModifier, MaterialisModifiers.terrabeamModifier);
			//mythicbotany materials
			addDefaultTraits(alfsteel, MaterialisModifiers.manashieldModifier, MaterialisModifiers.elvenBeamModifier);
			//draconic evolution materials
			addDefaultTraits(draconium, new ModifierEntry(MaterialisModifiers.powerHungryModifier, 1), new ModifierEntry(MaterialisModifiers.fluxshieldModifier, 2));
			addDefaultTraits(awakenedDraconium, new ModifierEntry(MaterialisModifiers.powerHungryModifier, 2), new ModifierEntry(MaterialisModifiers.fluxripperModifier, 1), new ModifierEntry(MaterialisModifiers.fluxburnerModifier, 1));
			addTraits(awakenedDraconium, LimbMaterialStats.ID, new ModifierEntry(MaterialisModifiers.powerHungryModifier, 2), new ModifierEntry(MaterialisModifiers.fluxripperModifier, 1), new ModifierEntry(MaterialisModifiers.fluxshotModifier, 1));
			addTraits(awakenedDraconium, GripMaterialStats.ID, new ModifierEntry(MaterialisModifiers.powerHungryModifier, 2), new ModifierEntry(MaterialisModifiers.fluxripperModifier, 1), new ModifierEntry(MaterialisModifiers.fluxshotModifier, 1));
			//redstone arsenal materials
			addDefaultTraits(fluxInfused, MaterialisModifiers.fluxripperModifier, MaterialisModifiers.fluxburnerModifier);
			addTraits(fluxInfused, LimbMaterialStats.ID, MaterialisModifiers.fluxripperModifier, MaterialisModifiers.fluxdrawModifier);
			addTraits(fluxInfused, GripMaterialStats.ID, MaterialisModifiers.fluxripperModifier, MaterialisModifiers.fluxdrawModifier);
			//avaritia materials
			addDefaultTraits(crystalMatrix, MaterialisModifiers.crystallineModifier);
			addDefaultTraits(neutronium, MaterialisModifiers.supermassiveModifier);
			addDefaultTraits(infinity, MaterialisModifiers.cataclysmicModifier, MaterialisModifiers.cosmicUnbreakableModifier, MaterialisModifiers.instakillModifier, MaterialisModifiers.instamineModifier, MaterialisModifiers.bedrockBreakerModifier, MaterialisModifiers.cosmicLuckModifier);
			noTraits(infinityEmbellishment);
		}
	}

	public static class MaterialisMaterialStats extends AbstractMaterialStatsDataProvider {

		public MaterialisMaterialStats(DataGenerator gen, AbstractMaterialDataProvider materials) {
			super(gen, materials);
		}

		@Override
		public String getName() {
			return "Materialis Material Stats";
		}

		@Override
		protected void addMaterialStats() {
			addMaterialStats(fairy, new HeadMaterialStats(250, 7.5f, Tiers.IRON, 2f), new HandleMaterialStats(0.9f, 1.15f, 1f, 1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(250, 0.05f, -0.05f, 0.0f), new GripMaterialStats(0.9f, 0.0f, 2f));
			//general oredict materials
			addMaterialStats(brass, new HeadMaterialStats(450, 7.5f, Tiers.IRON, 2f), new HandleMaterialStats(0.9f, 1.1f, 1.1f, 1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(450, -0.15f, 0.1f, -0.05f), new GripMaterialStats(0.9f, -0.05f, 2f));
			addMaterialStats(aluminum, new HeadMaterialStats(220, 5f, Tiers.IRON, 1f), new HandleMaterialStats(0.9f, 1.1f, 1.1f, 0.9f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(nickel, new HeadMaterialStats(520, 5f, Tiers.IRON, 1.5f), new HandleMaterialStats(1f, 0.9f, 1f, 1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(520, -0.15f, 0.05f, 0.05f), new GripMaterialStats(1f, 0.05f, 1.5f));
			addMaterialStats(uranium, new HeadMaterialStats(689, 7f, Tiers.IRON, 2f), new HandleMaterialStats(0.9f, 0.9f, 0.9f, 1.1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(689, 0f, 0.1f, -0.2f), new GripMaterialStats(0.9f, -0.2f, 2f));
			//create materials
			addMaterialStats(roseQuartz, new HeadMaterialStats(175, 4f, Tiers.IRON, 3f), new HandleMaterialStats(0.5f, 1f, 1f, 1.1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(refinedRadiance, new HeadMaterialStats(999, 9f, Tiers.NETHERITE, 3f), new HandleMaterialStats(0.5f, 1f, 1f, 1.3f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(999, -0.3f, 0.25f, 0.1f), new GripMaterialStats(0.5f, 0.1f, 3f));
			addMaterialStats(shadowSteel, new HeadMaterialStats(1300, 10f, Tiers.NETHERITE, 3.5f), new HandleMaterialStats(1f, 1f, 1.1f, 1.1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(1300, 0.05f, 0.1f, -0.1f), new GripMaterialStats(1f, -0.1f, 3.5f));
			//eidolon materials
			addMaterialStats(pewter, new HeadMaterialStats(325, 6.5f, Tiers.IRON, 1f), new HandleMaterialStats(1f, 1f, 0.9f, 1.05f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(arcaneGold, new HeadMaterialStats(200, 9f, Tiers.IRON, 2f), new HandleMaterialStats(0.8f, 1.1f, 1.1f, 1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(200, 0.15f, -0.2f, 0.1f), new GripMaterialStats(0.8f, 0.1f, 2f));
			//aquaculture materials
			addMaterialStats(neptunium, new HeadMaterialStats(1450, 8f, Tiers.DIAMOND, 3f), new HandleMaterialStats(1.1f, 0.9f, 1f, 0.9f), ExtraMaterialStats.DEFAULT);
			//mystical world materials
			addMaterialStats(quicksilver, new HeadMaterialStats(75, 6f, Tiers.DIAMOND, 2f), new HandleMaterialStats(0.25f, 1.2f, 1.2f, 1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(75, 0.1f, 0.0f, 0.15f), new GripMaterialStats(0.25f, 0.15f, 2f));
			//astral sorcery materials
			addMaterialStats(starmetal, new HeadMaterialStats(340, 6f, Tiers.IRON, 1.5f), new HandleMaterialStats(1.1f, 1f, 1f, 1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(340, -0.2f, 0.1f, 0.1f), new GripMaterialStats(1.1f, 0.0f, 1.5f));
			//industrial foregoing materials
			addMaterialStats(plastic, new HeadMaterialStats(200, 4f, Tiers.IRON, 1f), new HandleMaterialStats(0.8f, 1f, 1.15f, 1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(200, 0.3f, -0.3f, 0.05f), new GripMaterialStats(0.8f, 0.05f, 1f));
			addMaterialStats(pinkSlime, new HeadMaterialStats(830, 7f, Tiers.DIAMOND, 2f), new HandleMaterialStats(1f, 1.1f, 1.1f, 1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(pinkSlimeball);
			//undergarden materials
			addMaterialStats(cloggrum, new HeadMaterialStats(286, 5f, Tiers.IRON, 1.5f), new HandleMaterialStats(1f, 1.1f, 0.8f, 1.1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(286, -0.05f, 0.1f, 0.0f), new GripMaterialStats(1f, 0.0f, 1.5f));
			addMaterialStats(froststeel, new HeadMaterialStats(575, 6f, Tiers.DIAMOND, 2f), new HandleMaterialStats(1.2f, 0.9f, 1f, 0.9f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(575, 0.0f, 0.1f, -0.05f), new GripMaterialStats(1.2f, -0.05f, 2f));
			addMaterialStats(utherium, new HeadMaterialStats(852, 7f, Tiers.NETHERITE, 2.5f), new HandleMaterialStats(1.1f, 1f, 0.9f, 1.1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(852, 0.15f, 0.0f, -0.1f), new GripMaterialStats(1.1f, -0.1f, 2.5f));
			addMaterialStats(regalium);
			addMaterialStats(forgottenMetal, new HeadMaterialStats(921, 7.5f, Tiers.NETHERITE, 3f), new HandleMaterialStats(1.2f, 0.9f, 1f, 0.9f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(921, -0.15f, 0.2f, 0.0f), new GripMaterialStats(1.2f, 0.0f, 3f));
			//mekanism materials
			addMaterialStats(refinedObsidian, new HeadMaterialStats(900, 9f, Tiers.DIAMOND, 2f), new HandleMaterialStats(1.3f, 0.9f, 1f, 0.9f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(refinedGlowstone, new HeadMaterialStats(200, 7f, Tiers.IRON, 2.5f), new HandleMaterialStats(0.7f, 1.1f, 1f, 1f), ExtraMaterialStats.DEFAULT);
			//psi materials
			addMaterialStats(psimetal, new HeadMaterialStats(330, 6f, Tiers.DIAMOND, 2f), new HandleMaterialStats(1f, 1.05f, 1f, 1.05f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(ebonyPsimetal, new HeadMaterialStats(600, 5f, Tiers.NETHERITE, 3f), new HandleMaterialStats(0.7f, 0.8f, 1f, 1.2f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(ivoryPsimetal, new HeadMaterialStats(600, 8f, Tiers.NETHERITE, 1.5f), new HandleMaterialStats(1.1f, 1.2f, 0.9f, 0.9f), ExtraMaterialStats.DEFAULT);
			//occultism materials
			addMaterialStats(iesnium, new HeadMaterialStats(921, 7f, Tiers.DIAMOND, 2f), new HandleMaterialStats(0.8f, 1.1f, 1f, 1f), ExtraMaterialStats.DEFAULT);
			//botania materials
			addMaterialStats(livingwood, new HeadMaterialStats(70, 3f, Tiers.STONE, 1f), new HandleMaterialStats(1.1f, 1f, 1f, 1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(70, 0.0f, 0.05f, 0.0f), new GripMaterialStats(1.1f, 0.0f, 1f));
			addMaterialStats(dreamwood, new HeadMaterialStats(160, 4f, Tiers.STONE, 1.2f), new HandleMaterialStats(1f, 1.1f, 1f, 1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(160, 0.05f, 0.0f, 0.0f), new GripMaterialStats(1f, 0.0f, 1.2f));
			addMaterialStats(manasteel, new HeadMaterialStats(300, 6.2f, Tiers.DIAMOND, 1.5f), new HandleMaterialStats(1.1f, 1.1f, 1f, 1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(300, -0.2f, 0.1f, 0.1f), new GripMaterialStats(1.1f, 0.1f, 1.5f));
			addMaterialStats(elementium, new HeadMaterialStats(720, 5f, Tiers.DIAMOND, 2f), new HandleMaterialStats(0.8f, 1.1f, 1.2f, 0.9f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(720, 0.1f, 0.0f, 0.05f), new GripMaterialStats(0.8f, 0.05f, 2f));
			addMaterialStats(terrasteel, new HeadMaterialStats(1000, 9f, Tiers.NETHERITE, 3f), new HandleMaterialStats(0.9f, 1.1f, 1.1f, 1.15f), ExtraMaterialStats.DEFAULT);
			//mythicbotany materials
			addMaterialStats(alfsteel, new HeadMaterialStats(1430, 9f, Tiers.NETHERITE, 3.2f), new HandleMaterialStats(0.9f, 1f, 1.15f, 1f), ExtraMaterialStats.DEFAULT);
			//draconic evolution materials
			addMaterialStats(draconium, new HeadMaterialStats(1000, 7f, Tiers.NETHERITE, 2f), new HandleMaterialStats(0.8f, 1.1f, 1f, 1.1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(1000, -0.2f, 0.2f, -0.05f), new GripMaterialStats(0.8f, -0.05f, 2f));
			addMaterialStats(awakenedDraconium, new HeadMaterialStats(1500, 9f, Tiers.NETHERITE, 3f), new HandleMaterialStats(1f, 1f, 1.1f, 1f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(1500, -0.1f, 0.2f, -0.1f), new GripMaterialStats(1.0f, -0.1f, 3f));
			//redstone arsenal materials
			addMaterialStats(fluxInfused, new HeadMaterialStats(400, 6f, Tiers.NETHERITE, 2.5f), new HandleMaterialStats(1f, 0.7f, 1.1f, 0.7f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(400, -0.15f, 0.1f, 0.05f), new GripMaterialStats(1.0f, 0.05f, 2.5f));
			//avaritia materials
			addMaterialStats(crystalMatrix, ExtraMaterialStats.DEFAULT);
			addMaterialStats(neutronium, new HeadMaterialStats(100, 7f, Tiers.NETHERITE, 2.75f), new HandleMaterialStats(1.2f, 1f, 0.7f, 1.2f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(100, -0.4f, 0.25f, -0.1f), new GripMaterialStats(1.2f, -0.1f, 2.75f));
			addMaterialStats(infinity, new HeadMaterialStats(9999, 999f, InfinityTier.instance, 99f), new HandleMaterialStats(9.99f, 9.99f, 9.99f, 9.99f), ExtraMaterialStats.DEFAULT, new LimbMaterialStats(9999, 99.9f, 99.9f, 1.0f), new GripMaterialStats(9.99f, 1.0f, 99f));
			addMaterialStats(infinityEmbellishment);
		}
	}
}
