package com.rcx.materialis.traits;

import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.traits.ITrait;

public class MaterialisTraits {

	public static ITrait fallback = new TraitNoEffect("materialis_fallback", 0x000000);

	public static ITrait blinding = fallback;
	public static ITrait bloodthirst = fallback;
	public static ITrait cosmic = fallback;
	public static ITrait crystalline = fallback;
	public static ITrait fancy = fallback;
	public static ITrait intangible = fallback;
	public static ITrait limited = fallback;
	public static ITrait phasing = fallback;
	public static ITrait shortFuse = fallback;
	public static ITrait supermassive = fallback;
	public static ITrait unbreakable = fallback;
	public static ITrait unlimited = fallback;

	public static void preInit() {
		if (TinkerRegistry.getTrait(TraitBlinding.id) == null)
			blinding = new TraitBlinding();
		if (TinkerRegistry.getTrait(TraitBloodthirst.id) == null)
			bloodthirst = new TraitBloodthirst();
		if (TinkerRegistry.getTrait(TraitCrystalline.id) == null)
			crystalline = new TraitCrystalline();
		if (TinkerRegistry.getTrait("fancy") == null)
			fancy = new TraitNoEffect("fancy", 0xFFE30B);
		if (TinkerRegistry.getTrait(TraitIntangible.id) == null)
			intangible = new TraitIntangible();
		if (TinkerRegistry.getTrait(TraitLimited.id) == null)
			limited = new TraitLimited();
		if (TinkerRegistry.getTrait(TraitPhasing.id) == null)
			phasing = new TraitPhasing();
		if (TinkerRegistry.getTrait(TraitShortFuse.id) == null)
			shortFuse = new TraitShortFuse();
		if (TinkerRegistry.getTrait(TraitSupermassive.id) == null)
			supermassive = new TraitSupermassive();
		if (TinkerRegistry.getTrait(TraitUnbreakable.id) == null)
			unbreakable = new TraitUnbreakable();
		if (TinkerRegistry.getTrait("unlimited") == null)
			unlimited = new TraitNoEffect("unlimited", 0x64A7B5);
	}
}
