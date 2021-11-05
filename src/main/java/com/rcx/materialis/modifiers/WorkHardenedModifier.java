package com.rcx.materialis.modifiers;

import javax.annotation.Nullable;

import net.minecraft.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class WorkHardenedModifier extends Modifier {

	public WorkHardenedModifier() {
		super(0xA7A88F);
	}

	@Override
	public int onDamageTool(IModifierToolStack toolStack, int level, int amount, @Nullable LivingEntity holder) {
		float total = (toolStack.getDamage() + toolStack.getCurrentDurability());
		float multiplier = (toolStack.getCurrentDurability() * level) / (total * 10.0f) - (level - 10.0f) / 10.0f;
		float damage = amount * multiplier;
		int roundDamage = (int) damage;
		float extraChance = damage - roundDamage;

		if (RANDOM.nextFloat() < extraChance)
			roundDamage++;

		return roundDamage;
	}
}