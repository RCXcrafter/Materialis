package com.rcx.materialis.modifiers;

import java.util.function.BiConsumer;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlot.Type;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import vazkii.botania.common.handler.PixieHandler;

public class PixiecallerModifier extends Modifier {

	boolean enabled = ModList.get().isLoaded("botania");

	@Override
	public void addAttributes(IToolStackView tool, int level, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
		if (enabled && slot.getType() == Type.HAND) {
			consumer.accept(PixieHandler.PIXIE_SPAWN_CHANCE, PixieHandler.makeModifier(slot, "Tool modifier", 0.03 * level));
		}
	}
}