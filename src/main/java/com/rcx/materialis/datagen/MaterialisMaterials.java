package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.modifiers.MaterialisModifiers;

import net.minecraft.data.DataGenerator;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.materials.MaterialId;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.stats.ExtraMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

public class MaterialisMaterials extends AbstractMaterialDataProvider {

	public static final MaterialId fairy = createMaterial("fairy");

	//general oredict materials
	public static final MaterialId brass = createMaterial("brass");
	public static final MaterialId aluminum = createMaterial("aluminum");
	public static final MaterialId nickel = createMaterial("nickel");
	public static final MaterialId platinum = createMaterial("platinum");
	public static final MaterialId uranium = createMaterial("uranium");
	public static final MaterialId osmium = createMaterial("osmium");
	public static final MaterialId tungsten = createMaterial("tungsten");
	public static final MaterialId invar = createMaterial("invar");

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

	//undergarden materials
	public static final MaterialId cloggrum = createMaterial("cloggrum");
	public static final MaterialId froststeel = createMaterial("froststeel");
	public static final MaterialId utherium = createMaterial("utherium");
	public static final MaterialId forgottenMetal = createMaterial("forgotten_metal");

	//mekanism materials
	public static final MaterialId refinedObsidian = createMaterial("refined_obsidian");
	public static final MaterialId refinedGlowstone = createMaterial("refined_glowstone");

	//psi materials
	public static final MaterialId psimetal = createMaterial("psimetal");
	public static final MaterialId ebonyPsimetal = createMaterial("ebony_psimetal");
	public static final MaterialId ivoryPsimetal = createMaterial("ivory_psimetal");

	public MaterialisMaterials(DataGenerator gen) {
		super(gen);
	}

	@Override
	public String getName() {
		return "Materialis Materials";
	}

	@Override
	protected void addMaterials() {
		addMetalMaterial(fairy, 3, ORDER_NETHER, MaterialisResources.FAIRY_FLUID.FLUID.get(), 0xFF87BC);
		//general oredict materials
		addCompatMetalMaterial(brass, 3, ORDER_WEAPON, TinkerFluids.moltenBrass.get(), 0xFFD359);
		addCompatMetalMaterial(aluminum, 2, ORDER_HARVEST, TinkerFluids.moltenAluminum.get(), 0xE6B7BF);
		addCompatMetalMaterial(nickel, 2, ORDER_HARVEST, TinkerFluids.moltenNickel.get(), 0xF9EA98);
		addCompatMetalMaterial(platinum, 3, ORDER_HARVEST, TinkerFluids.moltenPlatinum.get(), 0xA1FFFF);
		addCompatMetalMaterial(uranium, 2, ORDER_HARVEST, TinkerFluids.moltenUranium.get(), 0x42BE30);
		addCompatMetalMaterial(osmium, 2, ORDER_WEAPON, TinkerFluids.moltenOsmium.get(), 0xCDE8FD);
		addCompatMetalMaterial(tungsten, 3, ORDER_WEAPON, TinkerFluids.moltenTungsten.get(), 0xA7A88F);
		addCompatMetalMaterial(invar, 3, ORDER_HARVEST, TinkerFluids.moltenInvar.get(), 0xBAE6D5);
		//create materials
		addMaterial(roseQuartz, 3, ORDER_NETHER, Fluids.EMPTY, 0, true, 0xFF8C80, new ModLoadedCondition("create"));
		addCompatMetalMaterial(refinedRadiance, 4, ORDER_SPECIAL, MaterialisResources.REFINED_RADIANCE_FLUID.FLUID.get(), 0xFFFFFF);
		addCompatMetalMaterial(shadowSteel, 4, ORDER_SPECIAL, MaterialisResources.SHADOW_STEEL_FLUID.FLUID.get(), 0x635D71);
		//eidolon materials
		addCompatMetalMaterial(pewter, 3, ORDER_HARVEST, TinkerFluids.moltenPewter.get(), 0xA1A097);
		addCompatMetalMaterial(arcaneGold, 3, ORDER_WEAPON, MaterialisResources.ARCANE_GOLD_FLUID.FLUID.get(), 0xFFC069);
		//aquaculture materials
		addCompatMetalMaterial(neptunium, 3, ORDER_GENERAL, MaterialisResources.NEPTUNIUM_FLUID.FLUID.get(), 0x17F1B6);
		//mystical world materials
		addCompatMetalMaterial(quicksilver, 2, ORDER_HARVEST, MaterialisResources.QUICKSILVER_FLUID.FLUID.get(), 0xA9C2C4);
		//astral sorcery materials
		addCompatMetalMaterial(starmetal, 3, ORDER_HARVEST, MaterialisResources.STARMETAL_FLUID.FLUID.get(), 0x003CC9);
		//industrial foregoing materials
		addMaterial(plastic, 2, ORDER_HARVEST, Fluids.EMPTY, 0, true, 0xD9D9D9, new NotCondition(new TagEmptyCondition("forge:plastic")));
		addCompatMetalMaterial(pinkSlime, 3, ORDER_GENERAL, MaterialisResources.PINK_SLIME_FLUID.FLUID.get(), 0xFF9FEF);
		//undergarden materials
		addCompatMetalMaterial(cloggrum, 2, ORDER_HARVEST, MaterialisResources.CLOGGRUM_FLUID.FLUID.get(), 0x9C8878);
		addCompatMetalMaterial(froststeel, 2, ORDER_WEAPON, MaterialisResources.FROSTSTEEL_FLUID.FLUID.get(), 0x95BDE3);
		addCompatMetalMaterial(utherium, 3, ORDER_WEAPON, MaterialisResources.UTHERIUM_FLUID.FLUID.get(), 0xEB515B);
		addMaterial(forgottenMetal, 3, ORDER_GENERAL, Fluids.EMPTY, 0, false, 0x6CD7AA, new NotCondition(new TagEmptyCondition("forge:ingots/forgotten_metal")));
		//mekanism materials
		addCompatMetalMaterial(refinedObsidian, 3, ORDER_HARVEST, MaterialisResources.REFINED_OBSIDIAN_FLUID.FLUID.get(), 0xB78FD2);
		addCompatMetalMaterial(refinedGlowstone, 3, ORDER_WEAPON, MaterialisResources.REFINED_GLOWSTONE_FLUID.FLUID.get(), 0xFFE55C);
		//psi materials
		addCompatMetalMaterial(psimetal, 2, ORDER_SPECIAL, MaterialisResources.PSIMETAL_FLUID.FLUID.get(), 0xB6A9E7);
		addCompatMetalMaterial(ebonyPsimetal, 3, ORDER_SPECIAL, MaterialisResources.EBONY_PSIMETAL_FLUID.FLUID.get(), 0x3D3838);
		addCompatMetalMaterial(ivoryPsimetal, 3, ORDER_SPECIAL, MaterialisResources.IVORY_PSIMETAL_FLUID.FLUID.get(), 0xF6F9ED);
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
			addDefaultTraits(fairy, MaterialisModifiers.daredevilModifier.get());
			//general oredict materials
			addDefaultTraits(brass, MaterialisModifiers.polishedModifier.get());
			addDefaultTraits(aluminum, MaterialisModifiers.featherweightModifier.get());
			addDefaultTraits(nickel, MaterialisModifiers.refuelingModifier.get());
			addDefaultTraits(platinum, MaterialisModifiers.brittleModifier.get());
			addDefaultTraits(uranium, MaterialisModifiers.halfLifeModifier.get());
			addDefaultTraits(osmium, MaterialisModifiers.adrenalineModifier.get());
			addDefaultTraits(tungsten, MaterialisModifiers.workHardenedModifier.get());
			addDefaultTraits(invar, MaterialisModifiers.quenchingModifier.get());
			//create materials
			addDefaultTraits(roseQuartz, MaterialisModifiers.enhancedQuartzModifier.get());
			addDefaultTraits(refinedRadiance, MaterialisModifiers.residualLightModifier.get());
			addDefaultTraits(shadowSteel, MaterialisModifiers.voidingModifier.get());
			//eidolon materials
			addDefaultTraits(pewter, MaterialisModifiers.inertiaModifier.get());
			addDefaultTraits(arcaneGold, MaterialisModifiers.arcaneModifier.get());
			//aquaculture materials
			addDefaultTraits(neptunium, MaterialisModifiers.neptunesBlessingModifier.get());
			//mystical world materials
			addDefaultTraits(quicksilver, MaterialisModifiers.decayModifier.get());
			//astral sorcery materials
			addDefaultTraits(starmetal, MaterialisModifiers.nocturnalModifier.get());
			//industrial foregoing materials
			addDefaultTraits(plastic, MaterialisModifiers.feebleModifier.get());
			addDefaultTraits(pinkSlime, MaterialisModifiers.overweightModifier.get(), TinkerModifiers.overslime.get());
			//undergarden materials
			addDefaultTraits(cloggrum, MaterialisModifiers.economicalModifier.get());
			addDefaultTraits(froststeel, MaterialisModifiers.freezingModifier.get());
			addDefaultTraits(utherium, MaterialisModifiers.cleansingModifier.get());
			addDefaultTraits(forgottenMetal, MaterialisModifiers.oldTimerModifier.get(), MaterialisModifiers.underlordModifier.get());
			//mekanism materials
			addDefaultTraits(refinedObsidian, MaterialisModifiers.shortSightedModifier.get());
			addDefaultTraits(refinedGlowstone, MaterialisModifiers.auxiliaryPowerModifier.get());
			//psi materials
			addDefaultTraits(psimetal, MaterialisModifiers.psionizingRadiationModifier.get());
			addDefaultTraits(ebonyPsimetal, MaterialisModifiers.psionizingRadiationModifier.get(), MaterialisModifiers.psichoKillerModifier.get());
			addDefaultTraits(ivoryPsimetal, MaterialisModifiers.psionizingRadiationModifier.get(), MaterialisModifiers.psichoDiggerModifier.get());
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
			addMaterialStats(fairy, new HeadMaterialStats(250, 7.5f, 2, 4f), new HandleMaterialStats(1.1f, 1.3f, 1f, 1f), ExtraMaterialStats.DEFAULT);
			//general oredict materials
			addMaterialStats(brass, new HeadMaterialStats(450, 9f, 2, 4f), new HandleMaterialStats(1f, 1.1f, 1.1f, 1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(aluminum, new HeadMaterialStats(220, 5f, 2, 5f), new HandleMaterialStats(0.9f, 1.1f, 1.1f, 1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(nickel, new HeadMaterialStats(520, 5f, 2, 3f), new HandleMaterialStats(1f, 0.9f, 1f, 1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(platinum, new HeadMaterialStats(100, 13f, 3, 3f), new HandleMaterialStats(0.7f, 1.4f, 1.3f, 0.9f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(uranium, new HeadMaterialStats(689, 9f, 2, 4f), new HandleMaterialStats(1.3f, 0.9f, 0.9f, 1.1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(osmium, new HeadMaterialStats(500, 7f, 2, 3f), new HandleMaterialStats(1.1f, 1f, 1f, 1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(tungsten, new HeadMaterialStats(740, 8f, 3, 5f), new HandleMaterialStats(1.1f, 1f, 0.9f, 1.2f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(invar, new HeadMaterialStats(710, 7f, 3, 4f), new HandleMaterialStats(1.2f, 1f, 0.9f, 1f), ExtraMaterialStats.DEFAULT);
			//create materials
			addMaterialStats(roseQuartz, new HeadMaterialStats(175, 6f, 2, 5f), new HandleMaterialStats(0.5f, 1f, 1f, 1.3f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(refinedRadiance, new HeadMaterialStats(999, 11f, 4, 5f), new HandleMaterialStats(0.5f, 1f, 1f, 1.4f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(shadowSteel, new HeadMaterialStats(1300, 12f, 4, 6f), new HandleMaterialStats(1.1f, 1f, 1.3f, 1.3f), ExtraMaterialStats.DEFAULT);
			//eidolon materials
			addMaterialStats(pewter, new HeadMaterialStats(325, 6.5f, 2, 2f), new HandleMaterialStats(1.1f, 1.1f, 0.8f, 1.1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(arcaneGold, new HeadMaterialStats(200, 11f, 2, 4f), new HandleMaterialStats(0.8f, 1.1f, 1.1f, 1f), ExtraMaterialStats.DEFAULT);
			//aquaculture materials
			addMaterialStats(neptunium, new HeadMaterialStats(1450, 9f, 3, 6f), new HandleMaterialStats(1f, 1.3f, 1f, 1f), ExtraMaterialStats.DEFAULT);
			//mystical world materials
			addMaterialStats(quicksilver, new HeadMaterialStats(75, 6f, 3, 2.5f), new HandleMaterialStats(0.25f, 1.2f, 1.2f, 1f), ExtraMaterialStats.DEFAULT);
			//astral sorcery materials
			addMaterialStats(starmetal, new HeadMaterialStats(340, 6f, 2, 3f), new HandleMaterialStats(1.1f, 1f, 1f, 1f), ExtraMaterialStats.DEFAULT);
			//industrial foregoing materials
			addMaterialStats(plastic, new HeadMaterialStats(200, 6f, 2, 2f), new HandleMaterialStats(0.8f, 1f, 1.2f, 1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(pinkSlime, new HeadMaterialStats(830, 7f, 3, 4f), new HandleMaterialStats(1f, 0.9f, 0.9f, 1.2f), ExtraMaterialStats.DEFAULT);
			//undergarden materials
			addMaterialStats(cloggrum, new HeadMaterialStats(286, 5f, 2, 4f), new HandleMaterialStats(1f, 1.1f, 0.8f, 1.1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(froststeel, new HeadMaterialStats(575, 6f, 3, 3f), new HandleMaterialStats(1.2f, 0.9f, 1f, 0.9f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(utherium, new HeadMaterialStats(852, 8f, 4, 3.5f), new HandleMaterialStats(1.1f, 1f, 0.9f, 1.1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(forgottenMetal, new HeadMaterialStats(921, 10f, 4, 5f), new HandleMaterialStats(1f, 1.2f, 1f, 1.2f), ExtraMaterialStats.DEFAULT);
			//mekanism materials
			addMaterialStats(refinedObsidian, new HeadMaterialStats(900, 11f, 3, 6f), new HandleMaterialStats(1.3f, 0.9f, 1f, 0.9f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(refinedGlowstone, new HeadMaterialStats(200, 8f, 2, 5f), new HandleMaterialStats(0.7f, 1.1f, 1f, 1f), ExtraMaterialStats.DEFAULT);
			//psi materials
			addMaterialStats(psimetal, new HeadMaterialStats(440, 6f, 3, 2.5f), new HandleMaterialStats(0.9f, 1.1f, 1.1f, 1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(ebonyPsimetal, new HeadMaterialStats(900, 8f, 4, 5f), new HandleMaterialStats(0.7f, 0.8f, 1f, 1.2f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(ivoryPsimetal, new HeadMaterialStats(900, 10f, 4, 3f), new HandleMaterialStats(1.1f, 1.1f, 0.8f, 1f), ExtraMaterialStats.DEFAULT);
		}
	}
}
