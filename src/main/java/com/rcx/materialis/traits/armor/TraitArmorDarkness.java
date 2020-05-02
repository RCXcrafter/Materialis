package com.rcx.materialis.traits.armor;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class TraitArmorDarkness extends AbstractArmorTrait {

	public static float chance = 0.3f;

	public TraitArmorDarkness() {
		super("darkness", 0x3661A0);
	}

	@Override
	public float onDamaged(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingDamageEvent evt) {
		Entity attacker = source.getTrueSource();
		if(!player.world.isRemote && attacker != null && attacker instanceof EntityLivingBase && random.nextFloat() < chance) {
			((EntityLivingBase) attacker).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 40, 1));
			if (!(attacker instanceof EntityPlayer))
				((EntityPlayer) attacker).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 1));
		}
		return newDamage;
	}
}
