package com.rcx.materialis.modules;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisConfig;
import com.rcx.materialis.MaterialisRegistry;
import com.rcx.materialis.resources.BlockBasic;
import com.rcx.materialis.resources.FluidCustom;
import com.rcx.materialis.resources.ItemBasic;
import com.rcx.materialis.traits.MaterialisTraits;

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

public class ModuleTConstruct implements IModule {

	public static Material fairy = new Material("fairy", 0xFF83C3);
	public static Item fairyIngot = new ItemBasic("fairy_ingot");
	public static Item fairyNugget = new ItemBasic("fairy_nugget");
	public static Block fairyBlock = new BlockBasic("fairy_block", net.minecraft.block.material.Material.IRON, 1.7F);
	public static FluidMolten fairyFluid = new FluidCustom("fairy");//no not ferrofluid

	public static Material pokefennium = new Material("pokefennium", 0x64A7B5);
	public static Item pokefenniumIngot = new ItemBasic("pokefennium_ingot");
	public static Item pokefenniumNugget = new ItemBasic("pokefennium_nugget");
	public static Block pokefenniumBlock = new BlockBasic("pokefennium_block", net.minecraft.block.material.Material.IRON, 2.2F);
	public static FluidMolten pokefenniumFluid = new FluidCustom("pokefennium");

	public static Material redAurum = new Material("red_aurum", 0xFFD000);
	public static Item redAurumIngot = new ItemBasic("red_aurum_ingot");
	public static Item redAurumNugget = new ItemBasic("red_aurum_nugget");
	public static Block redAurumBlock = new BlockBasic("red_aurum_block", net.minecraft.block.material.Material.IRON, 1.3F);
	public static FluidMolten redAurumFluid = new FluidCustom("red_aurum");

	public static Material drulloy = new Material("drulloy", 0xD2915C);
	public static Item drulloyIngot = new ItemBasic("drulloy_ingot");
	public static Item drulloyNugget = new ItemBasic("drulloy_nugget");
	public static Block drulloyBlock = new BlockBasic("drulloy_block", net.minecraft.block.material.Material.IRON, 1.4F);
	public static FluidMolten drulloyFluid = new FluidCustom("drulloy");

	public static Material alumite = new Material("alumite", 0xFF7CE2);
	public static Item alumiteIngot = new ItemBasic("alumite_ingot");
	public static Item alumiteNugget = new ItemBasic("alumite_nugget");
	public static Block alumiteBlock = new BlockBasic("alumite_block", net.minecraft.block.material.Material.IRON, 3.2F);
	public static FluidMolten alumiteFluid = new FluidCustom("alumite");

	@Override
	public Boolean shouldLoad() {
		return !MaterialisConfig.blacklist.isModuleBlacklisted(this.getName());
	}

	@Override
	public String getName() {
		return "tconstruct";
	}

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("fairy")) {
			MaterialisRegistry.registerItem(fairyIngot);
			MaterialisRegistry.registerItem(fairyNugget);
			MaterialisRegistry.registerBlock(fairyBlock);
			fairyFluid.setTemperature(900);
			MaterialisRegistry.registerFluid(fairyFluid, fairy.materialTextColor);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("pokefennium")) {
			MaterialisRegistry.registerItem(pokefenniumIngot);
			MaterialisRegistry.registerItem(pokefenniumNugget);
			MaterialisRegistry.registerBlock(pokefenniumBlock);
			pokefenniumFluid.setTemperature(850);
			MaterialisRegistry.registerFluid(pokefenniumFluid, pokefennium.materialTextColor);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("red_aurum")) {
			MaterialisRegistry.registerItem(redAurumIngot);
			MaterialisRegistry.registerItem(redAurumNugget);
			MaterialisRegistry.registerBlock(redAurumBlock);
			redAurumFluid.setTemperature(455);
			MaterialisRegistry.registerFluid(redAurumFluid, redAurum.materialTextColor);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("drulloy")) {
			MaterialisRegistry.registerItem(drulloyIngot);
			MaterialisRegistry.registerItem(drulloyNugget);
			MaterialisRegistry.registerBlock(drulloyBlock);
			drulloyFluid.setTemperature(643);
			MaterialisRegistry.registerFluid(drulloyFluid, drulloy.materialTextColor);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("alumite")) {
			MaterialisRegistry.registerItem(alumiteIngot);
			MaterialisRegistry.registerItem(alumiteNugget);
			MaterialisRegistry.registerBlock(alumiteBlock);
			alumiteFluid.setTemperature(940);
			MaterialisRegistry.registerFluid(alumiteFluid, alumite.materialTextColor);
		}
	}

	@Override
	public void registerItems(Register<Item> event) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("fairy")) {
			OreDictionary.registerOre("ingotFairy", new ItemStack(fairyIngot));
			OreDictionary.registerOre("nuggetFairy", new ItemStack(fairyNugget));
			OreDictionary.registerOre("blockFairy", new ItemStack(fairyBlock));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("pokefennium")) {
			OreDictionary.registerOre("ingotPokefennium", new ItemStack(pokefenniumIngot));
			OreDictionary.registerOre("nuggetPokefennium", new ItemStack(pokefenniumNugget));
			OreDictionary.registerOre("blockPokefennium", new ItemStack(pokefenniumBlock));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("red_aurum")) {
			OreDictionary.registerOre("ingotRedAurum", new ItemStack(redAurumIngot));
			OreDictionary.registerOre("nuggetRedAurum", new ItemStack(redAurumNugget));
			OreDictionary.registerOre("blockRedAurum", new ItemStack(redAurumBlock));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("drulloy")) {
			OreDictionary.registerOre("ingotDrulloy", new ItemStack(drulloyIngot));
			OreDictionary.registerOre("nuggetDrulloy", new ItemStack(drulloyNugget));
			OreDictionary.registerOre("blockDrulloy", new ItemStack(drulloyBlock));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("alumite")) {
			OreDictionary.registerOre("ingotAlumite", new ItemStack(alumiteIngot));
			OreDictionary.registerOre("nuggetAlumite", new ItemStack(alumiteNugget));
			OreDictionary.registerOre("blockAlumite", new ItemStack(alumiteBlock));
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("fairy")) {
			fairy.addItem("ingotFairy", 1, Material.VALUE_Ingot);
			fairy.addItem("nuggetFairy", 1, Material.VALUE_Nugget);
			fairy.addItem("blockFairy", 1, Material.VALUE_Block);
			fairy.setRepresentativeItem(fairyIngot);
			fairy.setFluid(fairyFluid);
			fairy.setCraftable(false).setCastable(true);
			fairy.addTrait(TinkerTraits.unnatural, MaterialTypes.HEAD);
			fairy.addTrait(TinkerTraits.holy);
			TinkerRegistry.addMaterial(fairy);
			TinkerRegistry.addMaterialStats(fairy,
					new HeadMaterialStats(843, 6.68F, 2.5F, 4),
					new HandleMaterialStats(0.5F, 188),
					new ExtraMaterialStats(200),
					new BowMaterialStats(0.9F, 1.1F, 1.0F));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("pokefennium")) {
			pokefennium.addItem("ingotPokefennium", 1, Material.VALUE_Ingot);
			pokefennium.addItem("nuggetPokefennium", 1, Material.VALUE_Nugget);
			pokefennium.addItem("blockPokefennium", 1, Material.VALUE_Block);
			pokefennium.setRepresentativeItem(pokefenniumIngot);
			pokefennium.setFluid(pokefenniumFluid);
			pokefennium.setCraftable(false).setCastable(true);
			pokefennium.addTrait(MaterialisTraits.unlimited, MaterialTypes.HEAD);
			pokefennium.addTrait(MaterialisTraits.limited, MaterialTypes.HANDLE);
			pokefennium.addTrait(MaterialisTraits.limited, MaterialTypes.EXTRA);
			TinkerRegistry.addMaterial(pokefennium);
			TinkerRegistry.addMaterialStats(pokefennium,
					new HeadMaterialStats(765, 6.98F, 2.5F, 4),
					new HandleMaterialStats(3.0F, 100),
					new ExtraMaterialStats(400),
					new BowMaterialStats(0.5F, 1.3F, 5.0F));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("red_aurum")) {
			redAurum.addItem("ingotRedAurum", 1, Material.VALUE_Ingot);
			redAurum.addItem("nuggetRedAurum", 1, Material.VALUE_Nugget);
			redAurum.addItem("blockRedAurum", 1, Material.VALUE_Block);
			redAurum.setRepresentativeItem(redAurumIngot);
			redAurum.setFluid(redAurumFluid);
			redAurum.setCraftable(false).setCastable(true);
			redAurum.addTrait(TinkerTraits.sharp);
			TinkerRegistry.addMaterial(redAurum);
			TinkerRegistry.addMaterialStats(redAurum,
					new HeadMaterialStats(22, 13.3F, 3.0F, 2),
					new HandleMaterialStats(0.5F, 6),
					new ExtraMaterialStats(30),
					new BowMaterialStats(1.9F, 1.1F, 0.0F));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("drulloy")) {
			drulloy.addItem("ingotDrulloy", 1, Material.VALUE_Ingot);
			drulloy.addItem("nuggetDrulloy", 1, Material.VALUE_Nugget);
			drulloy.addItem("blockDrulloy", 1, Material.VALUE_Block);
			drulloy.setRepresentativeItem(drulloyIngot);
			drulloy.setFluid(drulloyFluid);
			drulloy.setCraftable(false).setCastable(true);
			drulloy.addTrait(TinkerTraits.heavy, MaterialTypes.HEAD);
			drulloy.addTrait(TinkerTraits.dense);
			TinkerRegistry.addMaterial(drulloy);
			TinkerRegistry.addMaterialStats(drulloy,
					new HeadMaterialStats(199, 8.45F, 2.9F, 2),
					new HandleMaterialStats(0.4F, 100),
					new ExtraMaterialStats(100),
					new BowMaterialStats(1.3F, 1.2F, 2.0F));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("alumite")) {
			alumite.addItem("ingotAlumite", 1, Material.VALUE_Ingot);
			alumite.addItem("nuggetAlumite", 1, Material.VALUE_Nugget);
			alumite.addItem("blockAlumite", 1, Material.VALUE_Block);
			alumite.setRepresentativeItem(alumiteIngot);
			alumite.setFluid(alumiteFluid);
			alumite.setCraftable(false).setCastable(true);
			alumite.addTrait(TinkerTraits.duritos);
			TinkerRegistry.addMaterial(alumite);
			TinkerRegistry.addMaterialStats(alumite,
					new HeadMaterialStats(700, 8.0F, 4.5F, 4),
					new HandleMaterialStats(1.3F, 80),
					new ExtraMaterialStats(100),
					new BowMaterialStats(1.1F, 1.3F, 4.0F));
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("fairy")) {
			Materialis.tabItem = new ItemStack(fairyIngot);
			TinkerRegistry.registerAlloy(new FluidStack(fairyFluid, 2), new FluidStack(TinkerFluids.ardite, 1), new FluidStack(TinkerFluids.obsidian, 2), new FluidStack(TinkerFluids.blood, 1));
			new MaterialIntegration(fairy, fairyFluid, "Fairy").toolforge().integrate();
			MaterialisRegistry.addRecipe(new ItemStack(fairyNugget, 9), "fairy_nugget", new Object[]{"I", 'I', "ingotFairy"});
			MaterialisRegistry.addRecipe(new ItemStack(fairyIngot), "fairy_unnugget", "fairy_ingot", new Object[]{"NNN", "NNN", "NNN", 'N', "nuggetFairy"});
			MaterialisRegistry.addRecipe(new ItemStack(fairyBlock), "fairy_block", new Object[]{"III", "III", "III", 'I', "ingotFairy"});
			MaterialisRegistry.addRecipe(new ItemStack(fairyIngot, 9), "fairy_unblock", "fairy_ingot", new Object[]{"B", 'B', "blockFairy"});
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("pokefennium")) {
			TinkerRegistry.registerAlloy(new FluidStack(pokefenniumFluid, 2), new FluidStack(TinkerFluids.cobalt, 1), new FluidStack(TinkerFluids.iron, 1), new FluidStack(TinkerFluids.blood, 2));
			new MaterialIntegration(pokefennium, pokefenniumFluid, "Pokefennium").toolforge().integrate();
			MaterialisRegistry.addRecipe(new ItemStack(pokefenniumNugget, 9), "pokefennium_nugget", new Object[]{"I", 'I', "ingotPokefennium"});
			MaterialisRegistry.addRecipe(new ItemStack(pokefenniumIngot), "pokefennium_unnugget", "pokefennium_ingot", new Object[]{"NNN", "NNN", "NNN", 'N', "nuggetPokefennium"});
			MaterialisRegistry.addRecipe(new ItemStack(pokefenniumBlock), "pokefennium_block", new Object[]{"III", "III", "III", 'I', "ingotPokefennium"});
			MaterialisRegistry.addRecipe(new ItemStack(pokefenniumIngot, 9), "pokefennium_unblock", "pokefennium_ingot", new Object[]{"B", 'B', "blockPokefennium"});
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("red_aurum")) {
			TinkerRegistry.registerAlloy(new FluidStack(redAurumFluid, 1), new FluidStack(TinkerFluids.gold, 1), new FluidStack(TinkerFluids.blood, 3));
			new MaterialIntegration(redAurum, redAurumFluid, "RedAurum").toolforge().integrate();
			MaterialisRegistry.addRecipe(new ItemStack(redAurumNugget, 9), "red_aurum_nugget", new Object[]{"I", 'I', "ingotRedAurum"});
			MaterialisRegistry.addRecipe(new ItemStack(redAurumIngot), "red_aurum_unnugget", "red_aurum_ingot", new Object[]{"NNN", "NNN", "NNN", 'N', "nuggetRedAurum"});
			MaterialisRegistry.addRecipe(new ItemStack(redAurumBlock), "red_aurum_block", new Object[]{"III", "III", "III", 'I', "ingotRedAurum"});
			MaterialisRegistry.addRecipe(new ItemStack(redAurumIngot, 9), "red_aurum_unblock", "red_aurum_ingot", new Object[]{"B", 'B', "blockRedAurum"});
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("drulloy")) {
			TinkerRegistry.registerAlloy(new FluidStack(drulloyFluid, 2), new FluidStack(TinkerFluids.gold, 1), new FluidStack(TinkerFluids.clay, 4));
			new MaterialIntegration(drulloy, drulloyFluid, "Drulloy").toolforge().integrate();
			MaterialisRegistry.addRecipe(new ItemStack(drulloyNugget, 9), "drulloy_nugget", new Object[]{"I", 'I', "ingotDrulloy"});
			MaterialisRegistry.addRecipe(new ItemStack(drulloyIngot), "drulloy_unnugget", "drulloy_ingot", new Object[]{"NNN", "NNN", "NNN", 'N', "nuggetDrulloy"});
			MaterialisRegistry.addRecipe(new ItemStack(drulloyBlock), "drulloy_block", new Object[]{"III", "III", "III", 'I', "ingotDrulloy"});
			MaterialisRegistry.addRecipe(new ItemStack(drulloyIngot, 9), "drulloy_unblock", "drulloy_ingot", new Object[]{"B", 'B', "blockDrulloy"});
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("alumite")) {
			TinkerRegistry.registerAlloy(new FluidStack(alumiteFluid, 3), new FluidStack(TinkerFluids.obsidian, 2), new FluidStack(TinkerFluids.iron, 2), new FluidStack(TinkerFluids.aluminum, 5));
			new MaterialIntegration(alumite, alumiteFluid, "Alumite").toolforge().integrate();
			MaterialisRegistry.addRecipe(new ItemStack(alumiteNugget, 9), "alumite_nugget", new Object[]{"I", 'I', "ingotAlumite"});
			MaterialisRegistry.addRecipe(new ItemStack(alumiteIngot), "alumite_unnugget", "alumite_ingot", new Object[]{"NNN", "NNN", "NNN", 'N', "nuggetAlumite"});
			MaterialisRegistry.addRecipe(new ItemStack(alumiteBlock), "alumite_block", new Object[]{"III", "III", "III", 'I', "ingotAlumite"});
			MaterialisRegistry.addRecipe(new ItemStack(alumiteIngot, 9), "alumite_unblock", "alumite_ingot", new Object[]{"B", 'B', "blockAlumite"});
		}
	}
}
