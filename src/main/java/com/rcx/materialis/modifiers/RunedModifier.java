package com.rcx.materialis.modifiers;

import com.rcx.materialis.Materialis;

import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ToolItem;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import vazkii.arl.util.ClientTicker;

public class RunedModifier extends SingleUseModifier {

	boolean enabled = ModList.get().isLoaded("quark");
	public static final ResourceLocation RUNE_COLOR = new ResourceLocation(Materialis.modID, "rune_color");

	public RunedModifier() {
		super(0xFFFFFF);
	}

	@Override
	public void addVolatileData(ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, int level, ModDataNBT volatileData) {
		volatileData.putBoolean(ToolItem.SHINY, true);
	}

	@Override
	public ValidatedResult validate(IModifierToolStack tool, int level) {
		//remove tags if modifier is removed
		if (level == 0)
			tool.getPersistentData().remove(RUNE_COLOR);

		return ValidatedResult.PASS;
	}

	@Override
	public ITextComponent getDisplayName(IModifierToolStack tool, int level) {
		if (!enabled || !tool.getPersistentData().contains(RUNE_COLOR, NBT.TAG_INT))
			return super.getDisplayName();

		int color = tool.getPersistentData().getInt(RUNE_COLOR);

		final int colorRGB;
		if (color > 15)
			colorRGB = java.awt.Color.HSBtoRGB((ClientTicker.total / 100F), 1F, 1F);
		else
			colorRGB = DyeColor.byId(color).getFireworkColor();

		return new TranslationTextComponent(getTranslationKey()).withStyle(style -> style.withColor(Color.fromRgb(colorRGB)));
	}
}