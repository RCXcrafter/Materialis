package com.rcx.materialis.modifiers;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slimeknights.tconstruct.library.modifiers.impl.SingleUseModifier;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class EngineersGogglesModifier extends SingleUseModifier {

	@OnlyIn(Dist.CLIENT)
	public boolean wearingGoggledHelmet() {
		Minecraft mc = Minecraft.getInstance();
		return ToolStack.from(mc.player.getItemBySlot(EquipmentSlot.HEAD)).getModifierLevel(this) > 0;
	}
}