package com.rcx.materialis.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitCrystalline extends AbstractTrait {

	public static String id = "crystalline";
	public float modifier = 1.5F;

	public TraitCrystalline() {
		super(id, 0x97F1EB);
	}

	@Override
	public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
		if (!ToolHelper.getTraits(tool).contains(MaterialisTraits.unlimited))
			event.setNewSpeed(event.getOriginalSpeed() * modifier);
	}

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
		if (ToolHelper.getTraits(tool).contains(MaterialisTraits.unlimited))
			return newDamage;
		return newDamage * modifier;
	}
}
