package com.rcx.materialis.traits;

import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.traits.ITrait;

public class MaterialisTraits {

	public static ITrait cosmic;
	public static ITrait crystalline = new TraitCrystalline();
	public static ITrait fancy = new TraitNoEffect("fancy", 0xFFE30B);
	public static ITrait limited = new TraitLimited();
	public static ITrait supermassive = new TraitSupermassive();
	public static ITrait unbreakable = new TraitUnbreakable();
	public static ITrait unlimited = new TraitNoEffect("unlimited", 0x64A7B5);
	
	public static void init() {
		TinkerRegistry.addTrait(crystalline);
		TinkerRegistry.addTrait(fancy);
		TinkerRegistry.addTrait(limited);
		TinkerRegistry.addTrait(supermassive);
		TinkerRegistry.addTrait(unbreakable);
		TinkerRegistry.addTrait(unlimited);
	}
}
