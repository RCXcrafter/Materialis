package com.rcx.materialis.modifiers;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.KeybindInteractModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap.Builder;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.utils.RestrictedCompoundTag;

public class OtherworldGogglesModifier extends NoLevelsModifier implements KeybindInteractModifierHook {

	public static final String OTHERWORLD_GOGGLES = "occultism:otherworld_goggles";

	@Override
	protected void registerHooks(Builder hookBuilder) {
		hookBuilder.addHook(this, TinkerHooks.ARMOR_INTERACT);
	}

	@Override
	public void addRawData(IToolStackView tool, int level, RestrictedCompoundTag tag) {
		tag.putBoolean(OTHERWORLD_GOGGLES, true);
	}

	@Override
	public void beforeRemoved(IToolStackView tool, RestrictedCompoundTag tag) {
		tag.remove(OTHERWORLD_GOGGLES);
	}

	@Override
	public boolean startInteract(IToolStackView tool, ModifierEntry modifier, Player player, EquipmentSlot slot, TooltipKey keyModifier) {
		if (player.isShiftKeyDown() && tool instanceof ToolStack) {
			RestrictedCompoundTag tag = ((ToolStack) tool).getRestrictedNBT();
			tag.putBoolean(OTHERWORLD_GOGGLES, !tag.getBoolean(OTHERWORLD_GOGGLES));
			return true;
		}
		return false;
	}
}