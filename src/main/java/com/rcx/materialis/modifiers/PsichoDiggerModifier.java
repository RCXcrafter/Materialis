package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipFlag;
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
			event.setNewSpeed(event.getNewSpeed() + level * tool.getModifier(ToolStats.MINING_SPEED) * data.getAvailablePsi() / ((float) data.getTotalPsi()));
		}
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, TooltipFlag flag) {
		addStatTooltip(tool, ToolStats.MINING_SPEED, TinkerTags.Items.HARVEST, level, tooltip);
	}
}
