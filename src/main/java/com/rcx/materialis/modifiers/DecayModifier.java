package com.rcx.materialis.modifiers;

import javax.annotation.Nullable;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class DecayModifier extends Modifier {

	int CHANCE_BOUND = 100;

	@Override
	public int getPriority() {
		return 125; //after overslime, but before reinforced
	}

	@Override
	public void onInventoryTick(IToolStackView tool, int level, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
		if (!tool.isBroken() && holder.tickCount % 20 == 0 && RANDOM.nextInt(CHANCE_BOUND) <= 1 + level) {
			tool.setDamage(tool.getDamage() + 1);
		}
	}

	@Override
	public int onDamageTool(IToolStackView toolStack, int level, int amount, @Nullable LivingEntity holder) {
		return 0;
	}
}