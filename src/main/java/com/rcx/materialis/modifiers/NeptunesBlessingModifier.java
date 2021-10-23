package com.rcx.materialis.modifiers;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class NeptunesBlessingModifier extends SingleUseModifier {

	public NeptunesBlessingModifier() {
		super(0x17F1B6);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (context.getTarget().isEyeInFluid(FluidTags.WATER))
			return damage * 1.25f;
		return damage;
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (isEffective)
			event.setNewSpeed(event.getNewSpeed() * negateWaterMiningModifier(event.getEntityLiving()));
	}

	public static float negateWaterMiningModifier(LivingEntity entity) {
		float modifier = 1.0f;
		if (entity.isEyeInFluid(FluidTags.WATER)) {
			if (!EnchantmentHelper.hasAquaAffinity(entity))
				modifier *= 5.0F;

			if (!entity.isOnGround())
				modifier *= 5.0F;
		}
		return modifier;
	}
}