package com.rcx.materialis.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.client.material.deserializers.AbstractRenderInfoDeserializer;
import slimeknights.tconstruct.library.client.texture.TinkerTexture;

public class TriColorRenderInfoDeserializer extends AbstractRenderInfoDeserializer {

	protected String bright;
	protected String mid;
	protected String dark;
	protected int threshold;

	@Override
	public MaterialRenderInfo getMaterialRenderInfo() {
		return new MaterialRenderInfo.AbstractMaterialRenderInfo() {
			@Override
			public TextureAtlasSprite getTexture(ResourceLocation baseTexture, String location) {
				return new TriColorTexture(baseTexture, location, fromHex(bright), fromHex(mid), fromHex(dark), threshold);
			}
		};
	}
}
