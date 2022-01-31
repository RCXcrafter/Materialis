package com.rcx.materialis.datagen;

import java.util.function.Consumer;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.MaterialisResources.IngotWithBlockNNugget;
import com.rcx.materialis.modifiers.CleansingModifier;
import com.rcx.materialis.modifiers.MaterialisModifiers;
import com.rcx.materialis.util.MaterialisByproduct;

import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Effects;
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
import net.minecraftforge.common.crafting.conditions.OrCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.mantle.recipe.EntityIngredient;
import slimeknights.mantle.recipe.SizedIngredient;
import slimeknights.mantle.recipe.data.ConsumerWrapperBuilder;
import slimeknights.mantle.recipe.data.ItemNameIngredient;
import slimeknights.mantle.recipe.data.ItemNameOutput;
import slimeknights.mantle.recipe.ingredient.IngredientIntersection;
import slimeknights.mantle.recipe.ingredient.IngredientWithout;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ICommonRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.RandomItem;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.entitymelting.EntityMeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.ingredient.MaterialIngredient;
import slimeknights.tconstruct.library.recipe.melting.MaterialMeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.modifiers.ModifierMatch;
import slimeknights.tconstruct.library.recipe.modifiers.adding.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.recipe.modifiers.adding.OverslimeModifierRecipeBuilder;
import slimeknights.tconstruct.library.recipe.modifiers.spilling.SpillingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.modifiers.spilling.effects.EffectSpillingEffect;
import slimeknights.tconstruct.library.recipe.tinkerstation.repairing.SpecializedRepairRecipeBuilder;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.item.ArmorSlotType;

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
		String salvageFolder = "tools/modifiers/salvage/";
		String spillFolder = "tools/spilling/";
		String toolFolder = "tools/building/";
		String partFolder = "tools/parts/";
		String castFolder = "smeltery/casts/";
		String armorFolder = "armor/building/";
		String armorRepairFolder = "armor/repair/";

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
		SpillingRecipeBuilder.forFluid(MaterialisResources.REFINED_RADIANCE_FLUID.FLUID.get(), FluidValues.NUGGET)
		.addEffect(new EffectSpillingEffect(Effects.GLOWING, 800, 1))
		.build(withCondition(consumer, new ModLoadedCondition("create")), prefix(MaterialisResources.REFINED_RADIANCE_FLUID.FLUID, spillFolder));

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
		toolMelting(withCondition(consumer, new ModLoadedCondition("aquaculture")), new NameFluid("neptunium", MaterialisResources.NEPTUNIUM_FLUID.FLUID.get()),
				new ToolValue("aquaculture:neptunium_axe", 3),
				new ToolValue("aquaculture:neptunium_hoe", 2),
				new ToolValue("aquaculture:neptunium_pickaxe", 3),
				new ToolValue("aquaculture:neptunium_shovel", 1),
				new ToolValue("aquaculture:neptunium_sword", 2),
				new ToolValue("aquaculture:neptunium_bow", 3),
				new ToolValue("aquaculture:neptunium_fishing_rod", 2),
				new ToolValue("aquaculture:neptunium_fillet_knife", 2),
				new ToolValue("aquaculture:neptunium_helmet", 5),
				new ToolValue("aquaculture:neptunium_chestplate", 8),
				new ToolValue("aquaculture:neptunium_leggings", 7),
				new ToolValue("aquaculture:neptunium_boots", 4));
		multipleToolMelting(withCondition(consumer, new ModLoadedCondition("aquaculture")), "aquaculture",
				new NameFluid[] {
						new NameFluid("iron", TinkerFluids.moltenIron.get()),
						new NameFluid("gold", TinkerFluids.moltenGold.get()),
						new NameFluid("diamond", TinkerFluids.moltenDiamond.get())
		},
				new ToolValue("_fishing_rod", 2),
				new ToolValue("_fillet_knife", 2));

		//mystical world stuff
		metalTagCasting(consumer, MaterialisResources.QUICKSILVER_FLUID.OBJECT, "quicksilver", castingFolder, false);
		metalMelting(consumer, MaterialisResources.QUICKSILVER_FLUID.FLUID.get(), "quicksilver", true, meltingFolder, true, MaterialisByproduct.ZINC, MaterialisByproduct.TIN);
		multipleToolMelting(withCondition(consumer, new ModLoadedCondition("mysticalworld")), "mysticalworld",
				new NameFluid[] {
						new NameFluid("copper", TinkerFluids.moltenCopper.get()),
						new NameFluid("lead", TinkerFluids.moltenLead.get()),
						new NameFluid("quicksilver", MaterialisResources.QUICKSILVER_FLUID.FLUID.get()),
						new NameFluid("silver", TinkerFluids.moltenSilver.get()),
						new NameFluid("tin", TinkerFluids.moltenTin.get())
		},
				new ToolValue("_axe", 3),
				new ToolValue("_hoe", 2),
				new ToolValue("_pickaxe", 3),
				new ToolValue("_shovel", 1),
				new ToolValue("_sword", 2),
				new ToolValue("_knife", 1),
				new ToolValue("_spear", 2),
				new ToolValue("_helmet", 5),
				new ToolValue("_chestplate", 8),
				new ToolValue("_leggings", 7),
				new ToolValue("_boots", 4));
		multipleToolMelting(withCondition(consumer, new ModLoadedCondition("mysticalworld")), "mysticalworld",
				new NameFluid[] {
						new NameFluid("iron", TinkerFluids.moltenIron.get()),
						new NameFluid("gold", TinkerFluids.moltenGold.get()),
						new NameFluid("diamond", TinkerFluids.moltenDiamond.get())
		},
				new ToolValue("_knife", 1),
				new ToolValue("_spear", 2));

		//astral sorcery stuff
		metalTagCasting(consumer, MaterialisResources.STARMETAL_FLUID.OBJECT, "starmetal", castingFolder, false);
		metalMelting(consumer, MaterialisResources.STARMETAL_FLUID.FLUID.get(), "starmetal", false, meltingFolder, true); //the ore recipe is defined manually
		SpillingRecipeBuilder.forFluid(MaterialisFluidTags.LIQUID_STARLIGHT, 50)
		.addEffect(new EffectSpillingEffect(Effects.NIGHT_VISION, 100, 1))
		.build(withCondition(consumer, new ModLoadedCondition("astralsorcery")), new ResourceLocation(Materialis.modID, spillFolder + "liquid_starlight"));

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
		metalMelting(consumer, MaterialisResources.FROSTSTEEL_FLUID.FLUID.get(), "froststeel", true, meltingFolder, true, MaterialisByproduct.REGALIUM);
		metalTagCasting(consumer, MaterialisResources.UTHERIUM_FLUID.OBJECT, "utherium", castingFolder, false);
		metalMelting(consumer, MaterialisResources.UTHERIUM_FLUID.FLUID.get(), "utherium", false, meltingFolder, true);
		oreMelting(consumer, MaterialisResources.UTHERIUM_FLUID.FLUID.get(), FluidValues.NUGGET, "ores/utherium", 1.5f, meltingFolder + "utherium/ore", true);
		metalTagCasting(consumer, MaterialisResources.FORGOTTEN_FLUID.OBJECT, "forgotten_metal", castingFolder, false);
		metalMelting(consumer, MaterialisResources.FORGOTTEN_FLUID.FLUID.get(), "forgotten_metal", false, meltingFolder, true);
		metalTagCasting(consumer, MaterialisResources.REGALIUM_FLUID.OBJECT, "regalium", castingFolder, false);
		metalMelting(consumer, MaterialisResources.REGALIUM_FLUID.FLUID.get(), "regalium", true, meltingFolder, true, MaterialisByproduct.CLOGGRUM);
		MeltingRecipeBuilder.melting(ItemNameIngredient.from(new ResourceLocation("undergarden", "utheric_shard")), MaterialisResources.UTHERIUM_FLUID.FLUID.get(), FluidValues.NUGGET / 4, 1.0f).build(withCondition(consumer, new ModLoadedCondition("undergarden")), modResource(meltingFolder + "utheric_shard"));
		EntityMeltingRecipeBuilder.melting(EntityIngredient.of(CleansingModifier.rotspawnTag), new FluidStack(MaterialisResources.UTHERIUM_FLUID.FLUID.get(), FluidValues.NUGGET / 8)).build(withCondition(consumer, new ModLoadedCondition("undergarden")));
		SpillingRecipeBuilder.forFluid(MaterialisFluidTags.VIRULENT_MIX, 50)
		.addEffect(new EffectSpillingEffect(Effects.POISON, 50, 1))
		.build(withCondition(consumer, new ModLoadedCondition("undergarden")), new ResourceLocation(Materialis.modID, spillFolder + "virulent_mix"));
		multipleToolMelting(withCondition(consumer, new ModLoadedCondition("undergarden")), "undergarden",
				new NameFluid[] {
						new NameFluid("cloggrum", MaterialisResources.CLOGGRUM_FLUID.FLUID.get()),
						new NameFluid("froststeel", MaterialisResources.FROSTSTEEL_FLUID.FLUID.get()),
						new NameFluid("utheric", MaterialisResources.UTHERIUM_FLUID.FLUID.get())
		},
				new ToolValue("_axe", 3),
				new ToolValue("_hoe", 2),
				new ToolValue("_pickaxe", 3),
				new ToolValue("_shovel", 1),
				new ToolValue("_sword", 2),
				new ToolValue("_helmet", 5),
				new ToolValue("_chestplate", 8),
				new ToolValue("_leggings", 7),
				new ToolValue("_boots", 4));
		toolMelting(withCondition(consumer, new ModLoadedCondition("undergarden")), new NameFluid("forgotten_metal", MaterialisResources.CLOGGRUM_FLUID.FLUID.get(), new FluidStack(MaterialisResources.FORGOTTEN_FLUID.FLUID.get(), FluidValues.INGOT), true),
				new ToolValue("undergarden:forgotten_axe", 3),
				new ToolValue("undergarden:forgotten_hoe", 2),
				new ToolValue("undergarden:forgotten_pickaxe", 3),
				new ToolValue("undergarden:forgotten_shovel", 1),
				new ToolValue("undergarden:forgotten_sword", 2),
				new ToolValue("undergarden:forgotten_battleaxe", 3));
		toolMelting(withCondition(consumer, new ModLoadedCondition("undergarden")), new NameFluid("cloggrum", MaterialisResources.CLOGGRUM_FLUID.FLUID.get()),
				new ToolValue("undergarden:cloggrum_shield", 6),
				new ToolValue("undergarden:cloggrum_battleaxe", 3));

		//mekanism stuff
		//metalTagCasting(consumer, TinkerFluids.moltenRefinedObsidian, "refined_obsidian", castingFolder, false);
		//metalMelting(consumer, TinkerFluids.moltenRefinedObsidian.get(), "refined_obsidian", false, false, meltingFolder, true);
		//metalTagCasting(consumer, TinkerFluids.moltenRefinedGlowstone, "refined_glowstone", castingFolder, false);
		//metalMelting(consumer, TinkerFluids.moltenRefinedGlowstone.get(), "refined_glowstone", false, meltingFolder, true);
		multipleToolMelting(withCondition(consumer, new ModLoadedCondition("mekanismtools")), "mekanismtools",
				new NameFluid[] {
						new NameFluid("bronze", TinkerFluids.moltenBronze.get()),
						new NameFluid("osmium", TinkerFluids.moltenOsmium.get()),
						new NameFluid("refined_glowstone", TinkerFluids.moltenRefinedGlowstone.get()),
						new NameFluid("refined_obsidian", TinkerFluids.moltenRefinedObsidian.get()),
						new NameFluid("steel", TinkerFluids.moltenSteel.get())
		},
				new ToolValue("_pickaxe", 3),
				new ToolValue("_axe", 3),
				new ToolValue("_shovel", 1),
				new ToolValue("_hoe", 2),
				new ToolValue("_sword", 2),
				new ToolValue("_paxel", 7),
				new ToolValue("_helmet", 5),
				new ToolValue("_chestplate", 8),
				new ToolValue("_leggings", 7),
				new ToolValue("_boots", 4));
		multipleToolMelting(withCondition(consumer, new ModLoadedCondition("mekanismtools")), "mekanismtools",
				new NameFluid[] {
						new NameFluid("bronze", TinkerFluids.moltenBronze.get(), new FluidStack(TinkerFluids.moltenIron.get(), FluidValues.INGOT), false),
						new NameFluid("osmium", TinkerFluids.moltenOsmium.get(), new FluidStack(TinkerFluids.moltenIron.get(), FluidValues.INGOT), false),
						new NameFluid("refined_glowstone", TinkerFluids.moltenRefinedGlowstone.get(), new FluidStack(TinkerFluids.moltenIron.get(), FluidValues.INGOT), false),
						new NameFluid("refined_obsidian", TinkerFluids.moltenRefinedObsidian.get(), new FluidStack(TinkerFluids.moltenIron.get(), FluidValues.INGOT), false),
						new NameFluid("steel", TinkerFluids.moltenSteel.get(), new FluidStack(TinkerFluids.moltenIron.get(), FluidValues.INGOT), false)
		},
				new ToolValue("_shield", 6));
		multipleToolMelting(withCondition(consumer, new ModLoadedCondition("mekanismtools")), "mekanismtools",
				new NameFluid[] {
						new NameFluid("iron", TinkerFluids.moltenIron.get()),
						new NameFluid("gold", TinkerFluids.moltenGold.get()),
						new NameFluid("diamond", TinkerFluids.moltenDiamond.get()),
						new NameFluid("netherite", TinkerFluids.moltenDiamond.get(), new FluidStack(TinkerFluids.moltenNetherite.get(), FluidValues.INGOT), true)
		},
				new ToolValue("_paxel", 7));

		//psi stuff
		ShapedRecipeBuilder.shaped(MaterialisResources.PSIMETAL_EXOSUIT.get(ArmorSlotType.HELMET))
		.pattern("gpg")
		.pattern("b b")
		.define('p', getTag("forge", "ingots/psimetal"))
		.define('g', getTag("forge", "gems/psigem"))
		.define('b', getTag("forge", "ingots/silicon_bronze"))
		.unlockedBy("has_item", has(getTag("forge", "gems/psigem")))
		.save(consumer, modResource(armorFolder + "exosuit_helmet"));
		ShapedRecipeBuilder.shaped(MaterialisResources.PSIMETAL_EXOSUIT.get(ArmorSlotType.CHESTPLATE))
		.pattern("p p")
		.pattern("gpg")
		.pattern("bpb")
		.define('p', getTag("forge", "ingots/psimetal"))
		.define('g', getTag("forge", "gems/psigem"))
		.define('b', getTag("forge", "ingots/silicon_bronze"))
		.unlockedBy("has_item", has(getTag("forge", "gems/psigem")))
		.save(consumer, modResource(armorFolder + "exosuit_chestplate"));
		ShapedRecipeBuilder.shaped(MaterialisResources.PSIMETAL_EXOSUIT.get(ArmorSlotType.LEGGINGS))
		.pattern("pgp")
		.pattern("b b")
		.pattern("p p")
		.define('p', getTag("forge", "ingots/psimetal"))
		.define('g', getTag("forge", "gems/psigem"))
		.define('b', getTag("forge", "ingots/silicon_bronze"))
		.unlockedBy("has_item", has(getTag("forge", "gems/psigem")))
		.save(consumer, modResource(armorFolder + "exosuit_leggings"));
		ShapedRecipeBuilder.shaped(MaterialisResources.PSIMETAL_EXOSUIT.get(ArmorSlotType.BOOTS))
		.pattern("g g")
		.pattern("b b")
		.define('g', getTag("forge", "gems/psigem"))
		.define('b', getTag("forge", "ingots/silicon_bronze"))
		.unlockedBy("has_item", has(getTag("forge", "gems/psigem")))
		.save(consumer, modResource(armorFolder + "exosuit_boots"));
		SpecializedRepairRecipeBuilder.repair(Ingredient.of(MaterialisResources.PSIMETAL_EXOSUIT.values().stream().map(ItemStack::new)), MaterialisMaterials.psimetal)
		.buildRepairKit(consumer, modResource(armorRepairFolder + "exosuit_repair_kit"))
		.build(consumer, modResource(armorRepairFolder + "exosuit_station"));
		metalTagCasting(consumer, MaterialisResources.PSIMETAL_FLUID.OBJECT, "psimetal", castingFolder, false);
		metalMelting(consumer, MaterialisResources.PSIMETAL_FLUID.FLUID.get(), "psimetal", false, meltingFolder, true);
		metalTagCasting(consumer, MaterialisResources.EBONY_PSIMETAL_FLUID.OBJECT, "ebony_psimetal", castingFolder, false);
		metalMelting(consumer, MaterialisResources.EBONY_PSIMETAL_FLUID.FLUID.get(), "ebony_psimetal", false, meltingFolder, true);
		metalTagCasting(consumer, MaterialisResources.IVORY_PSIMETAL_FLUID.OBJECT, "ivory_psimetal", castingFolder, false);
		metalMelting(consumer, MaterialisResources.IVORY_PSIMETAL_FLUID.FLUID.get(), "ivory_psimetal", false, meltingFolder, true);
		toolMelting(withCondition(consumer, new AndCondition(new ModLoadedCondition("psi"), new NotCondition(new ModLoadedCondition("magipsi")))), new NameFluid("psimetal", MaterialisResources.PSIMETAL_FLUID.FLUID.get(), new FluidStack(TinkerFluids.moltenIron.get(), FluidValues.INGOT * 2), false),
				new ToolValue("psi:psimetal_axe", 2),
				new ToolValue("psi:psimetal_pickaxe", 2),
				new ToolValue("psi:psimetal_shovel", 1));
		toolMelting(withCondition(consumer, new AndCondition(new ModLoadedCondition("psi"), new NotCondition(new ModLoadedCondition("magipsi")))), new NameFluid("psimetal", MaterialisResources.PSIMETAL_FLUID.FLUID.get(), new FluidStack(TinkerFluids.moltenIron.get(), FluidValues.INGOT), false),
				new ToolValue("psi:psimetal_sword", 1));
		toolMelting(withCondition(consumer, new ModLoadedCondition("magipsi")), new NameFluid("psimetal_magical", MaterialisResources.PSIMETAL_FLUID.FLUID.get(), new FluidStack(TinkerFluids.moltenGold.get(), FluidValues.INGOT), false),
				new ToolValue("psi:psimetal_axe", 2),
				new ToolValue("psi:psimetal_pickaxe", 2),
				new ToolValue("psi:psimetal_shovel", 1),
				new ToolValue("psi:psimetal_sword", 1));
		toolMelting(withCondition(consumer, new ModLoadedCondition("psi")), new NameFluid("psimetal", MaterialisResources.PSIMETAL_FLUID.FLUID.get()),
				new ToolValue("psi:psimetal_exosuit_helmet", 3),
				new ToolValue("psi:psimetal_exosuit_chestplate", 6),
				new ToolValue("psi:psimetal_exosuit_leggings", 5),
				new ToolValue("psi:psimetal_exosuit_boots", 2));

		//occultism stuff
		metalTagCasting(consumer, MaterialisResources.IESNIUM_FLUID.OBJECT, "iesnium", castingFolder, false);
		metalMelting(consumer, MaterialisResources.IESNIUM_FLUID.FLUID.get(), "iesnium", true, meltingFolder, true, MaterialisByproduct.SILVER);
		toolMelting(withCondition(consumer, new ModLoadedCondition("occultism")), new NameFluid("iesnium", MaterialisResources.IESNIUM_FLUID.FLUID.get()),
				new ToolValue("occultism:iesnium_pickaxe", 3));

		//botania stuff
		metalTagCasting(consumer, MaterialisResources.MANASTEEL_FLUID.OBJECT, "manasteel", castingFolder, false);
		metalMelting(consumer, MaterialisResources.MANASTEEL_FLUID.FLUID.get(), "manasteel", false, meltingFolder, true);
		metalTagCasting(consumer, MaterialisResources.ELEMENTIUM_FLUID.OBJECT, "elementium", castingFolder, false);
		metalMelting(consumer, MaterialisResources.ELEMENTIUM_FLUID.FLUID.get(), "elementium", false, meltingFolder, true);
		metalTagCasting(consumer, MaterialisResources.TERRASTEEL_FLUID.OBJECT, "terrasteel", castingFolder, false);
		metalMelting(consumer, MaterialisResources.TERRASTEEL_FLUID.FLUID.get(), "terrasteel", false, meltingFolder, true);
		multipleToolMelting(withCondition(consumer, new ModLoadedCondition("botania")), "botania",
				new NameFluid[] {
						new NameFluid("manasteel", MaterialisResources.MANASTEEL_FLUID.FLUID.get()),
						new NameFluid("elementium", MaterialisResources.ELEMENTIUM_FLUID.FLUID.get())
		},
				//new ToolValue("_pickaxe", 3), //botania is inconsistent with these names >:(
				new ToolValue("_axe", 3),
				new ToolValue("_shovel", 1),
				new ToolValue("_hoe", 2),
				new ToolValue("_sword", 2),
				new ToolValue("_shears", 2),
				new ToolValue("_helmet", 5),
				new ToolValue("_chestplate", 8),
				new ToolValue("_leggings", 7),
				new ToolValue("_boots", 4));
		toolMelting(withCondition(consumer, new ModLoadedCondition("botania")), new NameFluid("manasteel", MaterialisResources.MANASTEEL_FLUID.FLUID.get()),
				new ToolValue("botania:manasteel_pick", 3));
		toolMelting(withCondition(consumer, new ModLoadedCondition("botania")), new NameFluid("elementium", MaterialisResources.ELEMENTIUM_FLUID.FLUID.get()),
				new ToolValue("botania:elementium_pickaxe", 3));
		toolMelting(withCondition(consumer, new ModLoadedCondition("botania")), new NameFluid("terrasteel", MaterialisResources.MANASTEEL_FLUID.FLUID.get(), new FluidStack(MaterialisResources.TERRASTEEL_FLUID.FLUID.get(), FluidValues.INGOT * 3), true),
				new ToolValue("botania:terrasteel_helmet", 5),
				new ToolValue("botania:terrasteel_chestplate", 8),
				new ToolValue("botania:terrasteel_leggings", 7),
				new ToolValue("botania:terrasteel_boots", 4));
		toolMelting(withCondition(consumer, new ModLoadedCondition("botania")), new NameFluid("terrasteel", MaterialisResources.TERRASTEEL_FLUID.FLUID.get()),
				new ToolValue("botania:terra_pick", 4),
				new ToolValue("botania:terra_axe", 4),
				new ToolValue("botania:terra_sword", 2));

		//mythicbotany stuff
		metalTagCasting(consumer, MaterialisResources.ALFSTEEL_FLUID.OBJECT, "alfsteel", castingFolder, false);
		metalMelting(consumer, MaterialisResources.ALFSTEEL_FLUID.FLUID.get(), "alfsteel", false, meltingFolder, true);
		MeltingRecipeBuilder.melting(ItemNameIngredient.from(new ResourceLocation("mythicbotany", "alfsteel_armor_upgrade")), MaterialisResources.ALFSTEEL_FLUID.FLUID.get(), FluidValues.INGOT * 2, 1.0f).build(withCondition(consumer, new ModLoadedCondition("mythicbotany")), modResource(meltingFolder + "alfsteel_double_ingot"));
		toolMelting(withCondition(consumer, new ModLoadedCondition("mythicbotany")), new NameFluid("alfsteel", MaterialisResources.TERRASTEEL_FLUID.FLUID.get(), new FluidStack(MaterialisResources.ALFSTEEL_FLUID.FLUID.get(), FluidValues.INGOT * 2), true),
				new ToolValue("mythicbotany:alfsteel_helmet", 3),
				new ToolValue("mythicbotany:alfsteel_chestplate", 3),
				new ToolValue("mythicbotany:alfsteel_leggings", 3),
				new ToolValue("mythicbotany:alfsteel_boots", 3));
		toolMelting(withCondition(consumer, new ModLoadedCondition("mythicbotany")), new NameFluid("alfsteel", MaterialisResources.TERRASTEEL_FLUID.FLUID.get(), new FluidStack(MaterialisResources.ALFSTEEL_FLUID.FLUID.get(), FluidValues.INGOT), true),
				new ToolValue("mythicbotany:alfsteel_pick", 4),
				new ToolValue("mythicbotany:alfsteel_axe", 4),
				new ToolValue("mythicbotany:alfsteel_sword", 2));

		//draconic evolution stuff
		metalTagCasting(consumer, MaterialisResources.DRACONIUM_FLUID.OBJECT, "draconium", castingFolder, false);
		metalMelting(consumer, MaterialisResources.DRACONIUM_FLUID.FLUID.get(), "draconium", true, meltingFolder, true, MaterialisByproduct.PLATINUM, MaterialisByproduct.COBALT);
		metalTagCasting(consumer, MaterialisResources.AWAKENED_DRACONIUM_FLUID.OBJECT, "draconium_awakened", castingFolder, false);
		metalMelting(consumer, MaterialisResources.AWAKENED_DRACONIUM_FLUID.FLUID.get(), "draconium_awakened", false, meltingFolder, true);



		//materials
		metalMaterialRecipe(consumer, MaterialisMaterials.fairy, materialFolder, "fairy", false);
		metalMaterialRecipe(consumer, MaterialisMaterials.brass, materialFolder, "brass", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.aluminum, materialFolder, "aluminum", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.nickel, materialFolder, "nickel", true);
		//metalMaterialRecipe(consumer, MaterialisMaterials.platinum, materialFolder, "platinum", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.uranium, materialFolder, "uranium", true);
		//metalMaterialRecipe(consumer, MaterialisMaterials.tungsten, materialFolder, "tungsten", true);
		//metalMaterialRecipe(consumer, MaterialisMaterials.invar, materialFolder, "invar", true);
		materialRecipe(withCondition(consumer, new ModLoadedCondition("create")), MaterialisMaterials.roseQuartz, ItemNameIngredient.from(new ResourceLocation("create", "rose_quartz"), new ResourceLocation("create", "polished_rose_quartz")), 1, 1, materialFolder + "rose_quartz");
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
		//metalMaterialRecipe(consumer, MaterialisMaterials.osmium, materialFolder, "osmium", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.refinedObsidian, materialFolder, "refined_obsidian", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.refinedGlowstone, materialFolder, "refined_glowstone", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.psimetal, materialFolder, "psimetal", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.ebonyPsimetal, materialFolder, "ebony_psimetal", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.ivoryPsimetal, materialFolder, "ivory_psimetal", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.iesnium, materialFolder, "iesnium", true);
		materialRecipe(withCondition(consumer, new ModLoadedCondition("botania")), MaterialisMaterials.livingwood, ItemNameIngredient.from(new ResourceLocation("botania", "livingwood")), 1, 1, materialFolder + "livingwood");
		materialRecipe(withCondition(consumer, new ModLoadedCondition("botania")), MaterialisMaterials.livingwood, ItemNameIngredient.from(new ResourceLocation("botania", "livingwood_twig")), 2, 1, materialFolder + "livingwood_twig");
		materialRecipe(withCondition(consumer, new ModLoadedCondition("botania")), MaterialisMaterials.dreamwood, ItemNameIngredient.from(new ResourceLocation("botania", "dreamwood")), 1, 1, materialFolder + "dreamwood");
		materialRecipe(withCondition(consumer, new ModLoadedCondition("botania")), MaterialisMaterials.dreamwood, ItemNameIngredient.from(new ResourceLocation("botania", "dreamwood_twig")), 2, 1, materialFolder + "dreamwood_twig");
		metalMaterialRecipe(consumer, MaterialisMaterials.manasteel, materialFolder, "manasteel", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.elementium, materialFolder, "elementium", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.terrasteel, materialFolder, "terrasteel", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.alfsteel, materialFolder, "alfsteel", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.draconium, materialFolder, "draconium", true);
		metalMaterialRecipe(consumer, MaterialisMaterials.awakenedDraconium, materialFolder, "draconium_awakened", true);

		//material casting
		materialMeltingCasting(consumer, MaterialisMaterials.fairy, MaterialisResources.FAIRY_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.brass, TinkerFluids.moltenBrass, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.aluminum, TinkerFluids.moltenAluminum, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.nickel, TinkerFluids.moltenNickel, materialFolder);
		//materialMeltingCasting(consumer, MaterialisMaterials.platinum, TinkerFluids.moltenPlatinum, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.uranium, TinkerFluids.moltenUranium, materialFolder);
		//materialMeltingCasting(consumer, MaterialisMaterials.tungsten, TinkerFluids.moltenTungsten, materialFolder);
		//materialMeltingCasting(consumer, MaterialisMaterials.invar, TinkerFluids.moltenInvar, materialFolder);
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
		materialComposite(withCondition(consumer, new AndCondition(tagCondition("ingots/cloggrum"), tagCondition("ingots/forgotten_metal"))), MaterialisMaterials.cloggrum, MaterialisMaterials.forgottenMetal, MaterialisResources.FORGOTTEN_FLUID.OBJECT, FluidValues.NUGGET * 3, false, compositeFolder);
		MaterialMeltingRecipeBuilder.material(MaterialisMaterials.forgottenMetal, new FluidStack(MaterialisResources.FORGOTTEN_FLUID.FLUID.get(), FluidValues.NUGGET * 3))
		.build(withCondition(consumer, new AndCondition(tagCondition("ingots/cloggrum"), tagCondition("ingots/forgotten_metal"))), modResource(materialFolder + "melting/forgotten_metal"));
		//materialMeltingCasting(consumer, MaterialisMaterials.osmium, TinkerFluids.moltenOsmium, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.refinedObsidian, TinkerFluids.moltenRefinedObsidian, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.refinedGlowstone, TinkerFluids.moltenRefinedGlowstone, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.psimetal, MaterialisResources.PSIMETAL_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.ebonyPsimetal, MaterialisResources.EBONY_PSIMETAL_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.ivoryPsimetal, MaterialisResources.IVORY_PSIMETAL_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.iesnium, MaterialisResources.IESNIUM_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.manasteel, MaterialisResources.MANASTEEL_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.elementium, MaterialisResources.ELEMENTIUM_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.terrasteel, MaterialisResources.TERRASTEEL_FLUID.OBJECT, materialFolder);
		materialComposite(withCondition(consumer, new ModLoadedCondition("mythicbotany")), MaterialisMaterials.terrasteel, MaterialisMaterials.alfsteel, MaterialisResources.ALFSTEEL_FLUID.OBJECT, FluidValues.NUGGET * 3, false, compositeFolder);
		MaterialMeltingRecipeBuilder.material(MaterialisMaterials.alfsteel, new FluidStack(MaterialisResources.ALFSTEEL_FLUID.FLUID.get(), FluidValues.NUGGET * 3))
		.build(withCondition(consumer, new ModLoadedCondition("mythicbotany")), modResource(materialFolder + "melting/alfsteel"));
		materialMeltingCasting(consumer, MaterialisMaterials.draconium, MaterialisResources.DRACONIUM_FLUID.OBJECT, materialFolder);
		materialMeltingCasting(consumer, MaterialisMaterials.awakenedDraconium, MaterialisResources.AWAKENED_DRACONIUM_FLUID.OBJECT, materialFolder);



		//modifiers
		ModifierRecipeBuilder.modifier(MaterialisModifiers.reapingModifier.get())
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("eidolon", "tattered_cloth"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("eidolon", "unholy_symbol"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("eidolon", "tattered_cloth"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("eidolon", "soul_shard"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("eidolon", "soul_shard"))))
		.addSalvage(RandomItem.range(ItemNameOutput.fromName(new ResourceLocation("eidolon", "soul_shard"), 2), 0))
		.addSalvage(RandomItem.range(ItemNameOutput.fromName(new ResourceLocation("eidolon", "tattered_cloth"), 2), 0))
		.setTools(TinkerTags.Items.MELEE)
		.setMaxLevel(1)
		.setSlots(SlotType.ABILITY, 1)
		.setGroup("materialis:eidolon")
		.includeUnarmed()
		.buildSalvage(consumer, prefix(MaterialisModifiers.reapingModifier, salvageFolder))
		.build(withCondition(consumer, new ModLoadedCondition("eidolon")), prefix(MaterialisModifiers.reapingModifier, modifierFolder));

		OverslimeModifierRecipeBuilder.modifier(MaterialisResources.PINK_SLIME_CRYSTAL.get(), 70)
		.build(withCondition(consumer, new ModLoadedCondition("industrialforegoing")), prefix(TinkerModifiers.overslime, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.runedModifier.get())
		.addInput(getTag("quark", "runes"))
		.addSalvage(RandomItem.chance(ItemNameOutput.fromName(new ResourceLocation("quark", "blank_rune")), 0.5f))
		.setTools(TinkerTags.Items.MODIFIABLE)
		.setMaxLevel(1)
		.setRequirements(ModifierMatch.entry(TinkerModifiers.shiny.get()))
		.setRequirementsError("recipe.materialis.modifier.runed_requirements")
		.setGroup("materialis:quark")
		.buildSalvage(consumer, prefix(MaterialisModifiers.runedModifier, salvageFolder))
		.build(ConsumerWrapperBuilder.wrap(MaterialisResources.runeModifierSerializer.get()).addCondition(new ModLoadedCondition("quark")).build(consumer), prefix(MaterialisModifiers.runedModifier, modifierFolder));

		Ingredient interactableWithDurability = new IngredientIntersection(Ingredient.of(TinkerTags.Items.DURABILITY), Ingredient.of(TinkerTags.Items.INTERACTABLE));
		ModifierRecipeBuilder.modifier(MaterialisModifiers.wrenchingModifier.get())
		.setTools(new IngredientWithout(interactableWithDurability, Ingredient.of(MaterialisItemTags.WRENCHING)))
		.addInput(SizedIngredient.of(MaterialIngredient.fromItem(MaterialisResources.WRENCH_HEAD.get())))
		.addInput(TinkerTags.Items.INGOTS_NETHERITE_SCRAP)
		.addInput(SizedIngredient.of(MaterialIngredient.fromItem(TinkerToolParts.toolBinding.get())))
		.addSalvage(Items.NETHERITE_SCRAP, 0.35f)
		.setMaxLevel(1)
		.setSlots(SlotType.ABILITY, 1)
		.buildSalvage(consumer, prefix(MaterialisModifiers.wrenchingModifier, salvageFolder))
		.build(consumer, prefix(MaterialisModifiers.wrenchingModifier, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.galvanizedModifier.get())
		.setTools(MaterialisItemTags.GALVANIZABLE)
		.addInput(Items.PHANTOM_MEMBRANE)
		.addInput(Tags.Items.INGOTS_IRON)
		.addSalvage(Items.IRON_INGOT, 0.7f)
		.addSalvage(Items.PHANTOM_MEMBRANE, 0.3f)
		.setSlots(SlotType.UPGRADE, 2)
		.buildSalvage(consumer, prefix(MaterialisModifiers.galvanizedModifier, salvageFolder))
		.build(consumer, prefix(MaterialisModifiers.galvanizedModifier, modifierFolder));

		ModifierMatch wrenching = ModifierMatch.list(1, ModifierMatch.entry(MaterialisModifiers.wrenchingModifier.get()), ModifierMatch.entry(MaterialisModifiers.wrenchingModifierHidden.get()));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.thermalWrenchingModifier.get())
		.setTools(interactableWithDurability)
		.addInput(getTag("forge", "gears/nickel"))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("thermal", "wrench"))))
		.addInput(getTag("forge", "gears/nickel"))
		.addSalvage(getTag("forge", "gears/nickel"), 0, 2)
		.setRequirements(wrenching)
		.setRequirementsError("recipe.materialis.modifier.thermal_wrenching_requirements")
		.setMaxLevel(1)
		.setSlots(SlotType.UPGRADE, 1)
		.setGroup("materialis:thermal")
		.buildSalvage(consumer, prefix(MaterialisModifiers.thermalWrenchingModifier, salvageFolder))
		.build(withCondition(consumer, new ModLoadedCondition("thermal")), prefix(MaterialisModifiers.thermalWrenchingModifier, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.createWrenchingModifier.get())
		.setTools(interactableWithDurability)
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("create", "cogwheel"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("create", "wrench"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("create", "cogwheel"))))
		.addSalvage(RandomItem.range(ItemNameOutput.fromName(new ResourceLocation("create", "cogwheel"), 2), 0))
		.addSalvage(RandomItem.chance(ItemNameOutput.fromName(new ResourceLocation("create", "wrench")), 0.9f))
		.setRequirements(wrenching)
		.setRequirementsError("recipe.materialis.modifier.create_wrenching_requirements")
		.setMaxLevel(1)
		.setSlots(SlotType.UPGRADE, 1)
		.setGroup("materialis:create")
		.buildSalvage(consumer, prefix(MaterialisModifiers.createWrenchingModifier, salvageFolder))
		.build(withCondition(consumer, new ModLoadedCondition("create")), prefix(MaterialisModifiers.createWrenchingModifier, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.immersiveWrenchingModifier.get())
		.setTools(interactableWithDurability)
		.addInput(getTag("forge", "plates/copper"))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("immersiveengineering", "hammer"))))
		.addInput(getTag("forge", "plates/copper"))
		.addSalvage(getTag("forge", "plates/copper"), 0, 2)
		.setRequirements(wrenching)
		.setRequirementsError("recipe.materialis.modifier.immersive_wrenching_requirements")
		.setMaxLevel(1)
		.setSlots(SlotType.UPGRADE, 1)
		.setGroup("materialis:immersiveengineering")
		.buildSalvage(consumer, prefix(MaterialisModifiers.immersiveWrenchingModifier, salvageFolder))
		.build(withCondition(consumer, new ModLoadedCondition("immersiveengineering")), prefix(MaterialisModifiers.immersiveWrenchingModifier, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.pipeWrenchingModifier.get())
		.setTools(interactableWithDurability)
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("prettypipes", "pipe"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("prettypipes", "wrench"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("prettypipes", "pipe"))))
		.addSalvage(RandomItem.range(ItemNameOutput.fromName(new ResourceLocation("prettypipes", "pipe"), 2), 0))
		.addSalvage(RandomItem.chance(ItemNameOutput.fromName(new ResourceLocation("prettypipes", "wrench")), 0.9f))
		.setRequirements(wrenching)
		.setRequirementsError("recipe.materialis.modifier.pipe_wrenching_requirements")
		.setMaxLevel(1)
		.setSlots(SlotType.UPGRADE, 1)
		.setGroup("materialis:prettypipes")
		.buildSalvage(consumer, prefix(MaterialisModifiers.pipeWrenchingModifier, salvageFolder))
		.build(withCondition(consumer, new ModLoadedCondition("prettypipes")), prefix(MaterialisModifiers.pipeWrenchingModifier, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.psionizingRadiationModifier.get())
		.addInput(getTag("forge", "ingots/psimetal"))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("psi", "cad_socket_basic"))))
		.addInput(getTag("forge", "ingots/psimetal"))
		.addInput(getTag("forge", "gems/psigem"))
		.addInput(getTag("forge", "gems/psigem"))
		.addSalvage(RandomItem.range(ItemNameOutput.fromName(new ResourceLocation("psi", "psimetal"), 2), 0))
		.addSalvage(RandomItem.range(ItemNameOutput.fromName(new ResourceLocation("psi", "psigem"), 2), 0))
		.addSalvage(RandomItem.chance(ItemNameOutput.fromName(new ResourceLocation("psi", "cad_socket_basic")), 0.5f))
		.setTools(TinkerTags.Items.HELD)
		.setSlots(SlotType.ABILITY, 1)
		.setGroup("materialis:psi")
		.buildSalvage(consumer, prefix(MaterialisModifiers.psionizingRadiationModifier, salvageFolder))
		.build(withCondition(consumer, new ModLoadedCondition("psi")), prefix(MaterialisModifiers.psionizingRadiationModifier, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.spellCastingModifier.get())
		.addInput(getTag("forge", "ingots/ebony_psimetal"))
		.addInput(getTag("forge", "gems/psigem"))
		.addInput(getTag("forge", "ingots/ivory_psimetal"))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("psi", "cad_battery_ultradense"))))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("psi", "cad_assembly_psimetal"))))
		.addSalvage(RandomItem.chance(ItemNameOutput.fromName(new ResourceLocation("psi", "ebony_psimetal")), 0.4f))
		.addSalvage(RandomItem.chance(ItemNameOutput.fromName(new ResourceLocation("psi", "ivory_psimetal")), 0.4f))
		.addSalvage(RandomItem.chance(ItemNameOutput.fromName(new ResourceLocation("psi", "psigem")), 0.2f))
		.addSalvage(RandomItem.chance(ItemNameOutput.fromName(new ResourceLocation("psi", "cad_battery_ultradense")), 0.3f))
		.addSalvage(RandomItem.chance(ItemNameOutput.fromName(new ResourceLocation("psi", "cad_assembly_psimetal")), 0.6f))
		.setRequirements(ModifierMatch.list(1, ModifierMatch.entry(MaterialisModifiers.psionizingRadiationModifier.get()), ModifierMatch.entry(MaterialisModifiers.lesserPsionizingRadiationModifier.get())))
		.setRequirementsError("recipe.materialis.modifier.casting_requirements")
		.setTools(TinkerTags.Items.HELD)
		.setSlots(SlotType.ABILITY, 1)
		.setGroup("materialis:psi")
		.buildSalvage(consumer, prefix(MaterialisModifiers.spellCastingModifier, salvageFolder))
		.build(withCondition(consumer, new ModLoadedCondition("psi")), prefix(MaterialisModifiers.spellCastingModifier, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.spellSocketModifier.get())
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("psi", "cad_socket_basic"))))
		.addSalvage(RandomItem.chance(ItemNameOutput.fromName(new ResourceLocation("psi", "cad_socket_basic")), 0.4f))
		.setTools(TinkerTags.Items.MODIFIABLE)
		.setMaxLevel(5)
		.setSlots(SlotType.UPGRADE, 1)
		.setGroup("materialis:psi")
		.buildSalvage(consumer, prefix(MaterialisModifiers.spellSocketModifier, salvageFolder))
		.build(withCondition(consumer, new ModLoadedCondition("psi")), prefix(MaterialisModifiers.spellSocketModifier, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.colorizedModifier.get())
		.addInput(getTag("psi", "colorizers"))
		.addSalvage(RandomItem.chance(ItemNameOutput.fromName(new ResourceLocation("psi", "psidust")), 0.3f))
		.addSalvage(Items.IRON_INGOT, 0.7f)
		.setTools(TinkerTags.Items.MODIFIABLE)
		.setMaxLevel(1)
		.setGroup("materialis:psi")
		.buildSalvage(consumer, prefix(MaterialisModifiers.colorizedModifier, salvageFolder))
		.build(ConsumerWrapperBuilder.wrap(MaterialisResources.colorizerModifierSerializer.get()).addCondition(new ModLoadedCondition("psi")).build(consumer), prefix(MaterialisModifiers.colorizedModifier, modifierFolder));

		ICondition capacitorIsUseful = new ModLoadedCondition("draconicevolution");

		ModifierRecipeBuilder.modifier(MaterialisModifiers.capacitorModifier.get())
		.addInput(getTag("forge", "ingots/lead"))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("thermal", "rf_coil"))))
		.addInput(getTag("forge", "ingots/lead"))
		.addSalvage(getTag("forge", "dusts/redstone"), 0, 1)
		.setTools(TinkerTags.Items.MODIFIABLE)
		.setMaxLevel(5)
		.setSlots(SlotType.UPGRADE, 1)
		.setGroup("materialis:flux")
		.buildSalvage(consumer, prefix(MaterialisModifiers.capacitorModifier, salvageFolder))
		.build(withCondition(consumer, new OrCondition(capacitorIsUseful, new ModLoadedCondition("thermal"))), prefix(MaterialisModifiers.capacitorModifier, modifierFolder + "thermal_"));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.capacitorModifier.get())
		.addInput(getTag("forge", "ingots/lead"))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("immersiveengineering", "wirecoil_copper"))))
		.addInput(getTag("forge", "dusts/redstone"))
		.setTools(TinkerTags.Items.MODIFIABLE)
		.setMaxLevel(5)
		.setSlots(SlotType.UPGRADE, 1)
		.setGroup("materialis:flux")
		.build(withCondition(consumer, new OrCondition(capacitorIsUseful, new ModLoadedCondition("immersiveengineering"))), prefix(MaterialisModifiers.capacitorModifier, modifierFolder + "immersiveengineering_"));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.capacitorModifier.get())
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("mekanism", "alloy_infused"))))
		.addInput(getTag("forge", "ingots/gold"))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("mekanism", "alloy_infused"))))
		.setTools(TinkerTags.Items.MODIFIABLE)
		.setMaxLevel(5)
		.setSlots(SlotType.UPGRADE, 1)
		.setGroup("materialis:flux")
		.build(withCondition(consumer, new OrCondition(capacitorIsUseful, new ModLoadedCondition("mekanism"))), prefix(MaterialisModifiers.capacitorModifier, modifierFolder + "mekanism_"));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.capacitorModifier.get())
		.addInput(getTag("forge", "dusts/redstone"))
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("draconicevolution", "draconium_core"))))
		.addInput(getTag("forge", "dusts/redstone"))
		.setTools(TinkerTags.Items.MODIFIABLE)
		.setMaxLevel(5)
		.setSlots(SlotType.UPGRADE, 1)
		.setGroup("materialis:flux")
		.build(withCondition(consumer, capacitorIsUseful), prefix(MaterialisModifiers.capacitorModifier, modifierFolder + "draconicevolution_"));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.psionizingRadiationModifierSensor.get())
		.setTools(MaterialisItemTags.SENSOR_SLOTTABLE)
		.addInput(MaterialisItemTags.SENSOR)
		.addSalvage(getTag("forge", "ingots/psimetal"), 0, 2)
		.setMaxLevel(1)
		.setSlots(MaterialisResources.SENSOR_SLOT, 1)
		.buildSalvage(withCondition(consumer, new ModLoadedCondition("psi")), prefix(MaterialisModifiers.psionizingRadiationModifierSensor, salvageFolder))
		.build(ConsumerWrapperBuilder.wrap(MaterialisResources.sensorModifierSerializer.get()).addCondition(new ModLoadedCondition("psi")).build(consumer), prefix(MaterialisModifiers.psionizingRadiationModifierSensor, modifierFolder));

		ModifierRecipeBuilder.modifier(MaterialisModifiers.engineersGogglesModifier.get())
		.setTools(TinkerTags.Items.HELMETS)
		.addInput(SizedIngredient.of(ItemNameIngredient.from(new ResourceLocation("create", "goggles"))))
		.addSalvage(RandomItem.chance(ItemNameOutput.fromName(new ResourceLocation("create", "goggles")), 0.9f))
		.setMaxLevel(1)
		.setSlots(SlotType.UPGRADE, 1)
		.setGroup("materialis:create")
		.buildSalvage(consumer, prefix(MaterialisModifiers.engineersGogglesModifier, salvageFolder))
		.build(withCondition(consumer, new ModLoadedCondition("create")), prefix(MaterialisModifiers.engineersGogglesModifier, modifierFolder));
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

	public void multipleToolMelting(Consumer<IFinishedRecipe> consumer, String modID, NameFluid[] names, ToolValue... values) {
		for (NameFluid name : names) {
			for(ToolValue value : values)
				toolMelting(consumer, name, new ToolValue(modID + ":" + name.name + value.toolID, value.ingotValue));
		}
	}

	public void toolMelting(Consumer<IFinishedRecipe> consumer, NameFluid name, ToolValue... values) {
		for (ToolValue value : values) {
			ResourceLocation toolLocation = new ResourceLocation(value.toolID);
			if (name.byproduct == null) {
				MeltingRecipeBuilder.melting(ItemNameIngredient.from(toolLocation), name.fluid, (int) (FluidValues.INGOT * value.ingotValue))
				.setDamagable()
				.build(consumer, modResource("smeltery/melting/metal/tools/" + name.name + "/" + toolLocation.getPath()));
			} else {
				if (!name.byproductIsMain) {
					MeltingRecipeBuilder.melting(ItemNameIngredient.from(toolLocation), name.fluid, (int) (FluidValues.INGOT * value.ingotValue))
					.addByproduct(name.byproduct)
					.setDamagable()
					.build(consumer, modResource("smeltery/melting/metal/tools/" + name.name + "/" + toolLocation.getPath()));
				} else {
					MeltingRecipeBuilder.melting(ItemNameIngredient.from(toolLocation), name.byproduct.getFluid(), name.byproduct.getAmount())
					.addByproduct(new FluidStack(name.fluid, (int) (FluidValues.INGOT * value.ingotValue)))
					.setDamagable()
					.build(consumer, modResource("smeltery/melting/metal/tools/" + name.name + "/" + toolLocation.getPath()));
				}
			}
		}
	}

	public static class NameFluid {

		public String name;
		public Fluid fluid;
		public FluidStack byproduct = null;
		public boolean byproductIsMain = false;

		public NameFluid(String name, Fluid fluid) {
			this.name = name;
			this.fluid = fluid;
		}

		public NameFluid(String name, Fluid fluid, FluidStack byproduct, boolean byproductIsMain) {
			this(name, fluid);
			this.byproduct = byproduct;
			this.byproductIsMain = byproductIsMain;
		}
	}

	public static class ToolValue {

		public String toolID;
		public Double ingotValue;

		public ToolValue(String toolID, double ingotValue) {
			this.toolID = toolID;
			this.ingotValue = ingotValue;
		}
	}
}
