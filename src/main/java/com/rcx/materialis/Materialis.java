package com.rcx.materialis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rcx.materialis.compat.PsiCompat;
import com.rcx.materialis.compat.QuarkCompat;
import com.rcx.materialis.datagen.MaterialisBlockStates;
import com.rcx.materialis.datagen.MaterialisBlockTags;
import com.rcx.materialis.datagen.MaterialisFluidTags;
import com.rcx.materialis.datagen.MaterialisItemModels;
import com.rcx.materialis.datagen.MaterialisItemTags;
import com.rcx.materialis.datagen.MaterialisLang;
import com.rcx.materialis.datagen.MaterialisLootTables;
import com.rcx.materialis.datagen.MaterialisMaterialTextures;
import com.rcx.materialis.datagen.MaterialisMaterials;
import com.rcx.materialis.datagen.MaterialisMaterials.MaterialisMaterialStats;
import com.rcx.materialis.datagen.MaterialisMaterials.MaterialisMaterialTraits;
import com.rcx.materialis.datagen.MaterialisPartTextures;
import com.rcx.materialis.datagen.MaterialisRecipes;
import com.rcx.materialis.datagen.MaterialisToolDefinitions;
import com.rcx.materialis.datagen.MaterialisToolSlotLayouts;
import com.rcx.materialis.modifiers.MaterialisModifiers;
import com.rcx.materialis.util.TintedModifierModel;

import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemTransformVec3f;
import net.minecraft.client.renderer.model.Variant;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.library.client.modifiers.ModifierModelManager.ModifierModelRegistrationEvent;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.tools.ToolClientEvents;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("materialis")
@Mod.EventBusSubscriber(bus = Bus.MOD)
public class Materialis {

	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger();
	public static final String modID = "materialis";
	private static Gson GSON = null;

	public Materialis() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		// Register the setup method for modloading
		bus.addListener(this::setup);
		// Register the enqueueIMC method for modloading
		//bus.addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		//bus.addListener(this::processIMC);
		// Register the doClientStuff method for modloading
		//bus.addListener(this::doClientStuff);

		MaterialisResources.FLUIDS.register(bus);
		MaterialisResources.BLOCKS.register(bus);
		MaterialisResources.ITEMS.register(bus);
		MaterialisResources.ITEMS_EXTENDED.register(bus);
		MaterialisResources.RECIPE_SERIALIZERS.register(bus);
		MaterialisModifiers.MODIFIERS.register(bus);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		// some preinit code
		//LOGGER.info("HELLO FROM PREINIT");
		//LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
		if (ModList.get().isLoaded("psi"))
			MinecraftForge.EVENT_BUS.addGenericListener(ItemStack.class, new PsiCompat()::attachCapabilities);
		if (ModList.get().isLoaded("quark"))
			MinecraftForge.EVENT_BUS.addGenericListener(ItemStack.class, new QuarkCompat()::attachCapabilities);
	}

	/*private void doClientStuff(final FMLClientSetupEvent event) {
		// do something that can only be done on the client
		//LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {
		// some example code to dispatch IMC to another mod
		InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
	}

	private void processIMC(final InterModProcessEvent event) {
		// some example code to receive and process InterModComms from other mods
		LOGGER.info("Got IMC {}", event.getIMCStream().
				map(m->m.getMessageSupplier().get()).
				collect(Collectors.toList()));
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		// do something when the server starts
		LOGGER.info("HELLO from server starting");
	}

	// You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
	// Event bus for receiving Registry Events)
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
			// register a new block here
			LOGGER.info("HELLO from Register Block");
		}
	}*/


	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		GSON = new GsonBuilder()
				.registerTypeAdapter(Variant.class, new Variant.Deserializer())
				.registerTypeAdapter(ItemCameraTransforms.class, new ItemCameraTransforms.Deserializer())
				.registerTypeAdapter(ItemTransformVec3f.class, new ItemTransformVec3f.Deserializer())
				.create();

		DataGenerator gen = event.getGenerator();

		if (event.includeClient()) {
			gen.addProvider(new MaterialisLang(gen));
			ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
			ItemModelProvider itemModels = new MaterialisItemModels(gen, existingFileHelper);
			gen.addProvider(itemModels);
			gen.addProvider(new MaterialisBlockStates(gen, itemModels.existingFileHelper));
			MaterialisMaterialTextures materialSprites = new MaterialisMaterialTextures();
			TinkerMaterialSpriteProvider tinkerMaterialSprites = new TinkerMaterialSpriteProvider();
			//gen.addProvider(new MaterialRenderInfoProvider(gen, materialSprites));

			//generate tinkers parts with materialis materials
			gen.addProvider(new MaterialPartTextureGenerator(gen, existingFileHelper, new TinkerPartSpriteProvider(), materialSprites));
			//generate materialis parts with materialis materials
			gen.addProvider(new MaterialPartTextureGenerator(gen, existingFileHelper, new MaterialisPartTextures(), materialSprites));
			//generate materialis parts with tinkers materials
			gen.addProvider(new MaterialPartTextureGenerator(gen, existingFileHelper, new MaterialisPartTextures(), tinkerMaterialSprites));
		} if (event.includeServer()) {
			gen.addProvider(new MaterialisLootTables(gen));
			gen.addProvider(new MaterialisRecipes(gen));
			BlockTagsProvider blockTags = new MaterialisBlockTags(gen, event.getExistingFileHelper());
			gen.addProvider(blockTags);
			gen.addProvider(new MaterialisItemTags(gen, blockTags, event.getExistingFileHelper()));
			gen.addProvider(new MaterialisFluidTags(gen, event.getExistingFileHelper()));
			AbstractMaterialDataProvider materials = new MaterialisMaterials(gen);
			gen.addProvider(materials);
			gen.addProvider(new MaterialisMaterialStats(gen, materials));
			gen.addProvider(new MaterialisMaterialTraits(gen, materials));
			gen.addProvider(new MaterialisToolDefinitions(gen));
			gen.addProvider(new MaterialisToolSlotLayouts(gen));
		}
	}

	@EventBusSubscriber(modid = modID, value = Dist.CLIENT, bus = Bus.MOD)
	public static class MaterialisClient {

		@SubscribeEvent
		static void itemColors(ColorHandlerEvent.Item event) {
			final ItemColors colors = event.getItemColors();

			//tint tool and part textures for fallback
			ToolClientEvents.registerToolItemColors(colors, MaterialisResources.WRENCH);
			ToolClientEvents.registerToolItemColors(colors, MaterialisResources.BATTLEWRENCH);
		}

		@SubscribeEvent
		static void registerModifierModels(ModifierModelRegistrationEvent event) {
			event.registerModel(new ResourceLocation(Materialis.modID, "tinted"), TintedModifierModel.UNBAKED_INSTANCE);
		}
	}
}
