package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.fml.ModList;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap.Builder;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import vazkii.botania.api.mana.ManaItemHandler;

public class ManashotModifier extends Modifier implements ConditionalStatModifierHook, ProjectileLaunchModifierHook {

	boolean enabled = ModList.get().isLoaded("botania");
	private static final int MANA_COST = 80;

	@Override
	protected void registerHooks(Builder hookBuilder) {
		hookBuilder.addHook(this, TinkerHooks.CONDITIONAL_STAT, TinkerHooks.PROJECTILE_LAUNCH);
	}

	@Override
	public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
		if (enabled && stat == ToolStats.VELOCITY && !tool.isBroken() && living instanceof Player player) {
			ItemStack toolStack = player.getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();

			if (ManaItemHandler.instance().requestManaExactForTool(toolStack, player, MANA_COST * modifier.getLevel(), false))
				return baseValue + 0.1f * multiplier * modifier.getLevel();
		}
		return baseValue;
	}

	@Override
	public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, AbstractArrow arrow, NamespacedNBT persistentData, boolean primary) {
		if (enabled && primary && shooter instanceof Player player) {
			ItemStack toolStack = player.getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();

			ManaItemHandler.instance().requestManaExactForTool(toolStack, player, MANA_COST * modifier.getLevel(), true); //only eat the mana if the projectile is actually fired
		}
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		addStatTooltip(tool, ToolStats.VELOCITY, TinkerTags.Items.RANGED, 0.1f * level, tooltip);
	}
}