package com.rcx.materialis.modifiers;

import javax.annotation.Nullable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;

public class HalfLifeModifier extends Modifier {

	public HalfLifeModifier() {
		super(0xF16F17);
	}

	@Override
	public int onDamageTool(IModifierToolStack toolStack, int level, int amount, @Nullable LivingEntity holder) {
		float multiplier = (float) Math.pow(4.0f * toolStack.getCurrentDurability() / (toolStack.getCurrentDurability() + toolStack.getDamage()), 0.5f);
		float damage = (float) (amount * Math.pow(multiplier, level));
		int roundDamage = (int) damage;
		float extraChance = damage - roundDamage;

		extraChance = Math.max(extraChance, 0.01f); //at least a 1% chance of taking damage
		if (RANDOM.nextFloat() < extraChance)
			roundDamage++;

		return roundDamage;
	}

	@Override
	public ITextComponent getDisplayName(int level) {
		if (level > 2 && level < 5) {
			return applyStyle(new TranslationTextComponent(getTranslationKey() + "." + level));
		}
		if (level > 2)
			level -= 2;
		return applyStyle(new TranslationTextComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level)));
	}
}