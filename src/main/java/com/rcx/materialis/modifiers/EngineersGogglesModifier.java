package com.rcx.materialis.modifiers;

import com.simibubi.create.content.contraptions.goggles.GogglesItem;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class EngineersGogglesModifier extends NoLevelsModifier {

	public EngineersGogglesModifier () {
		if (ModList.get().isLoaded("create"))
			GogglesItem.addIsWearingPredicate(this::wearingGoggledHelmet);
	}

	public boolean wearingGoggledHelmet(Player player) {
		return ToolStack.from(player.getItemBySlot(EquipmentSlot.HEAD)).getModifierLevel(this) > 0;
	}
}