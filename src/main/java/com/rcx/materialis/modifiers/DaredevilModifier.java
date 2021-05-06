package com.rcx.materialis.modifiers;

import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class DaredevilModifier extends SingleUseModifier {

	public DaredevilModifier() {
		super(0x17F1B6);
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		float multiplier = (event.getEntityLiving().getMaxHealth() - event.getEntityLiving().getHealth()) / event.getEntityLiving().getMaxHealth() + 1.0f;
		event.setNewSpeed(event.getNewSpeed() * multiplier);
	}
}