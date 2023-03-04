package com.rcx.materialis.item;

import morph.avaritia.entity.InfinityArrowEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HeavenShotItem extends ArrowItem {

	public HeavenShotItem(Properties prop) {
		super(prop);
	}

	@Override
	public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
		return AvaritiaStuff.getArrow(pLevel, pStack, pShooter);
	}

	@Override
	public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
		return true;
	}

	@Override
	public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {}

	static class AvaritiaStuff {
		static AbstractArrow getArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
			return new InfinityArrowEntity(pLevel, pShooter);
		}
	}
}
