package com.rcx.materialis.modifiers;

import javax.annotation.Nullable;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.hooks.IArmorInteractModifier;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.utils.RestrictedCompoundTag;

public class OtherworldGogglesModifier extends NoLevelsModifier implements IArmorInteractModifier {

	public static final String OTHERWORLD_GOGGLES = "occultism:otherworld_goggles";

	@Override
	public void addRawData(IToolStackView tool, int level, RestrictedCompoundTag tag) {
		tag.putBoolean(OTHERWORLD_GOGGLES, true);
	}

	@Override
	public void beforeRemoved(IToolStackView tool, RestrictedCompoundTag tag) {
		tag.remove(OTHERWORLD_GOGGLES);
	}

	@Override
	public boolean startArmorInteract(IToolStackView tool, int level, Player player, EquipmentSlot slot) {
		if (player.isShiftKeyDown() && tool instanceof ToolStack) {
			RestrictedCompoundTag tag = ((ToolStack) tool).getRestrictedNBT();
			tag.putBoolean(OTHERWORLD_GOGGLES, !tag.getBoolean(OTHERWORLD_GOGGLES));
			return true;
		}
		return false;
	}

	@Nullable
	@Override
	public <T> T getModule(Class<T> type) {
		return tryModuleMatch(type, IArmorInteractModifier.class, this);
	}
}