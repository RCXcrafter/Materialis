package com.rcx.materialis.datagen;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;

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
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.IForgeRegistryEntry;
import slimeknights.mantle.recipe.data.ConsumerWrapperBuilder;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.materials.MaterialValues;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.molding.MoldingRecipeBuilder;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

public class MaterialisRecipes extends RecipeProvider implements IConditionBuilder {

	public MaterialisRecipes(DataGenerator gen) {
		super(gen);
	}

	public void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		blockIngotNuggetCompression(consumer, MaterialisResources.FAIRY_BLOCK_ITEM.get(), MaterialisResources.FAIRY_INGOT.get(), MaterialisResources.FAIRY_NUGGET.get());
		String metalFolder = "smeltery/casting/metal/";
		addMetalCastingRecipe(consumer, MaterialisResources.FAIRY_FLUID.FLUID, MaterialisResources.FAIRY_BLOCK_ITEM.get(), MaterialisResources.FAIRY_INGOT.get(), MaterialisResources.FAIRY_NUGGET.get(), metalFolder, "fairy");

		addMetalMelting(consumer, MaterialisResources.FAIRY_FLUID.FLUID.get(), "fairy", false, metalFolder, false);

		String folder = "smeltery/alloys/";
		AlloyRecipeBuilder.alloy(MaterialisResources.FAIRY_FLUID.FLUID.get(), MaterialValues.INGOT)
		.addInput(TinkerFluids.moltenGold.get(), MaterialValues.INGOT)
		.addInput(TinkerFluids.liquidSoul.get(), MaterialValues.GLASS_BLOCK)
		.addInput(Fluids.MILK, 1000)
		.build(consumer, prefixR(MaterialisResources.FAIRY_FLUID.FLUID, folder));

		//create stuff
		addMetalOptionalCasting(consumer, MaterialisResources.REFINED_RADIANCE_FLUID.FLUID.get(), "refined_radiance", folder);
		addMetalMelting(consumer, MaterialisResources.REFINED_RADIANCE_FLUID.FLUID.get(), "refined_radiance", false, metalFolder, true);
		addMetalOptionalCasting(consumer, MaterialisResources.SHADOW_STEEL_FLUID.FLUID.get(), "shadow_steel", folder);
		addMetalMelting(consumer, MaterialisResources.SHADOW_STEEL_FLUID.FLUID.get(), "shadow_steel", false, metalFolder, true);

		//eidolon stuff
		addMetalOptionalCasting(consumer, MaterialisResources.ARCANE_GOLD_FLUID.FLUID.get(), "arcane_gold", folder);
		addMetalMelting(consumer, MaterialisResources.ARCANE_GOLD_FLUID.FLUID.get(), "arcane_gold", false, metalFolder, true);

		addCastCastingRecipe(withCondition(consumer, tagConditionDomain("materialis", "inlays")),  getTag("materialis", "inlays"),  MaterialisResources.INLAY_CAST,  folder);
		addOptionalCastingWithCastDomain(consumer, TinkerFluids.moltenPewter.get(), MaterialValues.INGOT * 2, MaterialisResources.INLAY_CAST, "inlays", "inlay", "pewter", folder, "materialis");
		addOptionalCastingWithCastDomain(consumer, MaterialisResources.ARCANE_GOLD_FLUID.FLUID.get(), MaterialValues.INGOT * 2, MaterialisResources.INLAY_CAST, "inlays", "inlay", "arcane_gold", folder, "materialis");

		MeltingRecipeBuilder.melting(Ingredient.of(getTag("materialis", "inlays/pewter")), TinkerFluids.moltenPewter.get(), MaterialValues.INGOT * 2, 1.5f).build(withCondition(consumer, tagConditionDomain("materialis", "inlays/pewter")), location(metalFolder + "pewter_inlay"));
		MeltingRecipeBuilder.melting(Ingredient.of(getTag("materialis", "inlays/arcane_gold")), MaterialisResources.ARCANE_GOLD_FLUID.FLUID.get(), MaterialValues.INGOT * 2, 1.5f).build(withCondition(consumer, tagConditionDomain("materialis", "inlays/arcane_gold")), location(metalFolder + "arcane_gold_inlay"));

		MeltingRecipeBuilder.melting(Ingredient.of(getTag("materialis", "pewter_blend")), TinkerFluids.moltenPewter.get(), MaterialValues.INGOT, 1.0f).build(withCondition(consumer, tagConditionDomain("materialis", "pewter_blend")), location(metalFolder + "pewter_blend"));

		//aquaculture stuff
		addMetalOptionalCasting(consumer, MaterialisResources.NEPTUNIUM_FLUID.FLUID.get(), "neptunium", folder);
		addMetalMelting(consumer, MaterialisResources.NEPTUNIUM_FLUID.FLUID.get(), "neptunium", false, metalFolder, true);

		//mystical world stuff
		addMetalOptionalCasting(consumer, MaterialisResources.QUICKSILVER_FLUID.FLUID.get(), "quicksilver", folder);
		addMetalMelting(consumer, MaterialisResources.QUICKSILVER_FLUID.FLUID.get(), "quicksilver", true, metalFolder, true);

		//astral sorcery stuff
		addMetalOptionalCasting(consumer, MaterialisResources.STARMETAL_FLUID.FLUID.get(), "starmetal", folder);
		addMetalMelting(consumer, MaterialisResources.STARMETAL_FLUID.FLUID.get(), "starmetal", true, metalFolder, true);

		//industrial foregoing stuff
		addMetalOptionalCasting(consumer, MaterialisResources.PINK_SLIME_FLUID.FLUID.get(), "pink_slime", folder);
		addMetalMelting(consumer, MaterialisResources.PINK_SLIME_FLUID.FLUID.get(), "pink_slime", false, metalFolder, true);

		Consumer<IFinishedRecipe> industrialForegoingLoaded = withCondition(consumer, new ModLoadedCondition("industrialforegoing"));
		AlloyRecipeBuilder.alloy(MaterialisResources.PINK_SLIME_FLUID.FLUID.get(), MaterialValues.INGOT)
		.addInput(TinkerFluids.moltenGold.get(), MaterialValues.INGOT * 2)
		.addInput(TinkerFluids.moltenIron.get(), MaterialValues.INGOT * 2)
		.addInput(MaterialisFluidTags.LIQUID_PINK_SLIME, 1000)
		.build(industrialForegoingLoaded, prefixR(MaterialisResources.PINK_SLIME_FLUID.FLUID, folder));

		CookingRecipeBuilder.blasting(Ingredient.of(MaterialisItemTags.PINK_SLIME), MaterialisResources.PINK_SLIME_CRYSTAL.get(), 1.0f, 400)
		.unlockedBy("has_item", has(MaterialisItemTags.PINK_SLIME))
		.save(industrialForegoingLoaded, new ResourceLocation(Materialis.modID, "pink_slime_crystal_blasting"));
	}

	public void blockIngotNuggetCompression(Consumer<IFinishedRecipe> consumer, Item block, Item ingot, Item nugget) {
		ConditionalRecipe.builder().addCondition(this.TRUE()).addRecipe(
				ShapedRecipeBuilder.shaped(block, 1)
				.pattern("XXX")
				.pattern("XXX")
				.pattern("XXX")
				.define('X', ingot)
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
				.pattern("XXX")
				.pattern("XXX")
				.define('X', nugget)
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

	/* Helpers */ //stolen from tinkers construct

	/**
	 * Base logic for {@link #addMetalMelting(Consumer, Fluid, String, boolean, String, boolean)}
	 * @param consumer    Recipe consumer
	 * @param fluid       Fluid to melt into
	 * @param amount      Amount to melt into
	 * @param isOre       If true, this is an ore recipe
	 * @param tagName     Input tag
	 * @param factor      Melting factor
	 * @param recipePath  Recipe output name
	 * @param isOptional  If true, recipe is optional
	 */
	private static void addMetalBase(Consumer<IFinishedRecipe> consumer, Fluid fluid, int amount, boolean isOre, String tagName, float factor, String recipePath, boolean isOptional) {
		Consumer<IFinishedRecipe> wrapped = isOptional ? withCondition(consumer, tagCondition(tagName)) : consumer;
		MeltingRecipeBuilder builder = MeltingRecipeBuilder.melting(Ingredient.of(getTag("forge", tagName)), fluid, amount, factor);
		if (isOre) {
			builder.setOre();
		}
		builder.build(wrapped, location(recipePath));
	}

	/**
	 * Adds a basic ingot, nugget, block, ore melting recipe set
	 * @param consumer  Recipe consumer
	 * @param fluid     Fluid result
	 * @param name      Resource name for tags
	 * @param hasOre    If true, adds recipe for melting the ore
	 * @param folder    Recipe folder
	 */
	private void addMetalMelting(Consumer<IFinishedRecipe> consumer, Fluid fluid, String name, boolean hasOre, String folder, boolean isOptional) {
		String prefix = folder + "/" + name + "/";
		addMetalBase(consumer, fluid, MaterialValues.METAL_BLOCK, false, "storage_blocks/" + name, 3.0f, prefix + "block", isOptional);
		addMetalBase(consumer, fluid, MaterialValues.INGOT, false, "ingots/" + name, 1.0f, prefix + "ingot", isOptional);
		addMetalBase(consumer, fluid, MaterialValues.NUGGET, false, "nuggets/" + name, 1 / 3f, prefix + "nugget", isOptional);
		if (hasOre) {
			addMetalBase(consumer, fluid, MaterialValues.INGOT, true, "ores/" + name, 1.5f, prefix + "ore", isOptional);
		}
		// dust is always optional, as we don't do dust
		addMetalBase(consumer, fluid, MaterialValues.INGOT,      false, "dusts/" + name,       0.75f, prefix + "dust",       true);
		addMetalBase(consumer, fluid, MaterialValues.INGOT,      false, "plates/" + name,      1.0f,  prefix + "plates",     true);
		addMetalBase(consumer, fluid, MaterialValues.INGOT * 4,  false, "gears/" + name,       2.0f,  prefix + "gear",       true);
		addMetalBase(consumer, fluid, MaterialValues.NUGGET * 3, false, "coins/" + name,       2/3f,  prefix + "coin",       true);
		addMetalBase(consumer, fluid, MaterialValues.INGOT / 2,  false, "rods/" + name,        1/5f,  prefix + "rod",        true);
		addMetalBase(consumer, fluid, MaterialValues.INGOT,      false, "sheetmetals/" + name, 1.0f,  prefix + "sheetmetal", true);
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
