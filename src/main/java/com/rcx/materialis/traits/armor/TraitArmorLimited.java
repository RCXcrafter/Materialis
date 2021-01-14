package com.rcx.materialis.traits.armor;

import com.rcx.materialis.traits.MaterialisTraits;
import com.rcx.materialis.traits.TraitLimited;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitArmorLimited extends AbstractArmorTrait {

	public float modifier = 1.5F;

	public TraitArmorLimited() {
		super(TraitLimited.id, 0x64A7B5);
	}

	@Override
	public float onDamaged(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingDamageEvent evt) {
		if (ToolHelper.getTraits(armor).contains(MaterialisTraits.unlimited))
			return newDamage;
		return newDamage * modifier;
	}
}
