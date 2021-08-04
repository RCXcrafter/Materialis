package com.rcx.materialis.modifiers;

import com.rcx.materialis.Materialis;

import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.Constants.NBT;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.utils.RestrictedCompoundTag;

public class OtherworldlyModifier extends Modifier {

	public final int tier;
	public static final String OTHERWORLD_TIER = "occultism:otherworldToolTier";

	public OtherworldlyModifier(int tier) {
		super(0x8ADAE3);
		this.tier = tier;
	}

	@Override
	public void addRawData(IModifierToolStack tool, int level, RestrictedCompoundTag tag) {
		if (tag.contains(OTHERWORLD_TIER, NBT.TAG_INT)) {
			tag.putInt(OTHERWORLD_TIER, Math.max(tag.getInt(OTHERWORLD_TIER), tier));
		} else {
			tag.putInt(OTHERWORLD_TIER, tier);
		} 
	}

	@Override
	public void beforeRemoved(IModifierToolStack tool, RestrictedCompoundTag tag) {
		tag.remove(OTHERWORLD_TIER);
	}

	@Override
	public ITextComponent getDisplayName(int level) {
		return super.getDisplayName(tier);
	}

	@Override
	protected String makeTranslationKey() {
		return "modifier." + Materialis.modID + ".otherworldly";
	}
}