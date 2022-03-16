package com.rcx.materialis.modifiers;

import javax.annotation.Nullable;

import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class BrittleModifier extends Modifier {

	@Override
	public int getPriority() {
		return 165; //before overslime
	}

	@Override
	public int onDamageTool(IToolStackView toolStack, int level, int amount, @Nullable LivingEntity holder) {
		return (int) (amount * Math.pow(2, level));
	}
}