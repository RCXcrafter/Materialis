package com.rcx.materialis.modifiers;

import javax.annotation.Nullable;

import net.minecraft.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class BrittleModifier extends Modifier {

	public BrittleModifier() {
		super(0xA1FFFF);
	}

	@Override
	public int getPriority() {
		return 165; //before overslime
	}

	@Override
	public int onDamageTool(IModifierToolStack toolStack, int level, int amount, @Nullable LivingEntity holder) {
		return (int) (amount * Math.pow(2, level));
	}
}