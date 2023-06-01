package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;

import slimeknights.tconstruct.library.client.data.material.AbstractPartSpriteProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

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

		//exosuit textures
		addSprite("psimetal_exosuit/helmet_modifiers/tconstruct_embellishment", TinkerPartSpriteProvider.PLATE);
		addSprite("psimetal_exosuit/helmet_modifiers/tconstruct_embellishment_broken", TinkerPartSpriteProvider.PLATE);
		addSprite("psimetal_exosuit/chestplate_modifiers/tconstruct_embellishment", TinkerPartSpriteProvider.PLATE);
		addSprite("psimetal_exosuit/chestplate_modifiers/tconstruct_embellishment_broken", TinkerPartSpriteProvider.PLATE);
		addSprite("psimetal_exosuit/leggings_modifiers/tconstruct_embellishment", TinkerPartSpriteProvider.PLATE);
		addSprite("psimetal_exosuit/leggings_modifiers/tconstruct_embellishment_broken", TinkerPartSpriteProvider.PLATE);
		addSprite("psimetal_exosuit/boot_modifiers/tconstruct_embellishment", TinkerPartSpriteProvider.PLATE);
		addSprite("psimetal_exosuit/boot_modifiers/tconstruct_embellishment_broken", TinkerPartSpriteProvider.PLATE);
		addTexture("models/armor/exosuit/accent", TinkerPartSpriteProvider.PLATE);
		addTexture("models/armor/exosuit_magical/accent", TinkerPartSpriteProvider.PLATE);
	}
}
