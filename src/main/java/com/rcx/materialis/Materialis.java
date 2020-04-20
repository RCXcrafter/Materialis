package com.rcx.materialis;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.mantle.client.CreativeTab;
import slimeknights.tconstruct.shared.TinkerCommons;

import com.rcx.materialis.proxy.CommonProxy;

@Mod(modid = Materialis.ID, name = Materialis.NAME, version = Materialis.VERSION, dependencies = "required-after:tconstruct")
public class Materialis {

	@SidedProxy(clientSide = "com.rcx.materialis.proxy.ClientProxy", serverSide = "com.rcx.materialis.proxy.CommonProxy")
	public static CommonProxy proxy;
	public static final String ID = "materialis";
	public static final String NAME = "Materialis";
	public static final String VERSION = "1.0.0";

	public static ItemStack tabItem = TinkerCommons.ingotKnightSlime;
	public static CreativeTab MatTab = new CreativeTab(ID + ".creativeTab", ItemStack.EMPTY) {
		@SideOnly(Side.CLIENT)
		public ItemStack getIconItemStack() {
			return tabItem;
		}

		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem() {
			return tabItem;
		};
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent) {
		MinecraftForge.EVENT_BUS.register(this);
		proxy.preInit(preEvent);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent postEvent) {
		proxy.postInit(postEvent);
	}

	@SubscribeEvent
	public void onConfigChangedEvent(OnConfigChangedEvent event) {
		proxy.ConfigChanged(event);
	}

	@SubscribeEvent
	public void blockRegistry(final RegistryEvent.Register<Block> event) {
		proxy.registerBlocks(event);
	}

	@SubscribeEvent
	public void itemRegistry(final RegistryEvent.Register<Item> event) {
		proxy.registerItems(event);
	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		proxy.registerModels(event);
	}
}
