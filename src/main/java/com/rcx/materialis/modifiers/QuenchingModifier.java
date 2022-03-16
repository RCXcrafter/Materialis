package com.rcx.materialis.modifiers;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class QuenchingModifier extends Modifier {

	@Override
	public int getPriority() {
		return 85; //after modifiers that set mobs on fire and after refueling
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		if (context.getTarget().isAlive() && context.getTarget().isOnFire()) {
			float bonus = 0.0f;
			bonus = (context.getTarget().getRemainingFireTicks() * level) / 60.0f;
			context.getTarget().clearFire();

			DamageSource source;
			Player player = context.getPlayerAttacker();
			if (player != null) {
				source = DamageSource.playerAttack(player);
			} else {
				source = DamageSource.mobAttack(context.getAttacker());
			}
			ToolAttackUtil.attackEntitySecondary(source, bonus * tool.getMultiplier(ToolStats.ATTACK_DAMAGE), context.getTarget(), context.getLivingTarget(), true);
		}
		return 0;
	}
}