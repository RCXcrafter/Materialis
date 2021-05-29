package com.rcx.materialis.item;

import java.util.function.Supplier;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.crafting.conditions.ICondition;

public class OptionalBucketItem extends BucketItem {

	public ICondition condition;

	public OptionalBucketItem(Supplier<? extends Fluid> supplier, Properties builder, ICondition condition) {
		super(supplier, builder);
		this.condition = condition;
	}

	public OptionalBucketItem(Fluid fluid, Properties builder, ICondition condition) {
		super(fluid, builder);
		this.condition = condition;
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		if (condition.test())
			super.fillItemCategory(group, items);
	}

}
