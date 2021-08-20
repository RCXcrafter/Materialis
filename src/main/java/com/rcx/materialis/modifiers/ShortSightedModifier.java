package com.rcx.materialis.modifiers;

import java.util.UUID;
import java.util.function.BiConsumer;

import com.rcx.materialis.Materialis;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.EquipmentSlotType.Group;
import net.minecraftforge.common.ForgeMod;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class ShortSightedModifier extends Modifier {

	private static final UUID MAINHAND_ATTRIBUTE_UUID = UUID.fromString("f8a6e738-642b-11eb-ae93-0242ac136002");
	private static final UUID OFFHAND_ATTRIBUTE_UUID = UUID.fromString("9720e9f3-c123-4b0b-bdb2-b4bc52eb39c9");
	private static final String ATTRIBUTE_NAME = Materialis.modID + ".short_sighted";

	public ShortSightedModifier() {
		super(0xB78FD2);
	}

	@Override
	public void addAttributes(IModifierToolStack tool, int level, EquipmentSlotType slot, BiConsumer<Attribute,AttributeModifier> consumer) {
		if (slot.getType() == Group.HAND) {
			consumer.accept(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(slot == EquipmentSlotType.OFFHAND ? OFFHAND_ATTRIBUTE_UUID : MAINHAND_ATTRIBUTE_UUID, ATTRIBUTE_NAME, -0.2 * level, Operation.MULTIPLY_TOTAL));
		}
	}
}