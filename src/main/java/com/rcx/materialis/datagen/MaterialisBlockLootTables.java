package com.rcx.materialis.datagen;

import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.MaterialisResources.IngotWithBlockNNugget;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class MaterialisBlockLootTables extends BlockLoot {

	@Nonnull
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return ForgeRegistries.BLOCKS.getValues().stream()
				.filter((block) -> Materialis.modID.equals(Objects.requireNonNull(block.getRegistryName()).getNamespace()))
				.collect(Collectors.toList());
	}

	@Override
	protected void addTables() {
		for (IngotWithBlockNNugget material : MaterialisResources.materialList) {
			this.dropSelf(material.BLOCK.get());
		}
	}
}
