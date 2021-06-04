package com.rcx.materialis.modifiers;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class CleansingModifier extends Modifier {

	ITag.INamedTag<EntityType<?>> rotspawnTag = EntityTypeTags.createOptional(new ResourceLocation("undergarden", "rotspawn"));

	public CleansingModifier() {
		super(0xEB515B);
	}

	@Override
	public float applyLivingDamage(IModifierToolStack tool, int level, LivingEntity attacker, LivingEntity target, float baseDamage, float damage, boolean isCritical, boolean fullyCharged) {
		if (target.getType().is(rotspawnTag))
			return super.applyLivingDamage(tool, level, attacker, target, baseDamage, damage, isCritical, fullyCharged) * (1.0f + 0.25f * level);
		return super.applyLivingDamage(tool, level, attacker, target, baseDamage, damage, isCritical, fullyCharged);
	}
}