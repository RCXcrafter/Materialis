package com.rcx.materialis.modifiers;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class EngineersGogglesModifier extends SingleUseModifier {

	public EngineersGogglesModifier() {
		super(0x825638);
	}

	@OnlyIn(Dist.CLIENT)
	public boolean wearingGoggledHelmet() {
		Minecraft mc = Minecraft.getInstance();
		return ToolStack.from(mc.player.getItemBySlot(EquipmentSlotType.HEAD)).getModifierLevel(this) > 0;
	}
}