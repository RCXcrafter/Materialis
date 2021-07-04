package com.rcx.materialis.modifiers;

import com.rcx.materialis.MaterialisResources;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BlockStateProperties;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.helper.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class ResidualLightModifier extends SingleUseModifier {

	public ResidualLightModifier() {
		super(0xFFFFFF);
	}

	@Override
	public int getPriority() {
		return Short.MIN_VALUE - 30; //after exchanging
	}

	@Override
	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
		if (context.getWorld().getBlockState(context.getPos()).isAir(context.getWorld(), context.getPos()))
			context.getWorld().setBlock(context.getPos(), MaterialisResources.LIGHT_RESIDUE.get().defaultBlockState(), 3 | 64);
		else if (context.getWorld().getBlockState(context.getPos()).equals(Blocks.WATER.defaultBlockState()))
			context.getWorld().setBlock(context.getPos(), MaterialisResources.LIGHT_RESIDUE.get().defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true), 3 | 64);
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		LivingEntity target = context.getLivingTarget();
		if (target != null && context.isFullyCharged() && target.isAlive()) {
			target.addEffect(new EffectInstance(Effects.GLOWING, 400, 0, false, true));
		}
		return 0;
	}
}