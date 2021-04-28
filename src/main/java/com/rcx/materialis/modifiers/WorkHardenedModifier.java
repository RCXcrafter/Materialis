package com.rcx.materialis.modifiers;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class WorkHardenedModifier extends Modifier {

	public WorkHardenedModifier() {
		super(0xA7A88F);
	}

	@Override
	public int onDamageTool(IModifierToolStack toolStack, int level, int amount) {
		float total = (toolStack.getDamage() + toolStack.getCurrentDurability());
		float multiplier = toolStack.getCurrentDurability() / total;
		float damage = (float) (amount * Math.pow(multiplier, (level + 1) / 3.0));
		int roundDamage = (int) damage;
		float extraChance = damage - roundDamage;

		extraChance = Math.max(extraChance, 0.01f); //at least a 1% chance of taking damage
		if (RANDOM.nextFloat() < extraChance)
			roundDamage++;

		return roundDamage;
	}
}