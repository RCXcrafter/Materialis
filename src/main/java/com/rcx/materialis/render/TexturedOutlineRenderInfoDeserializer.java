package com.rcx.materialis.render;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;

public class TexturedOutlineRenderInfoDeserializer extends TriColorRenderInfoDeserializer {

	protected String texture;
	protected String mode;

	@Override
	public MaterialRenderInfo getMaterialRenderInfo() {
		return new MaterialRenderInfo.AbstractMaterialRenderInfo() {
			@Override
			public TextureAtlasSprite getTexture(ResourceLocation baseTexture, String location) {
				return new TexturedOutlineTexture(baseTexture, location, new ResourceLocation(texture), mode, fromHex(bright), fromHex(mid), fromHex(dark), midthreshold, darkthreshold);
			}
		};
	}
}
