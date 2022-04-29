package com.rcx.materialis.modifiers;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.util.ITintingModifier;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ColorizedModifier extends NoLevelsModifier implements ITintingModifier {

	public static boolean enabled = ModList.get().isLoaded("psi");
	public static final ResourceLocation COLORIZER = new ResourceLocation(Materialis.modID, "colorizer");

	@Override
	public void onRemoved(IToolStackView tool) {
		tool.getPersistentData().remove(COLORIZER);
	}

	@Override
	public Component getDisplayName(IToolStackView tool, int level) {
		return new TranslatableComponent(getTranslationKey()).withStyle(style -> style.withColor(TextColor.fromRgb(getTint(tool))));
	}

	@Override
	public int getTint(IToolStackView tool) {
		/*if (enabled && tool.getPersistentData().contains(COLORIZER, Tag.TAG_COMPOUND)) {
			ItemStack stack = ItemStack.of(tool.getPersistentData().getCompound(COLORIZER));
			if (stack.getItem() instanceof ItemCADColorizerPsi)
				return getPsiColor(); //I am against paid features in mods, even purely cosmetic ones, and I will not let such features carry over into my addon
			if (stack.getItem() instanceof ICADColorizer)
				return ((ICADColorizer) stack.getItem()).getColor(stack);
		}*/
		return getPsiColor();
	};

	@Override
	public int getLuminosity(IToolStackView tool) {
		return 15;
	};

	public static int getPsiColor() {
		/*if (enabled) {
			float time = ClientTickHandler.total;
			float w = (float) (Math.sin(time * 0.4) * 0.5 + 0.5) * 0.1F;
			float r = (float) (Math.sin(time * 0.1) * 0.5 + 0.5) * 0.5F + 0.25F + w;
			float g = 0.5F + w;
			float b = 1F;
			return new java.awt.Color((int) (r * 255), (int) (g * 255), (int) (b * 255)).getRGB();
		}*/
		return 0xFFFFFF;
	}
}