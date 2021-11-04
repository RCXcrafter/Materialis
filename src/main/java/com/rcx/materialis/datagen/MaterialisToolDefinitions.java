package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.modifiers.MaterialisModifiers;

import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
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
		.smallToolStartingSlots()
		//traits
		.trait(MaterialisModifiers.wrenchingModifierHidden);

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
		.largeToolStartingSlots()
		// traits
		.trait(MaterialisModifiers.wrenchingModifierHidden)
		.trait(TinkerModifiers.twoHanded);
	}

	@Override
	public String getName() {
		return "Materialis Tool Definition Data Generator";
	}
}
