package com.rcx.materialis;

import java.util.Random;

import slimeknights.tconstruct.library.recipe.tinkerstation.modifier.ModifierRecipeLookup;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class MaterialisUtil {
	/**
	 * Gets the looting level for the given modifier level and tool data
	 * @param tool     Tool instance
	 * @param level    Modifier level
	 * @param random   Random instance, for partial levels
	 * @return  Looting level
	 */
	public static int getEffectiveLuckLevel(IModifierToolStack tool, Random random) {
		// each level of the modifier is worth 3 levels of the enchant
		int applyLevel = tool.getModifierLevel(TinkerModifiers.luck.get()) * 3;
		int neededPerSlot = ModifierRecipeLookup.getNeededPerLevel(TinkerModifiers.luck.get());
		if (neededPerSlot > 0) {
			// if we just have a partial amount, every third is worth 1 level
			int neededPerLevel = neededPerSlot / 3;
			int amount = TinkerModifiers.luck.get().getAmount(tool);

			// 0 to 33%: lose 2 levels
			if (amount < neededPerLevel) {
				applyLevel -= 2;
			} else {
				// rescale to be from 0 to 67%
				amount -= neededPerLevel;
				// 33% to 66%: lose 1 level
				if (amount < neededPerLevel) {
					applyLevel--;
				} else {
					// 66% to 100%, further rescale amount
					amount -= neededPerLevel;
				}
			}

			// for the remainder, if we don't have a full level left decrease it
			if (amount < neededPerLevel && amount < random.nextInt(neededPerLevel)) {
				applyLevel--;
			}
		}
		return applyLevel;
	}
}
