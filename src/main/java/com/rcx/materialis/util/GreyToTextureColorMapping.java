package com.rcx.materialis.util;

import static net.minecraft.client.renderer.texture.NativeImage.combine;
import static net.minecraft.client.renderer.texture.NativeImage.getA;
import static net.minecraft.client.renderer.texture.NativeImage.getB;
import static net.minecraft.client.renderer.texture.NativeImage.getG;
import static net.minecraft.client.renderer.texture.NativeImage.getR;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.ImmutableList;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.client.data.SpriteReader;
import slimeknights.tconstruct.library.client.data.spritetransformer.IColorMapping;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class GreyToTextureColorMapping implements IColorMapping {

	private final List<ColorTextureMapping> mappings;

	/**
	 * Interpolates two numbers
	 * @param a        First number
	 * @param b        Second number
	 * @param x        Amount of A, such that x / divisor is the percentage from A to B
	 * @param divisor  Divisor to use with X
	 * @return  Interpolated value
	 */
	private static int interpolate(int a, int b, int x, int divisor) {
		return a + (((b - a) * x) / divisor);
	}

	/**
	 * Gets the color for the given greyscale from the palette
	 * @param grey  Grey value
	 * @return  Color
	 */
	private int getColor(int grey, int x, int y) {
		// we need to find up to two colors, a less and a greater than
		// ideally we find a direct match, but if not we interpolate
		int size = mappings.size();
		ColorTextureMapping first = mappings.get(0);
		// grey is before the first point, return the first value
		if (size == 1 || grey <= first.getGrey()) {
			return first.getColor(x, y);
		}

		// grey is after the first point, so try to find two points its between
		ColorTextureMapping second = mappings.get(1);
		for (int i = 1; i < size; i++) {
			// locate an upper bound, once we find one we have a pair to use
			int newGrey = second.getGrey();
			if (grey < newGrey) {
				break;
			}
			// if the upper bound is an exact match, nothing else to do
			if (grey == newGrey) {
				return second.getColor(x, y);
			}
			first = second;
			second = mappings.get(i);
		}

		// if its bigger than the last, return the last value
		if (grey > second.getGrey()) {
			return second.getColor(x, y);
		}

		// at this point, grey is strictly between first and second, interpolate between the two
		int diff = grey - first.getGrey();
		int divisor = second.getGrey() - first.getGrey();
		int colorA = first.getColor(x, y);
		int colorB = second.getColor(x, y);
		// interpolate each pair of colors
		int alpha = interpolate(getA(colorA), getA(colorB), diff, divisor);
		int red   = interpolate(getR(colorA), getR(colorB), diff, divisor);
		int green = interpolate(getG(colorA), getG(colorB), diff, divisor);
		int blue  = interpolate(getB(colorA), getB(colorB), diff, divisor);
		return combine(alpha, blue, green, red);
	}

	@Override
	public int mapColor(int color) {
		return mapColor(color, 0, 0);
	}

	public int mapColor(int color, int x, int y) {
		// if fully transparent, just return fully transparent
		// we do not do 0 alpha RGB values to save effort
		int alpha = getA(color);
		if (alpha == 0) {
			return 0x00000000;
		}
		// figure out our new greyscale from the given color, we just base it on the largest
		int red = getR(color);
		int green = getG(color);
		int blue = getB(color);
		int grey = Math.max(red, Math.max(green, blue));
		int newColor = getColor(grey, x, y);
		// if the original color was partially transparent, set the alpha
		if (alpha < 255) newColor = (newColor & 0x00FFFFFF) | ((alpha * getA(newColor) / 255) << 24);
		// if any of RGB are lower than the max, scale it down
		if (red   < grey) newColor = (newColor & 0xFFFFFF00) | (((newColor & 0x000000FF) * red   / grey) & 0x000000FF);
		if (green < grey) newColor = (newColor & 0xFFFF00FF) | (((newColor & 0x0000FF00) * green / grey) & 0x0000FF00);
		if (blue  < grey) newColor = (newColor & 0xFF00FFFF) | (((newColor & 0x00FF0000) * blue  / grey) & 0x00FF0000);

		// final color
		return newColor;
	}

	/** Creates a new grey to color builder */
	public static Builder builder(SpriteReader spriteReader) {
		return new Builder(spriteReader);
	}

	/** Creates a new grey to color builder starting with greyscale 0 as white */
	public static Builder builderFromBlack(SpriteReader spriteReader) {
		return builder(spriteReader).addABGR(0, 0xFF000000);
	}

	/** Mapping from greyscale to color or texture */
	private static class ColorTextureMapping {
		private final int grey;
		private final int color;
		private final ResourceLocation textureLocation;
		private final SpriteReader spriteReader;
		private NativeImage texture = null;

		public ColorTextureMapping(SpriteReader spriteReader, int grey, int color) {
			this.spriteReader = spriteReader;
			this.grey = grey;
			this.color = color;
			this.textureLocation = null;
		}

		public ColorTextureMapping(SpriteReader spriteReader, int grey, ResourceLocation textureLocation) {
			this.spriteReader = spriteReader;
			this.grey = grey;
			this.color = -1;
			this.textureLocation = textureLocation;
		}

		public int getGrey() {
			return grey;
		}

		public int getColor(int x, int y) {
			if (textureLocation != null) {
				if (texture == null) {
					try {
						texture = spriteReader.read(textureLocation);
					} catch (IOException e) {
						throw new IllegalStateException("Missing sprite at " + textureLocation.getNamespace() + ":material/" + textureLocation.getPath() + ".png, cannot generate textures");
					}
				}
				return texture.getPixelRGBA(x % texture.getWidth(), y % texture.getHeight());
			}
			return color;
		}
	}

	/** Builder to create a palette of this type */
	public static class Builder {

		private final ImmutableList.Builder<ColorTextureMapping> builder = ImmutableList.builder();
		private int lastGrey = -1;
		private final SpriteReader spriteReader;

		public Builder(SpriteReader spriteReader) {
			this.spriteReader = spriteReader;
		}

		/** Validates the given grey value */
		private void checkGrey(int grey) {
			if (grey < 0 || grey > 255) {
				throw new IllegalArgumentException("Invalid grey value, must be between 0 and 255, inclusive");
			}
			if (grey <= lastGrey) {
				throw new IllegalArgumentException("Grey value must be greater than the previous value");
			}
			lastGrey = grey;
		}

		/** Adds a color to the palette in ABGR format */
		public Builder addABGR(int grey, int color) {
			checkGrey(grey);
			builder.add(new ColorTextureMapping(spriteReader, grey, color));
			return this;
		}

		/** Adds a color to the palette in ARGB format */
		public Builder addARGB(int grey, int color) {
			checkGrey(grey);
			int newColor = (color & 0xFF00FF00) | (((color & 0x00FF0000) >> 16) & 0x000000FF) | (((color & 0x000000FF) << 16) & 0x00FF0000);
			builder.add(new ColorTextureMapping(spriteReader, grey, newColor));
			return this;
		}

		/** Adds a color to the palette in ABGR format */
		public Builder addTexture(int grey, ResourceLocation texture) {
			checkGrey(grey);
			builder.add(new ColorTextureMapping(spriteReader, grey, texture));
			return this;
		}

		/** Builds a color mapping */
		public GreyToTextureColorMapping build() {
			List<ColorTextureMapping> list = builder.build();
			if (list.size() < 2) {
				throw new IllegalStateException("Too few colors in palette, must have at least 2");
			}
			return new GreyToTextureColorMapping(list);
		}
	}
}
