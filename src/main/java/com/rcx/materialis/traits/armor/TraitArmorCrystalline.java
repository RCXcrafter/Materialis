package com.rcx.materialis.traits.armor;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class TraitArmorCrystalline extends AbstractArmorTrait {

	public float modifier = 1.5F;

	public TraitArmorCrystalline() {
		super("crystalline", 0x97F1EB);
	}

	@Override
	public float onDamaged(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingDamageEvent evt) {
		return newDamage / modifier;
	}
}
