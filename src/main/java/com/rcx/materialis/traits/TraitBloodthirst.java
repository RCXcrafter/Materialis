package com.rcx.materialis.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitBloodthirst extends AbstractTrait {

	public TraitBloodthirst() {
		super("bloodthirst", 0xA52919);
	}

	public void onPlayerHurt(ItemStack tool, EntityPlayer player, EntityLivingBase attacker, LivingHurtEvent event) {
		if (!event.getSource().isFireDamage() && !event.getSource().isMagicDamage())
			ToolHelper.healTool(tool, (int) event.getAmount(), player);
	}
}
