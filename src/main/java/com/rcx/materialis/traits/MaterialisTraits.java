package com.rcx.materialis.traits;

import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.traits.ITrait;

public class MaterialisTraits {

	public static ITrait blinding = new TraitBlinding();
	public static ITrait bloodthirst = new TraitBloodthirst();
	public static ITrait cosmic;
	public static ITrait crystalline = new TraitCrystalline();
	public static ITrait fancy = new TraitNoEffect("fancy", 0xFFE30B);
	public static ITrait intangible = new TraitIntangible();
	public static ITrait limited = new TraitLimited();
	public static ITrait phasing = new TraitPhasing();
	public static ITrait shortFuse = new TraitShortFuse();
	public static ITrait supermassive = new TraitSupermassive();
	public static ITrait unbreakable = new TraitUnbreakable();
	public static ITrait unlimited = new TraitNoEffect("unlimited", 0x64A7B5);

	public static void preInit() {
		registerTrait(blinding);
		registerTrait(blinding);
		registerTrait(bloodthirst);
		registerTrait(crystalline);
		registerTrait(fancy);
		registerTrait(intangible);
		registerTrait(limited);
		registerTrait(phasing);
		registerTrait(shortFuse);
		registerTrait(supermassive);
		registerTrait(unbreakable);
		registerTrait(unlimited);
	}

	public static void registerTrait(ITrait trait) {
		if (TinkerRegistry.getTrait(trait.getIdentifier()) == null)
			TinkerRegistry.addTrait(trait);
	}
}
