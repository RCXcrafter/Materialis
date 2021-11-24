package com.rcx.materialis.modifiers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class QuenchingModifier extends Modifier {

	public QuenchingModifier() {
		super(0xBAE6D5);
	}

	@Override
	public int getPriority() {
		return 85; //after modifiers that set mobs on fire and after refueling
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (context.getTarget().isAlive() && context.getTarget().isOnFire()) {
			float bonus = 0.0f;
			bonus = (context.getTarget().getRemainingFireTicks() * level) / 60.0f;
			context.getTarget().clearFire();

			DamageSource source;
			PlayerEntity player = context.getPlayerAttacker();
			if (player != null) {
				source = DamageSource.playerAttack(player);
			} else {
				source = DamageSource.mobAttack(context.getAttacker());
			}
			ToolAttackUtil.attackEntitySecondary(source, bonus * tool.getModifier(ToolStats.ATTACK_DAMAGE), context.getTarget(), context.getLivingTarget(), true);
		}
		return 0;
	}
}