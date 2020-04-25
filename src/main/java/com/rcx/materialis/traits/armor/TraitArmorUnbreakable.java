package com.rcx.materialis.traits.armor;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class TraitArmorUnbreakable extends AbstractArmorTrait {

	public TraitArmorUnbreakable() {
		super("unbreakable", 0xFF5555);
	}

    @Override
    public int onArmorDamage(ItemStack armor, DamageSource source, int damage, int newDamage, EntityPlayer player, int slot) {
        return 0;
    }
}
