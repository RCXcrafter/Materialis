package com.rcx.materialis.modules;

import com.rcx.materialis.MaterialisConfig;
import com.rcx.materialis.MaterialisRegistry;
import com.rcx.materialis.Util;
import com.rcx.materialis.resources.FluidCustom;

import c4.conarm.common.armor.traits.ArmorTraits;
import c4.conarm.lib.materials.ArmorMaterialType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.tools.TinkerMaterials;
import slimeknights.tconstruct.tools.TinkerTraits;

import java.util.*;

public class ModuleAquaculture implements IModule {

	private static final Material NEPTUNIUM = new Material("neptunium", 0x3EF7C4);
	private static final FluidMolten NEPTUNIUM_FLUID = new FluidCustom("neptunium");

	private static final Map<String, Boolean> I_REGISTERED_THE_MATERIAL = new HashMap<>();

	@Override
	public Boolean shouldLoad() {
		return Loader.isModLoaded(this.getName()) && !MaterialisConfig.blacklist.isModuleBlacklisted(this.getName());
	}

	@Override
	public String getName() {
		return "aquaculture";
	}

	@Override
	public void earlyPreInit(FMLPreInitializationEvent preEvent) {}

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(NEPTUNIUM.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(NEPTUNIUM.getIdentifier(), true);

			NEPTUNIUM_FLUID.setTemperature(1000);
			MaterialisRegistry.registerFluid(NEPTUNIUM_FLUID, NEPTUNIUM.materialTextColor);

			NEPTUNIUM.addTrait(TinkerTraits.holy, MaterialTypes.HEAD);
			NEPTUNIUM.addTrait(TinkerTraits.aquadynamic, MaterialTypes.HEAD);
			NEPTUNIUM.addTrait(TinkerTraits.aquadynamic);
			TinkerRegistry.addMaterial(NEPTUNIUM);
			TinkerRegistry.addMaterialStats(NEPTUNIUM,
					new HeadMaterialStats(650, 9.0F, 6.0F, 3),
					new HandleMaterialStats(2.0F, 200),
					new ExtraMaterialStats(500),
					new BowMaterialStats(0.7F, 1.9F, 9.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(NEPTUNIUM, 2.0F);
				NEPTUNIUM.addTrait(ArmorTraits.blessed, ArmorMaterialType.CORE);
				NEPTUNIUM.addTrait(ArmorTraits.absorbent, ArmorMaterialType.CORE);
				NEPTUNIUM.addTrait(ArmorTraits.aquaspeed, ArmorMaterialType.PLATES);
				NEPTUNIUM.addTrait(ArmorTraits.aquaspeed, ArmorMaterialType.TRIM);
			}
		}
	}

	@Override
	public void registerItems(Register<Item> event) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(NEPTUNIUM.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(NEPTUNIUM.getIdentifier(), false)) {
			OreDictionary.registerOre("ingotNeptunium", new ItemStack(Util.getItem("aquaculture", "loot"), 1, 1));
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		TinkerMaterials.bone.addItem(new ItemStack(Util.getItem("aquaculture", "fish")), 1, Material.VALUE_Ingot);

		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(NEPTUNIUM.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(NEPTUNIUM.getIdentifier(), false)) {
			NEPTUNIUM.addCommonItems("Neptunium");
			NEPTUNIUM.setRepresentativeItem(new ItemStack(Util.getItem("aquaculture", "loot"), 1, 1));
			NEPTUNIUM.setFluid(NEPTUNIUM_FLUID);
			NEPTUNIUM.setCraftable(false).setCastable(true);
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(NEPTUNIUM.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(NEPTUNIUM.getIdentifier(), false)) {
			new MaterialIntegration(NEPTUNIUM, NEPTUNIUM_FLUID, "Neptunium").toolforge().integrate();
		}
	}
}
