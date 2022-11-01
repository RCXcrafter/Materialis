package com.rcx.materialis.modifiers;

import net.minecraft.core.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class InstamineModifier extends NoLevelsModifier {

	@Override
	public int getPriority() {
		return 902; //cosmic modifier priority
	}

	@Override
	public void onBreakSpeed(IToolStackView tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		event.setNewSpeed(Float.MAX_VALUE);
	}
}