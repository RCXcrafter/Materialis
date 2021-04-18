package com.rcx.materialis.modules;

import com.rcx.materialis.MaterialisConfig;
import com.rcx.materialis.traits.armor.MaterialisArmorTraits;

import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;

public class ModuleConarm implements IModule {

	//cache this because it's needed often
	public static boolean shouldLoad;
	public static boolean isCached = false;

	@Override
	public Boolean shouldLoad() {
		return loadArmor();
	}

	//static version of shouldLoad, kinda messy
	public static Boolean loadArmor() {
		if (!isCached) {
			shouldLoad = Loader.isModLoaded("conarm") && !MaterialisConfig.blacklist.isModuleBlacklisted("conarm");
			isCached = true;
		}
		return shouldLoad;
	}

	@Override
	public String getName() {
		return "conarm";
	}

	@Override
	public void earlyPreInit(FMLPreInitializationEvent preEvent) {
		MaterialisArmorTraits.preInit();
	}

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {}

	@Override
	public void registerItems(Register<Item> event) {}

	@Override
	public void init(FMLInitializationEvent event) {}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {}

	public static void generateArmorStats(Material material, float toughness) {
		HeadMaterialStats head = material.getStats(MaterialTypes.HEAD);
		HandleMaterialStats handle = material.getStats(MaterialTypes.HANDLE);
		ExtraMaterialStats extra = material.getStats(MaterialTypes.EXTRA);

		if (head != null)
			TinkerRegistry.addMaterialStats(material, new CoreMaterialStats(head.durability / 30, head.attack * 2.2F));
		if (handle != null)
			TinkerRegistry.addMaterialStats(material, new PlatesMaterialStats(handle.modifier, handle.durability / 17, toughness));
		if (extra != null)
			TinkerRegistry.addMaterialStats(material, new TrimMaterialStats(extra.extraDurability / 16));
	}
}
