package com.rcx.materialis.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.client.material.deserializers.AbstractRenderInfoDeserializer;
import slimeknights.tconstruct.library.client.texture.TinkerTexture;

public class TexturedOutlineRenderInfoDeserializer extends AbstractRenderInfoDeserializer {

	protected String texture;
	protected String mode;

	protected String bright;
	protected String mid;
	protected String dark;
	protected int threshold;

	@Override
	public MaterialRenderInfo getMaterialRenderInfo() {
		return new MaterialRenderInfo.AbstractMaterialRenderInfo() {
			@Override
			public TextureAtlasSprite getTexture(ResourceLocation baseTexture, String location) {
				TextureAtlasSprite blockTexture = Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(new ResourceLocation(texture).toString());
				if(blockTexture == null) {
					blockTexture = TinkerTexture.loadManually(new ResourceLocation(texture));
				}
				return new TexturedOutlineTexture(baseTexture, location, new ResourceLocation(blockTexture.getIconName()), mode, fromHex(bright), fromHex(mid), fromHex(dark), threshold);
			}
		};
	}
}
