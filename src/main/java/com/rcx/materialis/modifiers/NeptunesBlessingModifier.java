package com.rcx.materialis.modifiers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class NeptunesBlessingModifier extends SingleUseModifier {

	public NeptunesBlessingModifier() {
		super(0x17F1B6);
	}

	@Override
	public int getPriority() {
		return 130; //before hydraulic
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
			event.setNewSpeed(event.getNewSpeed() * negateWaterMiningModifier(event.getEntityLiving(), tool.getModifierLevel(TinkerModifiers.airborne.get()) == 0));
	}

	public static float negateWaterMiningModifier(LivingEntity entity, boolean negateSwimming) {
		float modifier = 1.0f;
		if (entity.isEyeInFluid(FluidTags.WATER)) {
			if (!ModifierUtil.hasAquaAffinity(entity))
				modifier *= 5.0F;

			if (!entity.isOnGround() && negateSwimming)
				modifier *= 5.0F;
		}
		return modifier;
	}
}