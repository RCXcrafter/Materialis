package com.rcx.materialis.modifiers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.core.handler.PlayerDataHandler.PlayerData;

public class PsichoDiggerModifier extends Modifier {

	boolean enabled = ModList.get().isLoaded("psi");

	public PsichoDiggerModifier() {
		super(0xF6F9ED);
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (enabled && !tool.isBroken() && event.getEntityLiving() instanceof PlayerEntity) {
			PlayerData data = PlayerDataHandler.get((PlayerEntity) event.getEntityLiving());
			event.setNewSpeed(event.getNewSpeed() + level * data.getAvailablePsi() / ((float) data.getTotalPsi()));
		}
	}
}