package com.rcx.materialis.render;

import java.awt.Color;
import java.util.Collection;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.client.RenderUtil;

public class TexturedTriColorTexture extends TriColorTexture {

	protected final ResourceLocation textureLocation;
	protected TextureAtlasSprite texture;
	protected int[] textureData;
	protected int textureWidth;
	protected int textureHeight;
	protected float scale;
	protected String mode;

	TexturedTriColorTexture(ResourceLocation baseTexture, String spriteName, ResourceLocation texture, String underlay, int bright, int mid, int dark, int midThreshold, int darkThreshold) {
		super(baseTexture, spriteName, bright, mid, dark, midThreshold, darkThreshold);
		this.textureLocation = texture;
		this.mode = underlay;
	}

	@Override
	public Collection<ResourceLocation> getDependencies() {
		return ImmutableList.<ResourceLocation>builder().addAll(super.getDependencies()).add(textureLocation).build();
	}

	@Override
	public boolean load(IResourceManager manager, ResourceLocation location, Function<ResourceLocation, TextureAtlasSprite> textureGetter) {
		texture = textureGetter.apply(textureLocation);
		super.load(manager, location, textureGetter);
		return false;
	}

	@Override
	protected void preProcess(int[] data) {
		super.preProcess(data);
		//prepare the texture
		textureData = texture.getFrameTextureData(0)[0];
		textureWidth = texture.getIconWidth();
		textureHeight = texture.getIconHeight();
		scale = (float) textureHeight / (float) textureWidth;
	}

	@Override
	protected void postProcess(int[] data) {
		super.postProcess(data);
		textureData = null;
	}

	@Override
	protected int colorPixel(int pixel, int pxCoord) {
		int a = RenderUtil.alpha(pixel);
		if (a == 0) {
			return pixel;
		}
		int texCoord = pxCoord;
		if (width > textureWidth || width < textureWidth) {
			int texX = (pxCoord % width) % textureWidth;
			int texY = (pxCoord / height) % textureHeight;
			texCoord = texY * textureWidth + texX;
		}

		int tc = textureData[texCoord];

		if (mode.equals("underlay")) {
			int r = RenderUtil.red(pixel);
			int g = RenderUtil.green(pixel);
			int b = RenderUtil.blue(pixel);

			r = mult(r, RenderUtil.red(tc)) & 0xff;
			g = mult(g, RenderUtil.green(tc)) & 0xff;
			b = mult(b, RenderUtil.blue(tc)) & 0xff;

			return super.colorPixel(RenderUtil.compose(r, g, b, a), pxCoord);
		}
		int c = super.colorPixel(pixel, pxCoord);

		int r = RenderUtil.red(c);
		int g = RenderUtil.green(c);
		int b = RenderUtil.blue(c);
		
		if (mode.equals("overlay_multiply")) {
			r = mult(r, RenderUtil.red(tc)) & 0xff;
			g = mult(g, RenderUtil.green(tc)) & 0xff;
			b = mult(b, RenderUtil.blue(tc)) & 0xff;
		} else if (mode.equals("overlay_hue")) {
			float[] underlay = Color.RGBtoHSB(r, g, b, null);
			Color color = Color.getHSBColor(Color.RGBtoHSB(RenderUtil.red(tc), RenderUtil.green(tc), RenderUtil.blue(tc), null)[0], underlay[1], underlay[2]);
			r = color.getRed();
			g = color.getGreen();
			b = color.getBlue();
		} else if (mode.equals("overlay_huesat")) {
			float[] overlay = Color.RGBtoHSB(RenderUtil.red(tc), RenderUtil.green(tc), RenderUtil.blue(tc), null);
			Color color = Color.getHSBColor(overlay[0], overlay[1], Color.RGBtoHSB(r, g, b, null)[2]);
			r = color.getRed();
			g = color.getGreen();
			b = color.getBlue();
		}
		
		return RenderUtil.compose(r, g, b, a);
	}
}