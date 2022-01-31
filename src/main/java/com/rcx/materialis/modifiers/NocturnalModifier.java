package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipFlag;

public class NocturnalModifier extends Modifier {

	public NocturnalModifier() {
		super(0x003CC9);
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		int time = (int) (event.getEntityLiving().level.getDayTime() % 24000l);
		if (time > 12000) {
			float bonus = (float) (-Math.sin(time * Math.PI / 12000.0f) * 2 * level);
			event.setNewSpeed(event.getNewSpeed() + bonus * tool.getModifier(ToolStats.MINING_SPEED));
		}
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, TooltipFlag flag) {
		addStatTooltip(tool, ToolStats.MINING_SPEED, TinkerTags.Items.HARVEST, 2 * level, tooltip);
	}
}