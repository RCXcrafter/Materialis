package com.rcx.materialis.modules;

import com.rcx.materialis.MaterialisConfig;
import com.rcx.materialis.MaterialisRegistry;
import com.rcx.materialis.Util;
import c4.conarm.common.armor.modifiers.ArmorModifiers;
import c4.conarm.common.armor.traits.ArmorTraits;
import c4.conarm.lib.materials.ArmorMaterialType;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.FletchingMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.tools.TinkerMaterials;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerTraits;

import java.util.*;

public class ModuleErebus implements IModule {

	private static final Material BAMBOO = new Material("bamboo", 0x91A043);

	private static final Material JADE = new Material("jade", 0x60CF7B);

	private static final Material FLY_WING = new Material("fly_wing", 0xA5A5A5);

	private static final Material DRAGONFLY_WING = new Material("dragonfly_wing", 0x9EC0D5);

	private static final Material EXOSKELETON = new Material("exoskeleton", 0xD8D8D2);

	private static final Material EXOSKELETON_REINFORCED = new Material("exoskeleton_reinforced", 0xC1C1B6);

	private static final Material EXOSKELETON_RHINO = new Material("exoskeleton_rhino", 0x5B5B5B);

	private static final Map<String, Boolean> I_REGISTERED_THE_MATERIAL = new HashMap<>();

	//public static AbstractTrait modWaterStrider;

	@Override
	public Boolean shouldLoad() {
		return Loader.isModLoaded(this.getName()) && !MaterialisConfig.blacklist.isModuleBlacklisted(this.getName());
	}

	@Override
	public String getName() {
		return "erebus";
	}

	@Override
	public void earlyPreInit(FMLPreInitializationEvent preEvent) {
		//register wood materials early
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(BAMBOO.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(BAMBOO.getIdentifier(), true);
			TinkerRegistry.addMaterial(BAMBOO);
		}
	}

	@Override
	public void preInit(FMLPreInitializationEvent preEvent) {
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(BAMBOO.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(BAMBOO.getIdentifier(), false)) {
			BAMBOO.addTrait(TinkerTraits.ecological);
			TinkerRegistry.addMaterialStats(BAMBOO,
					new HeadMaterialStats(100, 3.0F, 3.0F, 0),
					new HandleMaterialStats(1.0F, 50),
					new ExtraMaterialStats(25),
					new BowMaterialStats(0.9F, 1.2F, 0.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(BAMBOO, 0.0F);
				BAMBOO.addTrait(ArmorTraits.ecological, ArmorMaterialType.CORE);
				BAMBOO.addTrait(ArmorTraits.ecological, ArmorMaterialType.PLATES);
				BAMBOO.addTrait(ArmorTraits.ecological, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(JADE.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(JADE.getIdentifier(), true);

			JADE.addTrait(TinkerTraits.established);
			TinkerRegistry.addMaterial(JADE);
			TinkerRegistry.addMaterialStats(JADE,
					new HeadMaterialStats(495, 10.0F, 4.0F, 2),
					new HandleMaterialStats(0.5F, 0),
					new ExtraMaterialStats(368),
					new BowMaterialStats(0.2F, 0.4F, -1.0F));
			if (ModuleConarm.loadArmor()) {
				ModuleConarm.generateArmorStats(JADE, 1.0F);
				JADE.addTrait(ArmorTraits.ambitious, ArmorMaterialType.CORE);
				JADE.addTrait(ArmorTraits.ambitious, ArmorMaterialType.PLATES);
				JADE.addTrait(ArmorTraits.ambitious, ArmorMaterialType.TRIM);
			}
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(FLY_WING.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(FLY_WING.getIdentifier(), true);

			TinkerRegistry.addMaterial(FLY_WING);
			TinkerRegistry.addMaterialStats(FLY_WING, new FletchingMaterialStats(0.6F, 1.5F));
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(DRAGONFLY_WING.getIdentifier())) {
			I_REGISTERED_THE_MATERIAL.put(DRAGONFLY_WING.getIdentifier(), true);

			TinkerRegistry.addMaterial(DRAGONFLY_WING);
			TinkerRegistry.addMaterialStats(DRAGONFLY_WING, new FletchingMaterialStats(1.0F, 1.25F));
		}

		if (ModuleConarm.loadArmor()) {
			if (!MaterialisConfig.blacklist.isMaterialBlacklisted(EXOSKELETON.getIdentifier())) {
				I_REGISTERED_THE_MATERIAL.put(EXOSKELETON.getIdentifier(), true);

				EXOSKELETON.addTrait(ArmorTraits.skeletal);
				TinkerRegistry.addMaterial(EXOSKELETON);
				TinkerRegistry.addMaterialStats(EXOSKELETON,
						new CoreMaterialStats(7, 8.2F),
						new PlatesMaterialStats(1.0F, 4, 1.0F),
						new TrimMaterialStats(17));
			}
			if (!MaterialisConfig.blacklist.isMaterialBlacklisted(EXOSKELETON_REINFORCED.getIdentifier())) {
				I_REGISTERED_THE_MATERIAL.put(EXOSKELETON_REINFORCED.getIdentifier(), true);

				EXOSKELETON_REINFORCED.addTrait(ArmorTraits.heavy);
				EXOSKELETON_REINFORCED.addTrait(ArmorTraits.dense);
				TinkerRegistry.addMaterial(EXOSKELETON_REINFORCED);
				TinkerRegistry.addMaterialStats(EXOSKELETON_REINFORCED,
						new CoreMaterialStats(21, 13.2F),
						new PlatesMaterialStats(1.3F, 7, 3.0F),
						new TrimMaterialStats(27));
			}
			if (!MaterialisConfig.blacklist.isMaterialBlacklisted(EXOSKELETON_RHINO.getIdentifier())) {
				I_REGISTERED_THE_MATERIAL.put(EXOSKELETON_RHINO.getIdentifier(), true);

				EXOSKELETON_RHINO.addTrait(ArmorTraits.dramatic);
				TinkerRegistry.addMaterial(EXOSKELETON_RHINO);
				TinkerRegistry.addMaterialStats(EXOSKELETON_RHINO,
						new CoreMaterialStats(21, 13.2F),
						new PlatesMaterialStats(1.3F, 7, 4.0F),
						new TrimMaterialStats(27));
			}

			//modWaterStrider = new ModWaterStrider();
		}
	}

	@Override
	public void registerItems(Register<Item> event) {}

	@Override
	public void init(FMLInitializationEvent event) {
		TinkerMaterials.bone.addItem(new ItemStack(Util.getItem("erebus", "materials"), 1, 2), 1, Material.VALUE_Shard);

		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(BAMBOO.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(BAMBOO.getIdentifier(), false)) {
			BAMBOO.addItem("bamboo", 1, Material.VALUE_Fragment);
			BAMBOO.addItem("plankBamboo", 1, Material.VALUE_Ingot);
			BAMBOO.setRepresentativeItem(new ItemStack(Util.getItem("erebus", "materials"), 1, 3));
			BAMBOO.setCraftable(true);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(JADE.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(JADE.getIdentifier(), false)) {
			JADE.addItem("gemJade", 1, Material.VALUE_Ingot);
			JADE.addItem("nuggetJade", 1, Material.VALUE_Nugget);
			JADE.addItem("blockJade", 1, Material.VALUE_Block);
			JADE.setRepresentativeItem(new ItemStack(Util.getItem("erebus", "materials"), 1, 1));
			JADE.setCraftable(true);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(FLY_WING.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(FLY_WING.getIdentifier(), false)) {
			FLY_WING.addItem(new ItemStack(Util.getItem("erebus", "materials"), 1, 6), 1, Material.VALUE_Ingot);
			FLY_WING.setRepresentativeItem(new ItemStack(Util.getItem("erebus", "materials"), 1, 6));
			FLY_WING.setCraftable(true);
		}
		if (!MaterialisConfig.blacklist.isMaterialBlacklisted(DRAGONFLY_WING.getIdentifier())
				&& I_REGISTERED_THE_MATERIAL.getOrDefault(DRAGONFLY_WING.getIdentifier(), false)) {
			DRAGONFLY_WING.addItem(new ItemStack(Util.getItem("erebus", "materials"), 1, 24), 1, Material.VALUE_Ingot);
			DRAGONFLY_WING.setRepresentativeItem(new ItemStack(Util.getItem("erebus", "materials"), 1, 24));
			DRAGONFLY_WING.setCraftable(true);
		}

		TinkerModifiers.modSharpness.addItem(new ItemStack(Util.getItem("erebus", "whetstone"), 1, 1), 1, 72);

		TinkerModifiers.modGlowing.addRecipeMatch(new RecipeMatch.ItemCombination(1, new ItemStack(Util.getItem("erebus", "materials"), 1, 12), new ItemStack(Items.ENDER_EYE), new ItemStack(Util.getItem("erebus", "materials"), 1, 12)));

		if (ModuleConarm.loadArmor()) {
			if (!MaterialisConfig.blacklist.isMaterialBlacklisted(EXOSKELETON.getIdentifier())
					&& I_REGISTERED_THE_MATERIAL.getOrDefault(EXOSKELETON.getIdentifier(), false)) {
				EXOSKELETON.addItem(new ItemStack(Util.getItem("erebus", "materials"), 1, 0), 1, Material.VALUE_Ingot);
				EXOSKELETON.setRepresentativeItem(new ItemStack(Util.getItem("erebus", "materials"), 1, 0));
				EXOSKELETON.setCraftable(true);
			}
			if (!MaterialisConfig.blacklist.isMaterialBlacklisted(EXOSKELETON_REINFORCED.getIdentifier())
					&& I_REGISTERED_THE_MATERIAL.getOrDefault(EXOSKELETON_REINFORCED.getIdentifier(), false)) {
				EXOSKELETON_REINFORCED.addItem(new ItemStack(Util.getItem("erebus", "materials"), 1, 15), 1, Material.VALUE_Ingot);
				EXOSKELETON_REINFORCED.setRepresentativeItem(new ItemStack(Util.getItem("erebus", "materials"), 1, 15));
				EXOSKELETON_REINFORCED.setCraftable(true);
			}
			if (!MaterialisConfig.blacklist.isMaterialBlacklisted(EXOSKELETON_RHINO.getIdentifier())
					&& I_REGISTERED_THE_MATERIAL.getOrDefault(EXOSKELETON_RHINO.getIdentifier(), false)) {
				EXOSKELETON_RHINO.addItem(new ItemStack(Util.getItem("erebus", "materials"), 1, 35), 1, Material.VALUE_Ingot);
				EXOSKELETON_RHINO.setRepresentativeItem(new ItemStack(Util.getItem("erebus", "materials"), 1, 35));
				EXOSKELETON_RHINO.setCraftable(true);
			}

			MaterialisRegistry.addRecipe(new ItemStack(Util.getItem("conarm", "travel_night")), "travel_night", "travel_night", "LGL", 'L', new ItemStack(Util.getItem("erebus", "materials"), 1, 5), 'G', new ItemStack(Util.getItem("conarm", "travel_goggles_base")));

			ArmorModifiers.modGlowing.addRecipeMatch(new RecipeMatch.ItemCombination(1, new ItemStack(Util.getItem("erebus", "materials"), 1, 12), new ItemStack(Items.ENDER_EYE), new ItemStack(Util.getItem("erebus", "materials"), 1, 12)));

			//modWaterStrider.addItem(new ItemStack(ModuleTConstruct.alumiteIngot), 1, 1);
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent postEvent) {}
}
