package com.rcx.materialis.modules;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisRegistry;
import com.rcx.materialis.resources.BlockBasic;
import com.rcx.materialis.resources.FluidCustom;
import com.rcx.materialis.resources.ItemBasic;
import com.rcx.materialis.traits.MaterialisTraits;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
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
	public static Block fairyBlock = new BlockBasic("fairy_block", net.minecraft.block.material.Material.IRON, 1.7F);
	public static FluidMolten fairyFluid = new FluidCustom("fairy");//no not ferrofluid

	public static Material pokefennium = new Material("pokefennium", 0x64A7B5);
	public static Item pokefenniumIngot = new ItemBasic("pokefennium_ingot");
	public static Block pokefenniumBlock = new BlockBasic("pokefennium_block", net.minecraft.block.material.Material.IRON, 2.2F);
	public static FluidMolten pokefenniumFluid = new FluidCustom("pokefennium");

	public static Material redAurum = new Material("red_aurum", 0xFFD000);
	public static Item redAurumIngot = new ItemBasic("red_aurum_ingot");
	public static Block redAurumBlock = new BlockBasic("red_aurum_block", net.minecraft.block.material.Material.IRON, 1.7F);
	public static FluidMolten redAurumFluid = new FluidCustom("red_aurum");

	public static Material drulloy = new Material("drulloy", 0xD2915C);
	public static Item drulloyIngot = new ItemBasic("drulloy_ingot");
	public static Block drulloyBlock = new BlockBasic("drulloy_block", net.minecraft.block.material.Material.IRON, 2.2F);
	public static FluidMolten drulloyFluid = new FluidCustom("drulloy");

	@Override
	public Boolean shouldLoad() {
		return true;
	}

	@Override
	public String getName() {
		return "tconstruct";
	}

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {
		MaterialisRegistry.registerItem(fairyIngot);
		MaterialisRegistry.registerBlock(fairyBlock);
		fairyFluid.setTemperature(900);
		MaterialisRegistry.registerFluid(fairyFluid, fairy.materialTextColor);
		Materialis.tabItem = new ItemStack(fairyIngot);

		MaterialisRegistry.registerItem(pokefenniumIngot);
		MaterialisRegistry.registerBlock(pokefenniumBlock);
		pokefenniumFluid.setTemperature(850);
		MaterialisRegistry.registerFluid(pokefenniumFluid, pokefennium.materialTextColor);

		MaterialisRegistry.registerItem(redAurumIngot);
		MaterialisRegistry.registerBlock(redAurumBlock);
		redAurumFluid.setTemperature(455);
		MaterialisRegistry.registerFluid(redAurumFluid, redAurum.materialTextColor);

		MaterialisRegistry.registerItem(drulloyIngot);
		MaterialisRegistry.registerBlock(drulloyBlock);
		drulloyFluid.setTemperature(643);
		MaterialisRegistry.registerFluid(drulloyFluid, drulloy.materialTextColor);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		OreDictionary.registerOre("ingotFairy", new ItemStack(fairyIngot));
		OreDictionary.registerOre("blockFairy", new ItemStack(fairyBlock));
		fairy.addItem("ingotFairy", 1, Material.VALUE_Ingot);
		fairy.setRepresentativeItem(fairyIngot);
		fairy.setFluid(fairyFluid);
		fairy.setCraftable(false).setCastable(true);
		//fairy.addTrait(TinkerTraits.hellish, MaterialTypes.HEAD);
		//fairy.addTrait(TinkerTraits.aridiculous, MaterialTypes.HEAD);
		//fairy.addTrait(TinkerTraits.hellish);
		TinkerRegistry.addMaterial(fairy);
		TinkerRegistry.addMaterialStats(fairy,
				new HeadMaterialStats(843, 6.68F, 2.5F, 4),
				new HandleMaterialStats(0.5F, 188),
				new ExtraMaterialStats(200),
				new BowMaterialStats(0.9F, 1.1F, 1.0F));

		OreDictionary.registerOre("ingotPokefennium", new ItemStack(pokefenniumIngot));
		OreDictionary.registerOre("blockPokefennium", new ItemStack(pokefenniumBlock));
		pokefennium.addItem("ingotPokefennium", 1, Material.VALUE_Ingot);
		pokefennium.setRepresentativeItem(pokefenniumIngot);
		pokefennium.setFluid(pokefenniumFluid);
		pokefennium.setCraftable(false).setCastable(true);
		pokefennium.addTrait(MaterialisTraits.limited, MaterialTypes.HANDLE);
		pokefennium.addTrait(MaterialisTraits.limited, MaterialTypes.EXTRA);
		TinkerRegistry.addMaterial(pokefennium);
		TinkerRegistry.addMaterialStats(pokefennium,
				new HeadMaterialStats(765, 10.98F, 1.0F, 4),
				new HandleMaterialStats(3.0F, 100),
				new ExtraMaterialStats(400),
				new BowMaterialStats(0.5F, 1.3F, 5.0F));

		OreDictionary.registerOre("ingotRedAurum", new ItemStack(redAurumIngot));
		OreDictionary.registerOre("blockRedAurum", new ItemStack(redAurumBlock));
		redAurum.addItem("ingotRedAurum", 1, Material.VALUE_Ingot);
		redAurum.setRepresentativeItem(redAurumIngot);
		redAurum.setFluid(redAurumFluid);
		redAurum.setCraftable(false).setCastable(true);
		//redAurum.addTrait(TinkerTraits.hellish, MaterialTypes.HEAD);
		//redAurum.addTrait(TinkerTraits.aridiculous, MaterialTypes.HEAD);
		//redAurum.addTrait(TinkerTraits.hellish);
		TinkerRegistry.addMaterial(redAurum);
		TinkerRegistry.addMaterialStats(redAurum,
				new HeadMaterialStats(22, 13.3F, 3.0F, 2),
				new HandleMaterialStats(0.5F, 6),
				new ExtraMaterialStats(30),
				new BowMaterialStats(1.9F, 1.1F, 0.0F));

		OreDictionary.registerOre("ingotDrulloy", new ItemStack(drulloyIngot));
		OreDictionary.registerOre("blockDrulloy", new ItemStack(drulloyBlock));
		drulloy.addItem("ingotDrulloy", 1, Material.VALUE_Ingot);
		drulloy.setRepresentativeItem(drulloyIngot);
		drulloy.setFluid(drulloyFluid);
		drulloy.setCraftable(false).setCastable(true);
		//drulloy.addTrait(TinkerTraits.hellish, MaterialTypes.HEAD);
		//drulloy.addTrait(TinkerTraits.aridiculous, MaterialTypes.HEAD);
		//drulloy.addTrait(TinkerTraits.hellish);
		TinkerRegistry.addMaterial(drulloy);
		TinkerRegistry.addMaterialStats(drulloy,
				new HeadMaterialStats(199, 8.45F, 2.9F, 2),
				new HandleMaterialStats(0.4F, 100),
				new ExtraMaterialStats(100),
				new BowMaterialStats(1.3F, 1.2F, 2.0F));
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {
		TinkerRegistry.registerAlloy(new FluidStack(fairyFluid, 2), new FluidStack(TinkerFluids.ardite, 1), new FluidStack(TinkerFluids.obsidian, 2), new FluidStack(TinkerFluids.blood, 1));
		new MaterialIntegration(fairy, fairyFluid, "Fairy").toolforge().integrate();
		MaterialisRegistry.addRecipe(new ItemStack(fairyBlock), "fairy_block", new Object[]{"III", "III", "III", 'I', "ingotFairy"});
		MaterialisRegistry.addRecipe(new ItemStack(fairyIngot, 9), "fairy_unblock", "fairy_ingot", new Object[]{"B", 'B', "blockFairy"});

		TinkerRegistry.registerAlloy(new FluidStack(pokefenniumFluid, 2), new FluidStack(TinkerFluids.cobalt, 1), new FluidStack(TinkerFluids.iron, 1), new FluidStack(TinkerFluids.blood, 2));
		new MaterialIntegration(pokefennium, pokefenniumFluid, "Pokefennium").toolforge().integrate();
		MaterialisRegistry.addRecipe(new ItemStack(pokefenniumBlock), "pokefennium_block", new Object[]{"III", "III", "III", 'I', "ingotPokefennium"});
		MaterialisRegistry.addRecipe(new ItemStack(pokefenniumIngot, 9), "pokefennium_unblock", "pokefennium_ingot", new Object[]{"B", 'B', "blockPokefennium"});

		TinkerRegistry.registerAlloy(new FluidStack(redAurumFluid, 1), new FluidStack(TinkerFluids.gold, 1), new FluidStack(TinkerFluids.blood, 3));
		new MaterialIntegration(redAurum, redAurumFluid, "RedAurum").toolforge().integrate();
		MaterialisRegistry.addRecipe(new ItemStack(redAurumBlock), "red_aurum_block", new Object[]{"III", "III", "III", 'I', "ingotRedAurum"});
		MaterialisRegistry.addRecipe(new ItemStack(redAurumIngot, 9), "red_aurum_unblock", "red_aurum_ingot", new Object[]{"B", 'B', "blockRedAurum"});

		TinkerRegistry.registerAlloy(new FluidStack(drulloyFluid, 2), new FluidStack(TinkerFluids.gold, 1), new FluidStack(TinkerFluids.clay, 4));
		new MaterialIntegration(drulloy, drulloyFluid, "Drulloy").toolforge().integrate();
		MaterialisRegistry.addRecipe(new ItemStack(drulloyBlock), "drulloy_block", new Object[]{"III", "III", "III", 'I', "ingotDrulloy"});
		MaterialisRegistry.addRecipe(new ItemStack(drulloyIngot, 9), "drulloy_unblock", "drulloy_ingot", new Object[]{"B", 'B', "blockDrulloy"});
	}
}
