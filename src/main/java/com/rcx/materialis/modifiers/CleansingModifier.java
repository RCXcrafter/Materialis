package com.rcx.materialis.modifiers;

import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.helper.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class CleansingModifier extends Modifier {

	ITag.INamedTag<EntityType<?>> rotspawnTag = EntityTypeTags.createOptional(new ResourceLocation("undergarden", "rotspawn"));

	public CleansingModifier() {
		super(0xEB515B);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (context.getTarget().getType().is(rotspawnTag))
			return damage * (1.0f + 0.25f * level);
		return damage;
	}
}