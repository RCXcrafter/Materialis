package com.rcx.materialis.item;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ICondition.IContext;

public class OptionalItem extends Item {

	public ICondition condition;

	public OptionalItem(Properties prop, ICondition condition) {
		super(prop);
		this.condition = condition;
	}

	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		if (condition.test(IContext.EMPTY))
			super.fillItemCategory(group, items);
	}
}
