package com.rcx.materialis.modifiers;

import javax.annotation.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;

public class HalfLifeModifier extends Modifier {

	@Override
	public int onDamageTool(IToolStackView toolStack, int level, int amount, @Nullable LivingEntity holder) {
		float multiplier = (float) Math.pow(4.0f * toolStack.getCurrentDurability() / (toolStack.getCurrentDurability() + toolStack.getDamage()), 0.5f);
		float damage = (float) (amount * Math.pow(multiplier * level, level));
		int roundDamage = (int) damage;
		float extraChance = damage - roundDamage;

		extraChance = Math.max(extraChance, 0.01f); //at least a 1% chance of taking damage
		if (RANDOM.nextFloat() < extraChance)
			roundDamage++;

		return roundDamage;
	}

	@Override
	public Component getDisplayName(int level) {
		if (level > 2 && level < 5) {
			return applyStyle(new TextComponent(getTranslationKey() + "." + level));
		}
		if (level > 2)
			level -= 2;
		return applyStyle(new TextComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level)));
	}
}