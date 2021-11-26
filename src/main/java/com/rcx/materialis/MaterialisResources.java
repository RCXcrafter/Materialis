package com.rcx.materialis;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.rcx.materialis.block.LightResidueBlock;
import com.rcx.materialis.item.OptionalItem;
import com.rcx.materialis.item.WrenchTool;
import com.rcx.materialis.util.ColorizerModifierRecipe;
import com.rcx.materialis.util.RuneModifierRecipe;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

public class MaterialisResources {

	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Materialis.modID);
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Materialis.modID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Materialis.modID);
	protected static final ItemDeferredRegisterExtension ITEMS_EXTENDED = new ItemDeferredRegisterExtension(Materialis.modID);
	private static final Supplier<Item.Properties> TOOL_PROPS = () -> new Item.Properties().tab(TinkerTools.TAB_TOOLS);
	private static final Item.Properties PARTS_PROPS = new Item.Properties().tab(TinkerToolParts.TAB_TOOL_PARTS);
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Materialis.modID);

	/*
	 * RECIPE SERIALIZERS
	 */

	public static final RegistryObject<RuneModifierRecipe.Serializer> runeModifierSerializer = RECIPE_SERIALIZERS.register("rune_modifier", RuneModifierRecipe.Serializer::new);
	public static final RegistryObject<ColorizerModifierRecipe.Serializer> colorizerModifierSerializer = RECIPE_SERIALIZERS.register("colorizer_modifier", ColorizerModifierRecipe.Serializer::new);


	/*
	 * FLUIDS
	 */
	public static List<FluidWithBlockNBucket> fluidList = new ArrayList<FluidWithBlockNBucket>();

	public static FluidWithBlockNBucket addFluid(String name, String localizedName, int temperature, int light, int density, int viscosity) {
		FluidWithBlockNBucket fluid = new FluidWithBlockNBucket(name, localizedName, temperature, light, density, viscosity);
		fluidList.add(fluid);
		return fluid;
	}

	//materialis fluids
	public static final FluidWithBlockNBucket FAIRY_FLUID = addFluid("molten_fairy", "Molten Fairy", 1050, 15, 3000, 6000);
	//public static final FluidWithBlockNBucket RED_AURUM_FLUID = addFluid("molten_red_aurum", "Molten Red Aurum", 1050, 15, 3000, 6000);
	//public static final FluidWithBlockNBucket DRULLOY_FLUID = addFluid("molten_drulloy", "Molten Drulloy", 1050, 15, 3000, 6000);
	//public static final FluidWithBlockNBucket POKEFENNIUM_FLUID = addFluid("molten_pokefennium", "Molten Pokefennium", 1050, 15, 3000, 6000);
	//public static final FluidWithBlockNBucket ALUMITE_FLUID = addFluid("molten_alumite", "Molten Alumite", 1050, 15, 3000, 6000);

	//create fluids
	public static final FluidWithBlockNBucket REFINED_RADIANCE_FLUID = addFluid("molten_refined_radiance", "Liquified Radiance", 1600, 15, 3000, 6000);
	public static final FluidWithBlockNBucket SHADOW_STEEL_FLUID = addFluid("molten_shadow_steel", "Molten Shadow Steel", 1600, 0, 3000, 6000);

	//eidolon fluids
	public static final FluidWithBlockNBucket ARCANE_GOLD_FLUID = addFluid("molten_arcane_gold", "Molten Arcane Gold", 970, 13, 3000, 6000);

	//aquaculture fluids
	public static final FluidWithBlockNBucket NEPTUNIUM_FLUID = addFluid("molten_neptunium", "Molten Neptunium", 1700, 13, 3000, 6000);

	//mystical world fluids
	public static final FluidWithBlockNBucket QUICKSILVER_FLUID = addFluid("molten_quicksilver", "Molten Quicksilver", 700, 12, 3000, 6000);

	//astral sorcery fluids
	public static final FluidWithBlockNBucket STARMETAL_FLUID = addFluid("molten_starmetal", "Molten Starmetal", 1050, 11, 3000, 6000);

	//industrial foregoing fluids
	public static final FluidWithBlockNBucket PINK_SLIME_FLUID = addFluid("molten_pink_slime", "Molten Pink Slime Alloy", 1260, 12, 3000, 6000);

	//undergarden fluids
	public static final FluidWithBlockNBucket CLOGGRUM_FLUID = addFluid("molten_cloggrum", "Molten Cloggrum", 950, 12, 3000, 6000);
	public static final FluidWithBlockNBucket FROSTSTEEL_FLUID = addFluid("molten_froststeel", "Molten Froststeel", 1160, 12, 3000, 6000);
	public static final FluidWithBlockNBucket UTHERIUM_FLUID = addFluid("molten_utherium", "Molten Utherium", 1370, 12, 3000, 6000);
	public static final FluidWithBlockNBucket FORGOTTEN_FLUID = addFluid("molten_forgotten_metal", "Molten Forgotten Metal", 1530, 12, 3000, 6000);
	public static final FluidWithBlockNBucket REGALIUM_FLUID = addFluid("molten_regalium", "Molten Regalium", 920, 12, 3000, 6000);

	//mekanism fluids
	//public static final FluidWithBlockNBucket REFINED_OBSIDIAN_FLUID = addFluid("molten_refined_obsidian", "Molten Refined Obsidian", 1500, 9, 3000, 6000);
	//public static final FluidWithBlockNBucket REFINED_GLOWSTONE_FLUID = addFluid("molten_refined_glowstone", "Molten Refined Glowstone", 1200, 15, 3000, 6000);

	//psi fluids
	public static final FluidWithBlockNBucket PSIMETAL_FLUID = addFluid("molten_psimetal", "Molten Psimetal", 1050, 12, 3000, 6000);
	public static final FluidWithBlockNBucket EBONY_PSIMETAL_FLUID = addFluid("molten_ebony_psimetal", "Molten Ebony Psimetal", 1430, 12, 3000, 6000);
	public static final FluidWithBlockNBucket IVORY_PSIMETAL_FLUID = addFluid("molten_ivory_psimetal", "Molten Ivory Psimetal", 1430, 12, 3000, 6000);

	//occultism fluids
	public static final FluidWithBlockNBucket IESNIUM_FLUID = addFluid("molten_iesnium", "Molten Iesnium", 1390, 12, 3000, 6000);

	//botania fluids
	public static final FluidWithBlockNBucket MANASTEEL_FLUID = addFluid("molten_manasteel", "Molten Manasteel", 1090, 12, 3000, 6000);
	public static final FluidWithBlockNBucket ELEMENTIUM_FLUID = addFluid("molten_elementium", "Molten Elementium", 1460, 12, 3000, 6000);
	public static final FluidWithBlockNBucket TERRASTEEL_FLUID = addFluid("molten_terrasteel", "Molten Terrasteel", 1580, 12, 3000, 6000);

	//mythicbotany fluids
	public static final FluidWithBlockNBucket ALFSTEEL_FLUID = addFluid("molten_alfsteel", "Molten Alfsteel", 1740, 12, 3000, 6000);

	//draconic evolution fluids
	public static final FluidWithBlockNBucket DRACONIUM_FLUID = addFluid("molten_draconium", "Molten Draconium", 1350, 12, 3000, 6000);
	public static final FluidWithBlockNBucket AWAKENED_DRACONIUM_FLUID = addFluid("molten_draconium_awakened", "Molten Awakened Draconium", 1770, 12, 3000, 6000);


	/*
	 * BLOCKS
	 */

	//light residue for residual light modifier
	public static final RegistryObject<Block> LIGHT_RESIDUE = BLOCKS.register("light_residue", () -> new LightResidueBlock(AbstractBlock.Properties.of(Material.AIR, MaterialColor.NONE).strength(0.0F, 0.0F).lightLevel((state) -> { return 15; }).randomTicks().air().noCollission().noDrops()));


	/*
	 * ITEMS
	 */

	//custom casts
	private static final Item.Properties SMELTERY_PROPS = new Item.Properties().tab(TinkerSmeltery.TAB_SMELTERY);
	public static final CastItemObject INLAY_CAST = ITEMS_EXTENDED.registerCast("inlay", SMELTERY_PROPS);
	public static final CastItemObject WRENCH_HEAD_CAST = ITEMS_EXTENDED.registerCast("wrench_head", SMELTERY_PROPS);

	//wrench
	public static final ItemObject<ToolPartItem> WRENCH_HEAD = ITEMS_EXTENDED.register("wrench_head", () -> new ToolPartItem(PARTS_PROPS, HeadMaterialStats.ID));

	public static final ToolDefinition WRENCH_DEFINITION = ToolDefinition.builder(new ResourceLocation(Materialis.modID, "wrench")).meleeHarvest().build();
	public static final ItemObject<WrenchTool> WRENCH = ITEMS_EXTENDED.register("wrench", () -> new WrenchTool(TOOL_PROPS.get().addToolType(WrenchTool.TOOL_TYPE, 0).addToolType(WrenchTool.HAMMER_TOOL_TYPE, 0), WRENCH_DEFINITION));

	public static final ToolDefinition BATTLEWRENCH_DEFINITION = ToolDefinition.builder(new ResourceLocation(Materialis.modID, "battlewrench")).meleeHarvest().build();
	public static final ItemObject<WrenchTool> BATTLEWRENCH = ITEMS_EXTENDED.register("battlewrench", () -> new WrenchTool(new Item.Properties().addToolType(WrenchTool.TOOL_TYPE, 0).addToolType(WrenchTool.HAMMER_TOOL_TYPE, 0), BATTLEWRENCH_DEFINITION));

	//industrial foregoing stuff
	public static final RegistryObject<Item> PINK_SLIME_CRYSTAL = ITEMS.register("pink_slime_crystal", () -> new OptionalItem(new Item.Properties().tab(TinkerModule.TAB_GENERAL), new ModLoadedCondition("industrialforegoing")));




	public static List<IngotWithBlockNNugget> materialList = new ArrayList<IngotWithBlockNNugget>();

	public static final IngotWithBlockNNugget FAIRY_INGOT = addIngot("fairy", "Fairy", MaterialColor.COLOR_PINK, 1, 6.0f, 6.0f);
	//public static final IngotWithBlockNNugget RED_AURUM_INGOT = addIngot("red_aurum", "Red Aurum", MaterialColor.COLOR_RED, 1, 6.0f, 6.0f);
	//public static final IngotWithBlockNNugget DRULLOY_INGOT = addIngot("drulloy", "Drulloy", MaterialColor.TERRACOTTA_BROWN, 1, 6.0f, 6.0f);
	//public static final IngotWithBlockNNugget POKEFENNIUM_INGOT = addIngot("pokefennium", "Pokefennium", MaterialColor.COLOR_CYAN, 1, 6.0f, 6.0f);
	//public static final IngotWithBlockNNugget ALUMITE_INGOT = addIngot("alumite", "Alumite", MaterialColor.TERRACOTTA_WHITE, 1, 6.0f, 6.0f);

	public static IngotWithBlockNNugget addIngot(String name, String localizedName, MaterialColor color, int miningLevel, float hardness, float explosionResistance) {
		IngotWithBlockNNugget ingot = new IngotWithBlockNNugget(name, localizedName, color, miningLevel, hardness, explosionResistance);
		materialList.add(ingot);
		return ingot;
	}

	public static class IngotWithBlockNNugget {

		public final String name;
		public final String localizedName;

		public final RegistryObject<Block> BLOCK;
		public final RegistryObject<Item> INGOT;
		public final RegistryObject<Item> NUGGET;
		public final RegistryObject<BlockItem> BLOCK_ITEM;


		public IngotWithBlockNNugget(String name, String localizedName, MaterialColor color, int miningLevel, float hardness, float explosionResistance) {
			this.name = name;
			this.localizedName = localizedName;

			BLOCK = BLOCKS.register(name + "_block", () -> new Block(AbstractBlock.Properties.of(Material.METAL, color).harvestLevel(miningLevel).harvestTool(ToolType.PICKAXE).strength(hardness, explosionResistance).sound(SoundType.METAL).requiresCorrectToolForDrops()));

			INGOT = ITEMS.register(name + "_ingot", () -> new Item(new Item.Properties().tab(TinkerModule.TAB_GENERAL)));
			NUGGET = ITEMS.register(name + "_nugget", () -> new Item(new Item.Properties().tab(TinkerModule.TAB_GENERAL)));
			BLOCK_ITEM = ITEMS.register(name + "_block", () -> new BlockItem(BLOCK.get(), new Item.Properties().tab(TinkerModule.TAB_GENERAL)));
		}
	}

	public static class FluidWithBlockNBucket {

		public final ForgeFlowingFluid.Properties PROPERTIES;

		public final RegistryObject<ForgeFlowingFluid.Source> FLUID;
		public final RegistryObject<ForgeFlowingFluid.Flowing> FLUID_FLOW;
		public final FluidObject<ForgeFlowingFluid> OBJECT;

		public final ResourceLocation TEXTURE_STILL;
		public final ResourceLocation TEXTURE_FLOW;

		public final RegistryObject<FlowingFluidBlock> FLUID_BLOCK;

		public final RegistryObject<BucketItem> FLUID_BUCKET;

		public final String name;
		public final String localizedName;
		public final int temperature;
		public final int light;
		public final int density;
		public final int viscosity;

		public FluidWithBlockNBucket(String name, String localizedName, int temperature, int light, int density, int viscosity) {
			this.name = name;
			this.localizedName = localizedName;
			this.temperature = temperature;
			this.light = light;
			this.density = density;
			this.viscosity = viscosity;

			FLUID = FLUIDS.register(name, () -> new ForgeFlowingFluid.Source(getFluidProperties()));
			FLUID_FLOW = FLUIDS.register("flowing_" + name, () -> new ForgeFlowingFluid.Flowing(getFluidProperties()));

			TEXTURE_STILL = new ResourceLocation(Materialis.modID, "block/fluid/" + name + "_still");
			TEXTURE_FLOW = new ResourceLocation(Materialis.modID, "block/fluid/" + name + "_flow");

			PROPERTIES = new ForgeFlowingFluid.Properties(FLUID, FLUID_FLOW, FluidAttributes.builder(TEXTURE_STILL, TEXTURE_FLOW).overlay(TEXTURE_STILL).luminosity(light).density(density).viscosity(6000).temperature(temperature).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA));

			FLUID_BLOCK = BLOCKS.register(name + "_block", () -> new FlowingFluidBlock(FLUID, Block.Properties.of(Material.LAVA).lightLevel((state) -> { return light; }).randomTicks().strength(100.0F).noDrops()));
			FLUID_BUCKET = ITEMS.register(name + "_bucket", () -> new BucketItem(FLUID, new BucketItem.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ItemGroup.TAB_MISC)));

			PROPERTIES.bucket(FLUID_BUCKET).block(FLUID_BLOCK).explosionResistance(1000F).tickRate(9);

			OBJECT = new FluidObject<ForgeFlowingFluid>(new ResourceLocation(Materialis.modID, name), name, FLUID, FLUID_FLOW, FLUID_BLOCK);
		}

		public ForgeFlowingFluid.Properties getFluidProperties() {
			return PROPERTIES;       
		}
	}
}
