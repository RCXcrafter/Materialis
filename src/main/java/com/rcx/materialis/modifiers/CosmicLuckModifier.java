package com.rcx.materialis.modifiers;

import java.util.function.BiConsumer;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import slimeknights.tconstruct.library.modifiers.hooks.IArmorLootModifier;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class CosmicLuckModifier extends NoLevelsModifier implements IArmorLootModifier {

	@Override
	public int getPriority() {
		return 900; //cosmic modifier priority
	}

	@Override
	public int getLootingValue(IToolStackView tool, int level, LivingEntity holder, Entity target, @org.jetbrains.annotations.Nullable DamageSource damageSource, int looting) {
		return looting + 10;
	}

	@Override
	public void applyHarvestEnchantments(IToolStackView tool, int level, ToolHarvestContext context, BiConsumer<Enchantment, Integer> consumer) {
		consumer.accept(Enchantments.BLOCK_FORTUNE, 10);
	}
}