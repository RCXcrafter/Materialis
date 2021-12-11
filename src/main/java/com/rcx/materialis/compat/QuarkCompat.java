package com.rcx.materialis.compat;

import com.rcx.materialis.Materialis;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import slimeknights.tconstruct.library.tools.item.ModifiableArmorItem;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;

public class QuarkCompat {

	public static final ResourceLocation RUNED_CAPABILITY = new ResourceLocation(Materialis.modID, "runed_capability");

	public void attachCapabilities(final AttachCapabilitiesEvent<ItemStack> event) {
		ItemStack stack = event.getObject();
		if (stack.getItem() instanceof ModifiableItem || stack.getItem() instanceof ModifiableArmorItem) {
			event.addCapability(RUNED_CAPABILITY, new TinkerToolRuneColor(stack));
		}
	}
}
