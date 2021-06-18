package com.rcx.materialis.modifiers;

import com.rcx.materialis.Materialis;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.Constants.NBT;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;

public class OtherworldlyModifier extends Modifier {

	public final int tier;
	public static final ResourceLocation OTHERWORLD_TIER = new ResourceLocation(Materialis.modID, "otherworld_tier");

	public OtherworldlyModifier(int tier) {
		super(0x8ADAE3);
		this.tier = tier;
	}

	@Override
	public ValidatedResult validate(IModifierToolStack tool, int level) {
		//remove tags if modifier is removed
		if (level == 0) {
			tool.getPersistentData().remove(OTHERWORLD_TIER);
		}
		return ValidatedResult.PASS;
	}

	@Override
	public void addVolatileData(ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, int level, ModDataNBT volatileData) {
		if (volatileData.contains(OTHERWORLD_TIER, NBT.TAG_INT)) {
			volatileData.putInt(OTHERWORLD_TIER, Math.max(volatileData.getInt(OTHERWORLD_TIER), tier));
		} else {
			volatileData.putInt(OTHERWORLD_TIER, tier);
		} //TODO: replace this with the actual nbt tag when tinkers supports it
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