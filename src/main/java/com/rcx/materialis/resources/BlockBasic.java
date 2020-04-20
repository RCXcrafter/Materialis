package com.rcx.materialis.resources;

import com.rcx.materialis.Materialis;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBasic extends Block {

	public BlockBasic(String name, Material material, float hardness) {
		super(material);
		setRegistryName(Materialis.ID, name);
		setUnlocalizedName(name);
		setHardness(hardness);
		setCreativeTab(Materialis.MatTab);
	}
}
