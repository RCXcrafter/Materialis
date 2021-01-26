package com.rcx.materialis.traits.armor;

import com.rcx.materialis.traits.TraitBlinding;
import com.rcx.materialis.traits.TraitCrystalline;
import com.rcx.materialis.traits.TraitIntangible;
import com.rcx.materialis.traits.TraitLimited;
import com.rcx.materialis.traits.TraitShortFuse;
import com.rcx.materialis.traits.TraitSupermassive;
import com.rcx.materialis.traits.TraitUnbreakable;

import c4.conarm.lib.traits.AbstractArmorTrait;
import c4.conarm.lib.traits.IArmorTrait;
import slimeknights.tconstruct.library.TinkerRegistry;

public class MaterialisArmorTraits {

	public static IArmorTrait fallback = new AbstractArmorTrait("materialis_fallback", 0x000000) {};

	public static IArmorTrait blinding = fallback;
	public static IArmorTrait cosmic = fallback;
	public static IArmorTrait crystalline = fallback;
	public static IArmorTrait fireproof = fallback;
	public static IArmorTrait intangible = fallback;
	public static IArmorTrait limited = fallback;
	public static IArmorTrait renewableEnergy = fallback;
	public static IArmorTrait shortFuse = fallback;
	public static IArmorTrait supermassive = fallback;
	public static IArmorTrait unbreakable = fallback;
	public static IArmorTrait untamed = fallback;

	public static void preInit() {
		if (TinkerRegistry.getModifier(TraitBlinding.id) == null)
			blinding = new TraitArmorBlinding();
		if (TinkerRegistry.getModifier(TraitArmorCosmic.id) == null)
			cosmic = new TraitArmorCosmic();
		if (TinkerRegistry.getModifier(TraitCrystalline.id) == null)
			crystalline = new TraitArmorCrystalline();
		if (TinkerRegistry.getModifier(TraitArmorFireproof.id) == null)
			fireproof = new TraitArmorFireproof();
		if (TinkerRegistry.getModifier(TraitIntangible.id) == null)
			intangible = new TraitArmorIntangible();
		if (TinkerRegistry.getModifier(TraitLimited.id) == null)
			limited = new TraitArmorLimited();
		if (TinkerRegistry.getModifier(TraitArmorRenewableEnergy.id) == null)
			renewableEnergy = new TraitArmorRenewableEnergy();
		if (TinkerRegistry.getModifier(TraitShortFuse.id) == null)
			shortFuse = new TraitArmorShortFuse();
		if (TinkerRegistry.getModifier(TraitSupermassive.id) == null)
			supermassive = new TraitArmorSupermassive();
		if (TinkerRegistry.getModifier(TraitUnbreakable.id) == null)
			unbreakable = new TraitArmorUnbreakable();
		if (TinkerRegistry.getModifier(TraitArmorUntamed.id) == null)
			untamed = new TraitArmorUntamed();
	}
}
