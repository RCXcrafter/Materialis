package com.rcx.materialis.modules;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisConfig;
import com.rcx.materialis.MaterialisRegistry;
import com.rcx.materialis.resources.BlockBasic;
import com.rcx.materialis.resources.FluidCustom;
import com.rcx.materialis.resources.ItemBasic;
import com.rcx.materialis.traits.MaterialisTraits;
import com.rcx.materialis.traits.armor.MaterialisArmorTraits;

import c4.conarm.common.armor.traits.ArmorTraits;
import c4.conarm.lib.materials.ArmorMaterialType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fluids.FluidStack;
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
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.tools.TinkerTraits;

import java.util.*;

public class ModuleTConstruct implements IModule {

	private static final Material FAIRY = new Material("fairy", 0xFF83C3);
	private static final Item FAIRY_INGOT = new ItemBasic("fairy_ingot");
	private static final Item FAIRY_NUGGET = new ItemBasic("fairy_nugget");
	private static final Block FAIRY_BLOCK = new BlockBasic("fairy_block", net.minecraft.block.material.Material.IRON, 1.7F);
	private static final FluidMolten FAIRY_FLUID = new FluidCustom("fairy");//no not ferrofluid

	private static final Material POKEFENNIUM = new Material("pokefennium", 0x64A7B5);
	private static final Item POKEFENNIUM_INGOT = new ItemBasic("pokefennium_ingot");
	private static final Item POKEFENNIUM_NUGGET = new ItemBasic("pokefennium_nugget");
	private static final Block POKEFENNIUM_BLOCK = new BlockBasic("pokefennium_block", net.minecraft.block.material.Material.IRON, 2.2F);
	private static final FluidMolten POKEFENNIUM_FLUID = new FluidCustom("pokefennium");

	private static final Material RED_AURUM = new Material("red_aurum", 0xFFD000);
	private static final Item RED_AURUM_INGOT = new ItemBasic("red_aurum_ingot");
	private static final Item RED_AURUM_NUGGET = new ItemBasic("red_aurum_nugget");
	private static final Block RED_AURUM_BLOCK = new BlockBasic("red_aurum_block", net.minecraft.block.material.Material.IRON, 1.3F);
	private static final FluidMolten RED_AURUM_FLUID = new FluidCustom("red_aurum");

	private static final Material DRULLOY = new Material("drulloy", 0xD2915C);
	private static final Item DRULLOY_INGOT = new ItemBasic("drulloy_ingot");
	private static final Item DRULLOY_NUGGET = new ItemBasic("drulloy_nugget");
	private static final Block DRULLOY_BLOCK = new BlockBasic("drulloy_block", net.minecraft.block.material.Material.IRON, 1.4F);
	private static final FluidMolten DRULLOY_FLUID = new FluidCustom("drulloy");

	private static final Material ALUMITE = new Material("alumite", 0xFF7CE2);
	private static final Item ALUMITE_INGOT = new ItemBasic("alumite_ingot");
	private static final Item ALUMITE_NUGGET = new ItemBasic("alumite_nugget");
	private static final Block ALUMITE_BLOCK = new BlockBasic("alumite_block", net.minecraft.block.material.Material.IRON, 3.2F);
	private static final FluidMolten ALUMITE_FLUID = new FluidCustom("alumite");

	private static final Map<String, Boolean> I_REGISTERED_THE_MATERIAL = new HashMap<>();

	@Override
	public Boolean shouldLoad() {
		return !MaterialisConfig.blacklist.isModuleBlacklisted(this.getName());
	}

	@Override
	public String getName() {
		return "tconstruct";
	}

	@Override
	public void earlyPreInit(FMLPreInitializationEvent preEvent) {}

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(FAIRY.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(FAIRY.getIdentifier(), true);

			MaterialisRegistry.registerItem(FAIRY_INGOT);
			MaterialisRegistry.registerItem(FAIRY_NUGGET);
			MaterialisRegistry.registerBlock(FAIRY_BLOCK);
			FAIRY_FLUID.setTemperature(900);
			MaterialisRegistry.registerFluid(FAIRY_FLUID, FAIRY.materialTextColor);

			if ("unknown".equals(TinkerRegistry.getMaterial(FAIRY.identifier).identifier)) {
				FAIRY.addTrait(TinkerTraits.unnatural, MaterialTypes.HEAD);
				FAIRY.addTrait(TinkerTraits.holy);
				TinkerRegistry.addMaterial(FAIRY);
				TinkerRegistry.addMaterialStats(FAIRY,
						new HeadMaterialStats(843, 6.68F, 2.5F, 4),
						new HandleMaterialStats(0.5F, 188),
						new ExtraMaterialStats(200),
						new BowMaterialStats(0.9F, 1.1F, 1.0F));
				if (ModuleConarm.loadArmor()) {
					ModuleConarm.generateArmorStats(FAIRY, 2.0F);
					FAIRY.addTrait(ArmorTraits.shielding, ArmorMaterialType.CORE);
					FAIRY.addTrait(ArmorTraits.blessed, ArmorMaterialType.PLATES);
					FAIRY.addTrait(ArmorTraits.blessed, ArmorMaterialType.TRIM);
				}
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(POKEFENNIUM.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(POKEFENNIUM.getIdentifier(), true);

			MaterialisRegistry.registerItem(POKEFENNIUM_INGOT);
			MaterialisRegistry.registerItem(POKEFENNIUM_NUGGET);
			MaterialisRegistry.registerBlock(POKEFENNIUM_BLOCK);
			POKEFENNIUM_FLUID.setTemperature(850);
			MaterialisRegistry.registerFluid(POKEFENNIUM_FLUID, POKEFENNIUM.materialTextColor);

			if ("unknown".equals(TinkerRegistry.getMaterial(POKEFENNIUM.identifier).identifier)) {
				POKEFENNIUM.addTrait(MaterialisTraits.unlimited, MaterialTypes.HEAD);
				POKEFENNIUM.addTrait(MaterialisTraits.limited, MaterialTypes.HANDLE);
				POKEFENNIUM.addTrait(MaterialisTraits.limited, MaterialTypes.EXTRA);
				TinkerRegistry.addMaterial(POKEFENNIUM);
				TinkerRegistry.addMaterialStats(POKEFENNIUM,
						new HeadMaterialStats(100, 6.98F, 2.5F, 4),
						new HandleMaterialStats(3.0F, 100),
						new ExtraMaterialStats(400),
						new BowMaterialStats(0.5F, 1.3F, 5.0F));
				if (ModuleConarm.loadArmor()) {
					ModuleConarm.generateArmorStats(POKEFENNIUM, 1.0F);
					POKEFENNIUM.addTrait(MaterialisTraits.unlimited, ArmorMaterialType.CORE);
					POKEFENNIUM.addTrait(MaterialisArmorTraits.limited, ArmorMaterialType.PLATES);
					POKEFENNIUM.addTrait(MaterialisArmorTraits.limited, ArmorMaterialType.TRIM);
				}
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(RED_AURUM.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(RED_AURUM.getIdentifier(), true);

			MaterialisRegistry.registerItem(RED_AURUM_INGOT);
			MaterialisRegistry.registerItem(RED_AURUM_NUGGET);
			MaterialisRegistry.registerBlock(RED_AURUM_BLOCK);
			RED_AURUM_FLUID.setTemperature(455);
			MaterialisRegistry.registerFluid(RED_AURUM_FLUID, RED_AURUM.materialTextColor);

			if ("unknown".equals(TinkerRegistry.getMaterial(RED_AURUM.identifier).identifier)) {
				RED_AURUM.addTrait(TinkerTraits.sharp);
				TinkerRegistry.addMaterial(RED_AURUM);
				TinkerRegistry.addMaterialStats(RED_AURUM,
						new HeadMaterialStats(46, 12.2F, 3.0F, 2),
						new HandleMaterialStats(0.5F, 6),
						new ExtraMaterialStats(30),
						new BowMaterialStats(1.9F, 0.5F, 0.0F));
				if (ModuleConarm.loadArmor()) {
					ModuleConarm.generateArmorStats(RED_AURUM, 0.0F);
					RED_AURUM.addTrait(ArmorTraits.indomitable, ArmorMaterialType.CORE);
					RED_AURUM.addTrait(ArmorTraits.indomitable, ArmorMaterialType.PLATES);
					RED_AURUM.addTrait(ArmorTraits.indomitable, ArmorMaterialType.TRIM);
				}
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(DRULLOY.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(DRULLOY.getIdentifier(), true);

			MaterialisRegistry.registerItem(DRULLOY_INGOT);
			MaterialisRegistry.registerItem(DRULLOY_NUGGET);
			MaterialisRegistry.registerBlock(DRULLOY_BLOCK);
			DRULLOY_FLUID.setTemperature(643);
			MaterialisRegistry.registerFluid(DRULLOY_FLUID, DRULLOY.materialTextColor);

			if ("unknown".equals(TinkerRegistry.getMaterial(DRULLOY.identifier).identifier)) {
				DRULLOY.addTrait(TinkerTraits.heavy, MaterialTypes.HEAD);
				DRULLOY.addTrait(TinkerTraits.dense);
				TinkerRegistry.addMaterial(DRULLOY);
				TinkerRegistry.addMaterialStats(DRULLOY,
						new HeadMaterialStats(199, 8.45F, 2.9F, 2),
						new HandleMaterialStats(0.4F, 100),
						new ExtraMaterialStats(100),
						new BowMaterialStats(1.3F, 1.2F, 2.0F));
				if (ModuleConarm.loadArmor()) {
					ModuleConarm.generateArmorStats(DRULLOY, 0.5F);
					DRULLOY.addTrait(ArmorTraits.heavy, ArmorMaterialType.CORE);
					DRULLOY.addTrait(ArmorTraits.dense, ArmorMaterialType.PLATES);
					DRULLOY.addTrait(ArmorTraits.dense, ArmorMaterialType.TRIM);
				}
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(ALUMITE.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(ALUMITE.getIdentifier(), true);

			MaterialisRegistry.registerItem(ALUMITE_INGOT);
			MaterialisRegistry.registerItem(ALUMITE_NUGGET);
			MaterialisRegistry.registerBlock(ALUMITE_BLOCK);
			ALUMITE_FLUID.setTemperature(940);
			MaterialisRegistry.registerFluid(ALUMITE_FLUID, ALUMITE.materialTextColor);

			if ("unknown".equals(TinkerRegistry.getMaterial(ALUMITE.identifier).identifier)) {
				ALUMITE.addTrait(TinkerTraits.duritos);
				TinkerRegistry.addMaterial(ALUMITE);
				TinkerRegistry.addMaterialStats(ALUMITE,
						new HeadMaterialStats(700, 8.0F, 4.5F, 4),
						new HandleMaterialStats(1.3F, 80),
						new ExtraMaterialStats(100),
						new BowMaterialStats(1.1F, 1.3F, 4.0F));
				if (ModuleConarm.loadArmor()) {
					ModuleConarm.generateArmorStats(ALUMITE, 2.5F);
					ALUMITE.addTrait(ArmorTraits.duritae, ArmorMaterialType.CORE);
					ALUMITE.addTrait(ArmorTraits.duritae, ArmorMaterialType.PLATES);
					ALUMITE.addTrait(ArmorTraits.duritae, ArmorMaterialType.TRIM);
				}
			}
		}
	}

	@Override
	public void registerItems(Register<Item> event) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(FAIRY.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(FAIRY.getIdentifier(), false)) {
			OreDictionary.registerOre("ingotFairy", new ItemStack(FAIRY_INGOT));
			OreDictionary.registerOre("nuggetFairy", new ItemStack(FAIRY_NUGGET));
			OreDictionary.registerOre("blockFairy", new ItemStack(FAIRY_BLOCK));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(POKEFENNIUM.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(POKEFENNIUM.getIdentifier(), false)) {
			OreDictionary.registerOre("ingotPokefennium", new ItemStack(POKEFENNIUM_INGOT));
			OreDictionary.registerOre("nuggetPokefennium", new ItemStack(POKEFENNIUM_NUGGET));
			OreDictionary.registerOre("blockPokefennium", new ItemStack(POKEFENNIUM_BLOCK));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(RED_AURUM.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(RED_AURUM.getIdentifier(), false)) {
			OreDictionary.registerOre("ingotRedAurum", new ItemStack(RED_AURUM_INGOT));
			OreDictionary.registerOre("nuggetRedAurum", new ItemStack(RED_AURUM_NUGGET));
			OreDictionary.registerOre("blockRedAurum", new ItemStack(RED_AURUM_BLOCK));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(DRULLOY.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(DRULLOY.getIdentifier(), false)) {
			OreDictionary.registerOre("ingotDrulloy", new ItemStack(DRULLOY_INGOT));
			OreDictionary.registerOre("nuggetDrulloy", new ItemStack(DRULLOY_NUGGET));
			OreDictionary.registerOre("blockDrulloy", new ItemStack(DRULLOY_BLOCK));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(ALUMITE.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(ALUMITE.getIdentifier(), false)) {
			OreDictionary.registerOre("ingotAlumite", new ItemStack(ALUMITE_INGOT));
			OreDictionary.registerOre("nuggetAlumite", new ItemStack(ALUMITE_NUGGET));
			OreDictionary.registerOre("blockAlumite", new ItemStack(ALUMITE_BLOCK));
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(FAIRY.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(FAIRY.getIdentifier(), false)) {
			FAIRY.addCommonItems("Fairy");
			FAIRY.setRepresentativeItem(FAIRY_INGOT);
			FAIRY.setFluid(FAIRY_FLUID);
			FAIRY.setCraftable(false).setCastable(true);

			Materialis.tabItem = new ItemStack(FAIRY_INGOT);
			TinkerRegistry.registerAlloy(new FluidStack(FAIRY_FLUID, 2), new FluidStack(TinkerFluids.ardite, 1), new FluidStack(TinkerFluids.obsidian, 2), new FluidStack(TinkerFluids.blood, 1));
			new MaterialIntegration(FAIRY, FAIRY_FLUID, "Fairy").toolforge().integrate();
			MaterialisRegistry.addRecipe(new ItemStack(FAIRY_NUGGET, 9), "fairy_nugget", new Object[]{"I", 'I', "ingotFairy"});
			MaterialisRegistry.addRecipe(new ItemStack(FAIRY_INGOT), "fairy_unnugget", "fairy_ingot", "NNN", "NNN", "NNN", 'N', "nuggetFairy");
			MaterialisRegistry.addRecipe(new ItemStack(FAIRY_BLOCK), "fairy_block", new Object[]{"III", "III", "III", 'I', "ingotFairy"});
			MaterialisRegistry.addRecipe(new ItemStack(FAIRY_INGOT, 9), "fairy_unblock", "fairy_ingot", "B", 'B', "blockFairy");
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(POKEFENNIUM.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(POKEFENNIUM.getIdentifier(), false)) {
			POKEFENNIUM.addCommonItems("Pokefennium");
			POKEFENNIUM.setRepresentativeItem(POKEFENNIUM_INGOT);
			POKEFENNIUM.setFluid(POKEFENNIUM_FLUID);
			POKEFENNIUM.setCraftable(false).setCastable(true);

			TinkerRegistry.registerAlloy(new FluidStack(POKEFENNIUM_FLUID, 2), new FluidStack(TinkerFluids.cobalt, 1), new FluidStack(TinkerFluids.iron, 1), new FluidStack(TinkerFluids.blood, 2));
			new MaterialIntegration(POKEFENNIUM, POKEFENNIUM_FLUID, "Pokefennium").toolforge().integrate();
			MaterialisRegistry.addRecipe(new ItemStack(POKEFENNIUM_NUGGET, 9), "pokefennium_nugget", new Object[]{"I", 'I', "ingotPokefennium"});
			MaterialisRegistry.addRecipe(new ItemStack(POKEFENNIUM_INGOT), "pokefennium_unnugget", "pokefennium_ingot", "NNN", "NNN", "NNN", 'N', "nuggetPokefennium");
			MaterialisRegistry.addRecipe(new ItemStack(POKEFENNIUM_BLOCK), "pokefennium_block", new Object[]{"III", "III", "III", 'I', "ingotPokefennium"});
			MaterialisRegistry.addRecipe(new ItemStack(POKEFENNIUM_INGOT, 9), "pokefennium_unblock", "pokefennium_ingot", "B", 'B', "blockPokefennium");
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(RED_AURUM.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(RED_AURUM.getIdentifier(), false)) {
			RED_AURUM.addCommonItems("RedAurum");
			RED_AURUM.setRepresentativeItem(RED_AURUM_INGOT);
			RED_AURUM.setFluid(RED_AURUM_FLUID);
			RED_AURUM.setCraftable(false).setCastable(true);

			TinkerRegistry.registerAlloy(new FluidStack(RED_AURUM_FLUID, 1), new FluidStack(TinkerFluids.gold, 1), new FluidStack(TinkerFluids.blood, 3));
			new MaterialIntegration(RED_AURUM, RED_AURUM_FLUID, "RedAurum").toolforge().integrate();
			MaterialisRegistry.addRecipe(new ItemStack(RED_AURUM_NUGGET, 9), "red_aurum_nugget", new Object[]{"I", 'I', "ingotRedAurum"});
			MaterialisRegistry.addRecipe(new ItemStack(RED_AURUM_INGOT), "red_aurum_unnugget", "red_aurum_ingot", "NNN", "NNN", "NNN", 'N', "nuggetRedAurum");
			MaterialisRegistry.addRecipe(new ItemStack(RED_AURUM_BLOCK), "red_aurum_block", new Object[]{"III", "III", "III", 'I', "ingotRedAurum"});
			MaterialisRegistry.addRecipe(new ItemStack(RED_AURUM_INGOT, 9), "red_aurum_unblock", "red_aurum_ingot", "B", 'B', "blockRedAurum");
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(DRULLOY.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(DRULLOY.getIdentifier(), false)) {
			DRULLOY.addCommonItems("Drulloy");
			DRULLOY.setRepresentativeItem(DRULLOY_INGOT);
			DRULLOY.setFluid(DRULLOY_FLUID);
			DRULLOY.setCraftable(false).setCastable(true);

			TinkerRegistry.registerAlloy(new FluidStack(DRULLOY_FLUID, 2), new FluidStack(TinkerFluids.gold, 1), new FluidStack(TinkerFluids.clay, 4));
			new MaterialIntegration(DRULLOY, DRULLOY_FLUID, "Drulloy").toolforge().integrate();
			MaterialisRegistry.addRecipe(new ItemStack(DRULLOY_NUGGET, 9), "drulloy_nugget", new Object[]{"I", 'I', "ingotDrulloy"});
			MaterialisRegistry.addRecipe(new ItemStack(DRULLOY_INGOT), "drulloy_unnugget", "drulloy_ingot", "NNN", "NNN", "NNN", 'N', "nuggetDrulloy");
			MaterialisRegistry.addRecipe(new ItemStack(DRULLOY_BLOCK), "drulloy_block", new Object[]{"III", "III", "III", 'I', "ingotDrulloy"});
			MaterialisRegistry.addRecipe(new ItemStack(DRULLOY_INGOT, 9), "drulloy_unblock", "drulloy_ingot", "B", 'B', "blockDrulloy");
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(ALUMITE.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(ALUMITE.getIdentifier(), false)) {
			ALUMITE.addCommonItems("Alumite");
			ALUMITE.setRepresentativeItem(ALUMITE_INGOT);
			ALUMITE.setFluid(ALUMITE_FLUID);
			ALUMITE.setCraftable(false).setCastable(true);

			TinkerRegistry.registerAlloy(new FluidStack(ALUMITE_FLUID, 3), new FluidStack(TinkerFluids.obsidian, 2), new FluidStack(TinkerFluids.iron, 2), new FluidStack(TinkerFluids.aluminum, 5));
			new MaterialIntegration(ALUMITE, ALUMITE_FLUID, "Alumite").toolforge().integrate();
			MaterialisRegistry.addRecipe(new ItemStack(ALUMITE_NUGGET, 9), "alumite_nugget", new Object[]{"I", 'I', "ingotAlumite"});
			MaterialisRegistry.addRecipe(new ItemStack(ALUMITE_INGOT), "alumite_unnugget", "alumite_ingot", "NNN", "NNN", "NNN", 'N', "nuggetAlumite");
			MaterialisRegistry.addRecipe(new ItemStack(ALUMITE_BLOCK), "alumite_block", new Object[]{"III", "III", "III", 'I', "ingotAlumite"});
			MaterialisRegistry.addRecipe(new ItemStack(ALUMITE_INGOT, 9), "alumite_unblock", "alumite_ingot", "B", 'B', "blockAlumite");
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {}
}
