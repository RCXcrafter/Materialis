package com.rcx.materialis.modifiers;

import com.rcx.materialis.MaterialisResources;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class ResidualLightModifier extends SingleUseModifier {

	public ResidualLightModifier() {
		super(0xFFFFFF);
	}

	@Override
	public void afterBlockBreak(IModifierToolStack tool, int level, World world, BlockState state, BlockPos pos, LivingEntity living, boolean canharvest, boolean wasEffective) {
		if (world.getBlockState(pos).isAir(world, pos))
			world.setBlock(pos, MaterialisResources.LIGHT_RESIDUE.get().defaultBlockState(), 3 | 64);
	}

	@Override
	public int afterLivingHit(IModifierToolStack tool, int level, LivingEntity attacker, LivingEntity target, float damageDealt, boolean isCritical, float cooldown) {
		if (target.isAlive()) {
			// set entity so the potion is attributed as a player kill
			target.setLastHurtByMob(attacker);
			target.addEffect(new EffectInstance(Effects.GLOWING, 400, 0, false, true));
		}
		return 0;
	}
}