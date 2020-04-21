package com.rcx.materialis.render;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;

public class TexturedTriColorRenderInfoDeserializer extends TriColorRenderInfoDeserializer {

	protected String overlay;
	protected boolean underlay;

	@Override
	public MaterialRenderInfo getMaterialRenderInfo() {
		return new MaterialRenderInfo.AbstractMaterialRenderInfo() {
			@Override
			public TextureAtlasSprite getTexture(ResourceLocation baseTexture, String location) {
				return new TexturedTriColorTexture(baseTexture, location, new ResourceLocation(overlay), underlay, fromHex(bright), fromHex(mid), fromHex(dark), threshold);
			}
		};
	}
}
