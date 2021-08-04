package com.rcx.materialis.util;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import lombok.Data;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.vector.TransformationMatrix;
import net.minecraftforge.client.model.ItemLayerModel;
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
	public static final IUnbakedModifierModel UNBAKED_INSTANCE = (smallGetter, largeGetter) -> {
		RenderMaterial smallTexture = smallGetter.apply("");
		RenderMaterial largeTexture = largeGetter.apply("");
		if (smallTexture != null || largeTexture != null) {
			return new TintedModifierModel(smallTexture, largeTexture);
		}
		return null;
	};

	/** Textures to show */
	private final RenderMaterial[] textures;
	/** Cache of quads */
	@SuppressWarnings("unchecked")
	private final ImmutableList<BakedQuad>[] quads = new ImmutableList[4];

	public TintedModifierModel(@Nullable RenderMaterial smallTexture, @Nullable RenderMaterial largeTexture) {
		this.textures = new RenderMaterial[]{ smallTexture, largeTexture };
	}

	@Nullable
	@Override
	public Object getCacheKey(IModifierToolStack tool, ModifierEntry entry) {
		if (entry.getModifier() instanceof ITintingModifier) {
			return new GlowingModifierCacheKey(entry.getModifier(), ((ITintingModifier) entry.getModifier()).doesGlow(tool));
		}
		return entry.getModifier();
	}

	@Override
	@Deprecated
	public ImmutableList<BakedQuad> getQuads(IModifierToolStack tool, ModifierEntry entry, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, TransformationMatrix transforms, boolean isLarge) {
		return this.getQuads(tool, entry, spriteGetter, transforms, isLarge, -1);
	}

	@Override
	public ImmutableList<BakedQuad> getQuads(IModifierToolStack tool, ModifierEntry entry, Function<RenderMaterial,TextureAtlasSprite> spriteGetter, TransformationMatrix transforms, boolean isLarge, int startTintIndex) {
		boolean glowing = false;
		if (entry.getModifier() instanceof ITintingModifier)
			glowing = ((ITintingModifier) entry.getModifier()).doesGlow(tool);

		int index = isLarge ? 1 : 0;
		int quadIndex = index + (glowing ? 2 : 0);

		if (quads[index] == null) {
			if (textures[index] == null) {
				quads[index] = ImmutableList.of();
			} else {
				quads[index] = ItemLayerModel.getQuadsForSprite(startTintIndex, spriteGetter.apply(textures[index]), transforms, glowing);
			}
		}
		return quads[index];
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
}