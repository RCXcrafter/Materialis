package com.rcx.materialis.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitBlinding extends AbstractTrait {

	public static String id = "blinding";

	public TraitBlinding() {
		super(id, 0x3661A0);
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
		if(wasHit) {
			target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 40, 1));
			if (!(target instanceof EntityPlayer))
				target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 1));
		}
	}
}
