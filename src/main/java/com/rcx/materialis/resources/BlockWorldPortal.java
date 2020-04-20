package com.rcx.materialis.resources;

import javax.annotation.Nullable;

import com.rcx.materialis.Materialis;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockWorldPortal extends Block {

	public BlockWorldPortal() {
		super(Material.IRON);
		setRegistryName(Materialis.ID, "world_portal");
		setUnlocalizedName("world_portal");
		setHardness(1.2F);
		setCreativeTab(CreativeTabs.REDSTONE);
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
}
