package com.rcx.materialis.modifiers;

import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class NocturnalModifier extends Modifier {

	public NocturnalModifier() {
		super(0x003CC9);
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		int time = (int) (event.getEntityLiving().level.getDayTime() % 24000l);
		if (time > 12000) {
			float bonus = (float) (-Math.sin(time * Math.PI / 12000.0f) * 2 * level);
			event.setNewSpeed(event.getNewSpeed() + bonus);
		}
	}
}