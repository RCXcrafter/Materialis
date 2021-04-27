package com.rcx.materialis;

import com.rcx.materialis.block.LightResidueBlock;

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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

public class MaterialisResources {

	/*
	 * FLUIDS
	 */
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Materialis.modID);

	//fairy
	public static final RegistryObject<ForgeFlowingFluid.Source> FAIRY_FLUID = FLUIDS.register("molten_fairy", () -> new ForgeFlowingFluid.Source(getFairyProperties()));
	public static final RegistryObject<ForgeFlowingFluid.Flowing> FAIRY_FLUID_FLOW = FLUIDS.register("flowing_molten_fairy", () -> new ForgeFlowingFluid.Flowing(getFairyProperties()));
	public static final ResourceLocation MOLTEN_FAIRY_STILL = new ResourceLocation(Materialis.modID, "block/fluid/molten_fairy_still");
	public static final ResourceLocation MOLTEN_FAIRY_FLOW = new ResourceLocation(Materialis.modID, "block/fluid/molten_fairy_flow");
	private static ForgeFlowingFluid.Properties getFairyProperties() {
		return new ForgeFlowingFluid.Properties(FAIRY_FLUID, FAIRY_FLUID_FLOW, FluidAttributes.builder(MOLTEN_FAIRY_STILL, MOLTEN_FAIRY_FLOW).overlay(MOLTEN_FAIRY_STILL).luminosity(15).density(3000).viscosity(6000).temperature(1050).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA)).bucket(FAIRY_BUCKET).block(MOLTEN_FAIRY).explosionResistance(1000F).tickRate(9);       
	}

	//refined radiance
	public static final RegistryObject<ForgeFlowingFluid.Source> REFINED_RADIANCE_FLUID = FLUIDS.register("molten_refined_radiance", () -> new ForgeFlowingFluid.Source(getRefinedRadianceProperties()));
	public static final RegistryObject<ForgeFlowingFluid.Flowing> REFINED_RADIANCE_FLUID_FLOW = FLUIDS.register("flowing_molten_refined_radiance", () -> new ForgeFlowingFluid.Flowing(getRefinedRadianceProperties()));
	public static final ResourceLocation MOLTEN_REFINED_RADIANCE_STILL = new ResourceLocation(Materialis.modID, "block/fluid/molten_refined_radiance_still");
	public static final ResourceLocation MOLTEN_REFINED_RADIANCE_FLOW = new ResourceLocation(Materialis.modID, "block/fluid/molten_refined_radiance_flow");
	private static ForgeFlowingFluid.Properties getRefinedRadianceProperties() {
		return new ForgeFlowingFluid.Properties(REFINED_RADIANCE_FLUID, REFINED_RADIANCE_FLUID_FLOW, FluidAttributes.builder(MOLTEN_REFINED_RADIANCE_STILL, MOLTEN_REFINED_RADIANCE_FLOW).overlay(MOLTEN_REFINED_RADIANCE_STILL).luminosity(15).density(3000).viscosity(6000).temperature(1600).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA)).bucket(REFINED_RADIANCE_BUCKET).block(MOLTEN_REFINED_RADIANCE).explosionResistance(1000F).tickRate(9);       
	}

	//shadow steel
	public static final RegistryObject<ForgeFlowingFluid.Source> SHADOW_STEEL_FLUID = FLUIDS.register("molten_shadow_steel", () -> new ForgeFlowingFluid.Source(getShadowSteelProperties()));
	public static final RegistryObject<ForgeFlowingFluid.Flowing> SHADOW_STEEL_FLUID_FLOW = FLUIDS.register("flowing_molten_shadow_steel", () -> new ForgeFlowingFluid.Flowing(getShadowSteelProperties()));
	public static final ResourceLocation MOLTEN_SHADOW_STEEL_STILL = new ResourceLocation(Materialis.modID, "block/fluid/molten_shadow_steel_still");
	public static final ResourceLocation MOLTEN_SHADOW_STEEL_FLOW = new ResourceLocation(Materialis.modID, "block/fluid/molten_shadow_steel_flow");
	private static ForgeFlowingFluid.Properties getShadowSteelProperties() {
		return new ForgeFlowingFluid.Properties(SHADOW_STEEL_FLUID, SHADOW_STEEL_FLUID_FLOW, FluidAttributes.builder(MOLTEN_SHADOW_STEEL_STILL, MOLTEN_SHADOW_STEEL_FLOW).overlay(MOLTEN_SHADOW_STEEL_STILL).luminosity(0).density(3000).viscosity(6000).temperature(1600).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA)).bucket(SHADOW_STEEL_BUCKET).block(MOLTEN_SHADOW_STEEL).explosionResistance(1000F).tickRate(9);       
	}

	//arcane gold
	public static final RegistryObject<ForgeFlowingFluid.Source> ARCANE_GOLD_FLUID = FLUIDS.register("molten_arcane_gold", () -> new ForgeFlowingFluid.Source(getArcaneGoldProperties()));
	public static final RegistryObject<ForgeFlowingFluid.Flowing> ARCANE_GOLD_FLUID_FLOW = FLUIDS.register("flowing_molten_arcane_gold", () -> new ForgeFlowingFluid.Flowing(getArcaneGoldProperties()));
	public static final ResourceLocation MOLTEN_ARCANE_GOLD_STILL = new ResourceLocation(Materialis.modID, "block/fluid/molten_arcane_gold_still");
	public static final ResourceLocation MOLTEN_ARCANE_GOLD_FLOW = new ResourceLocation(Materialis.modID, "block/fluid/molten_arcane_gold_flow");
	private static ForgeFlowingFluid.Properties getArcaneGoldProperties() {
		return new ForgeFlowingFluid.Properties(ARCANE_GOLD_FLUID, ARCANE_GOLD_FLUID_FLOW, FluidAttributes.builder(MOLTEN_ARCANE_GOLD_STILL, MOLTEN_ARCANE_GOLD_FLOW).overlay(MOLTEN_ARCANE_GOLD_STILL).luminosity(15).density(3000).viscosity(6000).temperature(970).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA)).bucket(ARCANE_GOLD_BUCKET).block(MOLTEN_ARCANE_GOLD).explosionResistance(1000F).tickRate(9);       
	}

	//neptunium
	public static final RegistryObject<ForgeFlowingFluid.Source> NEPTUNIUM_FLUID = FLUIDS.register("molten_neptunium", () -> new ForgeFlowingFluid.Source(getNeptuniumProperties()));
	public static final RegistryObject<ForgeFlowingFluid.Flowing> NEPTUNIUM_FLUID_FLOW = FLUIDS.register("flowing_molten_neptunium", () -> new ForgeFlowingFluid.Flowing(getNeptuniumProperties()));
	public static final ResourceLocation MOLTEN_NEPTUNIUM_STILL = new ResourceLocation(Materialis.modID, "block/fluid/molten_neptunium_still");
	public static final ResourceLocation MOLTEN_NEPTUNIUM_FLOW = new ResourceLocation(Materialis.modID, "block/fluid/molten_neptunium_flow");
	private static ForgeFlowingFluid.Properties getNeptuniumProperties() {
		return new ForgeFlowingFluid.Properties(NEPTUNIUM_FLUID, NEPTUNIUM_FLUID_FLOW, FluidAttributes.builder(MOLTEN_NEPTUNIUM_STILL, MOLTEN_NEPTUNIUM_FLOW).overlay(MOLTEN_NEPTUNIUM_STILL).luminosity(15).density(3000).viscosity(6000).temperature(1700).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA)).bucket(NEPTUNIUM_BUCKET).block(MOLTEN_NEPTUNIUM).explosionResistance(1000F).tickRate(9);       
	}




	/*
	 * BLOCKS
	 */
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Materialis.modID);

	//light residue for residual light modifier
	public static final RegistryObject<Block> LIGHT_RESIDUE = BLOCKS.register("light_residue", () -> new LightResidueBlock(AbstractBlock.Properties.of(Material.AIR, MaterialColor.NONE).strength(0.0F, 0.0F).lightLevel((state) -> { return 15; }).randomTicks().air().noCollission().noDrops()));

	//fairy
	public static final RegistryObject<Block> FAIRY_BLOCK = BLOCKS.register("fairy_block", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_PINK).harvestLevel(1).harvestTool(ToolType.PICKAXE).strength(6.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<FlowingFluidBlock> MOLTEN_FAIRY = BLOCKS.register("molten_fairy_block", () -> new FlowingFluidBlock(FAIRY_FLUID, Block.Properties.of(Material.LAVA).lightLevel((state) -> { return 15; }).randomTicks().strength(100.0F).noDrops()));

	//create fluids
	public static final RegistryObject<FlowingFluidBlock> MOLTEN_REFINED_RADIANCE = BLOCKS.register("molten_refined_radiance_block", () -> new FlowingFluidBlock(REFINED_RADIANCE_FLUID, Block.Properties.of(Material.LAVA).lightLevel((state) -> { return 15; }).randomTicks().strength(100.0F).noDrops()));
	public static final RegistryObject<FlowingFluidBlock> MOLTEN_SHADOW_STEEL = BLOCKS.register("molten_shadow_steel_block", () -> new FlowingFluidBlock(SHADOW_STEEL_FLUID, Block.Properties.of(Material.LAVA).lightLevel((state) -> { return 0; }).randomTicks().strength(100.0F).noDrops()));

	//eidolon fluids
	public static final RegistryObject<FlowingFluidBlock> MOLTEN_ARCANE_GOLD = BLOCKS.register("molten_arcane_gold_block", () -> new FlowingFluidBlock(ARCANE_GOLD_FLUID, Block.Properties.of(Material.LAVA).lightLevel((state) -> { return 15; }).randomTicks().strength(100.0F).noDrops()));

	//aquaculture fluids
	public static final RegistryObject<FlowingFluidBlock> MOLTEN_NEPTUNIUM = BLOCKS.register("molten_neptunium_block", () -> new FlowingFluidBlock(NEPTUNIUM_FLUID, Block.Properties.of(Material.LAVA).lightLevel((state) -> { return 15; }).randomTicks().strength(100.0F).noDrops()));




	/*
	 * ITEMS
	 */
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Materialis.modID);
	protected static final ItemDeferredRegisterExtension ITEMS_EXTENDED = new ItemDeferredRegisterExtension(Materialis.modID);

	//fairy
	public static final RegistryObject<Item> FAIRY_INGOT = ITEMS.register("fairy_ingot", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<Item> FAIRY_NUGGET = ITEMS.register("fairy_nugget", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<BucketItem> FAIRY_BUCKET = ITEMS.register("fairy_bucket", () -> new BucketItem(FAIRY_FLUID, new BucketItem.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<BlockItem> FAIRY_BLOCK_ITEM = ITEMS.register("fairy_block", () -> new BlockItem(FAIRY_BLOCK.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));

	//custom casts
	private static final Item.Properties SMELTERY_PROPS = new Item.Properties().tab(TinkerSmeltery.TAB_SMELTERY);
	public static final CastItemObject INLAY_CAST = ITEMS_EXTENDED.registerCast("inlay", SMELTERY_PROPS);

	//create fluids
	public static final RegistryObject<BucketItem> REFINED_RADIANCE_BUCKET = ITEMS.register("refined_radiance_bucket", () -> new BucketItem(REFINED_RADIANCE_FLUID, new BucketItem.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ItemGroup.TAB_MISC)));
	public static final RegistryObject<BucketItem> SHADOW_STEEL_BUCKET = ITEMS.register("shadow_steel_bucket", () -> new BucketItem(SHADOW_STEEL_FLUID, new BucketItem.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ItemGroup.TAB_MISC)));

	//eidolon fluids
	public static final RegistryObject<BucketItem> ARCANE_GOLD_BUCKET = ITEMS.register("arcane_gold_bucket", () -> new BucketItem(ARCANE_GOLD_FLUID, new BucketItem.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ItemGroup.TAB_MISC)));

	//aquaculture fluids
	public static final RegistryObject<BucketItem> NEPTUNIUM_BUCKET = ITEMS.register("neptunium_bucket", () -> new BucketItem(NEPTUNIUM_FLUID, new BucketItem.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ItemGroup.TAB_MISC)));




	//my clever plan doesn't work >:(
	/*
	public static List<ItemRegistryStuff> items = new ArrayList<ItemRegistryStuff>();

	static {
		registerItem("fairy_ingot", "Fairy Ingot", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
		registerItem("fairy_nugget", "Fairy Nugget", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));
	}

	public static void registerItem(String unlocName, String locName, Supplier<? extends Item> item) {
		items.add(new ItemRegistryStuff(locName, locName, item));
		ITEMS.register(unlocName, item);
	}

	public static class ItemRegistryStuff {

		public String unlocName;
		public String locName;
		public Supplier<? extends Item> item;

		public ItemRegistryStuff(String unlocName, String locName, Supplier<? extends Item> item) {
			this.unlocName = unlocName;
			this.locName = locName;
			this.item = item;
		}
	}*/
}
