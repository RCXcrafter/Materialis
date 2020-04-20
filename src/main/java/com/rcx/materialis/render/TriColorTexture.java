package com.rcx.materialis.render;

import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.client.RenderUtil;
import slimeknights.tconstruct.library.client.texture.AbstractColoredTexture;

public class TriColorTexture extends AbstractColoredTexture {

	protected int bright;
	protected int mid;
	protected int dark;
	protected float cap;

	TriColorTexture(ResourceLocation baseTextureLocation, String spriteName, int bright, int mid, int dark, int threshold) {
		super(baseTextureLocation, spriteName);
		this.bright = bright;
		this.mid = mid;
		this.dark = dark;
		this.cap = threshold;
	}

	@Override
	protected int colorPixel(int pixel, int pxCoord) {
		int a = RenderUtil.alpha(pixel);
		if(a == 0) {
			return pixel;
		}

		float brightness = 2.0F * (Math.max(0, RenderUtil.red(pixel)-cap) + Math.max(0, RenderUtil.green(pixel)-cap) + Math.max(0, RenderUtil.blue(pixel)-cap)) / (3.0F * (255.0F-cap));

		if (brightness > 1.0f) {
			return interpolateColors(mid, bright, brightness - 1.0f);
		}
		return interpolateColors(dark, mid, brightness);
	}

	public static int interpolateColors(int colorDark, int colorBright, float p) {
		return RenderUtil.compose(
				(int) (RenderUtil.red(colorBright) * p + RenderUtil.red(colorDark) * (1 - p)),
				(int) (RenderUtil.green(colorBright) * p + RenderUtil.green(colorDark) * (1 - p)),
				(int) (RenderUtil.blue(colorBright) * p + RenderUtil.blue(colorDark) * (1 - p)),
				255);//(int) (RenderUtil.alpha(colorDark) * p + RenderUtil.alpha(colorBright) * (1 - p)));
	}
}