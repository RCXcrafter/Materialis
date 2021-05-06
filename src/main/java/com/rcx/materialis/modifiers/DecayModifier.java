package com.rcx.materialis.modifiers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class DecayModifier extends Modifier {

	int CHANCE_BOUND = 100;

	public DecayModifier() {
		super(0xA9C2C4);
	}

	@Override
	public void onInventoryTick(IModifierToolStack tool, int level, World world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
		if (!tool.isBroken() && holder.tickCount % 20 == 0 && RANDOM.nextInt(CHANCE_BOUND) <= 1 + level) {
			tool.setDamage(tool.getDamage() + 1);
		}
	}

	@Override
	public int onDamageTool(IModifierToolStack toolStack, int level, int amount) {
		return 0;
	}
}