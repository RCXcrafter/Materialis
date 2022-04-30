package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.helper.ModifierLootingHandler;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class VoidingModifier extends NoLevelsModifier {

	public VoidingModifier() {
		super();
		MinecraftForge.EVENT_BUS.addListener(this::beforeBlockBreak);
		MinecraftForge.EVENT_BUS.addListener(this::onExperienceDrop);
	}

	@Override
	public int getPriority() {
		return 93; //hopefully after other loot modifying modifiers, but before melting
	}

	@Override
	public List<ItemStack> processLoot(IToolStackView tool, int level, List<ItemStack> generatedLoot, LootContext context) {
		generatedLoot.clear();
		return generatedLoot;
	}

	public static int boostXP(int xp, int luck) {
		float modifier = 1 + RANDOM.nextFloat() * luck;
		return (int) ((xp + RANDOM.nextFloat()) * modifier);
	}

	private void beforeBlockBreak(BreakEvent event) {
		ToolStack tool = getHeldTool(event.getPlayer(), InteractionHand.MAIN_HAND);
		if (tool != null && tool.getModifierLevel(this) > 0) {
			event.setExpToDrop(boostXP(event.getExpToDrop(), EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, event.getPlayer().getMainHandItem())));
		}
	}

	private void onExperienceDrop(LivingExperienceDropEvent event) {
		ToolStack tool = getHeldTool(event.getAttackingPlayer(), ModifierLootingHandler.getLootingSlot(event.getAttackingPlayer()));
		if (tool != null && tool.getModifierLevel(this) > 0) {
			event.setDroppedExperience(boostXP(event.getDroppedExperience(), ModifierUtil.getLeggingsLootingLevel(event.getAttackingPlayer(), event.getEntity(), null, ModifierUtil.getLootingLevel(tool, event.getAttackingPlayer(), event.getEntity(), null))));
		}
	}
}