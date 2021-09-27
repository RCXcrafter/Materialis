package com.rcx.materialis.util;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.vector.TransformationMatrix;
import slimeknights.mantle.client.model.util.MantleItemLayerModel;
import slimeknights.mantle.util.ItemLayerPixels;
import slimeknights.mantle.util.JsonHelper;
import slimeknights.tconstruct.library.client.modifiers.IBakedModifierModel;
import slimeknights.tconstruct.library.client.modifiers.IUnbakedModifierModel;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

/**
 * Model for modifiers that can tint their own texture and/or have a glowing texture
 */
public class TintedModifierModel implements IBakedModifierModel {

	/** Constant unbaked model instance, as they are all the same */
	public static final IUnbakedModifierModel UNBAKED_INSTANCE = new Unbaked(-1);

	/** Textures to show */
	private final RenderMaterial[] textures;
	/** Color to apply to the texture */
	private final int color;

	public TintedModifierModel(@Nullable RenderMaterial smallTexture, @Nullable RenderMaterial largeTexture, int color) {
		this.color = color;
		this.textures = new RenderMaterial[]{ smallTexture, largeTexture };
	}

	public TintedModifierModel(@Nullable RenderMaterial smallTexture, @Nullable RenderMaterial largeTexture) {
		this(smallTexture, largeTexture, -1);
	}

	@Nullable
	@Override
	public Object getCacheKey(IModifierToolStack tool, ModifierEntry entry) {
		if (entry.getModifier() instanceof ITintingModifier) {
			return new GlowingModifierCacheKey(entry.getModifier(), ((ITintingModifier) entry.getModifier()).doesGlow(tool));
		}
		return entry.getModifier();
	}

	@Deprecated
	@Override
	public ImmutableList<BakedQuad> getQuads(IModifierToolStack tool, ModifierEntry modifier, Function<RenderMaterial,TextureAtlasSprite> spriteGetter, TransformationMatrix transforms, boolean isLarge) {
		return getQuads(tool, modifier, spriteGetter, transforms, isLarge, -1, null);
	}

	@Override
	public ImmutableList<BakedQuad> getQuads(IModifierToolStack tool, ModifierEntry modifier, Function<RenderMaterial,TextureAtlasSprite> spriteGetter, TransformationMatrix transforms, boolean isLarge, int startTintIndex, @Nullable ItemLayerPixels pixels) {
		int index = isLarge ? 1 : 0;
		boolean glowing = false;
		if (modifier.getModifier() instanceof ITintingModifier)
			glowing = ((ITintingModifier) modifier.getModifier()).doesGlow(tool);
		return MantleItemLayerModel.getQuadsForSprite(color, startTintIndex, spriteGetter.apply(textures[index]), transforms, glowing ? 15 : 0, pixels);
	}

	@Override
	public int getTintIndexes() {
		return 1;
	}

	@Override
	public int getTint(IModifierToolStack tool, ModifierEntry entry, int index) {
		if (entry.getModifier() instanceof ITintingModifier)
			return ((ITintingModifier) entry.getModifier()).getTint(tool);
		return -1;
	}

	@Data
	private static class GlowingModifierCacheKey {
		private final Modifier modifier;
		private final boolean glowing;
	}

	@RequiredArgsConstructor
	private static class Unbaked implements IUnbakedModifierModel {
		private final int color;

		@Nullable
		@Override
		public IBakedModifierModel forTool(Function<String,RenderMaterial> smallGetter, Function<String,RenderMaterial> largeGetter) {
			RenderMaterial smallTexture = smallGetter.apply("");
			RenderMaterial largeTexture = largeGetter.apply("");
			if (smallTexture != null || largeTexture != null) {
				return new TintedModifierModel(smallTexture, largeTexture, color);
			}
			return null;
		}

		@Override
		public IUnbakedModifierModel configure(JsonObject data) {
			// parse the two keys, if we ended up with something new create an instance
			int color = JsonHelper.parseColor(JSONUtils.getAsString(data, "color", ""));
			if (color != this.color) {
				return new Unbaked(color);
			}
			return this;
		}
	}
}