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

import java.util.*;

public class ModuleRoots implements IModule {

	private static final Material LIVING = new Material("living", 0x349434);

	private static final Material WILDWOOD = new Material("wildwood", 0x917341);

	private static final Map<String, Boolean> I_REGISTERED_THE_MATERIAL = new HashMap<>();

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
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(LIVING.getIdentifier())
				&& "unknown".equals(TinkerRegistry.getMaterial(LIVING.getIdentifier()).getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(LIVING.getIdentifier(), true);

			LIVING.addTrait(TinkerTraits.ecological);
			TinkerRegistry.addMaterial(LIVING);
			TinkerRegistry.addMaterialStats(LIVING,
					new HeadMaterialStats(204, 6.0F, 4.0F, 2),
					new HandleMaterialStats(0.85F, 60),
					new ExtraMaterialStats(50),
					new BowMaterialStats(0.5F, 1.5F, 7.0F));
			if (ModuleConarm.loadArmor()) {
				LIVING.addTrait(ArmorTraits.ecological, ArmorMaterialType.CORE);
				LIVING.addTrait(ArmorTraits.ecological, ArmorMaterialType.PLATES);
				LIVING.addTrait(ArmorTraits.ecological, ArmorMaterialType.TRIM);
				TinkerRegistry.addMaterialStats(LIVING,
						new CoreMaterialStats(12, 11.25F),
						new PlatesMaterialStats(0.85F, 5, 0.0F),
						new TrimMaterialStats(4));
			}
		}
		if (ModuleConarm.loadArmor() && !MaterialisConfig.blacklist.isMaterialBlacklisted(WILDWOOD.getIdentifier())
				&& "unknown".equals(TinkerRegistry.getMaterial(WILDWOOD.getIdentifier()).getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(WILDWOOD.getIdentifier(), true);

			WILDWOOD.addTrait(MaterialisArmorTraits.untamed, ArmorMaterialType.CORE);
			WILDWOOD.addTrait(ArmorTraits.shielding);
			TinkerRegistry.addMaterial(WILDWOOD);
			TinkerRegistry.addMaterialStats(WILDWOOD,
					new CoreMaterialStats(12, 12.0F),
					new PlatesMaterialStats(0.95F, 5, 1.0F),
					new TrimMaterialStats(4));
		}
	}

	@Override
	public void registerItems(Register<Item> event) {}

	@Override
	public void init(FMLInitializationEvent event) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(LIVING.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(LIVING.getIdentifier(), false)) {
			LIVING.addItem(Util.getItem("roots", "spirit_herb"), 1, 0);
			LIVING.setRepresentativeItem(Util.getItem("roots", "spirit_herb"));

			for (IToolPart part : TinkerRegistry.getToolParts()) {
				if (part.canUseMaterial(LIVING) && part.canUseMaterial(TinkerMaterials.wood) && part instanceof MaterialItem)
					ModRecipes.addFeyCraftingRecipe(new ResourceLocation(Materialis.ID, "living_toolpart_" + ((MaterialItem) part).getRegistryName()),
							new FeyCraftingRecipe(part.getItemstackWithMaterial(LIVING), 1).addIngredients(
									new GoldOrSilverIngotIngredient(),
									part.getItemstackWithMaterial(TinkerMaterials.wood),
									new OreIngredient("wildroot"),
									new OreIngredient("rootsBark"),
									new OreIngredient("rootsBark")));
			}
		}
		if (ModuleConarm.loadArmor()
				&& !MaterialisConfig.blacklist.isMaterialBlacklisted(WILDWOOD.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(WILDWOOD.getIdentifier(), false)) {
			WILDWOOD.addItem(Util.getItem("roots", "bark_wildwood"), 1, Material.VALUE_Nugget);
			WILDWOOD.setRepresentativeItem(Util.getItem("roots", "bark_wildwood"));

			ItemStack wildwoodBark = new ItemStack(Util.getItem("roots", "bark_wildwood"));
			for (IToolPart part : TinkerRegistry.getToolParts()) {
				if (part.canUseMaterial(WILDWOOD) && part.canUseMaterial(TinkerMaterials.iron) && part instanceof MaterialItem)
					ModRecipes.addFeyCraftingRecipe(new ResourceLocation(Materialis.ID, "wildwood_armor_toolpart_" + ((MaterialItem) part).getRegistryName()),
							new FeyCraftingRecipe(part.getItemstackWithMaterial(WILDWOOD), 1).addIngredients(
									part.getItemstackWithMaterial(TinkerMaterials.iron),
									wildwoodBark,
									wildwoodBark,
									new OreIngredient("plankWood"),
									new OreIngredient("gemDiamond")));
			}
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {}
}
