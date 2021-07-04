package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.helper.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class RefuelingModifier extends Modifier {

	public RefuelingModifier() {
		super(0xF9EA98);
	}

	@Override
	public int getPriority() {
		return 90; //after modifiers that set mobs on fire
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (context.getTarget().isAlive() && context.getTarget().isOnFire()) {
			context.getTarget().setRemainingFireTicks(context.getTarget().getRemainingFireTicks() + 10 * level);
		}
		return 0;
	}
}