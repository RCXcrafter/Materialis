package com.rcx.materialis.modifiers;

import java.util.UUID;
import java.util.function.BiConsumer;

import com.rcx.materialis.Materialis;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlot.Type;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraftforge.common.ForgeMod;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ShortSightedModifier extends Modifier {

	private static final UUID MAINHAND_ATTRIBUTE_UUID = UUID.fromString("f8a6e738-642b-11eb-ae93-0242ac136002");
	private static final UUID OFFHAND_ATTRIBUTE_UUID = UUID.fromString("9720e9f3-c123-4b0b-bdb2-b4bc52eb39c9");
	private static final String ATTRIBUTE_NAME = Materialis.modID + ".short_sighted";

	@Override
	public void addAttributes(IToolStackView tool, int level, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
		if (slot.getType() == Type.HAND) {
			consumer.accept(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(slot == EquipmentSlot.OFFHAND ? OFFHAND_ATTRIBUTE_UUID : MAINHAND_ATTRIBUTE_UUID, ATTRIBUTE_NAME, -0.2 * level, Operation.MULTIPLY_TOTAL));
		}
	}
}