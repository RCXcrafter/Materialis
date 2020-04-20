package com.rcx.materialis.modules;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IModule {

	public Boolean shouldLoad();

	public String getName();

	public void preInit(FMLPreInitializationEvent preEvent);

	public void init(FMLInitializationEvent event);

	public void postInit(FMLPostInitializationEvent postEvent);
}
