package com.rcx.materialis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rcx.materialis.compat.TinkerToolRuneColor;
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
import com.rcx.materialis.datagen.MaterialisModifiers;
import com.rcx.materialis.datagen.MaterialisPartTextures;
import com.rcx.materialis.datagen.MaterialisRecipes;
import com.rcx.materialis.datagen.MaterialisRenderInfo;
import com.rcx.materialis.datagen.MaterialisToolDefinitions;
import com.rcx.materialis.datagen.MaterialisToolSlotLayouts;
import com.rcx.materialis.modifiers.OtherworldlyModifier;
import com.rcx.materialis.util.PacketElvenBeam;
import com.rcx.materialis.util.PacketTerraBeam;
import com.rcx.materialis.util.TinkerToolFluxed;
import com.rcx.materialis.util.TintedModifierModel;

import net.minecraft.client.color.item.ItemColors;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import slimeknights.mantle.client.model.NBTKeyModel;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.library.client.model.tools.ToolModel;
import slimeknights.tconstruct.library.client.modifiers.ModifierModelManager.ModifierModelRegistrationEvent;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.modifiers.ModifierManager;
import slimeknights.tconstruct.library.tools.capability.ToolCapabilityProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;
import vazkii.botania.forge.network.ForgePacketHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("materialis")
@Mod.EventBusSubscriber(bus = Bus.MOD)
public class Materialis {

	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger();
	public static final String modID = "materialis";

	public Materialis() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::setup);
		bus.addListener(this::doClientStuff);

		MaterialisResources.FLUIDS.register(bus);
		MaterialisResources.BLOCKS.register(bus);
		MaterialisResources.ITEMS.register(bus);
		MaterialisResources.ITEMS_EXTENDED.register(bus);
		MaterialisResources.RECIPE_SERIALIZERS.register(bus);
		MaterialisModifiers.MODIFIERS.register(bus);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> MaterialisClient::onConstruct);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		//if (ModList.get().isLoaded("psi"))
		//ToolCapabilityProvider.register(TinkerToolSocketable::new);
		if (ModList.get().isLoaded("quark"))
			ToolCapabilityProvider.register(TinkerToolRuneColor::new);
		if (ModList.get().isLoaded("botania")) {
			ForgePacketHandler.CHANNEL.registerMessage(293, PacketTerraBeam.class, PacketTerraBeam::encode, PacketTerraBeam::decode, PacketTerraBeam::handle);
			ForgePacketHandler.CHANNEL.registerMessage(294, PacketElvenBeam.class, PacketElvenBeam::encode, PacketElvenBeam::decode, PacketElvenBeam::handle);
		}
		ToolCapabilityProvider.register(TinkerToolFluxed::new);
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		NBTKeyModel.registerExtraTexture(new ResourceLocation(TConstruct.MOD_ID, "creative_slot"), "sensor", new ResourceLocation(Materialis.modID, "item/sensor_slot"));
	}

	@SubscribeEvent
	public static void registerSerializers(RegistryEvent.Register<RecipeSerializer<?>> event) {
		ModifierManager.MODIFIER_LOADERS.register(new ResourceLocation(modID, "otherworldly"), OtherworldlyModifier.LOADER);
	}

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();

		if (event.includeClient()) {
			gen.addProvider(new MaterialisLang(gen));
			ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
			ItemModelProvider itemModels = new MaterialisItemModels(gen, existingFileHelper);
			gen.addProvider(itemModels);
			gen.addProvider(new MaterialisBlockStates(gen, existingFileHelper));
			gen.addProvider(new MaterialisModifiers(gen));
			MaterialisMaterialTextures materialSprites = new MaterialisMaterialTextures();
			MaterialisPartTextures partSprites = new MaterialisPartTextures();
			TinkerMaterialSpriteProvider tinkerMaterialSprites = new TinkerMaterialSpriteProvider();
			gen.addProvider(new MaterialisRenderInfo(gen, materialSprites));
			gen.addProvider(new GeneratorPartTextureJsonGenerator(gen, Materialis.modID, partSprites));
			//generate tinkers parts with materialis materials
			gen.addProvider(new MaterialPartTextureGenerator(gen, existingFileHelper, new TinkerPartSpriteProvider(), materialSprites));
			//generate materialis parts with tinkers and materialis materials
			gen.addProvider(new MaterialPartTextureGenerator(gen, existingFileHelper, partSprites, materialSprites, tinkerMaterialSprites));
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

		public static void onConstruct() {
			/*if (Minecraft.getInstance() != null) {
				ResourceManager manager = Minecraft.getInstance().getResourceManager();
				if (manager instanceof IReloadableResourceManager) {
					((IReloadableResourceManager) manager).registerReloadListener(ExosuitModel.RELOAD_LISTENER);
				}
			}*/
		}

		@SubscribeEvent
		static void itemColors(ColorHandlerEvent.Item event) {
			final ItemColors colors = event.getItemColors();

			//tint tool and part textures for fallback
			ToolModel.registerItemColors(colors, MaterialisResources.WRENCH);
			ToolModel.registerItemColors(colors, MaterialisResources.BATTLEWRENCH);
			/*ToolModel.registerItemColors(colors, () -> MaterialisResources.PSIMETAL_EXOSUIT.get(ArmorSlotType.HELMET));
			ToolModel.registerItemColors(colors, () -> MaterialisResources.PSIMETAL_EXOSUIT.get(ArmorSlotType.CHESTPLATE));
			ToolModel.registerItemColors(colors, () -> MaterialisResources.PSIMETAL_EXOSUIT.get(ArmorSlotType.LEGGINGS));
			ToolModel.registerItemColors(colors, () -> MaterialisResources.PSIMETAL_EXOSUIT.get(ArmorSlotType.BOOTS));*/
		}

		@SubscribeEvent
		static void registerModifierModels(ModifierModelRegistrationEvent event) {
			event.registerModel(new ResourceLocation(Materialis.modID, "tinted"), TintedModifierModel.UNBAKED_INSTANCE);
		}
	}
}
