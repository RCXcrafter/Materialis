package com.rcx.materialis.datagen;

import java.util.Objects;

import com.rcx.materialis.MaterialisResources;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.crafting.Ingredient;
import slimeknights.tconstruct.library.data.tinkering.AbstractStationSlotLayoutProvider;
import slimeknights.tconstruct.library.recipe.partbuilder.Pattern;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.TinkerTools;

public class MaterialisToolSlotLayouts extends AbstractStationSlotLayoutProvider {

	public MaterialisToolSlotLayouts(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void addLayouts() {
		//wrench
		defineModifiable(MaterialisResources.WRENCH)
		.sortIndex(SORT_HARVEST)
		.addInputItem(MaterialisResources.WRENCH_HEAD, 39, 35)
		.addInputItem(TinkerToolParts.toolHandle, 21, 53)
		.build();
		defineModifiable(TinkerTools.sledgeHammer)
		.sortIndex(SORT_HARVEST + SORT_LARGE)
		.addInputItem(TinkerToolParts.hammerHead,  44, 29)
		.addInputItem(TinkerToolParts.toughHandle, 21, 52)
		.addInputSlot(new Pattern(Objects.requireNonNull(TinkerToolParts.largePlate.asItem().getRegistryName())), TinkerToolParts.largePlate.asItem().getDescriptionId(), 50, 48, Ingredient.of(TinkerToolParts.largePlate, MaterialisResources.WRENCH_HEAD))
		.addInputSlot(new Pattern(Objects.requireNonNull(TinkerToolParts.largePlate.asItem().getRegistryName())), TinkerToolParts.largePlate.asItem().getDescriptionId(), 25, 20, Ingredient.of(TinkerToolParts.largePlate, MaterialisResources.WRENCH_HEAD))
		.build();
	}

	@Override
	public String getName() {
		return "Materialis Tinker Station Slot Layouts";
	}
}
