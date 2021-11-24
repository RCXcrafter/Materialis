package com.rcx.materialis.modifiers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class ArcaneModifier extends Modifier {

	public ArcaneModifier() {
		super(0xFFC069);
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		DamageSource source;
		PlayerEntity player = context.getPlayerAttacker();
		if (player != null) {
			source = DamageSource.playerAttack(player);
		} else {
			source = DamageSource.mobAttack(context.getAttacker());
		}
		ToolAttackUtil.attackEntitySecondary(source.setMagic().bypassArmor(), level * tool.getModifier(ToolStats.ATTACK_DAMAGE), context.getTarget(), context.getLivingTarget(), true);
		return 0;
	}
}