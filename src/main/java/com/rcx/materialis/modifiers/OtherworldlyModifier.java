package com.rcx.materialis.modifiers;

import com.rcx.materialis.Materialis;

import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RestrictedCompoundTag;

public class OtherworldlyModifier extends Modifier {

	public final int tier;
	public static final String OTHERWORLD_TIER = "occultism:otherworldToolTier";

	public OtherworldlyModifier(int tier) {
		this.tier = tier;
	}

	@Override
	public void addRawData(IToolStackView tool, int level, RestrictedCompoundTag tag) {
		if (tag.contains(OTHERWORLD_TIER, Tag.TAG_INT)) {
			tag.putInt(OTHERWORLD_TIER, Math.max(tag.getInt(OTHERWORLD_TIER), tier));
		} else {
			tag.putInt(OTHERWORLD_TIER, tier);
		} 
	}

	@Override
	public void beforeRemoved(IToolStackView tool, RestrictedCompoundTag tag) {
		tag.remove(OTHERWORLD_TIER);
	}

	@Override
	public Component getDisplayName(int level) {
		return super.getDisplayName(tier);
	}

	@Override
	protected String makeTranslationKey() {
		return "modifier." + Materialis.modID + ".otherworldly";
	}
}