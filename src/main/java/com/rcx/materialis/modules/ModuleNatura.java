package com.rcx.materialis.modules;

import com.rcx.materialis.MaterialisConfig;
import com.rcx.materialis.Util;
import com.rcx.materialis.traits.MaterialisTraits;
import com.rcx.materialis.traits.armor.MaterialisArmorTraits;

import c4.conarm.lib.materials.ArmorMaterialType;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.BowStringMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.FletchingMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.tools.TinkerMaterials;

import java.util.*;

public class ModuleNatura implements IModule {

	private static final Material GHOSTWOOD = new Material("ghostwood", 0xBDBDBD);

	private static final Material GHOSTWOOD_LEAF = new Material("ghostwood_leaf", 0xF1E9D4);

	private static final Material BLOODWOOD = new Material("bloodwood", 0xA52919);

	private static final Material DARKWOOD = new Material("darkwood", 0x3661A0);

	private static final Material FUSEWOOD = new Material("fusewood", 0x416B4F);

	private static final Material FLAMESTRING = new Material("flamestring", 0xB15E31);

	private static final Material IMPSKIN = new Material("impskin", 0xBF7C18);

	private static final Map<String, Boolean> I_REGISTERED_THE_MATERIAL = new HashMap<>();

	@Override
	public Boolean shouldLoad() {
		return Loader.isModLoaded(this.getName()) && !MaterialisConfig.blacklist.isModuleBlacklisted(this.getName());
	}

	@Override
	public String getName() {
		return "natura";
	}

	@Override
	public void earlyPreInit(FMLPreInitializationEvent preEvent) {
		//register wood materials early
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(GHOSTWOOD.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(GHOSTWOOD.getIdentifier(), true);
			TinkerRegistry.addMaterial(GHOSTWOOD);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(GHOSTWOOD_LEAF.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(GHOSTWOOD_LEAF.getIdentifier(), true);
			TinkerRegistry.addMaterial(GHOSTWOOD_LEAF);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(BLOODWOOD.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(BLOODWOOD.getIdentifier(), true);
			TinkerRegistry.addMaterial(BLOODWOOD);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(DARKWOOD.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(DARKWOOD.getIdentifier(), true);
			TinkerRegistry.addMaterial(DARKWOOD);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(FUSEWOOD.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(FUSEWOOD.getIdentifier(), true);
			TinkerRegistry.addMaterial(FUSEWOOD);
		}
	}

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(GHOSTWOOD.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(GHOSTWOOD.getIdentifier(), false)) {
			GHOSTWOOD.addTrait(MaterialisTraits.intangible);
			TinkerRegistry.addMaterialStats(GHOSTWOOD,
					new HeadMaterialStats(35, 2.0F, 2.0F, 0),
					new HandleMaterialStats(1.0F, 25),
					new ExtraMaterialStats(15),
					new BowMaterialStats(1.0F, 1.0F, 0.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(GHOSTWOOD, 0.0F);
				GHOSTWOOD.addTrait(MaterialisArmorTraits.intangible, ArmorMaterialType.CORE);
				GHOSTWOOD.addTrait(MaterialisArmorTraits.intangible, ArmorMaterialType.PLATES);
				GHOSTWOOD.addTrait(MaterialisArmorTraits.intangible, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(GHOSTWOOD_LEAF.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(GHOSTWOOD_LEAF.getIdentifier(), false)) {
			GHOSTWOOD_LEAF.addTrait(MaterialisTraits.phasing);
			TinkerRegistry.addMaterialStats(GHOSTWOOD_LEAF, new FletchingMaterialStats(0.9F, 1.5F));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(BLOODWOOD.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(BLOODWOOD.getIdentifier(), false)) {
			BLOODWOOD.addTrait(MaterialisTraits.bloodthirst);
			TinkerRegistry.addMaterialStats(BLOODWOOD,
					new HeadMaterialStats(250, 7.0F, 5.0F, 2),
					new HandleMaterialStats(1.0F, 88),
					new ExtraMaterialStats(60),
					new BowMaterialStats(1.0F, 1.0F, 0.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(BLOODWOOD, 0.0F);
				BLOODWOOD.addTrait(MaterialisArmorTraits.renewableEnergy, ArmorMaterialType.CORE);
				BLOODWOOD.addTrait(MaterialisArmorTraits.renewableEnergy, ArmorMaterialType.PLATES);
				BLOODWOOD.addTrait(MaterialisArmorTraits.renewableEnergy, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(DARKWOOD.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(DARKWOOD.getIdentifier(), false)) {
			DARKWOOD.addTrait(MaterialisTraits.blinding);
			TinkerRegistry.addMaterialStats(DARKWOOD,
					new HeadMaterialStats(120, 4.0F, 3.0F, 1),
					new HandleMaterialStats(1.0F, 36),
					new ExtraMaterialStats(20),
					new BowMaterialStats(1.0F, 1.0F, 0.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(DARKWOOD, 0.0F);
				DARKWOOD.addTrait(MaterialisArmorTraits.blinding, ArmorMaterialType.CORE);
				DARKWOOD.addTrait(MaterialisArmorTraits.blinding, ArmorMaterialType.PLATES);
				DARKWOOD.addTrait(MaterialisArmorTraits.blinding, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(FUSEWOOD.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(FUSEWOOD.getIdentifier(), false)) {
			FUSEWOOD.addTrait(MaterialisTraits.shortFuse);
			TinkerRegistry.addMaterialStats(FUSEWOOD,
					new HeadMaterialStats(204, 6.0F, 4.0F, 2),
					new HandleMaterialStats(1.0F, 60),
					new ExtraMaterialStats(30),
					new BowMaterialStats(1.0F, 1.0F, 0.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(FUSEWOOD, 0.0F);
				FUSEWOOD.addTrait(MaterialisArmorTraits.shortFuse, ArmorMaterialType.CORE);
				FUSEWOOD.addTrait(MaterialisArmorTraits.shortFuse, ArmorMaterialType.PLATES);
				FUSEWOOD.addTrait(MaterialisArmorTraits.shortFuse, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(FLAMESTRING.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(FLAMESTRING.getIdentifier(), true);

			TinkerRegistry.addMaterial(FLAMESTRING);
			TinkerRegistry.addMaterialStats(FLAMESTRING, new BowStringMaterialStats(1.2F));
		}

		if (ModuleConarm.loadArmor()) {
			if (!MaterialisConfig.blacklist.isMaterialBlacklisted(IMPSKIN.getIdentifier())) {
				I_REGISTERED_THE_MATERIAL.put(IMPSKIN.getIdentifier(), true);

				IMPSKIN.addTrait(MaterialisArmorTraits.fireproof);
				TinkerRegistry.addMaterial(IMPSKIN);
				TinkerRegistry.addMaterialStats(IMPSKIN,
						new CoreMaterialStats(20, 8.0F),
						new PlatesMaterialStats(1.0F, 3, 0.0F),
						new TrimMaterialStats(15));
			}
		}
	}

	@Override
	public void registerItems(Register<Item> event) {}

	@Override
	public void init(FMLInitializationEvent event) {
		TinkerMaterials.cactus.addItem(new ItemStack(Util.getItem("natura", "saguaro")), 1, Material.VALUE_Ingot);

		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(GHOSTWOOD.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(GHOSTWOOD.getIdentifier(), false)) {
			GHOSTWOOD.addItem(Util.getItem("natura", "nether_planks"), 1, Material.VALUE_Ingot);
			GHOSTWOOD.addItem(new ItemStack(Util.getItem("natura", "sticks"), 1, 9), 1, Material.VALUE_Shard);
			GHOSTWOOD.addItem(Util.getItem("natura", "nether_logs"), 1, Material.VALUE_Ingot * 4);
			GHOSTWOOD.setRepresentativeItem(Util.getItem("natura", "nether_planks"));
			GHOSTWOOD.setCraftable(true);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(GHOSTWOOD_LEAF.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(GHOSTWOOD_LEAF.getIdentifier(), false)) {
			GHOSTWOOD_LEAF.addItem(Util.getItem("natura", "nether_leaves"), 1, Material.VALUE_Ingot);
			GHOSTWOOD_LEAF.setRepresentativeItem(Util.getItem("natura", "nether_leaves"));
			GHOSTWOOD_LEAF.setCraftable(true);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(BLOODWOOD.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(BLOODWOOD.getIdentifier(), false)) {
			BLOODWOOD.addItem(new ItemStack(Util.getItem("natura", "nether_planks"), 1, 1), 1, Material.VALUE_Ingot);
			BLOODWOOD.addItem(new ItemStack(Util.getItem("natura", "sticks"), 1, 12), 1, Material.VALUE_Shard);
			BLOODWOOD.addItem(Util.getItem("natura", "nether_logs2"), 1, Material.VALUE_Ingot * 4);
			BLOODWOOD.addItem(new ItemStack(Util.getItem("natura", "nether_logs2"), 1, 15), 1, Material.VALUE_Ingot * 4);
			BLOODWOOD.setRepresentativeItem(new ItemStack(Util.getItem("natura", "nether_planks"), 1, 1));
			BLOODWOOD.setCraftable(true);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(DARKWOOD.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(DARKWOOD.getIdentifier(), false)) {
			DARKWOOD.addItem(new ItemStack(Util.getItem("natura", "nether_planks"), 1, 2), 1, Material.VALUE_Ingot);
			DARKWOOD.addItem(new ItemStack(Util.getItem("natura", "sticks"), 1, 10), 1, Material.VALUE_Shard);
			DARKWOOD.addItem(new ItemStack(Util.getItem("natura", "nether_logs"), 1, 1), 1, Material.VALUE_Ingot * 4);
			DARKWOOD.setRepresentativeItem(new ItemStack(Util.getItem("natura", "nether_planks"), 1, 2));
			DARKWOOD.setCraftable(true);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(FUSEWOOD.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(FUSEWOOD.getIdentifier(), false)) {
			FUSEWOOD.addItem(new ItemStack(Util.getItem("natura", "nether_planks"), 1, 3), 1, Material.VALUE_Ingot);
			FUSEWOOD.addItem(new ItemStack(Util.getItem("natura", "sticks"), 1, 11), 1, Material.VALUE_Shard);
			FUSEWOOD.addItem(new ItemStack(Util.getItem("natura", "nether_logs"), 1, 2), 1, Material.VALUE_Ingot * 4);
			FUSEWOOD.setRepresentativeItem(new ItemStack(Util.getItem("natura", "nether_planks"), 1, 3));
			FUSEWOOD.setCraftable(true);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(FLAMESTRING.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(FLAMESTRING.getIdentifier(), false)) {
			FLAMESTRING.addItem(new ItemStack(Util.getItem("natura", "materials"), 1, 7), 1, Material.VALUE_Ingot);
			FLAMESTRING.setRepresentativeItem(new ItemStack(Util.getItem("natura", "materials"), 1, 7));
			FLAMESTRING.setCraftable(true);
		}

		if (ModuleConarm.loadArmor()
				&& !MaterialisConfig.blacklist.isMaterialBlacklisted(IMPSKIN.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(IMPSKIN.getIdentifier(), false)) {
			IMPSKIN.addItem(new ItemStack(Util.getItem("natura", "materials"), 1, 6), 1, Material.VALUE_Ingot);
			IMPSKIN.setRepresentativeItem(new ItemStack(Util.getItem("natura", "materials"), 1, 6));
			IMPSKIN.setCraftable(true);
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {}
}
