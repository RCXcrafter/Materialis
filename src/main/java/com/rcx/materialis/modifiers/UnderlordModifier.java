package com.rcx.materialis.modifiers;

import net.minecraft.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class UnderlordModifier extends Modifier {

	public UnderlordModifier() {
		super(0x6CD7AA);
	}

	@Override
	public float applyLivingDamage(IModifierToolStack tool, int level, LivingEntity attacker, LivingEntity target, float baseDamage, float damage, boolean isCritical, boolean fullyCharged) {
		if (target.getType().getRegistryName().getNamespace().equals("undergarden") && target.canChangeDimensions())
			return super.applyLivingDamage(tool, level, attacker, target, baseDamage, damage, isCritical, fullyCharged) * (1.0f + 0.5f * level);
		return super.applyLivingDamage(tool, level, attacker, target, baseDamage, damage, isCritical, fullyCharged);
	}
}