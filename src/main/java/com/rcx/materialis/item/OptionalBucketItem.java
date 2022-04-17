package com.rcx.materialis.item;

import java.util.function.Supplier;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ICondition.IContext;

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
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		if (condition.test(IContext.EMPTY))
			super.fillItemCategory(group, items);
	}

}
