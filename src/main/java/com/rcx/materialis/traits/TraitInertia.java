package com.rcx.materialis.traits;

import java.util.UUID;

import javax.annotation.Nonnull;

import com.google.common.collect.Multimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitInertia extends AbstractTrait {

	protected static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

	public float modifier = 1.5F;

	public TraitInertia() {
		super("inertia", 0xFFFFFF);
	}

	@Override
	public void getAttributeModifiers(@Nonnull EntityEquipmentSlot slot, ItemStack stack, Multimap<String, AttributeModifier> attributeMap) {
		if(slot == EntityEquipmentSlot.MAINHAND || slot == EntityEquipmentSlot.OFFHAND) {
			//AttributeModifier modifier = attributeMap.get(SharedMonsterAttributes.ATTACK_SPEED.getName()).re;
			attributeMap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double) -4.0F, 0));
		}
	}
}
