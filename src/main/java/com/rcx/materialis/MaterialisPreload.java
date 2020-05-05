package com.rcx.materialis;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Materialis.ID + "preload", version = Materialis.VERSION, dependencies = "required-before:tconstruct")
public class MaterialisPreload {
	@EventHandler
	public void preInit(FMLPreInitializationEvent preEvent) {
		Materialis.proxy.earlyPreInit(preEvent);
	}
}
