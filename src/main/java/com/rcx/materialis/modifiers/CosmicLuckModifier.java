package com.rcx.materialis.modifiers;

import java.util.function.BiConsumer;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.HarvestEnchantmentsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.LootingModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap.Builder;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class CosmicLuckModifier extends NoLevelsModifier implements LootingModifierHook, HarvestEnchantmentsModifierHook {

	@Override
	public int getPriority() {
		return 900; //cosmic modifier priority
	}

	@Override
	protected void registerHooks(Builder hookBuilder) {
		super.registerHooks(hookBuilder);
		hookBuilder.addHook(this, TinkerHooks.TOOL_LOOTING, TinkerHooks.LEGGINGS_LOOTING, TinkerHooks.PROJECTILE_LOOTING, TinkerHooks.TOOL_HARVEST_ENCHANTMENTS, TinkerHooks.LEGGINGS_HARVEST_ENCHANTMENTS);
	}

	@Override
	public void applyHarvestEnchantments(IToolStackView tool, ModifierEntry modifier, ToolHarvestContext context, BiConsumer<Enchantment, Integer> consumer) {
		consumer.accept(Enchantments.BLOCK_FORTUNE, 10);
	}

	@Override
	public int getLootingValue(IToolStackView tool, ModifierEntry modifier, LivingEntity holder, Entity target, DamageSource damageSource, int looting) {
		return looting + 10;
	}
}