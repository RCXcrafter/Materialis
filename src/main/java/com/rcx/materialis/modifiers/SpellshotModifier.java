package com.rcx.materialis.modifiers;

import java.util.List;
import java.util.function.Predicate;

import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.hollingsworth.arsnouveau.api.spell.SpellContext;
import com.hollingsworth.arsnouveau.api.spell.SpellResolver;
import com.hollingsworth.arsnouveau.common.items.SpellArrow;
import com.hollingsworth.arsnouveau.common.spell.casters.ReactiveCaster;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.fml.ModList;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.BowAmmoModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap.Builder;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class SpellshotModifier extends NoLevelsModifier implements BowAmmoModifierHook, ProjectileLaunchModifierHook, ProjectileHitModifierHook {

	boolean enabled = ModList.get().isLoaded("ars_nouveau");

	@Override
	protected void registerHooks(Builder hookBuilder) {
		hookBuilder.addHook(this, TinkerHooks.BOW_AMMO, TinkerHooks.PROJECTILE_LAUNCH, TinkerHooks.PROJECTILE_HIT);
	}

	@Override
	public int getPriority() {
		return 60; // before bulk quiver, after crystalshot
	}

	@Override
	public ItemStack findAmmo(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, ItemStack standardAmmo, Predicate<ItemStack> ammoPredicate) {
		if (enabled && shooter instanceof Player player && tool instanceof ToolStack) {
			Predicate<ItemStack> predicate = i -> i.getItem() instanceof SpellArrow && canPlayerCastSpell(((ToolStack) tool).createStack(), player);
			ItemStack itemstack = ProjectileWeaponItem.getHeldProjectile(player, predicate);
			if (!itemstack.isEmpty()) {
				return itemstack;
			}
			for (int i = 0; i < player.getInventory().getContainerSize(); ++i) {
				itemstack = player.getInventory().getItem(i);
				if (predicate.test(itemstack)) {
					return itemstack;
				}
			}
			for (ModifierEntry entry : tool.getModifierList()) {
				if (entry == modifier)
					continue;
				itemstack = entry.getHook(TinkerHooks.BOW_AMMO).findAmmo(tool, entry, player, standardAmmo, predicate);
				if (!itemstack.isEmpty()) {
					return itemstack;
				}
			}
		}
		return ItemStack.EMPTY;
	}

	public boolean canPlayerCastSpell(ItemStack bow, Player playerentity){
		return new SpellResolver(new SpellContext(new ReactiveCaster(bow).getSpell(), playerentity)).withSilent(true).canCast(playerentity);
	}

	@Override
	public void shrinkAmmo(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, ItemStack ammo, int needed) {
		if (enabled && shooter instanceof Player player) {
			ItemStack toolStack = player.getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();

		}
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		if (enabled && tool instanceof ToolStack) {
			Spell spell = new ReactiveCaster(((ToolStack) tool).createStack()).getSpell();
			if (spell.isValid()) {
				tooltip.add(new TextComponent(spell.getDisplayString()));
			}
		}
	}

	@Override
	public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, AbstractArrow arrow, NamespacedNBT persistentData, boolean primary) {
		// TODO Auto-generated method stub

	}
}