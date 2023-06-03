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
		buildRenderInfo(MaterialisMaterials.orichalcum).color(0xD6B07C);
		//astral sorcery materials
		buildRenderInfo(MaterialisMaterials.starmetal).color(0x0040D6).luminosity(2);
		//industrial foregoing materials
		buildRenderInfo(MaterialisMaterials.plastic).color(0xD9D9D9);
		buildRenderInfo(MaterialisMaterials.pinkSlime).color(0xFF9FEF);
		buildRenderInfo(MaterialisMaterials.pinkSlimeball).color(0xE27BE3);
		//undergarden materials
		buildRenderInfo(MaterialisMaterials.cloggrum).color(0x9C8878);
		buildRenderInfo(MaterialisMaterials.froststeel).color(0x95BDE3);
		buildRenderInfo(MaterialisMaterials.utherium).color(0xEB515B);
		buildRenderInfo(MaterialisMaterials.regalium).color(0xEBBB71);
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
		//botania materials
		buildRenderInfo(MaterialisMaterials.livingwood).color(0x421909);
		buildRenderInfo(MaterialisMaterials.dreamwood).color(0xFFC1CCCC);
		buildRenderInfo(MaterialisMaterials.manasteel).color(0x3389FF);
		buildRenderInfo(MaterialisMaterials.elementium).color(0xF15CAE);
		buildRenderInfo(MaterialisMaterials.terrasteel).color(0x5CFF12);
		buildRenderInfo(MaterialisMaterials.manastring).color(0xA5F6FF);
		//botania materials
		buildRenderInfo(MaterialisMaterials.alfsteel).color(0xFFC400);
		//draconic evolution materials
		buildRenderInfo(MaterialisMaterials.draconium).color(0x9B3DF2);
		buildRenderInfo(MaterialisMaterials.awakenedDraconium).color(0xFF8800);
		//redstone arsenal materials
		buildRenderInfo(MaterialisMaterials.fluxInfused).color(0xFFC149);
		//avaritia materials
		buildRenderInfo(MaterialisMaterials.crystalMatrix).color(0x79F2E9).luminosity(1);
		buildRenderInfo(MaterialisMaterials.neutronium).color(0x3F3F3F);
		buildRenderInfo(MaterialisMaterials.infinity).color(0xFFFFFF).luminosity(15);
		buildRenderInfo(MaterialisMaterials.infinityEmbellishment).color(0xFFFFFF).luminosity(15);
	}

	@Override
	public String getName() {
		return "Materialis Material Render Info Provider";
	}
}
