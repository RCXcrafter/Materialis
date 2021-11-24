package com.rcx.materialis.modifiers;

import javax.annotation.Nullable;

import com.rcx.materialis.util.TinkerToolFluxed;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class PowerHungryModifier extends CapacitorModifier {

	private static final int ENERGY_COST = 100;

	public PowerHungryModifier() {
		super(0xB51212);
	}

	@Override
	public int getPriority() {
		return 160; //before overslime >:)
	}

	@Override
	public int onDamageTool(IModifierToolStack tool, int level, int amount, @Nullable LivingEntity holder) {
		TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, false, true);
		return amount;
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, true, false)) {
			return damage;
		}
		return damage / 1.5f;
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (!TinkerToolFluxed.removeEnergy(tool, ENERGY_COST * level, true, false)) {
			event.setNewSpeed(event.getNewSpeed() / 1.5f);
		}
	}

	@Override
	public int getCapacity() {
		return 10000;
	}
}