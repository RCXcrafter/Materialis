package com.rcx.materialis.traits.armor;

import c4.conarm.lib.traits.IArmorTrait;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.traits.ITrait;

public class MaterialisArmorTraits {

	public static IArmorTrait blinding = new TraitArmorBlinding();
	public static IArmorTrait cosmic = new TraitArmorCosmic();
	public static IArmorTrait crystalline = new TraitArmorCrystalline();
	public static IArmorTrait fireproof = new TraitArmorFireproof();
	public static IArmorTrait intangible = new TraitArmorIntangible();
	public static IArmorTrait limited = new TraitArmorLimited();
	public static IArmorTrait renewableEnergy = new TraitArmorRenewableEnergy();
	public static IArmorTrait shortFuse = new TraitArmorShortFuse();
	public static IArmorTrait supermassive = new TraitArmorSupermassive();
	public static IArmorTrait unbreakable = new TraitArmorUnbreakable();
	public static IArmorTrait untamed = new TraitArmorUntamed();

	public static void preInit() {
		registerTrait(blinding);
		registerTrait(cosmic);
		registerTrait(crystalline);
		registerTrait(fireproof);
		registerTrait(intangible);
		registerTrait(limited);
		registerTrait(renewableEnergy);
		registerTrait(shortFuse);
		registerTrait(supermassive);
		registerTrait(unbreakable);
		registerTrait(untamed);
	}

	public static void registerTrait(ITrait trait) {
		if (TinkerRegistry.getTrait(trait.getIdentifier()) == null)
			TinkerRegistry.addTrait(trait);
	}
}
