package com.rcx.materialis.modifiers;

import java.util.function.Predicate;

import com.rcx.materialis.MaterialisResources;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.BowAmmoModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap.Builder;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class HeavenshotModifier extends NoLevelsModifier implements BowAmmoModifierHook {

	boolean enabled = ModList.get().isLoaded("avaritia");

	@Override
	protected void registerHooks(Builder hookBuilder) {
		hookBuilder.addHook(this, TinkerHooks.BOW_AMMO);
	}

	@Override
	public int getPriority() {
		return 60; // before bulk quiver, after
	}

	@Override
	public ItemStack findAmmo(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, ItemStack standardAmmo, Predicate<ItemStack> ammoPredicate) {
		if (enabled) {
			return new ItemStack(MaterialisResources.HEAVENSHOT.get(), 64);
		}
		return ItemStack.EMPTY;
	}

	@Override
	public void shrinkAmmo(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, ItemStack ammo, int needed) {}
}