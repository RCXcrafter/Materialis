package com.rcx.materialis.modifiers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class NeptunesBlessingModifier extends SingleUseModifier {

	public NeptunesBlessingModifier() {
		super(0x17F1B6);
	}

	@Override
	public float applyLivingDamage(IModifierToolStack tool, int level, LivingEntity attacker, LivingEntity target, float baseDamage, float damage, boolean isCritical, boolean fullyCharged) {
		if (target.isEyeInFluid(FluidTags.WATER))
			return super.applyLivingDamage(tool, level, attacker, target, baseDamage, damage, isCritical, fullyCharged) * 1.5f;
		return super.applyLivingDamage(tool, level, attacker, target, baseDamage, damage, isCritical, fullyCharged);
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (event.getEntityLiving().isEyeInFluid(FluidTags.WATER))
			event.setNewSpeed(event.getNewSpeed() * 5.0f);
	}
}