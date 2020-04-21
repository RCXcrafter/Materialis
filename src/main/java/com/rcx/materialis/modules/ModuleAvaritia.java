package com.rcx.materialis.modules;

import com.rcx.materialis.MaterialisConfig;
import com.rcx.materialis.modifiers.ModInfinity;
import com.rcx.materialis.traits.MaterialisTraits;

import morph.avaritia.init.ModItems;
import net.minecraft.item.Item;
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

public class ModuleAvaritia implements IModule {

	public static Material crystalMatrix = new Material("crystal_matrix", 0x97F1EB);

	public static Material neutronium = new Material("cosmic_neutronium", 0x6B6B6B);

	public static Material infinity = new Material("infinity", 0xFF5555);

	public static Modifier modInfinity = new ModInfinity();

	@Override
	public Boolean shouldLoad() {
		return Loader.isModLoaded(this.getName()) && !MaterialisConfig.blacklist.isModuleBlacklisted(this.getName());
	}

	@Override
	public String getName() {
		return "avaritia";
	}

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {}

	@Override
	public void registerItems(Register<Item> event) {}

	@Override
	public void init(FMLInitializationEvent event) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("crystal_matrix")) {
			crystalMatrix.addItem("ingotCrystalMatrix", 1, Material.VALUE_Ingot);
			crystalMatrix.addItem("blockCrystalMatrix", 1, Material.VALUE_Block);
			crystalMatrix.setRepresentativeItem(ModItems.crystal_matrix_ingot);
			crystalMatrix.setCraftable(true);
			crystalMatrix.addTrait(MaterialisTraits.crystalline);
			TinkerRegistry.addMaterial(crystalMatrix);
			TinkerRegistry.addMaterialStats(crystalMatrix,
					new ExtraMaterialStats(600));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("cosmic_neutronium")) {
			neutronium.addItem("ingotCosmicNeutronium", 1, Material.VALUE_Ingot);
			neutronium.addItem("nuggetCosmicNeutronium", 1, Material.VALUE_Nugget);
			neutronium.addItem("blockCosmicNeutronium", 1, Material.VALUE_Block);
			neutronium.setRepresentativeItem(ModItems.neutronium_ingot);
			neutronium.setCraftable(true);
			neutronium.addTrait(MaterialisTraits.supermassive);
			TinkerRegistry.addMaterial(neutronium);
			TinkerRegistry.addMaterialStats(neutronium,
					new HeadMaterialStats(765, 10.98F, 1.0F, 4),
					new HandleMaterialStats(2.5F, 100),
					new ExtraMaterialStats(400),
					new BowMaterialStats(0.5F, 1.3F, 5.0F));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("infinity")) {
			infinity.addItem("ingotInfinity", 1, Material.VALUE_Nugget * 2);
			infinity.addItem("blockInfinity", 1, Material.VALUE_Ingot * 2);
			infinity.setRepresentativeItem(ModItems.infinity_ingot);
			infinity.setCraftable(true);
			infinity.addTrait(MaterialisTraits.cosmic);
			infinity.addTrait(MaterialisTraits.unbreakable);
			TinkerRegistry.addMaterial(infinity);
			TinkerRegistry.addMaterialStats(infinity,
					new HeadMaterialStats(9999, 99999.99F, 99.9F, 99),
					new HandleMaterialStats(10.0F, 999),
					new ExtraMaterialStats(999),
					new BowMaterialStats(9999.9F, 999.91F, 99.9F));
		}
		modInfinity.addItem(ModItems.infinity_catalyst, 1, 1);
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {}
}
