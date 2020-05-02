package com.rcx.materialis.traits.armor;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import slimeknights.tconstruct.shared.TinkerCommons;

public class TraitArmorRenewableEnergy extends AbstractArmorTrait {

	public TraitArmorRenewableEnergy() {
		super("renewable_energy", 0xA52919);
	}

	@Override
	public float onDamaged(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingDamageEvent evt) {
		if (!player.world.isRemote && !source.isFireDamage() && !source.isMagicDamage() && random.nextFloat() < newDamage / 10.0F) {
			EntityItem entity = new EntityItem(player.world, player.posX, player.posY, player.posZ, TinkerCommons.matSlimeBallBlood.copy());
			player.world.spawnEntity(entity);
		}
		return newDamage;
	}
}
