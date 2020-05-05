package com.rcx.materialis.modules;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IModule {

	public Boolean shouldLoad();

	public String getName();

	public void earlyPreInit(FMLPreInitializationEvent preEvent);

	public void preInit(FMLPreInitializationEvent preEvent);

	public void registerItems(RegistryEvent.Register<Item> event);

	public void init(FMLInitializationEvent event);

	public void postInit(FMLPostInitializationEvent postEvent);
}
