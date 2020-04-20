package com.rcx.materialis.resources;

import com.rcx.materialis.Materialis;

import net.minecraft.item.Item;

public class ItemBasic extends Item {

	public ItemBasic(String name) {
		super();
		this.setCreativeTab(Materialis.MatTab);
		this.setUnlocalizedName(name.toLowerCase());
		this.setRegistryName(Materialis.ID, name.toLowerCase());
	}
}
