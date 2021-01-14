package com.rcx.materialis.traits;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitShortFuse extends AbstractTrait {

	public static String id = "short_fuse";
	public static float chance = 0.3f;

	public TraitShortFuse() {
		super(id, 0x416B4F);
	}

	@Override
	public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
		if(!world.isRemote && random.nextFloat() < chance) {
			world.createExplosion(player, pos.getX(), pos.getY(), pos.getZ(), 1.75f, true);
		}
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
		if(wasHit && !player.world.isRemote && random.nextFloat() < chance) {
			player.world.createExplosion(player, target.posX, target.posY, target.posZ, 1.75f, false);
		}
	}
}
