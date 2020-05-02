package com.rcx.materialis.traits.armor;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class TraitArmorFireproof extends AbstractArmorTrait {

	public TraitArmorFireproof() {
		super("fireproof", 0xBF7C18);
	}

	@Override
	public float onHurt(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingHurtEvent evt) {
		if (source.isFireDamage()) {
			NBTTagCompound tag = TinkerUtil.getModifierTag(armor, identifier);
			ModifierNBT.IntegerNBT data = ModifierNBT.readInteger(tag);
			newDamage -= damage * 0.04F * data.level;
		}
		return newDamage;
	}

	@Override
	public int onArmorDamage(ItemStack armor, DamageSource source, int damage, int newDamage, EntityPlayer player, int slot) {
		if (source.isFireDamage())
			return 0;
		return newDamage;
	}
}
