package com.rcx.materialis.modifiers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.botania.api.mana.ManaItemHandler;

public class ManaburnerModifier extends Modifier {

	boolean enabled = ModList.get().isLoaded("botania");
	private static final int MANA_COST = 80;

	public ManaburnerModifier() {
		super(0xB8EDFF);
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (enabled && isEffective && !tool.isBroken() && event.getEntityLiving() instanceof PlayerEntity) {
			ItemStack toolStack = event.getEntityLiving().getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();

			if (ManaItemHandler.instance().requestManaExactForTool(toolStack, (PlayerEntity) event.getEntityLiving(), MANA_COST * level, false))
				event.setNewSpeed(event.getNewSpeed() + 2.5f * level);
		}
	}

	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
		if (enabled && context.isAOE() && context.isEffective() && !tool.isBroken() && context.getPlayer() != null) {
			ItemStack toolStack = context.getPlayer().getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();

			ManaItemHandler.instance().requestManaExactForTool(toolStack, context.getPlayer(), MANA_COST * level, true); //only eat the mana if the block is actually broken
		}
	}
}