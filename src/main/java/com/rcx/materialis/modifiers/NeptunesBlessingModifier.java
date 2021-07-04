package com.rcx.materialis.modifiers;

import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.helper.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class NeptunesBlessingModifier extends SingleUseModifier {

	public NeptunesBlessingModifier() {
		super(0x17F1B6);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (context.getTarget().isEyeInFluid(FluidTags.WATER))
			return damage * 1.5f;
		return damage;
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (isEffective && event.getEntityLiving().isEyeInFluid(FluidTags.WATER))
			event.setNewSpeed(event.getNewSpeed() * 5.0f);
	}
}