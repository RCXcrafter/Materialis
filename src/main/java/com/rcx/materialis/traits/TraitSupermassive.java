package com.rcx.materialis.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitSupermassive extends AbstractTrait {

	public float modifier = 10.0F;

	public TraitSupermassive() {
		super("supermassive", 0x6B6B6B);
	}

	@Override
	public float knockBack(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float knockback, float newKnockback, boolean isCritical) {
		return newKnockback * modifier + modifier;
	}
}
