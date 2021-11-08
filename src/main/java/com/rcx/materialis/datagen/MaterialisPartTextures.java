package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;

import slimeknights.tconstruct.library.client.data.material.AbstractPartSpriteProvider;

public class MaterialisPartTextures extends AbstractPartSpriteProvider {

	public MaterialisPartTextures() {
		super(Materialis.modID);
	}

	@Override
	public String getName() {
		return "Materialis Part Textures";
	}

	@Override
	protected void addAllSpites() {
		//wrench
		buildTool("wrench").addBreakableHead("head").addHandle("handle");
		buildTool("battlewrench").withLarge().addBreakableHead("head").addBreakableHead("back").addBreakableHead("front");
	}
}
