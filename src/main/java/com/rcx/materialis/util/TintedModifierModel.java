package com.rcx.materialis.util;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import com.mojang.math.Transformation;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.util.GsonHelper;
import slimeknights.mantle.client.model.util.MantleItemLayerModel;
import slimeknights.mantle.util.ItemLayerPixels;
import slimeknights.mantle.util.JsonHelper;
import slimeknights.tconstruct.library.client.modifiers.IBakedModifierModel;
import slimeknights.tconstruct.library.client.modifiers.IUnbakedModifierModel;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * Model for modifiers that can tint their own texture and/or have a glowing texture
 */
public class TintedModifierModel implements IBakedModifierModel {

	/** Constant unbaked model instance, as they are all the same */
	public static final IUnbakedModifierModel UNBAKED_INSTANCE = new Unbaked(-1);

	/** Textures to show */
	private final Material[] textures;
	/** Color to apply to the texture */
	private final int color;

	public TintedModifierModel(@Nullable Material smallTexture, @Nullable Material largeTexture, int color) {
		this.color = color;
		this.textures = new Material[]{ smallTexture, largeTexture };
	}

	public TintedModifierModel(@Nullable Material smallTexture, @Nullable Material largeTexture) {
		this(smallTexture, largeTexture, -1);
	}

	@Nullable
	@Override
	public Object getCacheKey(IToolStackView tool, ModifierEntry entry) {
		if (entry.getModifier() instanceof ITintingModifier) {
			return new GlowingModifierCacheKey(entry.getModifier(), ((ITintingModifier) entry.getModifier()).getLuminosity(tool));
		}
		return entry.getModifier();
	}

	@Override
	public ImmutableList<BakedQuad> getQuads(IToolStackView tool, ModifierEntry modifier, Function<Material, TextureAtlasSprite> spriteGetter, Transformation transforms, boolean isLarge, int startTintIndex, @Nullable ItemLayerPixels pixels) {
		int index = isLarge ? 1 : 0;
		int luminosity = 0;
		if (modifier.getModifier() instanceof ITintingModifier)
			luminosity = ((ITintingModifier) modifier.getModifier()).getLuminosity(tool);
		return MantleItemLayerModel.getQuadsForSprite(color, startTintIndex, spriteGetter.apply(textures[index]), transforms, luminosity, pixels);
	}

	@Override
	public int getTintIndexes() {
		return 1;
	}

	@Override
	public int getTint(IToolStackView tool, ModifierEntry entry, int index) {
		if (entry.getModifier() instanceof ITintingModifier)
			return ((ITintingModifier) entry.getModifier()).getTint(tool);
		return -1;
	}

	@Data
	private static class GlowingModifierCacheKey {
		private final Modifier modifier;
		private final int luminosity;
	}

	@RequiredArgsConstructor
	private static class Unbaked implements IUnbakedModifierModel {
		private final int color;

		@Nullable
		@Override
		public IBakedModifierModel forTool(Function<String,Material> smallGetter, Function<String,Material> largeGetter) {
			Material smallTexture = smallGetter.apply("");
			Material largeTexture = largeGetter.apply("");
			if (smallTexture != null || largeTexture != null) {
				return new TintedModifierModel(smallTexture, largeTexture, color);
			}
			return null;
		}

		@Override
		public IUnbakedModifierModel configure(JsonObject data) {
			// parse the two keys, if we ended up with something new create an instance
			int color = JsonHelper.parseColor(GsonHelper.getAsString(data, "color", ""));
			if (color != this.color) {
				return new Unbaked(color);
			}
			return this;
		}
	}
}