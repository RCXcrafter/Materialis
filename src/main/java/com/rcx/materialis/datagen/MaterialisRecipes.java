package com.rcx.materialis.datagen;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.MaterialisResources.IngotWithBlockNNugget;
import com.rcx.materialis.util.MaterialisByproduct;

import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.Fluids;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.AndCondition;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import net.minecraftforge.common.crafting.conditions.TrueCondition;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.IForgeRegistryEntry;
import slimeknights.mantle.recipe.data.ConsumerWrapperBuilder;
import slimeknights.mantle.recipe.data.ItemNameIngredient;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.materials.MaterialId;
import slimeknights.tconstruct.library.materials.MaterialValues;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.material.CompositeCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.material.MaterialRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.molding.MoldingRecipeBuilder;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

public class MaterialisRecipes extends RecipeProvider implements IConditionBuilder {

	public MaterialisRecipes(DataGenerator gen) {
		super(gen);
	}

	public void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		for (IngotWithBlockNNugget material : MaterialisResources.materialList) {
			blockIngotNuggetCompression(consumer, material.name, material.BLOCK_ITEM.get(), material.INGOT.get(), material.NUGGET.get());
		}

		String castingFolder = "smeltery/casting/metal/";
		String meltingFolder = "smeltery/melting/metal/";
		String alloyFolder = "smeltery/alloys/";
		addMetalCastingRecipe(consumer, MaterialisResources.FAIRY_FLUID.FLUID, MaterialisResources.FAIRY_INGOT.BLOCK.get(), MaterialisResources.FAIRY_INGOT.INGOT.get(), MaterialisResources.FAIRY_INGOT.NUGGET.get(), castingFolder, "fairy");
		addMetalMelting(consumer, MaterialisResources.FAIRY_FLUID.FLUID.get(), "fairy", false, meltingFolder, false);
		//addMetalCastingRecipe(consumer, MaterialisResources.RED_AURUM_FLUID.FLUID, MaterialisResources.RED_AURUM_INGOT.BLOCK.get(), MaterialisResources.RED_AURUM_INGOT.INGOT.get(), MaterialisResources.RED_AURUM_INGOT.NUGGET.get(), metalFolder, "red_aurum");
		//addMetalMelting(consumer, MaterialisResources.RED_AURUM_FLUID.FLUID.get(), "red_aurum", false, metalFolder, false);
		//addMetalCastingRecipe(consumer, MaterialisResources.DRULLOY_FLUID.FLUID, MaterialisResources.DRULLOY_INGOT.BLOCK.get(), MaterialisResources.DRULLOY_INGOT.INGOT.get(), MaterialisResources.DRULLOY_INGOT.NUGGET.get(), metalFolder, "drulloy");
		//addMetalMelting(consumer, MaterialisResources.DRULLOY_FLUID.FLUID.get(), "drulloy", false, metalFolder, false);
		//addMetalCastingRecipe(consumer, MaterialisResources.POKEFENNIUM_FLUID.FLUID, MaterialisResources.POKEFENNIUM_INGOT.BLOCK.get(), MaterialisResources.POKEFENNIUM_INGOT.INGOT.get(), MaterialisResources.POKEFENNIUM_INGOT.NUGGET.get(), metalFolder, "pokefennium");
		//addMetalMelting(consumer, MaterialisResources.POKEFENNIUM_FLUID.FLUID.get(), "pokefennium", false, metalFolder, false);
		//addMetalCastingRecipe(consumer, MaterialisResources.ALUMITE_FLUID.FLUID, MaterialisResources.ALUMITE_INGOT.BLOCK.get(), MaterialisResources.ALUMITE_INGOT.INGOT.get(), MaterialisResources.ALUMITE_INGOT.NUGGET.get(), metalFolder, "alumite");
		//addMetalMelting(consumer, MaterialisResources.ALUMITE_FLUID.FLUID.get(), "alumite", false, metalFolder, false);

		AlloyRecipeBuilder.alloy(MaterialisResources.FAIRY_FLUID.FLUID.get(), MaterialValues.INGOT)
		.addInput(TinkerFluids.moltenGold.get(), MaterialValues.INGOT)
		.addInput(TinkerFluids.liquidSoul.get(), MaterialValues.GLASS_BLOCK)
		.addInput(Fluids.MILK, 1000)
		.build(consumer, prefixR(MaterialisResources.FAIRY_FLUID.FLUID, alloyFolder));

		//create stuff
		addMetalOptionalCasting(consumer, MaterialisResources.REFINED_RADIANCE_FLUID.FLUID.get(), "refined_radiance", castingFolder);
		addMetalMelting(consumer, MaterialisResources.REFINED_RADIANCE_FLUID.FLUID.get(), "refined_radiance", false, meltingFolder, true);
		addMetalOptionalCasting(consumer, MaterialisResources.SHADOW_STEEL_FLUID.FLUID.get(), "shadow_steel", castingFolder);
		addMetalMelting(consumer, MaterialisResources.SHADOW_STEEL_FLUID.FLUID.get(), "shadow_steel", false, meltingFolder, true);

		//eidolon stuff
		addMetalOptionalCasting(consumer, MaterialisResources.ARCANE_GOLD_FLUID.FLUID.get(), "arcane_gold", castingFolder);
		addMetalMelting(consumer, MaterialisResources.ARCANE_GOLD_FLUID.FLUID.get(), "arcane_gold", false, meltingFolder, true);

		addCastCastingRecipe(withCondition(consumer, tagConditionDomain("materialis", "inlays")),  getTag("materialis", "inlays"),  MaterialisResources.INLAY_CAST,  castingFolder);
		addOptionalCastingWithCastDomain(consumer, TinkerFluids.moltenPewter.get(), MaterialValues.INGOT * 2, MaterialisResources.INLAY_CAST, "inlays", "inlay", "pewter", castingFolder, "materialis");
		addOptionalCastingWithCastDomain(consumer, MaterialisResources.ARCANE_GOLD_FLUID.FLUID.get(), MaterialValues.INGOT * 2, MaterialisResources.INLAY_CAST, "inlays", "inlay", "arcane_gold", castingFolder, "materialis");

		MeltingRecipeBuilder.melting(Ingredient.of(getTag("materialis", "inlays/pewter")), TinkerFluids.moltenPewter.get(), MaterialValues.INGOT * 2, 1.5f).build(withCondition(consumer, tagConditionDomain("materialis", "inlays/pewter")), location(meltingFolder + "pewter_inlay"));
		MeltingRecipeBuilder.melting(Ingredient.of(getTag("materialis", "inlays/arcane_gold")), MaterialisResources.ARCANE_GOLD_FLUID.FLUID.get(), MaterialValues.INGOT * 2, 1.5f).build(withCondition(consumer, tagConditionDomain("materialis", "inlays/arcane_gold")), location(meltingFolder + "arcane_gold_inlay"));

		MeltingRecipeBuilder.melting(ItemNameIngredient.from(new ResourceLocation("eidolon", "pewter_blend")), TinkerFluids.moltenPewter.get(), MaterialValues.INGOT, 1.0f).build(withCondition(consumer, new ModLoadedCondition("eidolon")), location(meltingFolder + "pewter_blend"));

		//aquaculture stuff
		addMetalOptionalCasting(consumer, MaterialisResources.NEPTUNIUM_FLUID.FLUID.get(), "neptunium", castingFolder);
		addMetalMelting(consumer, MaterialisResources.NEPTUNIUM_FLUID.FLUID.get(), "neptunium", false, meltingFolder, true);

		//mystical world stuff
		addMetalOptionalCasting(consumer, MaterialisResources.QUICKSILVER_FLUID.FLUID.get(), "quicksilver", castingFolder);
		addMetalMelting(consumer, MaterialisResources.QUICKSILVER_FLUID.FLUID.get(), "quicksilver", true, meltingFolder, true, MaterialisByproduct.ZINC, MaterialisByproduct.TIN);

		//astral sorcery stuff
		addMetalOptionalCasting(consumer, MaterialisResources.STARMETAL_FLUID.FLUID.get(), "starmetal", castingFolder);
		addMetalMelting(consumer, MaterialisResources.STARMETAL_FLUID.FLUID.get(), "starmetal", false, meltingFolder, true); //the ore recipe is defined manually

		//industrial foregoing stuff
		addMetalOptionalCasting(consumer, MaterialisResources.PINK_SLIME_FLUID.FLUID.get(), "pink_slime", castingFolder);
		addMetalMelting(consumer, MaterialisResources.PINK_SLIME_FLUID.FLUID.get(), "pink_slime", false, meltingFolder, true);

		Consumer<IFinishedRecipe> industrialForegoingLoaded = withCondition(consumer, new ModLoadedCondition("industrialforegoing"));
		AlloyRecipeBuilder.alloy(MaterialisResources.PINK_SLIME_FLUID.FLUID.get(), MaterialValues.INGOT)
		.addInput(TinkerFluids.moltenGold.get(), MaterialValues.INGOT * 2)
		.addInput(TinkerFluids.moltenIron.get(), MaterialValues.INGOT * 2)
		.addInput(MaterialisFluidTags.LIQUID_PINK_SLIME, 1000)
		.build(industrialForegoingLoaded, prefixR(MaterialisResources.PINK_SLIME_FLUID.FLUID, alloyFolder));

		CookingRecipeBuilder.blasting(Ingredient.of(MaterialisItemTags.PINK_SLIME), MaterialisResources.PINK_SLIME_CRYSTAL.get(), 1.0f, 400)
		.unlockedBy("has_item", has(MaterialisItemTags.PINK_SLIME))
		.save(industrialForegoingLoaded, new ResourceLocation(Materialis.modID, "pink_slime_crystal_blasting"));

		//undergarden stuff
		addMetalOptionalCasting(consumer, MaterialisResources.CLOGGRUM_FLUID.FLUID.get(), "cloggrum", castingFolder);
		addMetalMelting(consumer, MaterialisResources.CLOGGRUM_FLUID.FLUID.get(), "cloggrum", true, meltingFolder, true, MaterialisByproduct.FROSTSTEEL);
		addMetalOptionalCasting(consumer, MaterialisResources.FROSTSTEEL_FLUID.FLUID.get(), "froststeel", castingFolder);
		addMetalMelting(consumer, MaterialisResources.FROSTSTEEL_FLUID.FLUID.get(), "froststeel", true, meltingFolder, true, MaterialisByproduct.UTHERIUM);
		addMetalOptionalCasting(consumer, MaterialisResources.UTHERIUM_FLUID.FLUID.get(), "utherium", castingFolder);
		addMetalMelting(consumer, MaterialisResources.UTHERIUM_FLUID.FLUID.get(), "utherium", true, meltingFolder, true, MaterialisByproduct.REGALIUM);
		addMetalOptionalCasting(consumer, MaterialisResources.FORGOTTEN_FLUID.FLUID.get(), "forgotten_metal", castingFolder);
		addMetalMelting(consumer, MaterialisResources.FORGOTTEN_FLUID.FLUID.get(), "forgotten_metal", false, meltingFolder, true);
		addMetalOptionalCasting(consumer, MaterialisResources.REGALIUM_FLUID.FLUID.get(), "regalium", castingFolder);
		addMetalMelting(consumer, MaterialisResources.REGALIUM_FLUID.FLUID.get(), "regalium", true, meltingFolder, true, MaterialisByproduct.CLOGGRUM);
		MeltingRecipeBuilder.melting(ItemNameIngredient.from(new ResourceLocation("undergarden", "utheric_shard")), MaterialisResources.UTHERIUM_FLUID.FLUID.get(), MaterialValues.NUGGET / 4, 1.0f).build(withCondition(consumer, new ModLoadedCondition("undergarden")), location(meltingFolder + "utheric_shard"));

		//mekanism stuff
		addMetalOptionalCasting(consumer, MaterialisResources.REFINED_OBSIDIAN_FLUID.FLUID.get(), "refined_obsidian", castingFolder);
		addMetalMelting(consumer, MaterialisResources.REFINED_OBSIDIAN_FLUID.FLUID.get(), "refined_obsidian", false, meltingFolder, true);
		addMetalOptionalCasting(consumer, MaterialisResources.REFINED_GLOWSTONE_FLUID.FLUID.get(), "refined_glowstone", castingFolder);
		addMetalMelting(consumer, MaterialisResources.REFINED_GLOWSTONE_FLUID.FLUID.get(), "refined_glowstone", false, meltingFolder, true);



		//materials
		String compositeFolder = "tools/parts/composite/";
		addIngotMaterialRepairs(consumer, MaterialisMaterials.fairy);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.brass);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.aluminum);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.uranium);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.tungsten);
		addMaterialRepairs(withCondition(consumer, new ModLoadedCondition("create")), MaterialisMaterials.roseQuartz, ItemNameIngredient.from(new ResourceLocation("create", "polished_rose_quartz")), 1, 1);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.refinedRadiance);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.shadowSteel);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.pewter);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.arcaneGold);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.neptunium);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.quicksilver);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.starmetal);
		addMaterialRepairs(withCondition(consumer, tagCondition("plastic")), MaterialisMaterials.plastic, Ingredient.of(ItemTags.bind("forge:plastic")), 1, 1);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.pinkSlime);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.cloggrum);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.froststeel);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.utherium);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.forgottenMetal);
		CompositeCastingRecipeBuilder.table(MaterialisMaterials.cloggrum, MaterialisMaterials.forgottenMetal)
		.setFluid(new FluidStack(MaterialisResources.FORGOTTEN_FLUID.FLUID.get(), MaterialValues.INGOT))
		.build(withCondition(consumer, new AndCondition(tagCondition("ingots/cloggrum"), tagCondition("ingots/forgotten_metal"))), location(compositeFolder + "forgotten_metal"));
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.osmium);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.refinedObsidian);
		addConditionalIngotMaterialRepairs(consumer, MaterialisMaterials.refinedGlowstone);
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

	private void addIngotMaterialRepairs(Consumer<IFinishedRecipe> consumer, MaterialId material) {
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
	}

	/* Helpers */ //stolen from tinkers construct

	/**
	 * Base logic for {@link  #addMetalMelting(Consumer, Fluid, String, boolean, String, boolean, MaterialisByproduct...)}
	 * @param consumer    Recipe consumer
	 * @param fluid       Fluid to melt into
	 * @param amount      Amount to melt into
	 * @param tagName     Input tag
	 * @param factor      Melting factor
	 * @param recipePath  Recipe output name
	 * @param isOptional  If true, recipe is optional
	 */
	private static void addMetalBase(Consumer<IFinishedRecipe> consumer, Fluid fluid, int amount, String tagName, float factor, String recipePath, boolean isOptional) {
		Consumer<IFinishedRecipe> wrapped = isOptional ? withCondition(consumer, tagCondition(tagName)) : consumer;
		MeltingRecipeBuilder.melting(Ingredient.of(getTag("forge", tagName)), fluid, amount, factor)
		.build(wrapped, location(recipePath));
	}

	/**
	 * Adds a basic ingot, nugget, block, ore melting recipe set
	 * @param consumer    Recipe consumer
	 * @param fluid       Fluid result
	 * @param name        Resource name for tags
	 * @param hasOre      If true, adds recipe for melting the ore
	 * @param folder      Recipe folder
	 * @param isOptional  If true, this recipe is entirely optional
	 * @param byproducts  List of byproduct options for this metal, first one that is present will be used
	 */
	private void addMetalMelting(Consumer<IFinishedRecipe> consumer, Fluid fluid, String name, boolean hasOre, String folder, boolean isOptional, MaterialisByproduct... byproducts) {
		String prefix = folder + "/" + name + "/";
		addMetalBase(consumer, fluid, MaterialValues.METAL_BLOCK, "storage_blocks/" + name, 3.0f, prefix + "block", isOptional);
		addMetalBase(consumer, fluid, MaterialValues.INGOT, "ingots/" + name, 1.0f, prefix + "ingot", isOptional);
		addMetalBase(consumer, fluid, MaterialValues.NUGGET, "nuggets/" + name, 1 / 3f, prefix + "nugget", isOptional);
		if (hasOre) {
			addOreMelting(consumer, fluid, MaterialValues.INGOT, "ores/" + name, 1.5f, prefix + "ore", isOptional, byproducts);
		}
		// dust is always optional, as we don't do dust
		addMetalBase(consumer, fluid, MaterialValues.INGOT,      "dusts/" + name,       0.75f, prefix + "dust",       true);
		addMetalBase(consumer, fluid, MaterialValues.INGOT,      "plates/" + name,      1.0f,  prefix + "plates",     true);
		addMetalBase(consumer, fluid, MaterialValues.INGOT * 4,  "gears/" + name,       2.0f,  prefix + "gear",       true);
		addMetalBase(consumer, fluid, MaterialValues.NUGGET * 3, "coins/" + name,       2/3f,  prefix + "coin",       true);
		addMetalBase(consumer, fluid, MaterialValues.INGOT / 2,  "rods/" + name,        1/5f,  prefix + "rod",        true);
		addMetalBase(consumer, fluid, MaterialValues.INGOT,      "sheetmetals/" + name, 1.0f,  prefix + "sheetmetal", true);
	}

	/**
	 * Base logic for {@link  #addMetalMelting(Consumer, Fluid, String, boolean, String, boolean, MaterialisByproduct...)}
	 * @param consumer    Recipe consumer
	 * @param fluid       Fluid to melt into
	 * @param amount      Amount to melt into
	 * @param tagName     Input tag
	 * @param factor      Melting factor
	 * @param recipePath  Recipe output name
	 * @param isOptional  If true, recipe is optional
	 * @param byproducts  List of byproduct options for this metal, first one that is present will be used
	 */
	private void addOreMelting(Consumer<IFinishedRecipe> consumer, Fluid fluid, int amount, String tagName, float factor, String recipePath, boolean isOptional, MaterialisByproduct... byproducts) {
		Consumer<IFinishedRecipe> wrapped = isOptional ? withCondition(consumer, tagCondition(tagName)) : consumer;
		Supplier<MeltingRecipeBuilder> supplier = () -> MeltingRecipeBuilder.melting(Ingredient.of(getTag("forge", tagName)), fluid, amount, factor).setOre();
		ResourceLocation location = location(recipePath);

		// if no byproducts, just build directly
		if (byproducts.length == 0) {
			supplier.get().build(wrapped, location);
			// if first option is always present, only need that one
		} else if (byproducts[0].isAlwaysPresent()) {
			supplier.get()
			.addByproduct(new FluidStack(byproducts[0].getFluid(), byproducts[0].getNuggets()))
			.build(wrapped, location);
		} else {
			// multiple options, will need a conditonal recipe
			ConditionalRecipe.Builder builder = ConditionalRecipe.builder();
			boolean alwaysPresent = false;
			for (MaterialisByproduct byproduct : byproducts) {
				builder.addCondition(tagCondition("ingots/" + byproduct.getName()));
				builder.addRecipe(supplier.get().addByproduct(new FluidStack(byproduct.getFluid(), byproduct.getNuggets()))::build);
				// found an always present byproduct? we are done
				alwaysPresent = byproduct.isAlwaysPresent();
				if (alwaysPresent) {
					break;
				}
			}
			// not always present? add a recipe with no byproducts as a final fallback
			if (!alwaysPresent) {
				builder.addCondition(TrueCondition.INSTANCE);
				builder.addRecipe(supplier.get()::build);
			}
			builder.build(wrapped, location);
		}
	}


	/* Casting */

	/** Gets the temperature for a fluid in celsius */
	private int getTemperature(Supplier<? extends Fluid> supplier) {
		return supplier.get().getAttributes().getTemperature() - 300;
	}

	/**
	 * Adds a casting recipe for a block
	 * @param consumer  Recipe consumer
	 * @param fluid     Input fluid
	 * @param amount    Input amount
	 * @param block     Output block
	 * @param location  Output name
	 */
	private void addBlockCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, int amount, IItemProvider block, String location) {
		ItemCastingRecipeBuilder.basinRecipe(block)
		.setFluidAndTime(new FluidStack(fluid.get(), amount))
		.build(consumer, location(location));
	}

	/**
	 * Adds a casting recipe for a block
	 * @param consumer  Recipe consumer
	 * @param fluid     Input fluid
	 * @param amount    Input amount
	 * @param block     Output block
	 * @param location  Output name
	 */
	private void addBlockCastingRecipe(Consumer<IFinishedRecipe> consumer, ITag<Fluid> fluid, int temperature, int amount, IItemProvider block, String location) {
		ItemCastingRecipeBuilder.basinRecipe(block)
		.setFluidAndTime(temperature, fluid, amount)
		.build(consumer, location(location));
	}

	/**
	 * Adds a recipe to create the given seared block using molten clay on stone
	 * @param consumer  Recipe consumer
	 * @param block     Output block
	 * @param cast      Cast item
	 * @param location  Recipe location
	 */
	private static void addSearedCastingRecipe(Consumer<IFinishedRecipe> consumer, IItemProvider block, Ingredient cast, String location) {
		addSearedCastingRecipe(consumer, block, cast, MaterialValues.SLIMEBALL * 2, location);
	}

	/**
	 * Adds a recipe to create the given seared slab block using molten clay on stone
	 * @param consumer  Recipe consumer
	 * @param block     Output block
	 * @param cast      Cast item
	 * @param location  Recipe location
	 */
	private static void addSearedSlabCastingRecipe(Consumer<IFinishedRecipe> consumer, IItemProvider block, Ingredient cast, String location) {
		addSearedCastingRecipe(consumer, block, cast, MaterialValues.SLIMEBALL, location);
	}

	/**
	 * Adds a recipe to create the given seared block using molten clay on stone
	 * @param consumer  Recipe consumer
	 * @param block     Output block
	 * @param cast      Cast item
	 * @param amount    Amount of fluid needed
	 * @param location  Recipe location
	 */
	private static void addSearedCastingRecipe(Consumer<IFinishedRecipe> consumer, IItemProvider block, Ingredient cast, int amount, String location) {
		ItemCastingRecipeBuilder.basinRecipe(block)
		.setFluidAndTime(new FluidStack(TinkerFluids.moltenClay.get(), amount))
		.setCast(cast, true)
		.build(consumer, location(location));
	}

	/**
	 * Adds a recipe for casting using a cast
	 * @param consumer  Recipe consumer
	 * @param fluid     Recipe fluid
	 * @param amount    Fluid amount
	 * @param cast      Cast used
	 * @param output    Recipe output
	 * @param location  Recipe base
	 */
	private void addCastingWithCastRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, int amount, CastItemObject cast, IItemProvider output, String location) {
		FluidStack fluidStack = new FluidStack(fluid.get(), amount);
		ItemCastingRecipeBuilder.tableRecipe(output)
		.setFluidAndTime(fluidStack)
		.setCast(cast.getMultiUseTag(), false)
		.build(consumer, location(location + "_gold_cast"));
		ItemCastingRecipeBuilder.tableRecipe(output)
		.setFluidAndTime(fluidStack)
		.setCast(cast.getSingleUseTag(), true)
		.build(consumer, location(location + "_sand_cast"));
	}

	/**
	 * Adds a casting recipe using an ingot cast
	 * @param consumer  Recipe consumer
	 * @param fluid     Input fluid
	 * @param amount    Recipe amount
	 * @param ingot     Ingot output
	 * @param location  Recipe base
	 */
	private void addIngotCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, int amount, IItemProvider ingot, String location) {
		addCastingWithCastRecipe(consumer, fluid, amount, TinkerSmeltery.ingotCast, ingot, location);
	}

	/**
	 * Adds a casting recipe using an ingot cast
	 * @param consumer  Recipe consumer
	 * @param fluid     Input fluid
	 * @param ingot     Ingot output
	 * @param location  Recipe base
	 */
	private void addIngotCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, IItemProvider ingot, String location) {
		addIngotCastingRecipe(consumer, fluid, MaterialValues.INGOT, ingot, location);
	}

	/**
	 * Adds a casting recipe using an ingot cast
	 * @param consumer  Recipe consumer
	 * @param fluid     Input fluid
	 * @param gem       Gem output
	 * @param location  Recipe base
	 */
	private void addGemCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, IItemProvider gem, String location) {
		addCastingWithCastRecipe(consumer, fluid, MaterialValues.GEM, TinkerSmeltery.gemCast, gem, location);
	}

	/**
	 * Adds a casting recipe using a nugget cast
	 * @param consumer  Recipe consumer
	 * @param fluid     Input fluid
	 * @param nugget    Nugget output
	 * @param location  Recipe base
	 */
	private void addNuggetCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, IItemProvider nugget, String location) {
		addCastingWithCastRecipe(consumer, fluid, MaterialValues.NUGGET, TinkerSmeltery.nuggetCast, nugget, location);
	}

	/**
	 * Add recipes for a standard mineral
	 * @param consumer  Recipe consumer
	 * @param fluid     Fluid input
	 * @param block     Block result
	 * @param ingot     Ingot result
	 * @param nugget    Nugget result
	 * @param folder    Output folder
	 */
	private void addMetalCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, @Nullable IItemProvider block, @Nullable IItemProvider ingot, @Nullable IItemProvider nugget, String folder, String metal) {
		String metalFolder = folder + metal + "/";
		if (block != null) {
			addBlockCastingRecipe(consumer, fluid, MaterialValues.METAL_BLOCK, block, metalFolder + "block");
		}
		if (ingot != null) {
			addIngotCastingRecipe(consumer, fluid, ingot, metalFolder + "ingot");
		}
		if (nugget != null) {
			addNuggetCastingRecipe(consumer, fluid, nugget, metalFolder + "nugget");
		}
		// plates are always optional, we don't ship them
		addOptionalCastingWithCast(consumer, fluid.get(), MaterialValues.INGOT,      TinkerSmeltery.plateCast, "plates", "plate", metal, folder);
		addOptionalCastingWithCast(consumer, fluid.get(), MaterialValues.INGOT * 4,  TinkerSmeltery.gearCast,  "gears",  "gear",  metal, folder);
		addOptionalCastingWithCast(consumer, fluid.get(), MaterialValues.NUGGET * 3, TinkerSmeltery.coinCast,  "coins",  "coin",  metal, folder);
		addOptionalCastingWithCast(consumer, fluid.get(), MaterialValues.INGOT / 2,  TinkerSmeltery.rodCast,   "rods",   "rod",   metal, folder);
	}

	/** Adds a recipe for casting using a cast */
	private void addOptionalCastingWithCast(Consumer<IFinishedRecipe> consumer, Fluid fluid, int amount, CastItemObject cast, String tagPrefix, String recipeName, String name, String folder) {
		addOptionalCastingWithCastDomain(consumer, fluid, amount, cast, tagPrefix, recipeName, name, folder, "forge");
	}

	private void addOptionalCastingWithCastDomain(Consumer<IFinishedRecipe> consumer, Fluid fluid, int amount, CastItemObject cast, String tagPrefix, String recipeName, String name, String folder, String domain) {
		String tagName = tagPrefix + "/" + name;
		ITag<Item> tag = getTag(domain, tagName);
		Consumer<IFinishedRecipe> wrapped = withCondition(consumer, tagConditionDomain(domain, tagName));
		ItemCastingRecipeBuilder.tableRecipe(tag)
		.setFluidAndTime(new FluidStack(fluid, amount))
		.setCast(cast.getMultiUseTag(), false)
		.build(wrapped, location(folder + name + "/" + recipeName + "_gold_cast"));
		ItemCastingRecipeBuilder.tableRecipe(tag)
		.setFluidAndTime(new FluidStack(fluid, amount))
		.setCast(cast.getSingleUseTag(), true)
		.build(wrapped, location(folder + name + "/" + recipeName + "_sand_cast"));
	}

	/**
	 * Add recipes for a standard mineral
	 * @param consumer  Recipe consumer
	 * @param fluid     Fluid input
	 * @param name      Name of ore
	 * @param folder    Output folder
	 */
	private void addMetalOptionalCasting(Consumer<IFinishedRecipe> consumer, Fluid fluid, String name, String folder) {
		// nugget and ingot
		addOptionalCastingWithCast(consumer, fluid, MaterialValues.NUGGET, TinkerSmeltery.nuggetCast, "nuggets", "nugget", name, folder);
		addOptionalCastingWithCast(consumer, fluid, MaterialValues.INGOT, TinkerSmeltery.ingotCast, "ingots", "ingot", name, folder);
		addOptionalCastingWithCast(consumer, fluid, MaterialValues.INGOT, TinkerSmeltery.plateCast, "plates", "plate", name, folder);
		addOptionalCastingWithCast(consumer, fluid, MaterialValues.INGOT * 4, TinkerSmeltery.gearCast, "gears", "gear", name, folder);
		addOptionalCastingWithCast(consumer, fluid, MaterialValues.NUGGET * 3, TinkerSmeltery.coinCast, "coins", "coin", name, folder);
		addOptionalCastingWithCast(consumer, fluid, MaterialValues.INGOT / 2, TinkerSmeltery.rodCast, "rods", "rod", name, folder);
		// block
		ITag<Item> block = getTag("forge", "storage_blocks/" + name);
		Consumer<IFinishedRecipe> wrapped = withCondition(consumer, tagCondition("storage_blocks/" + name));
		ItemCastingRecipeBuilder.basinRecipe(block)
		.setFluidAndTime(new FluidStack(fluid, MaterialValues.METAL_BLOCK))
		.build(wrapped, location(folder + name + "/block"));
	}

	/**
	 * Adds recipe to create a cast
	 * @param consumer  Recipe consumer
	 * @param input     Item consumed to create cast
	 * @param cast      Produced cast
	 * @param folder    Output folder
	 */
	private void addCastCastingRecipe(Consumer<IFinishedRecipe> consumer, INamedTag<Item> input, CastItemObject cast, String folder) {
		String path = input.getName().getPath();
		ItemCastingRecipeBuilder.tableRecipe(cast)
		.setFluidAndTime(new FluidStack(TinkerFluids.moltenGold.get(), MaterialValues.INGOT))
		.setCast(input, true)
		.setSwitchSlots()
		.build(consumer, location(folder + "casts/" + path));
		MoldingRecipeBuilder.moldingTable(cast.getSand())
		.setMaterial(TinkerSmeltery.blankCast.getSand())
		.setPattern(input, false)
		.build(consumer, location(folder + "sand_casts/" + path));
		MoldingRecipeBuilder.moldingTable(cast.getRedSand())
		.setMaterial(TinkerSmeltery.blankCast.getRedSand())
		.setPattern(input, false)
		.build(consumer, location(folder + "red_sand_casts/" + path));
	}

	/**
	 * Gets a resource location for Tinkers
	 * @param id  Location path
	 * @return  Location for Tinkers
	 */
	protected static ResourceLocation location(String id) {
		return new ResourceLocation(Materialis.modID, id);
	}

	/**
	 * Gets a tag by name
	 * @param modId  Mod ID for tag
	 * @param name   Tag name
	 * @return  Tag instance
	 */
	protected static INamedTag<Item> getTag(String modId, String name) {
		return ItemTags.bind(modId + ":" + name);
	}

	/**
	 * Creates a condition for a tag existing
	 * @param name  Forge tag name
	 * @return  Condition for tag existing
	 */
	protected static ICondition tagCondition(String name) {
		return new NotCondition(new TagEmptyCondition("forge", name));
	}

	/**
	 * Creates a condition for a tag existing
	 * @param domain  Tag domain
	 * @param name  Tag name
	 * @return  Condition for tag existing
	 */
	protected static ICondition tagConditionDomain(String domain, String name) {
		return new NotCondition(new TagEmptyCondition(domain, name));
	}

	/**
	 * Creates a consumer instance with the added conditions
	 * @param consumer    Base consumer
	 * @param conditions  Extra conditions
	 * @return  Wrapped consumer
	 */
	protected static Consumer<IFinishedRecipe> withCondition(Consumer<IFinishedRecipe> consumer, ICondition... conditions) {
		ConsumerWrapperBuilder builder = ConsumerWrapperBuilder.wrap();
		for (ICondition condition : conditions) {
			builder.addCondition(condition);
		}
		return builder.build(consumer);
	}

	/**
	 * Prefixes the resource location path with the given value
	 * @param entry   Entry registry name to use
	 * @param prefix  Prefix value
	 * @return  Resource location path
	 */
	protected static ResourceLocation prefixR(Supplier<? extends IForgeRegistryEntry<?>> entry, String prefix) {
		ResourceLocation loc = Objects.requireNonNull(entry.get().getRegistryName());
		return location(prefix + loc.getPath());
	}
}
