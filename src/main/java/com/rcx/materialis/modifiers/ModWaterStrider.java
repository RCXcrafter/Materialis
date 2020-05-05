package com.rcx.materialis.modifiers;

import c4.conarm.lib.modifiers.ArmorModifierTrait;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ModWaterStrider extends ArmorModifierTrait {

	public ModWaterStrider() {
		super("water_strider", 0x009999, 1, 0);
	}

	@Override
	public void onArmorTick(ItemStack armor, World world, EntityPlayer player) {
		int x = MathHelper.floor(player.posX);
		int y = MathHelper.floor(player.posY - player.getYOffset());
		int z = MathHelper.floor(player.posZ);

		player.posY += -player.motionY;

		if (world.getBlockState(new BlockPos(x, y - 1, z)).getMaterial() == Material.WATER) {
			if (player.motionY < 0.0D && player.getEntityBoundingBox().minY < y) {
				player.motionY = 0.0D;
				player.jumpMovementFactor *= 0.4F;
				player.fallDistance = 0.0F;
				player.onGround = true;
			}
		}
	}

	@Override
	public boolean canApplyCustom(ItemStack stack) {
		return EntityLiving.getSlotForItemStack(stack) == EntityEquipmentSlot.FEET && super.canApplyCustom(stack);
	}
}
