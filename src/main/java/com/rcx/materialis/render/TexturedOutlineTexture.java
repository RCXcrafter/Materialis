package com.rcx.materialis.render;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.client.RenderUtil;
import java.awt.image.DirectColorModel;
import java.util.Collection;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;

public class TexturedOutlineTexture extends TriColorTexture {


	protected final ResourceLocation textureLocation;
	protected TextureAtlasSprite texture;
	protected int[] textureData;
	protected int textureWidth;
	protected int textureHeight;
	protected float scale;
	protected boolean edgeMode;
	protected boolean bothMode;
	protected boolean brightnessMode;

	boolean[] outer;
	boolean[] edge;

	public TexturedOutlineTexture(ResourceLocation baseTexture, String spriteName, ResourceLocation texture, String mode, int bright, int mid, int dark, int midThreshold, int darkThreshold) {
		super(baseTexture, spriteName, bright, mid, dark, midThreshold, darkThreshold);
		this.textureLocation = texture;
		this.edgeMode = mode.equals("edge");
		this.bothMode = mode.equals("both");
		this.brightnessMode = mode.equals("brightness");
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
		DirectColorModel color = new DirectColorModel(32, 16711680, '\uff00', 255, -16777216);

		edge = new boolean[width * height];
		outer = new boolean[width * height];

		int y;
		int c;
		for(int x = 0; x < width; ++x) {
			for(y = 0; y < height; ++y) {
				if(x == 0 || y == 0 || x == width - 1 || y == height - 1) {
					edge[coord(x, y)] = true;
				}

				c = data[coord(x, y)];
				if(c == 0 || color.getAlpha(c) < 64) {
					outer[coord(x, y)] = true;
					if(x > 0) {
						edge[coord(x - 1, y)] = true;
					}

					if(y > 0) {
						edge[coord(x, y - 1)] = true;
					}

					if(x < width - 1) {
						edge[coord(x + 1, y)] = true;
					}

					if(y < height - 1) {
						edge[coord(x, y + 1)] = true;
					}
				}
			}
		}

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
		if (!outer[pxCoord]) {
			if (((edgeMode || bothMode) && edge[pxCoord]) ||
					((brightnessMode || bothMode) && (RenderUtil.red(pixel) + RenderUtil.green(pixel) + RenderUtil.blue(pixel)) < darkCap * 3.0F)) {
				int texCoord = pxCoord;
				if (width > textureWidth || width < textureWidth) {
					int texX = (pxCoord % width) % textureWidth;
					int texY = (pxCoord / height) % textureHeight;
					texCoord = texY * textureWidth + texX;
				}
				int c = textureData[texCoord];
				int a = RenderUtil.alpha(pixel);
				int r = RenderUtil.red(c);
				int b = RenderUtil.blue(c);
				int g = RenderUtil.green(c);

				return RenderUtil.compose(r, g, b, a);
			} else {
				return super.colorPixel(pixel, pxCoord);
			}
		}
		return pixel;
	}
}
