package com.rcx.materialis.block;

import java.util.Random;

import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class LightResidueBlock extends AirBlock {

	public LightResidueBlock(Properties prop) {
		super(prop);
	}

	protected boolean isAir(BlockState state) {
		return true;
	}

	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		world.removeBlock(pos, false);
	}
}
