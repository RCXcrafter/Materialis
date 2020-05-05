package com.rcx.materialis.modules;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisConfig;
import com.rcx.materialis.Util;
import com.rcx.materialis.traits.armor.MaterialisArmorTraits;

import c4.conarm.common.armor.traits.ArmorTraits;
import c4.conarm.lib.materials.ArmorMaterialType;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import epicsquid.roots.init.ModRecipes;
import epicsquid.roots.recipe.FeyCraftingRecipe;
import epicsquid.roots.recipe.ingredient.GoldOrSilverIngotIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreIngredient;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.MaterialItem;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.tools.TinkerMaterials;
import slimeknights.tconstruct.tools.TinkerTraits;

public class ModuleRoots implements IModule {

	public static Material living = new Material("living", 0x349434);

	public static Material wildwood = new Material("wildwood", 0x917341);

	@Override
	public Boolean shouldLoad() {
		return Loader.isModLoaded(this.getName()) && !MaterialisConfig.blacklist.isModuleBlacklisted(this.getName());
	}

	@Override
	public String getName() {
		return "roots";
	}

	@Override
	public void earlyPreInit(FMLPreInitializationEvent preEvent) {}

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("living")) {
			living.addTrait(TinkerTraits.ecological);
			TinkerRegistry.addMaterial(living);
			TinkerRegistry.addMaterialStats(living,
					new HeadMaterialStats(204, 6.0F, 4.0F, 2),
					new HandleMaterialStats(0.85F, 60),
					new ExtraMaterialStats(50),
					new BowMaterialStats(0.5F, 1.5F, 7.0F));
			if (ModuleConarm.loadArmor()) {
				living.addTrait(ArmorTraits.ecological, ArmorMaterialType.CORE);
				TinkerRegistry.addMaterialStats(living,
						new CoreMaterialStats(12, 11.25F),
						new PlatesMaterialStats(0.85F, 5, 0.0F),
						new TrimMaterialStats(4));
			}
		}
		if (ModuleConarm.loadArmor()) {
			if (!MaterialisConfig.blacklist.isMaterialBlacklisted("wildwood")) {
				wildwood.addTrait(MaterialisArmorTraits.untamed, ArmorMaterialType.CORE);
				wildwood.addTrait(ArmorTraits.shielding);
				TinkerRegistry.addMaterial(wildwood);
				TinkerRegistry.addMaterialStats(wildwood,
						new CoreMaterialStats(12, 12.0F),
						new PlatesMaterialStats(0.95F, 5, 1.0F),
						new TrimMaterialStats(4));
			}
		}
	}

	@Override
	public void registerItems(Register<Item> event) {}

	@Override
	public void init(FMLInitializationEvent event) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted("living")) {
			living.addItem(Util.getItem("roots", "spirit_herb"), 1, 0);
			living.setRepresentativeItem(Util.getItem("roots", "spirit_herb"));

			for (IToolPart part : TinkerRegistry.getToolParts()) {
				if (part.canUseMaterial(living) && part.canUseMaterial(TinkerMaterials.wood) && part instanceof MaterialItem)
					ModRecipes.addFeyCraftingRecipe(new ResourceLocation(Materialis.ID, "living_toolpart_" + ((MaterialItem) part).getRegistryName()),
							new FeyCraftingRecipe(part.getItemstackWithMaterial(living), 1).addIngredients(
									new GoldOrSilverIngotIngredient(),
									part.getItemstackWithMaterial(TinkerMaterials.wood),
									new OreIngredient("wildroot"),
									new OreIngredient("rootsBark"),
									new OreIngredient("rootsBark")));
			}
		}
		if (ModuleConarm.loadArmor()) {
			if (!MaterialisConfig.blacklist.isMaterialBlacklisted("wildwood")) {
				wildwood.addItem(Util.getItem("roots", "bark_wildwood"), 1, Material.VALUE_Nugget);
				wildwood.setRepresentativeItem(Util.getItem("roots", "bark_wildwood"));

				ItemStack wildwoodBark = new ItemStack(Util.getItem("roots", "bark_wildwood"));
				for (IToolPart part : TinkerRegistry.getToolParts()) {
					if (part.canUseMaterial(wildwood) && part.canUseMaterial(TinkerMaterials.iron) && part instanceof MaterialItem)
						ModRecipes.addFeyCraftingRecipe(new ResourceLocation(Materialis.ID, "wildwood_armor_toolpart_" + ((MaterialItem) part).getRegistryName()),
								new FeyCraftingRecipe(part.getItemstackWithMaterial(wildwood), 1).addIngredients(
										part.getItemstackWithMaterial(TinkerMaterials.iron),
										wildwoodBark,
										wildwoodBark,
										new OreIngredient("plankWood"),
										new OreIngredient("gemDiamond")));
				}
			}
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {}
}
