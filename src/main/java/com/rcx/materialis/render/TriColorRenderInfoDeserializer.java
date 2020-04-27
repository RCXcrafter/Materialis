package com.rcx.materialis.render;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.client.material.deserializers.AbstractRenderInfoDeserializer;

public class TriColorRenderInfoDeserializer extends AbstractRenderInfoDeserializer {

	protected String bright;
	protected String mid;
	protected String dark;
	protected int midthreshold;
	protected int darkthreshold;

	@Override
	public MaterialRenderInfo getMaterialRenderInfo() {
		return new MaterialRenderInfo.AbstractMaterialRenderInfo() {
			@Override
			public TextureAtlasSprite getTexture(ResourceLocation baseTexture, String location) {
				return new TriColorTexture(baseTexture, location, fromHex(bright), fromHex(mid), fromHex(dark), midthreshold, darkthreshold);
			}
		};
	}
}
