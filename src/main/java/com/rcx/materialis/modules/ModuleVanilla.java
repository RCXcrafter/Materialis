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

public class ModuleVanilla implements IModule {

	public static Material gold = new Material("gold", 0xFFE30B);

	public static Material quartz = new Material("quartz", 0xD2C7BA);

	public static Material diamond = new Material("diamond", 0x33EBCB);

	public static Material emerald = new Material("emerald", 0x41F384);

	public static Material leather = new Material("leather", 0xC65C35);

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
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("gold")) {
			gold.addTrait(MaterialisTraits.fancy);
			TinkerRegistry.addMaterial(gold);
			TinkerRegistry.addMaterialStats(gold,
					new HeadMaterialStats(22, 13.0F, 1.0F, 1),
					new HandleMaterialStats(0.7F, 0),
					new ExtraMaterialStats(50),
					new BowMaterialStats(0.9F, 1.3F, 6.0F));
			if (ModuleConarm.loadArmor())
				ModuleConarm.generateArmorStats(gold, 0.0F);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("quartz")) {
			quartz.addTrait(TinkerTraits.sharp);
			TinkerRegistry.addMaterial(quartz);
			TinkerRegistry.addMaterialStats(quartz,
					new HeadMaterialStats(150, 5.6F, 4.0F, 1),
					new HandleMaterialStats(0.6F, -10),
					new ExtraMaterialStats(25),
					new BowMaterialStats(0.2F, 0.4F, -1.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(quartz, 0.0F);
				quartz.addTrait(ArmorTraits.rough, ArmorMaterialType.CORE);
				quartz.addTrait(ArmorTraits.rough, ArmorMaterialType.PLATES);
				quartz.addTrait(ArmorTraits.rough, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("diamond")) {
			diamond.addTrait(TinkerTraits.crumbling, MaterialTypes.HEAD);
			diamond.addTrait(TinkerTraits.crude);
			TinkerRegistry.addMaterial(diamond);
			TinkerRegistry.addMaterialStats(diamond,
					new HeadMaterialStats(848, 8.0F, 5.0F, 3),
					new HandleMaterialStats(1.0F, 50),
					new ExtraMaterialStats(400),
					new BowMaterialStats(0.2F, 0.4F, -1.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(diamond, 2.0F);
				diamond.addTrait(ArmorTraits.subterranean, ArmorMaterialType.CORE);
				diamond.addTrait(ArmorTraits.subterranean, ArmorMaterialType.PLATES);
				diamond.addTrait(ArmorTraits.subterranean, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("emerald")) {
			emerald.addTrait(TinkerTraits.established);
			TinkerRegistry.addMaterial(emerald);
			TinkerRegistry.addMaterialStats(emerald,
					new HeadMaterialStats(200, 6.66F, 4.0F, 1),
					new HandleMaterialStats(0.5F, 100),
					new ExtraMaterialStats(150),
					new BowMaterialStats(0.2F, 0.4F, -1.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(emerald, 1.0F);
				emerald.addTrait(ArmorTraits.ambitious, ArmorMaterialType.CORE);
				emerald.addTrait(ArmorTraits.ambitious, ArmorMaterialType.PLATES);
				emerald.addTrait(ArmorTraits.ambitious, ArmorMaterialType.TRIM);
			}
		}
		if (ModuleConarm.loadArmor() && !MaterialisConfig.blacklist.isMaterialBlacklisted("leather")) {
			leather.addTrait(ArmorTraits.ecological);
			TinkerRegistry.addMaterial(leather);
			TinkerRegistry.addMaterialStats(leather,
					new CoreMaterialStats(15, 8.0F),
					new PlatesMaterialStats(1.0F, 3, 0.0F),
					new TrimMaterialStats(5));
		}
	}

	@Override
	public void registerItems(Register<Item> event) {}

	@Override
	public void init(FMLInitializationEvent event) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("gold")) {
			gold.addCommonItems("Gold");
			gold.setRepresentativeItem(Items.GOLD_INGOT);
			gold.setFluid(TinkerFluids.gold);
			gold.setCraftable(false).setCastable(true);

			new MaterialIntegration(gold, TinkerFluids.gold, "Gold").toolforge().integrate();
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("quartz")) {
			quartz.addItem("gemQuartz", 1, Material.VALUE_Ingot);
			quartz.addItem("nuggetQuartz", 1, Material.VALUE_Nugget);
			quartz.addItem("blockQuartz", 1, Material.VALUE_Ingot * 4);
			quartz.setRepresentativeItem(Items.QUARTZ);
			quartz.setCraftable(true);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("diamond")) {
			diamond.addItem("gemDiamond", 1, Material.VALUE_Ingot);
			diamond.addItem("nuggetDiamond", 1, Material.VALUE_Nugget);
			diamond.addItem("blockDiamond", 1, Material.VALUE_Block);
			diamond.setRepresentativeItem(Items.DIAMOND);
			diamond.setCraftable(true);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("emerald")) {
			emerald.addItem("gemEmerald", 1, Material.VALUE_Ingot);
			emerald.addItem("nuggetEmerald", 1, Material.VALUE_Nugget);
			emerald.addItem("blockEmerald", 1, Material.VALUE_Block);
			emerald.setRepresentativeItem(Items.EMERALD);
			emerald.setCraftable(true);
		}
		if (ModuleConarm.loadArmor() && !MaterialisConfig.blacklist.isMaterialBlacklisted("leather")) {
			leather.addItem("leather", 1, Material.VALUE_Ingot);
			leather.setRepresentativeItem(new ItemStack(Items.LEATHER));
			leather.setCraftable(true);
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {}
}
