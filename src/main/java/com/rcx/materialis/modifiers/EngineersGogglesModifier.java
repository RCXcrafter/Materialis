package com.rcx.materialis.modifiers;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.impl.SingleUseModifier;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class EngineersGogglesModifier extends SingleUseModifier {

	public boolean wearingGoggledHelmet(Player player) {
		return ToolStack.from(player.getItemBySlot(EquipmentSlot.HEAD)).getModifierLevel(this) > 0;
	}
}