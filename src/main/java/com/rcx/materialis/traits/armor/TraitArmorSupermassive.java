package com.rcx.materialis.traits.armor;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;

public class TraitArmorSupermassive extends AbstractArmorTrait {

	public TraitArmorSupermassive() {
		super("supermassive", 0x64A7B5);
	}

	@Override
	public void onKnockback(ItemStack armor, EntityPlayer player, LivingKnockBackEvent event) {
		event.setCanceled(true);
	}
}
