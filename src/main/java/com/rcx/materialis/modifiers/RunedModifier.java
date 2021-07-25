package com.rcx.materialis.modifiers;

import com.rcx.materialis.Materialis;

import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ToolItem;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import vazkii.arl.util.ClientTicker;
import vazkii.quark.api.IRuneColorProvider;

public class RunedModifier extends SingleUseModifier {

	static boolean enabled = ModList.get().isLoaded("quark");
	public static final ResourceLocation RUNE = new ResourceLocation(Materialis.modID, "rune");

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
			tool.getPersistentData().remove(RUNE);

		return ValidatedResult.PASS;
	}

	@Override
	public ITextComponent getDisplayName(IModifierToolStack tool, int level) {
		if (!enabled || !tool.getPersistentData().contains(RUNE, NBT.TAG_COMPOUND))
			return super.getDisplayName();

		int color = getColor(tool);

		final int colorRGB;
		if (color > 15)
			colorRGB = java.awt.Color.HSBtoRGB((ClientTicker.total / 100F), 1F, 1F);
		else
			colorRGB = DyeColor.byId(color).getFireworkColor();

		return new TranslationTextComponent(getTranslationKey()).withStyle(style -> style.withColor(Color.fromRgb(colorRGB)));
	}

	public static int getColor(IModifierToolStack tool) {
		if (enabled && FMLEnvironment.dist == Dist.CLIENT && tool.getPersistentData().contains(RUNE, NBT.TAG_COMPOUND)) {
			ItemStack stack = ItemStack.of(tool.getPersistentData().getCompound(RUNE));
			if (stack.getItem() instanceof IRuneColorProvider)
				return ((IRuneColorProvider) stack.getItem()).getRuneColor(stack);
		}
		return 16;
	};
}