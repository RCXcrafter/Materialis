package com.rcx.materialis.traits.armor;

import com.rcx.materialis.traits.TraitShortFuse;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class TraitArmorShortFuse extends AbstractArmorTrait {

	public static float chance = 0.3f;

	public TraitArmorShortFuse() {
		super(TraitShortFuse.id, 0x416B4F);
	}

	@Override
	public float onDamaged(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingDamageEvent evt) {
		Entity attacker = source.getTrueSource();
		if(!player.world.isRemote && !source.isProjectile() && attacker != null && random.nextFloat() < chance) {
			player.world.createExplosion(player, attacker.posX, attacker.posY, attacker.posZ, 1.75f, false);
		}
		return newDamage;
	}
}
