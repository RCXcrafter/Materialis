package com.rcx.materialis.modifiers;

import net.minecraft.core.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class DaredevilModifier extends Modifier {

	@Override
	public void onBreakSpeed(IToolStackView tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		float multiplier = level * (event.getEntityLiving().getMaxHealth() - event.getEntityLiving().getHealth()) / event.getEntityLiving().getMaxHealth() + 1.0f;
		event.setNewSpeed(event.getNewSpeed() * multiplier);
	}
}