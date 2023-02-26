package com.rcx.materialis.item;

import com.rcx.materialis.MaterialisResources;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class ManaShotItem extends ArrowItem {

	public ManaShotItem(Properties prop) {
		super(prop);
	}

	@Override
	public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
		return new ManashotEntity(pLevel, pShooter);
	}

	@Override
	public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
		return EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow) > 0;
	}

	@Override
	public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {}

	public static class ManashotEntity extends AbstractArrow {

		public ManashotEntity(EntityType<? extends ManashotEntity> type, Level level) {
			super(type, level);
			pickup = Pickup.CREATIVE_ONLY;
		}

		public ManashotEntity(Level level, LivingEntity shooter) {
			super(MaterialisResources.MANASHOT_ENTITY.get(), shooter, level);
			pickup = Pickup.CREATIVE_ONLY;
		}

		@Override
		public ItemStack getPickupItem() {
			return new ItemStack(MaterialisResources.MANASHOT.get());
		}
	}
}
