package com.rcx.materialis.traits.armor;

import c4.conarm.lib.traits.IArmorTrait;
import slimeknights.tconstruct.library.TinkerRegistry;

public class MaterialisArmorTraits {

	public static IArmorTrait cosmic = new TraitArmorCosmic();
	public static IArmorTrait crystalline = new TraitArmorCrystalline();
	public static IArmorTrait darkness = new TraitArmorBlinding();
	public static IArmorTrait fireproof = new TraitArmorFireproof();
	public static IArmorTrait intangible = new TraitArmorIntangible();
	public static IArmorTrait limited = new TraitArmorLimited();
	public static IArmorTrait renewableEnergy = new TraitArmorRenewableEnergy();
	public static IArmorTrait shortFuse = new TraitArmorShortFuse();
	public static IArmorTrait supermassive = new TraitArmorSupermassive();
	public static IArmorTrait unbreakable = new TraitArmorUnbreakable();
	public static IArmorTrait untamed = new TraitArmorUntamed();
	
	public static void init() {
		TinkerRegistry.addTrait(cosmic);
		TinkerRegistry.addTrait(crystalline);
		TinkerRegistry.addTrait(darkness);
		TinkerRegistry.addTrait(fireproof);
		TinkerRegistry.addTrait(intangible);
		TinkerRegistry.addTrait(limited);
		TinkerRegistry.addTrait(renewableEnergy);
		TinkerRegistry.addTrait(shortFuse);
		TinkerRegistry.addTrait(supermassive);
		TinkerRegistry.addTrait(unbreakable);
		TinkerRegistry.addTrait(untamed);
	}
}
