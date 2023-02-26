package com.rcx.materialis.util;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.item.ManaShotItem.ManashotEntity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class ManashotRenderer extends ArrowRenderer<ManashotEntity> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(Materialis.modID, "textures/models/manashot.png");

	public ManashotRenderer(Context context) {
		super(context);
	}

	@Override
	public ResourceLocation getTextureLocation(ManashotEntity arrow) {
		return TEXTURE;
	}
}
