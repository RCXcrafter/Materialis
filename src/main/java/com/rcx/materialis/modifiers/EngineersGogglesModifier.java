package com.rcx.materialis.modifiers;

import com.simibubi.create.content.contraptions.goggles.GoggleOverlayRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class EngineersGogglesModifier extends SingleUseModifier {

	public EngineersGogglesModifier() {
		super(0x825638);
		if (ModList.get().isLoaded("create"))
			GoggleOverlayRenderer.registerCustomGoggleCondition(this::wearingGoggledHelmet);
	}

	public boolean wearingGoggledHelmet() {
		Minecraft mc = Minecraft.getInstance();
		return ToolStack.from(mc.player.getItemBySlot(EquipmentSlotType.HEAD)).getModifierLevel(this) > 0;
	}
}