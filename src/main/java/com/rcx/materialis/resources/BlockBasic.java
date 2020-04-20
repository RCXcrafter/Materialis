package com.rcx.materialis.resources;

import javax.annotation.Nullable;

import com.rcx.materialis.Materialis;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBasic extends Block {

	public BlockBasic(String name, Material material, float hardness) {
		super(material);
		setRegistryName(Materialis.ID, name);
		setUnlocalizedName(name);
		setHardness(hardness);
		setCreativeTab(Materialis.MatTab);
	}
}
