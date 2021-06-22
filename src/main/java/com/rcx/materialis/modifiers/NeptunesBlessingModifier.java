package com.rcx.materialis.modifiers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class NeptunesBlessingModifier extends SingleUseModifier {

	public NeptunesBlessingModifier() {
		super(0x17F1B6);
	}

	@Override
	public float applyLivingDamage(IModifierToolStack tool, int level, LivingEntity attacker, Hand hand, LivingEntity target, float baseDamage, float damage, boolean isCritical, boolean fullyCharged, boolean isExtraAttack) {
		if (target.isEyeInFluid(FluidTags.WATER))
			return super.applyLivingDamage(tool, level, attacker, hand, target, baseDamage, damage, isCritical, fullyCharged, isExtraAttack) * 1.5f;
		return super.applyLivingDamage(tool, level, attacker, hand, target, baseDamage, damage, isCritical, fullyCharged, isExtraAttack);
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (isEffective && event.getEntityLiving().isEyeInFluid(FluidTags.WATER))
			event.setNewSpeed(event.getNewSpeed() * 5.0f);
	}
}