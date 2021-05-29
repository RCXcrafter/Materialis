package com.rcx.materialis.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.crafting.conditions.ICondition;

public class OptionalItem extends Item {

	public ICondition condition;

	public OptionalItem(Properties prop, ICondition condition) {
		super(prop);
		this.condition = condition;
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		if (condition.test())
			super.fillItemCategory(group, items);
	}
}
