package com.rcx.materialis.modules;

import com.rcx.materialis.MaterialisConfig;
import com.rcx.materialis.Util;
import com.rcx.materialis.modifiers.ModInfinity;
import com.rcx.materialis.traits.MaterialisTraits;
import com.rcx.materialis.traits.TraitCosmic;
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
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.Modifier;

import java.util.*;

public class ModuleAvaritia implements IModule {

	private static final Material CRYSTAL_MATRIX = new Material("crystal_matrix", 0x97F1EB);

	private static final Material NEUTRONIUM = new Material("cosmic_neutronium", 0x6B6B6B);

	private static final Material INFINITY = new Material("infinity", 0xFF5555);

	private static Modifier modInfinity;

	private static final Map<String, Boolean> I_REGISTERED_THE_MATERIAL = new HashMap<>();

	@Override
	public Boolean shouldLoad() {
		return Loader.isModLoaded(this.getName()) && !MaterialisConfig.blacklist.isModuleBlacklisted(this.getName());
	}

	@Override
	public String getName() {
		return "avaritia";
	}

	@Override
	public void earlyPreInit(FMLPreInitializationEvent preEvent) {}

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {
		MaterialisTraits.cosmic = new TraitCosmic();
		TinkerRegistry.addTrait(MaterialisTraits.cosmic);

		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(CRYSTAL_MATRIX.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(CRYSTAL_MATRIX.getIdentifier(), true);

			CRYSTAL_MATRIX.addTrait(MaterialisTraits.crystalline);
			TinkerRegistry.addMaterial(CRYSTAL_MATRIX);
			TinkerRegistry.addMaterialStats(CRYSTAL_MATRIX,
					new ExtraMaterialStats(600));
			if (ModuleConarm.loadArmor()) {
				TinkerRegistry.addMaterialStats(CRYSTAL_MATRIX,
						new TrimMaterialStats(40));
				CRYSTAL_MATRIX.addTrait(MaterialisArmorTraits.crystalline, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(NEUTRONIUM.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(NEUTRONIUM.getIdentifier(), true);

			NEUTRONIUM.addTrait(MaterialisTraits.supermassive);
			TinkerRegistry.addMaterial(NEUTRONIUM);
			TinkerRegistry.addMaterialStats(NEUTRONIUM,
					new HeadMaterialStats(765, 10.98F, 1.0F, 4),
					new HandleMaterialStats(2.5F, 100),
					new ExtraMaterialStats(400),
					new BowMaterialStats(0.5F, 1.3F, 5.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(NEUTRONIUM, 3.0F);
				NEUTRONIUM.addTrait(MaterialisArmorTraits.supermassive, ArmorMaterialType.CORE);
				NEUTRONIUM.addTrait(MaterialisArmorTraits.supermassive, ArmorMaterialType.PLATES);
				NEUTRONIUM.addTrait(MaterialisArmorTraits.supermassive, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(INFINITY.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(INFINITY.getIdentifier(), true);

			INFINITY.addTrait(MaterialisTraits.cosmic);
			INFINITY.addTrait(MaterialisTraits.unbreakable);
			TinkerRegistry.addMaterial(INFINITY);
			TinkerRegistry.addMaterialStats(INFINITY,
					new HeadMaterialStats(9999, 99999.99F, 99.9F, 99),
					new HandleMaterialStats(10.0F, 999),
					new ExtraMaterialStats(999),
					new BowMaterialStats(9999.9F, 999.91F, 99.9F));
			if (ModuleConarm.loadArmor()) {
				TinkerRegistry.addMaterialStats(INFINITY,
						new CoreMaterialStats(9999, 999.9F),
						new PlatesMaterialStats(10.0F, 999, 99.9F),
						new TrimMaterialStats(999));
				INFINITY.addTrait(MaterialisArmorTraits.cosmic, ArmorMaterialType.CORE);
				INFINITY.addTrait(MaterialisArmorTraits.cosmic, ArmorMaterialType.PLATES);
				INFINITY.addTrait(MaterialisArmorTraits.cosmic, ArmorMaterialType.TRIM);
				INFINITY.addTrait(MaterialisArmorTraits.unbreakable, ArmorMaterialType.CORE);
				INFINITY.addTrait(MaterialisArmorTraits.unbreakable, ArmorMaterialType.PLATES);
				INFINITY.addTrait(MaterialisArmorTraits.unbreakable, ArmorMaterialType.TRIM);
			}
		}
		modInfinity = new ModInfinity();
	}

	@Override
	public void registerItems(Register<Item> event) {}

	@Override
	public void init(FMLInitializationEvent event) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(CRYSTAL_MATRIX.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(CRYSTAL_MATRIX.getIdentifier(), false)) {
			CRYSTAL_MATRIX.addCommonItems("CrystalMatrix");
			CRYSTAL_MATRIX.setRepresentativeItem(new ItemStack(Util.getItem("avaritia", "resource"), 1, 1));
			CRYSTAL_MATRIX.setCraftable(true);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(NEUTRONIUM.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(NEUTRONIUM.getIdentifier(), false)) {
			NEUTRONIUM.addCommonItems("CosmicNeutronium");
			NEUTRONIUM.setRepresentativeItem(new ItemStack(Util.getItem("avaritia", "resource"), 1, 4));
			NEUTRONIUM.setCraftable(true);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(INFINITY.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(INFINITY.getIdentifier(), false)) {
			INFINITY.addItem("ingotInfinity", 1, Material.VALUE_Nugget * 2);
			INFINITY.addItem("blockInfinity", 1, Material.VALUE_Ingot * 2);
			INFINITY.setRepresentativeItem(new ItemStack(Util.getItem("avaritia", "resource"), 1, 6));
			INFINITY.setCraftable(true);
		}
		modInfinity.addItem(new ItemStack(Util.getItem("avaritia", "resource"), 1, 5), 1, 1);
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {}
}
