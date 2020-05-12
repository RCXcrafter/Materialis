package com.rcx.materialis.render;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;

public class TexturedTriColorRenderInfoDeserializer extends TriColorRenderInfoDeserializer {

	protected String overlay;
	protected String mode;

	@Override
	public MaterialRenderInfo getMaterialRenderInfo() {
		return new MaterialRenderInfo.AbstractMaterialRenderInfo() {
			@Override
			public TextureAtlasSprite getTexture(ResourceLocation baseTexture, String location) {
				return new TexturedTriColorTexture(baseTexture, location, new ResourceLocation(overlay), mode, fromHex(bright), fromHex(mid), fromHex(dark), midthreshold, darkthreshold);
			}
		};
	}
}
