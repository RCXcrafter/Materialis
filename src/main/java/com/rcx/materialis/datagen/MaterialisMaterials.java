package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.modifiers.MaterialisModifiers;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
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
	//public static final MaterialId nickel = createMaterial("nickel");
	//public static final MaterialId platinum = createMaterial("platinum");
	//public static final MaterialId tin = createMaterial("tin");
	//public static final MaterialId zinc = createMaterial("zinc");
	public static final MaterialId uranium = createMaterial("uranium");
	//public static final MaterialId osmium = createMaterial("osmium");
	public static final MaterialId tungsten = createMaterial("tungsten");
	//public static final MaterialId invar = createMaterial("invar");

	//create materials
	public static final MaterialId rose_quartz = createMaterial("rose_quartz");
	public static final MaterialId refined_radiance = createMaterial("refined_radiance");
	public static final MaterialId shadow_steel = createMaterial("shadow_steel");

	//eidolon materials
	public static final MaterialId pewter = createMaterial("pewter");
	public static final MaterialId arcane_gold = createMaterial("arcane_gold");

	//aquaculture materials
	public static final MaterialId neptunium = createMaterial("neptunium");

	//mystical world materials
	public static final MaterialId quicksilver = createMaterial("quicksilver");

	//astral sorcery materials
	public static final MaterialId starmetal = createMaterial("starmetal");

	//industrial foregoing materials
	public static final MaterialId plastic = createMaterial("plastic");
	public static final MaterialId pink_slime = createMaterial("pink_slime");

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
		addMetalMaterial(brass, 3, ORDER_WEAPON, TinkerFluids.moltenBrass.get(), 0xFFD359);
		addMetalMaterial(aluminum, 2, ORDER_HARVEST, TinkerFluids.moltenAluminum.get(), 0xE6B7BF);
		addMetalMaterial(uranium, 2, ORDER_HARVEST, TinkerFluids.moltenUranium.get(), 0x42BE30);
		addMetalMaterial(tungsten, 3, ORDER_WEAPON, TinkerFluids.moltenTungsten.get(), 0xA7A88F);
		//create materials
		addMaterialNoFluid(rose_quartz, 3, ORDER_NETHER, true, 0xFF8C80);
		addMetalMaterial(refined_radiance, 4, ORDER_SPECIAL, MaterialisResources.REFINED_RADIANCE_FLUID.FLUID.get(), 0xFFFFFF);
		addMetalMaterial(shadow_steel, 4, ORDER_SPECIAL, MaterialisResources.SHADOW_STEEL_FLUID.FLUID.get(), 0x635D71);
		//eidolon materials
		addMetalMaterial(pewter, 3, ORDER_HARVEST, TinkerFluids.moltenPewter.get(), 0xA1A097);
		addMetalMaterial(arcane_gold, 3, ORDER_WEAPON, MaterialisResources.ARCANE_GOLD_FLUID.FLUID.get(), 0xFFC069);
		//aquaculture materials
		addMetalMaterial(neptunium, 3, ORDER_GENERAL, MaterialisResources.NEPTUNIUM_FLUID.FLUID.get(), 0x17F1B6);
		//mystical world materials
		addMetalMaterial(quicksilver, 2, ORDER_HARVEST, MaterialisResources.QUICKSILVER_FLUID.FLUID.get(), 0xA9C2C4);
		//astral sorcery materials
		addMetalMaterial(starmetal, 3, ORDER_HARVEST, MaterialisResources.STARMETAL_FLUID.FLUID.get(), 0x003CC9);
		//industrial foregoing materials
		addMaterialNoFluid(plastic, 2, ORDER_HARVEST, true, 0xD9D9D9);
		addMetalMaterial(pink_slime, 3, ORDER_GENERAL, MaterialisResources.PINK_SLIME_FLUID.FLUID.get(), 0xFF9FEF);
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
			//addDefaultTraits(nickel, MaterialisModifiers.daredevilModifier.get());
			//addDefaultTraits(platinum, MaterialisModifiers.daredevilModifier.get());
			//addDefaultTraits(tin, MaterialisModifiers.daredevilModifier.get());
			//addDefaultTraits(zinc, MaterialisModifiers.daredevilModifier.get());
			addDefaultTraits(uranium, MaterialisModifiers.halfLifeModifier.get());
			//addDefaultTraits(osmium, MaterialisModifiers.daredevilModifier.get());
			addDefaultTraits(tungsten, MaterialisModifiers.workHardenedModifier.get());
			//addDefaultTraits(invar, MaterialisModifiers.daredevilModifier.get());
			//create materials
			addDefaultTraits(rose_quartz, MaterialisModifiers.enhancedQuartzModifier.get());
			addDefaultTraits(refined_radiance, MaterialisModifiers.residualLightModifier.get());
			addDefaultTraits(shadow_steel, MaterialisModifiers.voidingModifier.get());
			//eidolon materials
			addDefaultTraits(pewter, MaterialisModifiers.inertiaModifier.get());
			addDefaultTraits(arcane_gold, MaterialisModifiers.arcaneModifier.get());
			//aquaculture materials
			addDefaultTraits(neptunium, MaterialisModifiers.neptunesBlessingModifier.get());
			//mystical world materials
			addDefaultTraits(quicksilver, MaterialisModifiers.decayModifier.get());
			//astral sorcery materials
			addDefaultTraits(starmetal, MaterialisModifiers.nocturnalModifier.get());
			//industrial foregoing materials
			addDefaultTraits(plastic, MaterialisModifiers.feebleModifier.get());
			addDefaultTraits(pink_slime, MaterialisModifiers.overweightModifier.get(), TinkerModifiers.overslime.get());
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
			//addMaterialStats(nickel, new HeadMaterialStats(150, 6f, 2, 1.5f), new HandleMaterialStats(0.6f, 1.1f, 1.1f, 1f), ExtraMaterialStats.DEFAULT);
			//addMaterialStats(platinum, new HeadMaterialStats(150, 6f, 2, 1.5f), new HandleMaterialStats(0.6f, 1.1f, 1.1f, 1f), ExtraMaterialStats.DEFAULT);
			//addMaterialStats(tin, new HeadMaterialStats(150, 6f, 2, 1.5f), new HandleMaterialStats(0.6f, 1.1f, 1.1f, 1f), ExtraMaterialStats.DEFAULT);
			//addMaterialStats(zinc, new HeadMaterialStats(150, 6f, 2, 1.5f), new HandleMaterialStats(0.6f, 1.1f, 1.1f, 1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(uranium, new HeadMaterialStats(689, 9f, 2, 4f), new HandleMaterialStats(1.3f, 0.9f, 0.9f, 1.1f), ExtraMaterialStats.DEFAULT);
			//addMaterialStats(osmium, new HeadMaterialStats(150, 6f, 2, 1.5f), new HandleMaterialStats(0.6f, 1.1f, 1.1f, 1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(tungsten, new HeadMaterialStats(740, 8f, 3, 5f), new HandleMaterialStats(1.1f, 1f, 0.9f, 1.2f), ExtraMaterialStats.DEFAULT);
			//addMaterialStats(invar, new HeadMaterialStats(150, 6f, 2, 1.5f), new HandleMaterialStats(0.6f, 1.1f, 1.1f, 1f), ExtraMaterialStats.DEFAULT);
			//create materials
			addMaterialStats(rose_quartz, new HeadMaterialStats(175, 6f, 2, 5f), new HandleMaterialStats(0.5f, 1f, 1f, 1.3f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(refined_radiance, new HeadMaterialStats(999, 11f, 4, 5f), new HandleMaterialStats(0.5f, 1f, 1f, 1.4f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(shadow_steel, new HeadMaterialStats(1300, 12f, 4, 6f), new HandleMaterialStats(1.1f, 1f, 1.3f, 1.3f), ExtraMaterialStats.DEFAULT);
			//eidolon materials
			addMaterialStats(pewter, new HeadMaterialStats(325, 6.5f, 2, 2f), new HandleMaterialStats(1.1f, 1.1f, 0.8f, 1.1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(arcane_gold, new HeadMaterialStats(200, 11f, 2, 4f), new HandleMaterialStats(0.8f, 1.1f, 1.1f, 1f), ExtraMaterialStats.DEFAULT);
			//aquaculture materials
			addMaterialStats(neptunium, new HeadMaterialStats(1450, 9f, 3, 6f), new HandleMaterialStats(1f, 1.3f, 1f, 1f), ExtraMaterialStats.DEFAULT);
			//mystical world materials
			addMaterialStats(quicksilver, new HeadMaterialStats(75, 6f, 3, 2.5f), new HandleMaterialStats(0.25f, 1.2f, 1.2f, 1f), ExtraMaterialStats.DEFAULT);
			//astral sorcery materials
			addMaterialStats(starmetal, new HeadMaterialStats(340, 6f, 2, 3f), new HandleMaterialStats(1.1f, 1f, 1f, 1f), ExtraMaterialStats.DEFAULT);
			//industrial foregoing materials
			addMaterialStats(plastic, new HeadMaterialStats(200, 6f, 2, 2f), new HandleMaterialStats(0.8f, 1f, 1.2f, 1f), ExtraMaterialStats.DEFAULT);
			addMaterialStats(pink_slime, new HeadMaterialStats(830, 7f, 3, 4f), new HandleMaterialStats(1f, 0.9f, 0.9f, 1.2f), ExtraMaterialStats.DEFAULT);
		}
	}
}
