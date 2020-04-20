package com.rcx.materialis;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.rcx.materialis.resources.BlockMoltenCustom;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import slimeknights.tconstruct.smeltery.block.BlockMolten;

public class MaterialisRegistry {

	public static List<ItemBlock> blocks = new ArrayList<ItemBlock>();
	public static List<Item> items = new ArrayList<Item>();
	
	public static void registerBlock(Block block) {
		blocks.add((ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}
	
	public static void registerItem(Item item) {
		items.add(item);
	}
	
	public static void registerFluid(Fluid fluid, int color) {
		fluid.setUnlocalizedName(fluid.getName());
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);
		BlockMolten block = new BlockMoltenCustom(fluid, color);
		block.setRegistryName(Materialis.ID, "molten_" + fluid.getName());
		registerBlock(block);
	}

	public static void addRecipe(@Nonnull ItemStack output, String name, Object... params) {
		addRecipe(output, name, name, params);
	}

	public static void addRecipe(@Nonnull ItemStack output, String name, String group, Object... params) {
		GameRegistry.addShapedRecipe(new ResourceLocation(Materialis.ID, "recipe_" + name), new ResourceLocation(group), output, params);
	}

	public static void addShapelessRecipe(@Nonnull ItemStack output, String name, Ingredient... params) {
		addShapelessRecipe(output, name, name, params);
	}

	public static void addShapelessRecipe(@Nonnull ItemStack output, String name, String group, Ingredient... params) {
		GameRegistry.addShapelessRecipe(new ResourceLocation(Materialis.ID, "recipe_" + name), new ResourceLocation(group), output, params);
	}
}
