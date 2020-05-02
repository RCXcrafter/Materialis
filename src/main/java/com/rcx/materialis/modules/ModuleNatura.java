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

public class ModuleNatura implements IModule {

	public static Material ghostwood = new Material("ghostwood", 0xBDBDBD, true);

	public static Material ghostwoodLeaf = new Material("ghostwood_leaf", 0xF1E9D4, true);

	public static Material bloodwood = new Material("bloodwood", 0xA52919, true);

	public static Material darkwood = new Material("darkwood", 0x3661A0, true);

	public static Material fusewood = new Material("fusewood", 0x416B4F, true);

	public static Material flamestring = new Material("flamestring", 0xB15E31);

	public static Material impskin = new Material("impskin", 0xBF7C18);

	@Override
	public Boolean shouldLoad() {
		return Loader.isModLoaded(this.getName()) && !MaterialisConfig.blacklist.isModuleBlacklisted(this.getName());
	}

	@Override
	public String getName() {
		return "natura";
	}

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {}

	@Override
	public void registerItems(Register<Item> event) {}

	@Override
	public void init(FMLInitializationEvent event) {
		TinkerMaterials.cactus.addItem(new ItemStack(Util.getItem("natura", "saguaro")), 1, Material.VALUE_Ingot);

		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("ghostwood")) {
			ghostwood.addItem(Util.getItem("natura", "nether_planks"), 1, Material.VALUE_Ingot);
			ghostwood.addItem(new ItemStack(Util.getItem("natura", "sticks"), 1, 9), 1, Material.VALUE_Shard);
			ghostwood.addItem(Util.getItem("natura", "nether_logs"), 1, Material.VALUE_Ingot * 4);
			ghostwood.setRepresentativeItem(Util.getItem("natura", "nether_planks"));
			ghostwood.setCraftable(true);
			ghostwood.addTrait(MaterialisTraits.intangible);
			ghostwood.setVisible();
			TinkerRegistry.addMaterialStats(ghostwood,
					new HeadMaterialStats(35, 2.0F, 2.0F, 0),
					new HandleMaterialStats(1.0F, 25),
					new ExtraMaterialStats(15),
					new BowMaterialStats(1.0F, 1.0F, 0.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(ghostwood, 0.0F);
				ghostwood.addTrait(MaterialisArmorTraits.intangible, ArmorMaterialType.CORE);
				ghostwood.addTrait(MaterialisArmorTraits.intangible, ArmorMaterialType.PLATES);
				ghostwood.addTrait(MaterialisArmorTraits.intangible, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("ghostwood_leaf")) {
			ghostwoodLeaf.addItem(Util.getItem("natura", "nether_leaves"), 1, Material.VALUE_Ingot);
			ghostwoodLeaf.setRepresentativeItem(Util.getItem("natura", "nether_leaves"));
			ghostwoodLeaf.setCraftable(true);
			ghostwoodLeaf.addTrait(MaterialisTraits.phasing);
			ghostwoodLeaf.setVisible();
			TinkerRegistry.addMaterialStats(ghostwoodLeaf, new FletchingMaterialStats(0.9F, 1.5F));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("ghostwood")) {
			bloodwood.addItem(new ItemStack(Util.getItem("natura", "nether_planks"), 1, 1), 1, Material.VALUE_Ingot);
			bloodwood.addItem(new ItemStack(Util.getItem("natura", "sticks"), 1, 12), 1, Material.VALUE_Shard);
			bloodwood.addItem(Util.getItem("natura", "nether_logs2"), 1, Material.VALUE_Ingot * 4);
			bloodwood.addItem(new ItemStack(Util.getItem("natura", "nether_logs2"), 1, 15), 1, Material.VALUE_Ingot * 4);
			bloodwood.setRepresentativeItem(new ItemStack(Util.getItem("natura", "nether_planks"), 1, 1));
			bloodwood.setCraftable(true);
			bloodwood.addTrait(MaterialisTraits.bloodthirst);
			bloodwood.setVisible();
			TinkerRegistry.addMaterialStats(bloodwood,
					new HeadMaterialStats(250, 7.0F, 5.0F, 2),
					new HandleMaterialStats(1.0F, 88),
					new ExtraMaterialStats(60),
					new BowMaterialStats(1.0F, 1.0F, 0.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(bloodwood, 0.0F);
				bloodwood.addTrait(MaterialisArmorTraits.renewableEnergy, ArmorMaterialType.CORE);
				bloodwood.addTrait(MaterialisArmorTraits.renewableEnergy, ArmorMaterialType.PLATES);
				bloodwood.addTrait(MaterialisArmorTraits.renewableEnergy, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("ghostwood")) {
			darkwood.addItem(new ItemStack(Util.getItem("natura", "nether_planks"), 1, 2), 1, Material.VALUE_Ingot);
			darkwood.addItem(new ItemStack(Util.getItem("natura", "sticks"), 1, 10), 1, Material.VALUE_Shard);
			darkwood.addItem(new ItemStack(Util.getItem("natura", "nether_logs"), 1, 1), 1, Material.VALUE_Ingot * 4);
			darkwood.setRepresentativeItem(new ItemStack(Util.getItem("natura", "nether_planks"), 1, 2));
			darkwood.setCraftable(true);
			darkwood.addTrait(MaterialisTraits.darkness);
			darkwood.setVisible();
			TinkerRegistry.addMaterialStats(darkwood,
					new HeadMaterialStats(120, 4.0F, 3.0F, 1),
					new HandleMaterialStats(1.0F, 36),
					new ExtraMaterialStats(20),
					new BowMaterialStats(1.0F, 1.0F, 0.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(darkwood, 0.0F);
				darkwood.addTrait(MaterialisArmorTraits.darkness, ArmorMaterialType.CORE);
				darkwood.addTrait(MaterialisArmorTraits.darkness, ArmorMaterialType.PLATES);
				darkwood.addTrait(MaterialisArmorTraits.darkness, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("ghostwood")) {
			fusewood.addItem(new ItemStack(Util.getItem("natura", "nether_planks"), 1, 3), 1, Material.VALUE_Ingot);
			fusewood.addItem(new ItemStack(Util.getItem("natura", "sticks"), 1, 11), 1, Material.VALUE_Shard);
			fusewood.addItem(new ItemStack(Util.getItem("natura", "nether_logs"), 1, 2), 1, Material.VALUE_Ingot * 4);
			fusewood.setRepresentativeItem(new ItemStack(Util.getItem("natura", "nether_planks"), 1, 3));
			fusewood.setCraftable(true);
			fusewood.addTrait(MaterialisTraits.shortFuse);
			fusewood.setVisible();
			TinkerRegistry.addMaterialStats(fusewood,
					new HeadMaterialStats(204, 6.0F, 4.0F, 2),
					new HandleMaterialStats(1.0F, 60),
					new ExtraMaterialStats(30),
					new BowMaterialStats(1.0F, 1.0F, 0.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(fusewood, 0.0F);
				fusewood.addTrait(MaterialisArmorTraits.shortFuse, ArmorMaterialType.CORE);
				fusewood.addTrait(MaterialisArmorTraits.shortFuse, ArmorMaterialType.PLATES);
				fusewood.addTrait(MaterialisArmorTraits.shortFuse, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("flamestring")) {
			flamestring.addItem(new ItemStack(Util.getItem("natura", "materials"), 1, 7), 1, Material.VALUE_Ingot);
			flamestring.setRepresentativeItem(new ItemStack(Util.getItem("natura", "materials"), 1, 7));
			flamestring.setCraftable(true);
			TinkerRegistry.addMaterial(flamestring);
			TinkerRegistry.addMaterialStats(flamestring, new BowStringMaterialStats(1.2F));
		}

		if (ModuleConarm.loadArmor()) {
			if (!MaterialisConfig.blacklist.isMaterialBlacklisted("impskin")) {
				impskin.addItem(new ItemStack(Util.getItem("natura", "materials"), 1, 6), 1, Material.VALUE_Ingot);
				impskin.setRepresentativeItem(new ItemStack(Util.getItem("natura", "materials"), 1, 6));
				impskin.setCraftable(true);
				impskin.addTrait(MaterialisArmorTraits.fireproof);
				TinkerRegistry.addMaterial(impskin);
				TinkerRegistry.addMaterialStats(impskin,
						new CoreMaterialStats(20, 8.0F),
						new PlatesMaterialStats(1.0F, 3, 0.0F),
						new TrimMaterialStats(15));
			}
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {}
}
