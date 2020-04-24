package com.rcx.materialis.modules;

import com.rcx.materialis.MaterialisConfig;
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

public class ModuleGeneric implements IModule {

	public static Material tin = new Material("tin", 0xD8BFA6);

	public static Material zinc = new Material("zinc", 0xEDEBBD);

	public static Material brass = new Material("brass", 0xFFD177);

	public static Material nickel = new Material("nickel", 0xCDD8B3);

	public static Material aluminum = new Material("aluminum", 0xEBC4BE);

	public static Material alubrass = new Material("alubrass", 0xF0D467);

	@Override
	public Boolean shouldLoad() {
		return !MaterialisConfig.blacklist.isModuleBlacklisted(this.getName());
	}

	@Override
	public String getName() {
		return "generic";
	}

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {}

	@Override
	public void registerItems(Register<Item> event) {}

	@Override
	public void init(FMLInitializationEvent event) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("tin") && OreDictionary.doesOreNameExist("ingotTin")) {
			tin.addCommonItems("Tin");
			tin.setRepresentativeItem(getOredictItem("ingotTin"));
			tin.setFluid(TinkerFluids.tin);
			tin.setCraftable(false).setCastable(true);
			tin.addTrait(TinkerTraits.depthdigger);
			TinkerRegistry.addMaterial(tin);
			TinkerRegistry.addMaterialStats(tin,
					new HeadMaterialStats(392, 6.5F, 2.5F, 1),
					new HandleMaterialStats(1.15F, 40),
					new ExtraMaterialStats(60),
					new BowMaterialStats(0.5F, 1.6F, 5.0F));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("zinc") && OreDictionary.doesOreNameExist("ingotZinc")) {
			zinc.addCommonItems("Zinc");
			zinc.setRepresentativeItem(getOredictItem("ingotZinc"));
			zinc.setFluid(TinkerFluids.zinc);
			zinc.setCraftable(false).setCastable(true);
			zinc.addTrait(TinkerTraits.fractured);
			TinkerRegistry.addMaterial(zinc);
			TinkerRegistry.addMaterialStats(zinc,
					new HeadMaterialStats(196, 5.6F, 3.0F, 1),
					new HandleMaterialStats(0.95F, 50),
					new ExtraMaterialStats(100),
					new BowMaterialStats(0.4F, 1.3F, 4.0F));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("brass") && OreDictionary.doesOreNameExist("ingotBrass")) {
			brass.addCommonItems("Brass");
			brass.setRepresentativeItem(getOredictItem("ingotBrass"));
			brass.setFluid(TinkerFluids.brass);
			brass.setCraftable(false).setCastable(true);
			brass.addTrait(TinkerTraits.momentum);
			TinkerRegistry.addMaterial(brass);
			TinkerRegistry.addMaterialStats(brass,
					new HeadMaterialStats(400, 6.9F, 3.2F, 1),
					new HandleMaterialStats(1.0F, 40),
					new ExtraMaterialStats(120),
					new BowMaterialStats(0.55F, 1.5F, 5.0F));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("nickel") && OreDictionary.doesOreNameExist("ingotNickel")) {
			nickel.addCommonItems("Nickel");
			nickel.setRepresentativeItem(getOredictItem("ingotNickel"));
			nickel.setFluid(TinkerFluids.nickel);
			nickel.setCraftable(false).setCastable(true);
			nickel.addTrait(TinkerTraits.jagged);
			TinkerRegistry.addMaterial(nickel);
			TinkerRegistry.addMaterialStats(nickel,
					new HeadMaterialStats(305, 7.0F, 5.0F, 2),
					new HandleMaterialStats(1.0F, 100),
					new ExtraMaterialStats(70),
					new BowMaterialStats(0.5F, 1.3F, 8.0F));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("aluminum") && OreDictionary.doesOreNameExist("ingotAluminum")) {
			aluminum.addCommonItems("Aluminum");
			aluminum.setRepresentativeItem(getOredictItem("ingotAluminum"));
			aluminum.setFluid(TinkerFluids.aluminum);
			aluminum.setCraftable(false).setCastable(true);
			aluminum.addTrait(TinkerTraits.lightweight);
			TinkerRegistry.addMaterial(aluminum);
			TinkerRegistry.addMaterialStats(aluminum,
					new HeadMaterialStats(220, 5.0F, 2.5F, 1),
					new HandleMaterialStats(1.1F, 25),
					new ExtraMaterialStats(100),
					new BowMaterialStats(1.2F, 1.1F, 1.0F));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("alubrass") && OreDictionary.doesOreNameExist("ingotAlubrass")) {
			alubrass.addCommonItems("Alubrass");
			alubrass.setRepresentativeItem(getOredictItem("ingotAlubrass"));
			alubrass.setFluid(TinkerFluids.alubrass);
			alubrass.setCraftable(false).setCastable(true);
			alubrass.addTrait(TinkerTraits.coldblooded);
			TinkerRegistry.addMaterial(alubrass);
			TinkerRegistry.addMaterialStats(alubrass,
					new HeadMaterialStats(230, 5.5F, 2.5F, 1),
					new HandleMaterialStats(1.2F, 25),
					new ExtraMaterialStats(120),
					new BowMaterialStats(0.9F, 1.3F, 3.0F));
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("tin") && OreDictionary.doesOreNameExist("ingotTin")) {
			new MaterialIntegration(tin, TinkerFluids.tin, "Tin").toolforge().integrate();
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("zinc") && OreDictionary.doesOreNameExist("ingotZinc")) {
			new MaterialIntegration(zinc, TinkerFluids.zinc, "Zinc").toolforge().integrate();
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("brass") && OreDictionary.doesOreNameExist("ingotBrass")) {
			new MaterialIntegration(brass, TinkerFluids.brass, "Brass").toolforge().integrate();
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("nickel") && OreDictionary.doesOreNameExist("ingotNickel")) {
			new MaterialIntegration(nickel, TinkerFluids.nickel, "Nickel").toolforge().integrate();
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("aluminum") && OreDictionary.doesOreNameExist("ingotAluminum")) {
			new MaterialIntegration(aluminum, TinkerFluids.aluminum, "Aluminum").toolforge().integrate();
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("alubrass") && OreDictionary.doesOreNameExist("ingotAlubrass")) {
			new MaterialIntegration(alubrass, TinkerFluids.alubrass, "Alubrass").toolforge().integrate();
		}
	}

	public static ItemStack getOredictItem(String entry) {
		NonNullList<ItemStack> entries = OreDictionary.getOres(entry);
		if (entries.isEmpty())
			return null;
		else
			return entries.get(0);
	}
}
