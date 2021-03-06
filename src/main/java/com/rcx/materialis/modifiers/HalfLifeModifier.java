package com.rcx.materialis.modifiers;

import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class HalfLifeModifier extends Modifier {

	public HalfLifeModifier() {
		super(0xF16F17);
	}

	@Override
	public int onDamageTool(IModifierToolStack toolStack, int level, int amount) {
		float half = (toolStack.getDamage() + toolStack.getCurrentDurability()) / 2;
		float multiplier = toolStack.getCurrentDurability() / half;
		float damage = (float) (amount * Math.pow(multiplier, level));
		int roundDamage = (int) damage;
		float extraChance = damage - roundDamage;

		extraChance = Math.max(extraChance, 0.005f); //at least a 0.5% chance of taking damage
		if (RANDOM.nextFloat() < extraChance)
			roundDamage++;

		return roundDamage;
	}

	@Override
	public ITextComponent getDisplayName(int level) {
		if (level > 2 && level < 5) {
			return new TranslationTextComponent(getTranslationKey() + "." + level) .withStyle(style -> style.withColor(Color.fromRgb(this.getColor())));
		}
		if (level > 2)
			level -= 2;
		return new TranslationTextComponent(getTranslationKey())
				.append(" ")
				.append(new TranslationTextComponent(KEY_LEVEL + level))
				.withStyle(style -> style.withColor(Color.fromRgb(this.getColor())));
	}
}