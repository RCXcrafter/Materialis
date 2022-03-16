package com.rcx.materialis.modifiers;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntityType;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class CleansingModifier extends Modifier {

	public static Tag<EntityType<?>> rotspawnTag = EntityTypeTags.createOptional(new ResourceLocation("undergarden", "rotspawn"));

	@Override
	public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (context.getTarget().getType().is(rotspawnTag))
			return damage * (1.0f + 0.25f * level);
		return damage;
	}
}