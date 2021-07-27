package com.rcx.materialis.datagen;

import java.util.function.Consumer;
import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.MaterialisResources.IngotWithBlockNNugget;
import com.rcx.materialis.modifiers.MaterialisModifiers;
import com.rcx.materialis.util.ColorizerModifierRecipeBuilder;
import com.rcx.materialis.util.MaterialisByproduct;
import com.rcx.materialis.util.RuneModifierRecipeBuilder;

import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.Tags.Fluids;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.AndCondition;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.mantle.recipe.SizedIngredient;
import slimeknights.mantle.recipe.data.ItemNameIngredient;
import slimeknights.mantle.recipe.ingredient.IngredientWithout;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ICommonRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.ingredient.MaterialIngredient;
import slimeknights.tconstruct.library.recipe.melting.MaterialMeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.tinkerstation.modifier.ModifierMatch;
import slimeknights.tconstruct.library.recipe.tinkerstation.modifier.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.recipe.tinkerstation.modifier.OverslimeModifierRecipeBuilder;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class MaterialisRecipes extends RecipeProvider implements IConditionBuilder, IMaterialRecipeHelper, IToolRecipeHelper, ISmelteryRecipeHelper, ICommonRecipeHelper {

	public MaterialisRecipes(DataGenerator gen) {
		super(gen);
	}

	@Override
	public String getModId() {
		return Materialis.modID;
	}

	public void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		for (IngotWithBlockNNugget material : MaterialisResources.materialList) {
			blockIngotNuggetCompression(consumer, material.name, material.BLOCK_ITEM.get(), material.INGOT.get(), material.NUGGET.get());
		}

		String castingFolder = "smeltery/casting/metal/";
		String meltingFolder = "smeltery/melting/metal/";
		String alloyFolder = "smeltery/alloys/";
		String materialFolder = "tools/materials/";
		String compositeFolder = "tools/parts/composite/";
		String modifierFolder = "tools/modifiers/";
		String toolFolder = "tools/building/";
		String partFolder = "tools/parts/";
		String castFolder = "smeltery/casts/";

		metalCasting(consumer, MaterialisResources.FAIRY_FLUID.OBJECT, MaterialisResources.FAIRY_INGOT.BLOCK.get(), MaterialisResources.FAIRY_INGOT.INGOT.get(), MaterialisResources.FAIRY_INGOT.NUGGET.get(), castingFolder, "fairy");
		metalMelting(consumer, MaterialisResources.FAIRY_FLUID.FLUID.get(), "fairy", false, meltingFolder, false);
		//metalCasting(consumer, MaterialisResources.RED_AURUM_FLUID.FLUID, MaterialisResources.RED_AURUM_INGOT.BLOCK.get(), MaterialisResources.RED_AURUM_INGOT.INGOT.get(), MaterialisResources.RED_AURUM_INGOT.NUGGET.get(), metalFolder, "red_aurum");
		//metalMelting(consumer, MaterialisResources.RED_AURUM_FLUID.FLUID.get(), "red_aurum", false, metalFolder, false);
		//metalCasting(consumer, MaterialisResources.DRULLOY_FLUID.FLUID, MaterialisResources.DRULLOY_INGOT.BLOCK.get(), MaterialisResources.DRULLOY_INGOT.INGOT.get(), MaterialisResources.DRULLOY_INGOT.NUGGET.get(), metalFolder, "drulloy");
		//metalMelting(consumer, MaterialisResources.DRULLOY_FLUID.FLUID.get(), "drulloy", false, metalFolder, false);
		//metalCasting(consumer, MaterialisResources.POKEFENNIUM_FLUID.FLUID, MaterialisResources.POKEFENNIUM_INGOT.BLOCK.get(), MaterialisResources.POKEFENNIUM_INGOT.INGOT.get(), MaterialisResources.POKEFENNIUM_INGOT.NUGGET.get(), metalFolder, "pokefennium");
		//metalMelting(consumer, MaterialisResources.POKEFENNIUM_FLUID.FLUID.get(), "pokefennium", false, metalFolder, false);
		//metalCasting(consumer, MaterialisResources.ALUMITE_FLUID.FLUID, MaterialisResources.ALUMITE_INGOT.BLOCK.get(), MaterialisResources.ALUMITE_INGOT.INGOT.get(), MaterialisResources.ALUMITE_INGOT.NUGGET.get(), metalFolder, "alumite");
		//metalMelting(consumer, MaterialisResources.ALUMITE_FLUID.FLUID.get(), "alumite", false, metalFolder, false);

		AlloyRecipeBuilder.alloy(MaterialisResources.FAIRY_FLUID.FLUID.get(), FluidValues.INGOT)
		.addInput(TinkerFluids.moltenGold.get(), FluidValues.INGOT)
		.addInput(TinkerFluids.liquidSoul.get(), FluidValues.GLASS_BLOCK)
		.addInput(Fluids.MILK, 1000)
		.build(consumer, prefix(MaterialisResources.FAIRY_FLUID.FLUID, alloyFolder));

		//wrench stuff
		partRecipes(consumer, MaterialisResources.WRENCH_HEAD, MaterialisResources.WRENCH_HEAD_CAST, 2, partFolder, castFolder);
		toolBuilding(consumer, MaterialisResources.WRENCH, toolFolder);
		toolBuilding(consumer, MaterialisResources.BATTLEWRENCH, toolFolder);

		//create stuff
		metalTagCasting(consumer, MaterialisResources.REFINED_RADIANCE_FLUID.OBJECT, "refined_radiance", castingFolder, false);
		metalMelting(consumer, MaterialisResources.REFINED_RADIANCE_FLUID.FLUID.get(), "refined_radiance", false, meltingFolder, true);
		metalTagCasting(consumer, MaterialisResources.SHADOW_STEEL_FLUID.OBJECT, "shadow_steel", castingFolder, false);
		metalMelting(consumer, MaterialisResources.SHADOW_STEEL_FLUID.FLUID.get(), "shadow_steel", false, meltingFolder, true);

		//eidolon stuff
		metalTagCasting(consumer, MaterialisResources.ARCANE_GOLD_FLUID.OBJECT, "arcane_gold", castingFolder, false);
		metalMelting(consumer, MaterialisResources.ARCANE_GOLD_FLUID.FLUID.get(), "arcane_gold", false, meltingFolder, true);

		castCreation(withCondition(consumer, tagConditionDomain("forge", "inlays")), MaterialisItemTags.INLAYS, MaterialisResources.INLAY_CAST, castFolder);
		tagCasting(consumer, TinkerFluids.moltenPewter, FluidValues.INGOT * 2, MaterialisResources.INLAY_CAST, "inlays/pewter", castingFolder + "pewter_inlay", true);
		tagCasting(consumer, MaterialisResources.ARCANE_GOLD_FLUID.OBJECT, FluidValues.INGOT * 2, MaterialisResources.INLAY_CAST, "inlays/arcane_gold", castingFolder + "arcane_gold_inlay", true);

		MeltingRecipeBuilder.melting(Ingredient.of(MaterialisItemTags.PEWTER_INLAY), TinkerFluids.moltenPewter.get(), FluidValues.INGOT * 2, 1.5f).build(withCondition(consumer, tagConditionDomain("forge", "inlays/pewter")), modResource(meltingFolder + "pewter_inlay"));
		MeltingRecipeBuilder.melting(Ingredient.of(MaterialisItemTags.ARCANE_GOLD_INLAY), MaterialisResources.ARCANE_GOLD_FLUID.FLUID.get(), FluidValues.INGOT * 2, 1.5f).build(withCondition(consumer, tagConditionDomain("forge", "inlays/arcane_gold")), modResource(meltingFolder + "arcane_gold_inlay"));

		MeltingRecipeBuilder.melting(ItemNameIngredient.from(new ResourceLocation("eidolon", "pewter_blend")), TinkerFluids.moltenPewter.get(), FluidValues.INGOT, 1.0f).build(withCondition(consumer, new ModLoadedCondition("eidolon")), modResource(meltingFolder + "pewter_blend"));

		//aquaculture stuff
		metalTagCasting(consumer, MaterialisResources.NEPTUNIUM_FLUID.OBJECT, "neptunium", castingFolder, false);
		metalMelting(consumer, MaterialisResources.NEPTUNIUM_FLUID.FLUID.get(), "neptunium", false, meltingFolder, true);

		//mystical world stuff
		metalTagCasting(consumer, MaterialisResources.QUICKSILVER_FLUID.OBJECT, "quicksilver", castingFolder, false);
		metalMelting(consumer, MaterialisResources.QUICKSILVER_FLUID.FLUID.get(), "quicksilver", true, meltingFolder, true, MaterialisByproduct.ZINC, MaterialisByproduct.TIN);

		//astral sorcery stuff
		metalTagCasting(consumer, MaterialisResources.STARMETAL_FLUID.OBJECT, "starmetal", castingFolder, false);
		metalMelting(consumer, MaterialisResources.STARMETAL_FLUID.FLUID.get(), "starmetal", false, meltingFolder, true); //the ore recipe is defined manually

		//industrial foregoing stuff
		metalTagCasting(consumer, MaterialisResources.PINK_SLIME_FLUID.OBJECT, "pink_slime", castingFolder, false);
		metalMelting(consumer, MaterialisResources.PINK_SLIME_FLUID.FLUID.get(), "pink_slime", false, meltingFolder, true);

		Consumer<IFinishedRecipe> industrialForegoingLoaded = withCondition(consumer, new ModLoadedCondition("industrialforegoing"));
		AlloyRecipeBuilder.alloy(MaterialisResources.PINK_SLIME_FLUID.FLUID.get(), FluidValues.INGOT)
		.addInput(TinkerFluids.moltenGold.get(), FluidValues.INGOT * 2)
		.addInput(TinkerFluids.moltenIron.get(), FluidValues.INGOT * 2)
		.addInput(MaterialisFluidTags.LIQUID_PINK_SLIME, 1000)
		.build(industrialForegoingLoaded, prefix(MaterialisResources.PINK_SLIME_FLUID.FLUID, alloyFolder));

		CookingRecipeBuilder.blasting(Ingredient.of(MaterialisItemTags.PINK_SLIME), MaterialisResources.PINK_SLIME_CRYSTAL.get(), 1.0f, 400)
		.unlockedBy("has_item", has(MaterialisItemTags.PINK_SLIME))
		.save(industrialForegoingLoaded, new ResourceLocation(Materialis.modID, "pink_slime_crystal_blasting"));

		//undergarden stuff
		metalTagCasting(consumer, MaterialisResources.CLOGGRUM_FLUID.OBJECT, "cloggrum", castingFolder, false);
		metalMelting(consumer, MaterialisResources.CLOGGRUM_FLUID.FLUID.get(), "cloggrum", true, meltingFolder, true, MaterialisByproduct.FROSTSTEEL);
		metalTagCasting(consumer, MaterialisResources.FROSTSTEEL_FLUID.OBJECT, "froststeel", castingFolder, false);
		metalMelting(consumer, MaterialisResources.FROSTSTEEL_FLUID.FLUID.get(), "froststeel", true, meltingFolder, true, MaterialisByproduct.UTHERIUM);
		metalTagCasting(consumer, MaterialisResources.UTHERIUM_FLUID.OBJECT, "utherium", castingFolder, false);
		metalMelting(consumer, MaterialisResources.UTHERIUM_FLUID.FLUID.get(), "utherium", true, meltingFolder, true, MaterialisByproduct.REGALIUM);
		metalTagCasting(consumer, MaterialisResources.FORGOTTEN_FLUID.OBJECT, "forgotten_metal", castingFolder, false);
		metalMelting(consumer, MaterialisResources.FORGOTTEN_FLUID.FLUID.get(), "forgotten_metal", false, meltingFolder, true);
		metalTagCasting(consumer, MaterialisResources.REGALIUM_FLUID.OBJECT, "regalium", castingFolder, false);
		metalMelting(consumer, MaterialisResources.REGALIUM_FLUID.FLUID.get(), "regalium", true, meltingFolder, true, MaterialisByproduct.CLOGGRUM);
		MeltingRecipeBuilder.melting(ItemNameIngredient.from(new ResourceLocation("undergarden", "utheric_shard")), MaterialisResources.UTHERIUM_FLUID.FLUID.get(), FluidValues.NUGGET / 4, 1.0f).build(withCondition(consumer, new ModLoadedCondition("undergarden")), modResource(meltingFolder + "utheric_shard"));

		//mekanism stuff
		metalTagCasting(consumer, MaterialisResources.REFINED_OBSIDIAN_FLUID.OBJECT, "refined_obsidian", castingFolder, false);
		metalMelting(consumer, MaterialisResources.REFINED_OBSIDIAN_FLUID.FLUID.get(), "refined_obsidian", false, meltingFolder, true);
		metalTagCasting(consumer, MaterialisResources.REFINED_GLOWSTONE_FLUID.OBJECT, "refined_glowstone", castingFolder, false);
		metalMelting(consumer, MaterialisResources.REFINED_GLOWSTONE_FLUID.FLUID.get(), "refined_glowstone", false, meltingFolder, true);

		//psi stuff
		metalTagCasting(consumer, MaterialisResources.PSIMETAL_FLUID.OBJECT, "psimetal", castingFolder, false);
		metalMelting(consumer, MaterialisResources.PSIMETAL_FLUID.FLUID.get(), "psimetal", false, meltingFolder, true);
		metalTagCasting(consumer, MaterialisResources.EBONY_PSIMETAL_FLUID.OBJECT, "ebony_psimetal", castingFolder, false);
		metalMelting(consumer, MaterialisResources.EBONY_PSIMETAL_FLUID.FLUID.get(), "ebony_psimetal", false, meltingFolder, true);
		metalTagCasting(consumer, MaterialisResources.IVORY_PSIMETAL_FLUID.OBJECT, "ivory_psimetal", castingFolder, false);
		metalMelting(consumer, MaterialisResources.IVORY_PSIMETAL_FLUID.FLUID.get(), "ivory_psimetal", false, meltingFolder, true);

		//occultism stuff
		metalTagCasting(consumer, MaterialisResources.IESNIUM_FLUID.OBJECT, "iesnium", castingFolder, false);
		metalMelting(consumer, MaterialisResources.IESNIUM_FLUID.FLUID.get(), "iesnium", true, meltingFolder, true, MaterialisByproduct.SILVER);



		//materials
		metalMaterialRecipe(consumer, MaterialisMaterials.fairy, materialFolder, "fairy", false);
		metalMaterialRecipe(consumer, MaterialisMaterials.brass, materialFolder, "brass", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.aluminum, materialFolder, "aluminum", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.nickel, materialFolder, "nickel", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.platinum, materialFolder, "platinum", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.uranium, materialFolder, "uranium", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.tungsten, materialFolder, "tungsten", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.invar, materialFolder, "invar", true);
		materialRecipe(withCondition(consumer, new ModLoadedCondition("create")), MaterialisMaterials.roseQuartz, ItemNameIngredient.from(new ResourceLocation("create", "polished_rose_quartz")), 1, 1, materialFolder + "rose_quartz");
		metalMaterialRecipe(consumer, MaterialisMaterials.refinedRadiance, materialFolder, "refined_radiance", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.shadowSteel, materialFolder, "shadow_steel", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.pewter, materialFolder, "pewter", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.arcaneGold, materialFolder, "arcane_gold", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.neptunium, materialFolder, "neptunium", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.quicksilver, materialFolder, "quicksilver", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.starmetal, materialFolder, "starmetal", true);
		materialRecipe(withCondition(consumer, tagCondition("plastic")), MaterialisMaterials.plastic, Ingredient.of(ItemTags.bind("forge:plastic")), 1, 1, materialFolder + "plastic");
		metalMaterialRecipe(consumer, MaterialisMaterials.pinkSlime, materialFolder, "pink_slime", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.cloggrum, materialFolder, "cloggrum", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.froststeel, materialFolder, "froststeel", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.utherium, materialFolder, "utherium", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.forgottenMetal, materialFolder, "forgotten_metal", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.osmium, materialFolder, "osmium", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.refinedObsidian, materialFolder, "refined_obsidian", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.refinedGlowstone, materialFolder, "refined_glowstone", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.psimetal, materialFolder, "psimetal", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.ebonyPsimetal, materialFolder, "ebony_psimetal", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.ivoryPsimetal, materialFolder, "ivory_psimetal", true);
		//metalMaterialRecipe(consumer, MaterialisMaterials.iesnium, materialFolder, "iesnium", true);

		//material casting
		materialMeltingCasting(consumer, MaterialisMaterials.fairy, MaterialisResources.FAIRY_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.brass, TinkerFluids.moltenBrass, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.aluminum, TinkerFluids.moltenAluminum, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.nickel, TinkerFluids.moltenNickel, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.platinum, TinkerFluids.moltenPlatinum, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.uranium, TinkerFluids.moltenUranium, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.tungsten, TinkerFluids.moltenTungsten, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.invar, TinkerFluids.moltenInvar, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.refinedRadiance, MaterialisResources.REFINED_RADIANCE_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.shadowSteel, MaterialisResources.SHADOW_STEEL_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.pewter, TinkerFluids.moltenPewter, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.arcaneGold, MaterialisResources.ARCANE_GOLD_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.neptunium, MaterialisResources.NEPTUNIUM_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.quicksilver, MaterialisResources.QUICKSILVER_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.starmetal, MaterialisResources.STARMETAL_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.pinkSlime, MaterialisResources.PINK_SLIME_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.cloggrum, MaterialisResources.CLOGGRUM_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.froststeel, MaterialisResources.FROSTSTEEL_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.utherium, MaterialisResources.UTHERIUM_FLUID.OBJECT, materialFolder);
		materialComposite(withCondition(consumer, new AndCondition(tagCondition("ingots/cloggrum"), tagCondition("ingots/forgotten_metal"))), MaterialisMaterials.cloggrum, MaterialisMaterials.forgottenMetal, MaterialisResources.FORGOTTEN_FLUID.OBJECT, FluidValues.INGOT, false, compositeFolder);
		MaterialMeltingRecipeBuilder.material(MaterialisMaterials.forgottenMetal, new FluidStack(MaterialisResources.FORGOTTEN_FLUID.FLUID.get(), FluidValues.INGOT))
		.build(withCondition(consumer, new AndCondition(tagCondition("ingots/cloggrum"), tagCondition("ingots/forgotten_metal"))), modResource(materialFolder + "melting/forgotten_metal"));
		materialMeltingCasting(consumer, MaterialisMaterials.osmium, TinkerFluids.moltenOsmium, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.refinedObsidian, MaterialisResources.REFINED_OBSIDIAN_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.refinedGlowstone, MaterialisResources.REFINED_GLOWSTONE_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.psimetal, MaterialisResources.PSIMETAL_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.ebonyPsimetal, MaterialisResources.EBONY_PSIMETAL_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.ivoryPsimetal, MaterialisResources.IVORY_PSIMETAL_FLUID.OBJECT, materialFolder);
		//materialMeltingCasting(consumer, MaterialisMaterials.iesnium, MaterialisResources.IESNIUM_FLUID.OBJECT, materialFolder);



		//modifiers
		ModifierRecipeBuilder.modifier(MaterialisModifiers.reapingModifier.get())
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("eidolon", "tattered_cloth"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("eidolon", "unholy_symbol"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("eidolon", "tattered_cloth"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("eidolon", "soul_shard"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("eidolon", "soul_shard"))))
		.setTools(TinkerTags.Items.MELEE)
		.setMaxLevel(1)
		.setAbilitySlots(1)
		.setGroup("materialis:eidolon")
		.build(withCondition(consumer, new ModLoadedCondition("eidolon")), prefix(MaterialisModifiers.reapingModifier, modifierFolder));

		OverslimeModifierRecipeBuilder.modifier(MaterialisResources.PINK_SLIME_CRYSTAL.get(), 70)
		.build(withCondition(consumer, new ModLoadedCondition("industrialforegoing")), prefix(TinkerModifiers.overslime, modifierFolder));

		RuneModifierRecipeBuilder.modifier(MaterialisModifiers.runedModifier.get())
		.addInput(getTag("quark", "runes"))
		.setTools(TinkerTags.Items.MODIFIABLE)
		.setMaxLevel(1)
		.setRequirements(ModifierMatch.entry(TinkerModifiers.shiny.get()))
		.setRequirementsError("recipe.materialis.modifier.runed_requirements")
		.setGroup("materialis:quark")
		.build(withCondition(consumer, new ModLoadedCondition("quark")), prefix(MaterialisModifiers.runedModifier, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.wrenchingModifier.get())
		.setTools(new IngredientWithout(Ingredient.of(TinkerTags.Items.MODIFIABLE), Ingredient.of(MaterialisItemTags.WRENCHING)))
		.addInput(SizedIngredient.of(MaterialIngredient.fromItem(MaterialisResources.WRENCH_HEAD.get())))
		.addInput(TinkerTags.Items.INGOTS_NETHERITE_SCRAP)
		.addInput(SizedIngredient.of(MaterialIngredient.fromItem(TinkerToolParts.toolBinding.get())))
		.setMaxLevel(1)
		.setAbilitySlots(1)
		.build(consumer, prefix(MaterialisModifiers.wrenchingModifier, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.galvanizedModifier.get())
		.setTools(Ingredient.of(MaterialisItemTags.GALVANIZABLE))
		.addInput(Items.PHANTOM_MEMBRANE)
		.addInput(Tags.Items.INGOTS_IRON)
		.setUpgradeSlots(2)
		.build(consumer, prefix(MaterialisModifiers.galvanizedModifier, modifierFolder));

		ModifierMatch wrenching = ModifierMatch.list(1, ModifierMatch.entry(MaterialisModifiers.wrenchingModifier.get()), ModifierMatch.entry(MaterialisModifiers.wrenchingModifierHidden.get()));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.thermalWrenchingModifier.get())
		.addInput(getTag("forge", "gears/nickel"))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("thermal", "wrench"))))
		.addInput(getTag("forge", "gears/nickel"))
		.setRequirements(wrenching)
		.setRequirementsError("recipe.materialis.modifier.thermal_wrenching_requirements")
		.setMaxLevel(1)
		.setUpgradeSlots(1)
		.setGroup("materialis:thermal")
		.build(withCondition(consumer, new ModLoadedCondition("thermal")), prefix(MaterialisModifiers.thermalWrenchingModifier, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.createWrenchingModifier.get())
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("create", "cogwheel"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("create", "wrench"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("create", "cogwheel"))))
		.setRequirements(wrenching)
		.setRequirementsError("recipe.materialis.modifier.create_wrenching_requirements")
		.setMaxLevel(1)
		.setUpgradeSlots(1)
		.setGroup("materialis:create")
		.build(withCondition(consumer, new ModLoadedCondition("create")), prefix(MaterialisModifiers.createWrenchingModifier, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.psionizingRadiationModifier.get())
		.addInput(getTag("forge", "ingots/psimetal"))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("psi", "cad_socket_basic"))))
		.addInput(getTag("forge", "ingots/psimetal"))
		.addInput(getTag("forge", "gems/psigem"))
		.addInput(getTag("forge", "gems/psigem"))
		.setTools(TinkerTags.Items.MELEE_OR_HARVEST)
		.setAbilitySlots(1)
		.setGroup("materialis:psi")
		.build(withCondition(consumer, new ModLoadedCondition("psi")), prefix(MaterialisModifiers.psionizingRadiationModifier, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.castingModifier.get())
		.addInput(getTag("forge", "ingots/ebony_psimetal"))
		.addInput(getTag("forge", "gems/psigem"))
		.addInput(getTag("forge", "ingots/ivory_psimetal"))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("psi", "cad_battery_ultradense"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("psi", "cad_assembly_psimetal"))))
		.setRequirements(ModifierMatch.entry(MaterialisModifiers.psionizingRadiationModifier.get()))
		.setRequirementsError("recipe.materialis.modifier.casting_requirements")
		.setTools(TinkerTags.Items.MODIFIABLE)
		.setAbilitySlots(1)
		.setGroup("materialis:psi")
		.build(withCondition(consumer, new ModLoadedCondition("psi")), prefix(MaterialisModifiers.castingModifier, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.spellSocketModifier.get())
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("psi", "cad_socket_basic"))))
		.setTools(TinkerTags.Items.MELEE_OR_HARVEST)
		.setMaxLevel(5)
		.setUpgradeSlots(1)
		.setGroup("materialis:psi")
		.build(withCondition(consumer, new ModLoadedCondition("psi")), prefix(MaterialisModifiers.spellSocketModifier, modifierFolder));

		ColorizerModifierRecipeBuilder.modifier(MaterialisModifiers.colorizedModifier.get())
		.addInput(MaterialisItemTags.COLORIZERS)
		.setTools(TinkerTags.Items.MODIFIABLE)
		.setMaxLevel(1)
		.setGroup("materialis:psi")
		.build(withCondition(consumer, new ModLoadedCondition("psi")), prefix(MaterialisModifiers.colorizedModifier, modifierFolder));
	}

	public void blockIngotNuggetCompression(Consumer<IFinishedRecipe> consumer, String name, Item block, Item ingot, Item nugget) {
		ConditionalRecipe.builder().addCondition(this.TRUE()).addRecipe(
				ShapedRecipeBuilder.shaped(block, 1)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', ItemTags.bind("forge:ingots/" + name))
				.define('Y', ingot)
				.group("")
				.unlockedBy("has_ingot", has(ingot))
				::save
				)
		.generateAdvancement()
		.build(consumer, new ResourceLocation(Materialis.modID, ingot.getRegistryName().getPath() + "_to_block"));

		ConditionalRecipe.builder().addCondition(this.TRUE()).addRecipe(
				ShapelessRecipeBuilder.shapeless(ingot, 9)
				.requires(block)
				.group("")
				.unlockedBy("has_block", has(block))
				::save
				)
		.generateAdvancement()
		.build(consumer, new ResourceLocation(Materialis.modID, block.getRegistryName().getPath() + "_to_ingot"));

		ConditionalRecipe.builder().addCondition(this.TRUE()).addRecipe(
				ShapedRecipeBuilder.shaped(ingot, 1)
				.pattern("XXX")
				.pattern("XYX")
				.pattern("XXX")
				.define('X', ItemTags.bind("forge:nuggets/" + name))
				.define('Y', nugget)
				.group("")
				.unlockedBy("has_nugget", has(nugget))
				::save
				)
		.generateAdvancement()
		.build(consumer, new ResourceLocation(Materialis.modID, nugget.getRegistryName().getPath() + "_to_ingot"));

		ConditionalRecipe.builder().addCondition(this.TRUE()).addRecipe(
				ShapelessRecipeBuilder.shapeless(nugget, 9)
				.requires(ingot)
				.group("")
				.unlockedBy("has_ingot", has(ingot))
				::save
				)
		.generateAdvancement()
		.build(consumer, new ResourceLocation(Materialis.modID, ingot.getRegistryName().getPath() + "_to_nugget"));
	}

	/*private void addIngotMaterialRepairs(Consumer<IFinishedRecipe> consumer, MaterialId material) {
		String name = material.getPath();
		addMaterialRepairs(consumer, material, Ingredient.of(ItemTags.bind("forge:nuggets/" + name)), 1, 9, name + "/nugget");
		addMaterialRepairs(consumer, material, Ingredient.of(ItemTags.bind("forge:ingots/" + name)), 1, 1, name + "/ingot");
		addMaterialRepairs(consumer, material, Ingredient.of(ItemTags.bind("forge:storage_blocks/" + name)), 9, 1, name + "/block");
	}

	private void addConditionalIngotMaterialRepairs(Consumer<IFinishedRecipe> consumer, MaterialId material) {
		String name = material.getPath();
		addMaterialRepairs(withCondition(consumer, tagCondition("nuggets/" + name)), material, Ingredient.of(ItemTags.bind("forge:nuggets/" + name)), 1, 9, name + "/nugget");
		addMaterialRepairs(withCondition(consumer, tagCondition("ingots/" + name)), material, Ingredient.of(ItemTags.bind("forge:ingots/" + name)), 1, 1, name + "/ingot");
		addMaterialRepairs(withCondition(consumer, tagCondition("storage_blocks/" + name)), material, Ingredient.of(ItemTags.bind("forge:storage_blocks/" + name)), 9, 1, name + "/block");
	}

	private void addMaterialRepairs(Consumer<IFinishedRecipe> consumer, MaterialId material, Ingredient input, int value, int needed) {
		addMaterialRepairs(consumer, material, input, value, needed, material.getPath());
	}

	private void addMaterialRepairs(Consumer<IFinishedRecipe> consumer, MaterialId material, Ingredient input, int value, int needed, String saveName) {
		MaterialRecipeBuilder.materialRecipe(material)
		.setIngredient(input)
		.setValue(value)
		.setNeeded(needed)
		.build(consumer, location("tools/materials/" + saveName));
	}*/

	protected static ICondition tagConditionDomain(String domain, String name) {
		return new NotCondition(new TagEmptyCondition(domain, name));
	}
}
