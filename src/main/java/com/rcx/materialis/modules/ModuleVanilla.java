package com.rcx.materialis.modules;

import com.rcx.materialis.MaterialisConfig;
import com.rcx.materialis.traits.MaterialisTraits;

import c4.conarm.common.armor.traits.ArmorTraits;
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
import slimeknights.tconstruct.shared.TinkerFluids;

public class ModuleVanilla implements IModule {

	public static Material gold = new Material("gold", 0xFFE30B);

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
	public void preInit(FMLPreInitializationEvent preEvent) {}

	@Override
	public void registerItems(Register<Item> event) {}

	@Override
	public void init(FMLInitializationEvent event) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("gold")) {
			gold.addCommonItems("Gold");
			gold.setRepresentativeItem(Items.GOLD_INGOT);
			gold.setFluid(TinkerFluids.gold);
			gold.setCraftable(false).setCastable(true);
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
		if (ModuleConarm.loadArmor() && !MaterialisConfig.blacklist.isMaterialBlacklisted("leather")) {
			leather.addItem("leather", 1, Material.VALUE_Ingot);
			leather.setRepresentativeItem(new ItemStack(Items.LEATHER));
			leather.setCraftable(true);
			leather.addTrait(ArmorTraits.ecological);
			TinkerRegistry.addMaterial(leather);
			TinkerRegistry.addMaterialStats(leather,
					new CoreMaterialStats(15, 8.0F),
					new PlatesMaterialStats(1.0F, 3, 0.0F),
					new TrimMaterialStats(5));
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("gold")) {
			new MaterialIntegration(gold, TinkerFluids.gold, "Gold").toolforge().integrate();
		}
	}
}
