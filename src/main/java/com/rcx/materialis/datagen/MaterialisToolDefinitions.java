package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.ToolActions;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class MaterialisToolDefinitions extends AbstractToolDefinitionDataProvider {

	public MaterialisToolDefinitions(DataGenerator generator) {
		super(generator, Materialis.modID);
	}

	@Override
	protected void addToolDefinitions() {
		//wrench
		define(MaterialisResources.WRENCH_DEFINITION)
		//parts
		.part(MaterialisResources.WRENCH_HEAD)
		.part(TinkerToolParts.toolHandle)
		//stats
		.multiplier(ToolStats.ATTACK_DAMAGE, 0.5f)
		.multiplier(ToolStats.ATTACK_SPEED, 1.8f)
		.multiplier(ToolStats.MINING_SPEED, 2.0f)
		.multiplier(ToolStats.DURABILITY, 1.5f)
		.startingSlots(SlotType.UPGRADE, 1)
		.startingSlots(SlotType.ABILITY, 3)
		//traits
		.trait(MaterialisModifiers.wrenchingModifier)
		// harvest
		.action(ToolActions.PICKAXE_DIG)
		.action(ToolActions.SHEARS_DISARM)
		.effective(MaterialisBlockTags.MINABLE_WITH_WRENCH);

		define(MaterialisResources.BATTLEWRENCH_DEFINITION)
		// parts
		.part(TinkerToolParts.hammerHead, 2)
		.part(TinkerToolParts.toughHandle)
		.part(MaterialisResources.WRENCH_HEAD, 1)
		.part(MaterialisResources.WRENCH_HEAD, 1)
		// stats
		.stat(ToolStats.ATTACK_DAMAGE, 4f)
		.multiplier(ToolStats.ATTACK_DAMAGE, 1.1f)
		.multiplier(ToolStats.ATTACK_SPEED, 1.1f)
		.multiplier(ToolStats.MINING_SPEED, 1.5f)
		.multiplier(ToolStats.DURABILITY, 2.5f)
		.startingSlots(SlotType.UPGRADE, 1)
		.startingSlots(SlotType.ABILITY, 2)
		// traits
		.trait(MaterialisModifiers.wrenchingModifier)
		// harvest
		.action(ToolActions.PICKAXE_DIG)
		.action(ToolActions.SHEARS_DISARM)
		.effective(MaterialisBlockTags.MINABLE_WITH_WRENCH);


		/*defineArmor(MaterialisResources.EXOSUIT_DEFINITION)
		.durabilityFactor(18)
		.stat(ToolStats.ARMOR, 1, 4, 5, 1)
		.startingSlots(ArmorSlotType.HELMET, MaterialisResources.SENSOR_SLOT, 1)
		.startingSlots(SlotType.UPGRADE, 1)
		.startingSlots(SlotType.DEFENSE, 2)
		.startingSlots(SlotType.ABILITY, 1)
		.trait(ArmorSlotType.BOOTS, MaterialisModifiers.psionizingRadiationModifierJump.get())
		.trait(ArmorSlotType.BOOTS, MaterialisModifiers.spellSocketModifier.get())
		.trait(ArmorSlotType.LEGGINGS, MaterialisModifiers.psionizingRadiationModifierTick.get())
		.trait(ArmorSlotType.LEGGINGS, MaterialisModifiers.spellSocketModifier.get())
		.trait(ArmorSlotType.CHESTPLATE, MaterialisModifiers.psionizingRadiationModifierDamage.get())
		.trait(ArmorSlotType.CHESTPLATE, MaterialisModifiers.spellSocketModifier.get())
		.trait(ArmorSlotType.HELMET, MaterialisModifiers.spellSocketModifier.get());*/
	}

	@Override
	public String getName() {
		return "Materialis Tool Definition Data Generator";
	}
}
