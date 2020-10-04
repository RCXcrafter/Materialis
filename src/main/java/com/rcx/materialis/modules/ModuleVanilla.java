package com.rcx.materialis.modules;

import com.rcx.materialis.MaterialisConfig;
import com.rcx.materialis.traits.MaterialisTraits;

import c4.conarm.common.armor.traits.ArmorTraits;
import c4.conarm.lib.materials.ArmorMaterialType;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.tools.TinkerTraits;

import java.util.*;

public class ModuleVanilla implements IModule {

	private static final Material GOLD = new Material("gold", 0xFFE30B);

	private static final Material QUARTZ = new Material("quartz", 0xD2C7BA);

	private static final Material DIAMOND = new Material("diamond", 0x33EBCB);

	private static final Material EMERALD = new Material("emerald", 0x41F384);

	private static final Material LEATHER = new Material("leather", 0xC65C35);

	private static final Map<String, Boolean> I_REGISTERED_THE_MATERIAL = new HashMap<>();

	@Override
	public Boolean shouldLoad() {
		return !MaterialisConfig.blacklist.isModuleBlacklisted(this.getName());
	}

	@Override
	public String getName() {
		return "vanilla";
	}

	@Override
	public void earlyPreInit(FMLPreInitializationEvent preEvent) {}

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(GOLD.getIdentifier())
				&& "unknown".equals(TinkerRegistry.getMaterial(GOLD.getIdentifier()).getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(GOLD.getIdentifier(), true);

			GOLD.addTrait(MaterialisTraits.fancy);
			TinkerRegistry.addMaterial(GOLD);
			TinkerRegistry.addMaterialStats(GOLD,
					new HeadMaterialStats(22, 13.0F, 1.0F, 1),
					new HandleMaterialStats(0.7F, 0),
					new ExtraMaterialStats(50),
					new BowMaterialStats(0.9F, 1.3F, 6.0F));
			if (ModuleConarm.loadArmor())
				ModuleConarm.generateArmorStats(GOLD, 0.0F);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(QUARTZ.getIdentifier())
				&& "unknown".equals(TinkerRegistry.getMaterial(QUARTZ.getIdentifier()).getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(QUARTZ.getIdentifier(), true);

			QUARTZ.addTrait(TinkerTraits.sharp);
			TinkerRegistry.addMaterial(QUARTZ);
			TinkerRegistry.addMaterialStats(QUARTZ,
					new HeadMaterialStats(150, 5.6F, 4.0F, 1),
					new HandleMaterialStats(0.6F, -10),
					new ExtraMaterialStats(25),
					new BowMaterialStats(0.2F, 0.4F, -1.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(QUARTZ, 0.0F);
				QUARTZ.addTrait(ArmorTraits.rough, ArmorMaterialType.CORE);
				QUARTZ.addTrait(ArmorTraits.rough, ArmorMaterialType.PLATES);
				QUARTZ.addTrait(ArmorTraits.rough, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(DIAMOND.getIdentifier())
				&& "unknown".equals(TinkerRegistry.getMaterial(DIAMOND.getIdentifier()).getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(DIAMOND.getIdentifier(), true);

			DIAMOND.addTrait(TinkerTraits.crumbling, MaterialTypes.HEAD);
			DIAMOND.addTrait(TinkerTraits.crude);
			TinkerRegistry.addMaterial(DIAMOND);
			TinkerRegistry.addMaterialStats(DIAMOND,
					new HeadMaterialStats(848, 8.0F, 5.0F, 3),
					new HandleMaterialStats(1.0F, 50),
					new ExtraMaterialStats(400),
					new BowMaterialStats(0.2F, 0.4F, -1.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(DIAMOND, 2.0F);
				DIAMOND.addTrait(ArmorTraits.subterranean, ArmorMaterialType.CORE);
				DIAMOND.addTrait(ArmorTraits.subterranean, ArmorMaterialType.PLATES);
				DIAMOND.addTrait(ArmorTraits.subterranean, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(EMERALD.getIdentifier())
				&& "unknown".equals(TinkerRegistry.getMaterial(EMERALD.getIdentifier()).getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(EMERALD.getIdentifier(), true);

			EMERALD.addTrait(TinkerTraits.established);
			TinkerRegistry.addMaterial(EMERALD);
			TinkerRegistry.addMaterialStats(EMERALD,
					new HeadMaterialStats(200, 6.66F, 4.0F, 1),
					new HandleMaterialStats(0.5F, 100),
					new ExtraMaterialStats(150),
					new BowMaterialStats(0.2F, 0.4F, -1.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(EMERALD, 1.0F);
				EMERALD.addTrait(ArmorTraits.ambitious, ArmorMaterialType.CORE);
				EMERALD.addTrait(ArmorTraits.ambitious, ArmorMaterialType.PLATES);
				EMERALD.addTrait(ArmorTraits.ambitious, ArmorMaterialType.TRIM);
			}
		}
		if (ModuleConarm.loadArmor()
				&& !MaterialisConfig.blacklist.isMaterialBlacklisted(LEATHER.getIdentifier())
				&& "unknown".equals(TinkerRegistry.getMaterial(LEATHER.getIdentifier()).getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(LEATHER.getIdentifier(), true);

			LEATHER.addTrait(ArmorTraits.ecological);
			TinkerRegistry.addMaterial(LEATHER);
			TinkerRegistry.addMaterialStats(LEATHER,
					new CoreMaterialStats(15, 8.0F),
					new PlatesMaterialStats(1.0F, 3, 0.0F),
					new TrimMaterialStats(5));
		}
	}

	@Override
	public void registerItems(Register<Item> event) {}

	@Override
	public void init(FMLInitializationEvent event) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(GOLD.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(GOLD.getIdentifier(), false)) {
			GOLD.addCommonItems("Gold");
			GOLD.setRepresentativeItem(Items.GOLD_INGOT);
			GOLD.setFluid(TinkerFluids.gold);
			GOLD.setCraftable(false).setCastable(true);

			new MaterialIntegration(GOLD, TinkerFluids.gold, "Gold").toolforge().integrate();
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(QUARTZ.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(QUARTZ.getIdentifier(), false)) {
			QUARTZ.addItem("gemQuartz", 1, Material.VALUE_Ingot);
			QUARTZ.addItem("nuggetQuartz", 1, Material.VALUE_Nugget);
			QUARTZ.addItem("blockQuartz", 1, Material.VALUE_Ingot * 4);
			QUARTZ.setRepresentativeItem(Items.QUARTZ);
			QUARTZ.setCraftable(true);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(DIAMOND.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(DIAMOND.getIdentifier(), false)) {
			DIAMOND.addItem("gemDiamond", 1, Material.VALUE_Ingot);
			DIAMOND.addItem("nuggetDiamond", 1, Material.VALUE_Nugget);
			DIAMOND.addItem("blockDiamond", 1, Material.VALUE_Block);
			DIAMOND.setRepresentativeItem(Items.DIAMOND);
			DIAMOND.setCraftable(true);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(EMERALD.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(EMERALD.getIdentifier(), false)) {
			EMERALD.addItem("gemEmerald", 1, Material.VALUE_Ingot);
			EMERALD.addItem("nuggetEmerald", 1, Material.VALUE_Nugget);
			EMERALD.addItem("blockEmerald", 1, Material.VALUE_Block);
			EMERALD.setRepresentativeItem(Items.EMERALD);
			EMERALD.setCraftable(true);
		}
		if (ModuleConarm.loadArmor()
				&& !MaterialisConfig.blacklist.isMaterialBlacklisted(LEATHER.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(LEATHER.getIdentifier(), false)) {
			LEATHER.addItem("leather", 1, Material.VALUE_Ingot);
			LEATHER.setRepresentativeItem(new ItemStack(Items.LEATHER));
			LEATHER.setCraftable(true);
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {}
}
