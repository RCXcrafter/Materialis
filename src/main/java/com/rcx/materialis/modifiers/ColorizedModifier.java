package com.rcx.materialis.modifiers;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.util.ITintingModifier;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import vazkii.psi.api.cad.ICADColorizer;
import vazkii.psi.client.core.handler.ClientTickHandler;
import vazkii.psi.common.item.component.ItemCADColorizerPsi;

public class ColorizedModifier extends SingleUseModifier implements ITintingModifier {

	boolean enabled = ModList.get().isLoaded("psi");
	public static final ResourceLocation COLORIZER = new ResourceLocation(Materialis.modID, "colorizer");

	public ColorizedModifier() {
		super(0xB6A9E7);
	}

	@Override
	public ValidatedResult validate(IModifierToolStack tool, int level) {
		//remove tags if modifier is removed
		if (level == 0)
			tool.getPersistentData().remove(COLORIZER);

		return ValidatedResult.PASS;
	}

	@Override
	public ITextComponent getDisplayName(IModifierToolStack tool, int level) {
		return new TranslationTextComponent(getTranslationKey()).withStyle(style -> style.withColor(Color.fromRgb(getTint(tool))));
	}

	@Override
	public int getTint(IModifierToolStack tool) {
		if (enabled && tool.getPersistentData().contains(COLORIZER, NBT.TAG_COMPOUND)) {
			ItemStack stack = ItemStack.of(tool.getPersistentData().getCompound(COLORIZER));
			if (stack.getItem() instanceof ItemCADColorizerPsi)
				return getPsiColor(); //I am against paid features in mods, even purely cosmetic ones, and I will not let such features carry over into my addon
			if (stack.getItem() instanceof ICADColorizer)
				return ((ICADColorizer) stack.getItem()).getColor(stack);
		}
		return getPsiColor();
	};

	@Override
	public boolean doesGlow(IModifierToolStack tool) {
		return true;
	};

	public int getPsiColor() {
		if (enabled) {
			float time = ClientTickHandler.total;
			float w = (float) (Math.sin(time * 0.4) * 0.5 + 0.5) * 0.1F;
			float r = (float) (Math.sin(time * 0.1) * 0.5 + 0.5) * 0.5F + 0.25F + w;
			float g = 0.5F + w;
			float b = 1F;
			return new java.awt.Color((int) (r * 255), (int) (g * 255), (int) (b * 255)).getRGB();
		}
		return this.getColor();
	}
}