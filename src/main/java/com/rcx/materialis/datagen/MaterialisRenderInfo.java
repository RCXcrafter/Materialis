package com.rcx.materialis.datagen;

import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;

public class MaterialisRenderInfo extends AbstractMaterialRenderInfoProvider {

	public MaterialisRenderInfo(DataGenerator gen, AbstractMaterialSpriteProvider spriteProvider) {
		super(gen, spriteProvider);
	}

	@Override
	protected void addMaterialRenderInfo() {
		// tier 1
		buildRenderInfo(MaterialisMaterials.fairy).color(0xFF87BC);
		//general oredict materials
		buildRenderInfo(MaterialisMaterials.brass).color(0xFFD359);
		buildRenderInfo(MaterialisMaterials.aluminum).color(0xFFDEE4);
		buildRenderInfo(MaterialisMaterials.nickel).color(0xF9EA98);
		buildRenderInfo(MaterialisMaterials.uranium).color(0x609159).luminosity(1);
		//create materials
		buildRenderInfo(MaterialisMaterials.roseQuartz).color(0xFF8C80);
		buildRenderInfo(MaterialisMaterials.refinedRadiance).color(0xFFFFFF).luminosity(15);
		buildRenderInfo(MaterialisMaterials.shadowSteel).color(0x594F71);
		//eidolon materials
		buildRenderInfo(MaterialisMaterials.pewter).color(0xDEDEB9);
		buildRenderInfo(MaterialisMaterials.arcaneGold).color(0xFFC069);
		//aquaculture materials
		buildRenderInfo(MaterialisMaterials.neptunium).color(0x17F1B6);
		//mystical world materials
		buildRenderInfo(MaterialisMaterials.quicksilver).color(0xD6B07C);
		//astral sorcery materials
		buildRenderInfo(MaterialisMaterials.starmetal).color(0x0040D6).luminosity(2);
		//industrial foregoing materials
		buildRenderInfo(MaterialisMaterials.plastic).color(0xD9D9D9);
		buildRenderInfo(MaterialisMaterials.pinkSlime).color(0xFF9FEF);
		//undergarden materials
		buildRenderInfo(MaterialisMaterials.cloggrum).color(0x9C8878);
		buildRenderInfo(MaterialisMaterials.froststeel).color(0x95BDE3);
		buildRenderInfo(MaterialisMaterials.utherium).color(0xEB515B);
		buildRenderInfo(MaterialisMaterials.forgottenMetal).color(0x6CD7AA);
		//mekanism materials
		buildRenderInfo(MaterialisMaterials.refinedObsidian).color(0xB78FD2);
		buildRenderInfo(MaterialisMaterials.refinedGlowstone).color(0xFFE55C).luminosity(13);
		//psi materials
		buildRenderInfo(MaterialisMaterials.psimetal).color(0xB6A9E7);
		buildRenderInfo(MaterialisMaterials.ebonyPsimetal).color(0x302C2C);
		buildRenderInfo(MaterialisMaterials.ivoryPsimetal).color(0xF6F9ED);
		//occultism materials
		buildRenderInfo(MaterialisMaterials.iesnium).color(0x8ADAE3);
	}

	@Override
	public String getName() {
		return "Materialis Material Render Info Provider";
	}
}
