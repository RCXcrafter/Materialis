package com.rcx.materialis.proxy;

import java.util.ArrayList;
import java.util.List;

import com.rcx.materialis.MaterialisRegistry;
import com.rcx.materialis.Materialis;
import com.rcx.materialis.modules.IModule;
import com.rcx.materialis.modules.ModuleTConstruct;
import com.rcx.materialis.resources.BlockWorldPortal;
import com.rcx.materialis.traits.MaterialisTraits;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public static List<IModule> modules = new ArrayList<IModule>();

	public void preInit(FMLPreInitializationEvent preEvent) {
		modules.add(new ModuleTConstruct());

		for (IModule module : modules) {
			if (module.shouldLoad())
				module.preInit(preEvent);
		}
	}

	public void init(FMLInitializationEvent event) {
		MaterialisTraits.init();
		for (IModule module : modules) {
			if (module.shouldLoad())
				module.init(event);
		}
	}

	public void postInit(FMLPostInitializationEvent postEvent) {
		for (IModule module : modules) {
			if (module.shouldLoad())
				module.postInit(postEvent);
		}
	}

	public void ConfigChanged(OnConfigChangedEvent event) {
		if (event.getModID().equals(Materialis.ID)) {
			ConfigManager.sync(Materialis.ID, Type.INSTANCE);
		}
	}

	public void registerBlocks(RegistryEvent.Register<Block> event) {
		for (ItemBlock block : MaterialisRegistry.blocks)
			event.getRegistry().register(block.getBlock());
	}

	public void registerItems(RegistryEvent.Register<Item> event) {
		for (ItemBlock block : MaterialisRegistry.blocks)
			event.getRegistry().register(block);

		for (Item item : MaterialisRegistry.items)
			event.getRegistry().register(item);
	}

	public void registerModels(ModelRegistryEvent event) {}
}
