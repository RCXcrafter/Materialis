package com.rcx.materialis.modifiers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.core.handler.PlayerDataHandler.PlayerData;

public class PsichoKillerModifier extends Modifier {

	boolean enabled = ModList.get().isLoaded("psi");

	public PsichoKillerModifier() {
		super(0x1B1515);
	}

	@Override
	public float applyLivingDamage(IModifierToolStack tool, int level, LivingEntity attacker, LivingEntity target, float baseDamage, float damage, boolean isCritical, boolean fullyCharged) {
		if (enabled && !tool.isBroken() && attacker instanceof PlayerEntity) {
			PlayerData data = PlayerDataHandler.get((PlayerEntity) attacker);
			return super.applyLivingDamage(tool, level, attacker, target, baseDamage, damage, isCritical, fullyCharged) + level * data.getAvailablePsi() * 0.5f / ((float) data.getTotalPsi());
		}
		return super.applyLivingDamage(tool, level, attacker, target, baseDamage, damage, isCritical, fullyCharged);
	}
}