package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class RefuelingModifier extends Modifier {

	@Override
	public int getPriority() {
		return 90; //after modifiers that set mobs on fire
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		if (context.getTarget().isAlive() && context.getTarget().isOnFire()) {
			context.getTarget().setRemainingFireTicks(context.getTarget().getRemainingFireTicks() + 10 * level);
		}
		return 0;
	}
}