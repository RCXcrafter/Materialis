package com.rcx.materialis.util;

import lombok.RequiredArgsConstructor;
import net.minecraft.client.renderer.texture.NativeImage;
import slimeknights.tconstruct.library.client.data.spritetransformer.ISpriteTransformer;

@RequiredArgsConstructor
public class RecolorTextureSpriteTransformer implements ISpriteTransformer {
	/** Color mapping to apply */
	private final GreyToTextureColorMapping colorMapping;

	@Override
	public void transform(NativeImage image) {
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				image.setPixelRGBA(x, y, colorMapping.mapColor(image.getPixelRGBA(x, y), x, y));
			}
		}
	}

	@Override
	public int getFallbackColor() {
		return colorMapping.mapColor(0xFFD8D8D8); // 216 on the greyscale, second color in most of our palettes
	}
}
