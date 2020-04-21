package com.rcx.materialis.traits;

import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.traits.ITrait;

public class MaterialisTraits {

	public static ITrait limited = new TraitLimited();
	public static ITrait unlimited = new TraitUnlimited();
	public static ITrait crystalline = new TraitCrystalline();
	public static ITrait supermassive = new TraitSupermassive();
	public static ITrait cosmic = new TraitCosmic();
	public static ITrait unbreakable = new TraitUnbreakable();
	
	public static void init() {
		TinkerRegistry.addTrait(limited);
		TinkerRegistry.addTrait(unlimited);
		TinkerRegistry.addTrait(crystalline);
		TinkerRegistry.addTrait(supermassive);
		TinkerRegistry.addTrait(cosmic);
		TinkerRegistry.addTrait(unbreakable);
	}
}
