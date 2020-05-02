package com.rcx.materialis;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class Util {

	public static Item getItem(String domain, String id) {
		return Item.REGISTRY.getObject(new ResourceLocation(domain, id));
	}

}
