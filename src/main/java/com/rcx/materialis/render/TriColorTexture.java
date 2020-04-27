package com.rcx.materialis.render;

import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.client.RenderUtil;
import slimeknights.tconstruct.library.client.texture.AbstractColoredTexture;

public class TriColorTexture extends AbstractColoredTexture {

	protected int bright;
	protected int mid;
	protected int dark;
	protected float midCap;
	protected float darkCap;

	TriColorTexture(ResourceLocation baseTextureLocation, String spriteName, int bright, int mid, int dark, int midThreshold, int darkThreshold) {
		super(baseTextureLocation, spriteName);
		this.bright = bright;
		this.mid = mid;
		this.dark = dark;
		this.midCap = midThreshold;
		this.darkCap = darkThreshold;
	}

	@Override
	protected int colorPixel(int pixel, int pxCoord) {
		int a = RenderUtil.alpha(pixel);
		if(a == 0) {
			return pixel;
		}
		int gray = (RenderUtil.red(pixel) + RenderUtil.green(pixel) + RenderUtil.blue(pixel)) / 3;
		if (gray <= darkCap) {
			return interpolateColors(0, dark, gray / darkCap);
		}
		if (gray >= midCap) {
			return interpolateColors(mid, bright, (gray - midCap) / (255.0F - midCap));
		}
		return interpolateColors(dark, mid, (gray - darkCap) / (midCap - darkCap));
	}

	public static int interpolateColors(int colorDark, int colorBright, float p) {
		return RenderUtil.compose(
				(int) (RenderUtil.red(colorBright) * p + RenderUtil.red(colorDark) * (1 - p)),
				(int) (RenderUtil.green(colorBright) * p + RenderUtil.green(colorDark) * (1 - p)),
				(int) (RenderUtil.blue(colorBright) * p + RenderUtil.blue(colorDark) * (1 - p)),
				255);//(int) (RenderUtil.alpha(colorDark) * p + RenderUtil.alpha(colorBright) * (1 - p)));
	}
}