package com.rcx.materialis.traits;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.traits.ITrait;

public class MaterialisTraits {
	
	public static ITrait limited = new TraitLimited();
	
	public static void init() {
		TinkerRegistry.addTrait(limited);
	}
}
