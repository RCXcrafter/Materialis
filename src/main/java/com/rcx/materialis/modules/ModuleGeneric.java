package com.rcx.materialis.modules;

import com.rcx.materialis.MaterialisConfig;

import c4.conarm.common.armor.traits.ArmorTraits;
import c4.conarm.lib.materials.ArmorMaterialType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.tools.TinkerTraits;

import java.util.*;

public class ModuleGeneric implements IModule {

	private static final Material TIN = new Material("tin", 0xD8BFA6, true);

	private static final Material ZINC = new Material("zinc", 0xEDEBBD, true);

	private static final Material BRASS = new Material("brass", 0xFFD177, true);

	private static final Material NICKEL = new Material("nickel", 0xCDD8B3, true);

	private static final Material ALUMINUM = new Material("aluminum", 0xEBC4BE, true);

	private static final Material ALUBRASS = new Material("alubrass", 0xF0D467, true);

	private static final Map<String, Boolean> I_REGISTERED_THE_MATERIAL = new HashMap<>();

	@Override
	public Boolean shouldLoad() {
		return !MaterialisConfig.blacklist.isModuleBlacklisted(this.getName());
	}

	@Override
	public String getName() {
		return "generic";
	}

	@Override
	public void earlyPreInit(FMLPreInitializationEvent preEvent) {}

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(TIN.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(TIN.getIdentifier(), true);

			TIN.addTrait(TinkerTraits.depthdigger);
			TinkerRegistry.addMaterial(TIN);
			TinkerRegistry.addMaterialStats(TIN,
					new HeadMaterialStats(392, 6.5F, 2.5F, 1),
					new HandleMaterialStats(1.15F, 40),
					new ExtraMaterialStats(60),
					new BowMaterialStats(0.5F, 1.6F, 5.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(TIN, 0.0F);
				TIN.addTrait(ArmorTraits.petravidity, ArmorMaterialType.CORE);
				TIN.addTrait(ArmorTraits.petravidity, ArmorMaterialType.PLATES);
				TIN.addTrait(ArmorTraits.petravidity, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(ZINC.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(ZINC.getIdentifier(), true);

			ZINC.addTrait(TinkerTraits.fractured);
			TinkerRegistry.addMaterial(ZINC);
			TinkerRegistry.addMaterialStats(ZINC,
					new HeadMaterialStats(196, 5.6F, 3.0F, 1),
					new HandleMaterialStats(0.95F, 50),
					new ExtraMaterialStats(100),
					new BowMaterialStats(0.4F, 1.3F, 4.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(ZINC, 0.0F);
				ZINC.addTrait(ArmorTraits.indomitable, ArmorMaterialType.CORE);
				ZINC.addTrait(ArmorTraits.indomitable, ArmorMaterialType.PLATES);
				ZINC.addTrait(ArmorTraits.indomitable, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(BRASS.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(BRASS.getIdentifier(), true);

			BRASS.addTrait(TinkerTraits.momentum);
			TinkerRegistry.addMaterial(BRASS);
			TinkerRegistry.addMaterialStats(BRASS,
					new HeadMaterialStats(400, 6.9F, 3.2F, 1),
					new HandleMaterialStats(1.0F, 40),
					new ExtraMaterialStats(120),
					new BowMaterialStats(0.55F, 1.5F, 5.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(BRASS, 0.5F);
				BRASS.addTrait(ArmorTraits.lightweight, ArmorMaterialType.CORE);
				BRASS.addTrait(ArmorTraits.lightweight, ArmorMaterialType.PLATES);
				BRASS.addTrait(ArmorTraits.lightweight, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(NICKEL.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(NICKEL.getIdentifier(), true);

			NICKEL.addTrait(TinkerTraits.jagged);
			TinkerRegistry.addMaterial(NICKEL);
			TinkerRegistry.addMaterialStats(NICKEL,
					new HeadMaterialStats(305, 7.0F, 5.0F, 2),
					new HandleMaterialStats(1.0F, 100),
					new ExtraMaterialStats(70),
					new BowMaterialStats(0.5F, 1.3F, 8.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(NICKEL, 1.0F);
				NICKEL.addTrait(ArmorTraits.rough, ArmorMaterialType.CORE);
				NICKEL.addTrait(ArmorTraits.rough, ArmorMaterialType.PLATES);
				NICKEL.addTrait(ArmorTraits.rough, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(ALUMINUM.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(ALUMINUM.getIdentifier(), true);

			ALUMINUM.addTrait(TinkerTraits.lightweight);
			TinkerRegistry.addMaterial(ALUMINUM);
			TinkerRegistry.addMaterialStats(ALUMINUM,
					new HeadMaterialStats(220, 5.0F, 2.5F, 1),
					new HandleMaterialStats(1.1F, 25),
					new ExtraMaterialStats(100),
					new BowMaterialStats(1.2F, 1.1F, 1.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(ALUMINUM, 0.0F);
				ALUMINUM.addTrait(ArmorTraits.lightweight, ArmorMaterialType.CORE);
				ALUMINUM.addTrait(ArmorTraits.featherweight, ArmorMaterialType.PLATES);
				ALUMINUM.addTrait(ArmorTraits.featherweight, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(ALUBRASS.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(ALUBRASS.getIdentifier(), true);

			ALUBRASS.addTrait(TinkerTraits.coldblooded);
			TinkerRegistry.addMaterial(ALUBRASS);
			TinkerRegistry.addMaterialStats(ALUBRASS,
					new HeadMaterialStats(230, 5.5F, 2.5F, 1),
					new HandleMaterialStats(1.2F, 25),
					new ExtraMaterialStats(120),
					new BowMaterialStats(0.9F, 1.3F, 3.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(ALUBRASS, 0.0F);
				ALUBRASS.addTrait(ArmorTraits.prideful, ArmorMaterialType.CORE);
				ALUBRASS.addTrait(ArmorTraits.prideful, ArmorMaterialType.PLATES);
				ALUBRASS.addTrait(ArmorTraits.prideful, ArmorMaterialType.TRIM);
			}
		}
	}

	@Override
	public void registerItems(Register<Item> event) {}

	@Override
	public void init(FMLInitializationEvent event) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(TIN.getIdentifier())
				&& OreDictionary.doesOreNameExist("ingotTin")
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(TIN.getIdentifier(), false)) {
			TIN.addCommonItems("Tin");
			TIN.setRepresentativeItem(getOredictItem("ingotTin"));
			TIN.setFluid(TinkerFluids.tin);
			TIN.setCraftable(false).setCastable(true);

			new MaterialIntegration(TIN, TinkerFluids.tin, "Tin").toolforge().integrate();
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(ZINC.getIdentifier())
				&& OreDictionary.doesOreNameExist("ingotZinc")
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(ZINC.getIdentifier(), false)) {
			ZINC.addCommonItems("Zinc");
			ZINC.setRepresentativeItem(getOredictItem("ingotZinc"));
			ZINC.setFluid(TinkerFluids.zinc);
			ZINC.setCraftable(false).setCastable(true);

			new MaterialIntegration(ZINC, TinkerFluids.zinc, "Zinc").toolforge().integrate();
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(BRASS.getIdentifier())
				&& OreDictionary.doesOreNameExist("ingotBrass")
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(BRASS.getIdentifier(), false)) {
			BRASS.addCommonItems("Brass");
			BRASS.setRepresentativeItem(getOredictItem("ingotBrass"));
			BRASS.setFluid(TinkerFluids.brass);
			BRASS.setCraftable(false).setCastable(true);

			new MaterialIntegration(BRASS, TinkerFluids.brass, "Brass").toolforge().integrate();
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(NICKEL.getIdentifier())
				&& OreDictionary.doesOreNameExist("ingotNickel")
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(NICKEL.getIdentifier(), false)) {
			NICKEL.addCommonItems("Nickel");
			NICKEL.setRepresentativeItem(getOredictItem("ingotNickel"));
			NICKEL.setFluid(TinkerFluids.nickel);
			NICKEL.setCraftable(false).setCastable(true);

			new MaterialIntegration(NICKEL, TinkerFluids.nickel, "Nickel").toolforge().integrate();
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(ALUMINUM.getIdentifier())
				&& OreDictionary.doesOreNameExist("ingotAluminum")
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(ALUMINUM.getIdentifier(), false)) {
			ALUMINUM.addCommonItems("Aluminum");
			ALUMINUM.setRepresentativeItem(getOredictItem("ingotAluminum"));
			ALUMINUM.setFluid(TinkerFluids.aluminum);
			ALUMINUM.setCraftable(false).setCastable(true);

			new MaterialIntegration(ALUMINUM, TinkerFluids.aluminum, "Aluminum").toolforge().integrate();
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(ALUBRASS.getIdentifier())
				&& OreDictionary.doesOreNameExist("ingotAlubrass")
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(ALUBRASS.getIdentifier(), false)) {
			ALUBRASS.addCommonItems("Alubrass");
			ALUBRASS.setRepresentativeItem(getOredictItem("ingotAlubrass"));
			ALUBRASS.setFluid(TinkerFluids.alubrass);
			ALUBRASS.setCraftable(false).setCastable(true);

			new MaterialIntegration(ALUBRASS, TinkerFluids.alubrass, "Alubrass").toolforge().integrate();
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {}

	public static ItemStack getOredictItem(String entry) {
		NonNullList<ItemStack> entries = OreDictionary.getOres(entry);
		if (entries.isEmpty())
			return null;
		else
			return entries.get(0);
	}
}
