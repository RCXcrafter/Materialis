package com.rcx.materialis.modifiers;

import java.util.function.BiConsumer;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.EquipmentSlotType.Group;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import vazkii.botania.common.core.handler.PixieHandler;

public class PixiecallerModifier extends Modifier {

	boolean enabled = ModList.get().isLoaded("botania");

	public PixiecallerModifier() {
		super(0xF15CAE);
	}

	@Override
	public void addAttributes(IModifierToolStack tool, int level, EquipmentSlotType slot, BiConsumer<Attribute,AttributeModifier> consumer) {
		if (enabled && slot.getType() == Group.HAND) {
			consumer.accept(PixieHandler.PIXIE_SPAWN_CHANCE, PixieHandler.makeModifier(slot, "Tool modifier", 0.03 * level));
		}
	}
}