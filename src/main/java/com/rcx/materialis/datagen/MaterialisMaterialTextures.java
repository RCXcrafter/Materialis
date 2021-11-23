package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;

import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToSpriteTransformer;

public class MaterialisMaterialTextures extends AbstractMaterialSpriteProvider {

	@Override
	public String getName() {
		return "Materialis Material Textures";
	}

	@Override
	protected void addAllMaterials() {
		buildMaterial(MaterialisMaterials.fairy)
		.meleeHarvest()
		.fallbacks("metal")
		.transformer(GreyToSpriteTransformer.builderFromBlack().addTexture(63, getTexture("material/fairy_outline_dark")).addTexture(102, getTexture("material/fairy_outline")).addARGB(140, 0xFFF2268E).addARGB(178, 0xFFFF44A3).addARGB(216, 0xFFFF65B4).addARGB(255, 0xFFFF89C6).build());
		//general oredict materials
		buildMaterial(MaterialisMaterials.brass)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF592521).addARGB(102, 0xFF7C4B34).addARGB(140, 0xFFA46F3E).addARGB(178, 0xFFD0A454).addARGB(216, 0xFFF1DD78).addARGB(255, 0xFFFFFCAE).build());
		buildMaterial(MaterialisMaterials.aluminum)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF4D322C).addARGB(102, 0xFF754B42).addARGB(140, 0xFFB26C5B).addARGB(178, 0xFFD17F6C).addARGB(216, 0xFFEB9886).addARGB(255, 0xFFFFC9BD).build());
		buildMaterial(MaterialisMaterials.nickel)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF6D5F41).addARGB(102, 0xFF7F704B).addARGB(140, 0xFF9B8B66).addARGB(178, 0xFFAA9B75).addARGB(216, 0xFFCEBE8E).addARGB(255, 0xFFF4F1B5).build());
		buildMaterial(MaterialisMaterials.uranium)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF282C24).addARGB(102, 0xFF373E31).addARGB(140, 0xFF485240).addARGB(178, 0xFF59664F).addARGB(216, 0xFF6D7C61).addARGB(255, 0xFF7F9374).build());
		//create materials
		buildMaterial(MaterialisMaterials.roseQuartz)
		.meleeHarvest()
		.fallbacks("rock")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF70283D).addARGB(102, 0xFFA53B57).addARGB(140, 0xFFE06464).addARGB(178, 0xFFFF828A).addARGB(216, 0xFFFFA699).addARGB(255, 0xFFFFDCD1).build());
		buildMaterial(MaterialisMaterials.refinedRadiance)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFFF7F6F2).addARGB(102, 0xFFFCFCFC).addARGB(140, 0xFFBFBEAE).addARGB(178, 0xFFD8D8CD).addARGB(216, 0xFFE8E8DA).addARGB(255, 0xFFF4F2E8).build());
		buildMaterial(MaterialisMaterials.shadowSteel)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF0B0719).addARGB(102, 0xFF2E2742).addARGB(140, 0xFF423C57).addARGB(178, 0xFF4D4861).addARGB(216, 0xFF575366).addARGB(255, 0xFF635D71).build());
		//eidolon materials
		buildMaterial(MaterialisMaterials.pewter)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF24241E).addARGB(102, 0xFF3B3B32).addARGB(140, 0xFF63635A).addARGB(178, 0xFF838379).addARGB(216, 0xFFA1A097).addARGB(255, 0xFFD6D6D4).build());
		buildMaterial(MaterialisMaterials.arcaneGold)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF5E232D).addARGB(102, 0xFF7A3030).addARGB(140, 0xFFB76541).addARGB(178, 0xFFDF9559).addARGB(216, 0xFFF3BF71).addARGB(255, 0xFFFFF0B3).build());
		//aquaculture materials
		buildMaterial(MaterialisMaterials.neptunium)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF013929).addARGB(102, 0xFF056B50).addARGB(140, 0xFF07A378).addARGB(178, 0xFF04D199).addARGB(216, 0xFF17F4B8).addARGB(255, 0xFFB6FBE8).build());
		//mystical world materials
		buildMaterial(MaterialisMaterials.quicksilver)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF825637).addARGB(102, 0xFF8C643B).addARGB(140, 0xFFC28858).addARGB(178, 0xFFBF987B).addARGB(216, 0xFFD0BFA1).addARGB(255, 0xFFF9FCC5).build());
		//astral sorcery materials
		buildMaterial(MaterialisMaterials.starmetal)
		.meleeHarvest()
		.fallbacks("metal")
		.transformer(GreyToSpriteTransformer.builderFromBlack().addARGB(63, 0xFF07062B).addARGB(102, 0xFF070B4F).addTexture(140, getTexture("material/starmetal_0")).addTexture(178, getTexture("material/starmetal_1")).addTexture(216, getTexture("material/starmetal_2")).addTexture(255, getTexture("material/starmetal_3")).build());
		//industrial foregoing materials
		buildMaterial(MaterialisMaterials.plastic)
		.meleeHarvest()
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF535353).addARGB(102, 0xFF686868).addARGB(140, 0xFF797979).addARGB(178, 0xFF959595).addARGB(216, 0xFFB6B6B6).addARGB(255, 0xFFD3D3D3).build());
		buildMaterial(MaterialisMaterials.pinkSlime)
		.meleeHarvest()
		.fallbacks("slime_metal", "metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF602B57).addARGB(102, 0xFF733469).addARGB(140, 0xFFB65DA7).addARGB(178, 0xFFC279B6).addARGB(216, 0xFFD6A5CD).addARGB(255, 0xFFFFEBFF).build());
		//undergarden materials
		buildMaterial(MaterialisMaterials.cloggrum)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF352926).addARGB(102, 0xFF493933).addARGB(140, 0xFF645144).addARGB(178, 0xFF7B6959).addARGB(216, 0xFF968168).addARGB(255, 0xFFBA9D7B).build());
		buildMaterial(MaterialisMaterials.froststeel)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF616A9B).addARGB(102, 0xFF6177A3).addARGB(140, 0xFF6988B0).addARGB(178, 0xFF7D9EBE).addARGB(216, 0xFF8AADCF).addARGB(255, 0xFFA7C4E0).build());
		buildMaterial(MaterialisMaterials.utherium)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF3D1421).addARGB(102, 0xFF65282A).addARGB(140, 0xFF9C3832).addARGB(178, 0xFFCE4650).addARGB(216, 0xFFED5F50).addARGB(255, 0xFFFA9387).build());
		buildMaterial(MaterialisMaterials.forgottenMetal)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF27373A).addARGB(102, 0xFF2F4C4C).addARGB(140, 0xFF279074).addARGB(178, 0xFF34B68B).addARGB(216, 0xFF51D8A4).addARGB(255, 0xFF7BFFBD).build());
		//mekanism materials
		buildMaterial(MaterialisMaterials.refinedObsidian)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF252036).addARGB(102, 0xFF312854).addARGB(140, 0xFF483E5C).addARGB(178, 0xFF62517C).addARGB(216, 0xFF7A67A1).addARGB(255, 0xFFA789CC).build());
		buildMaterial(MaterialisMaterials.refinedGlowstone)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF9E842A).addARGB(102, 0xFFC5A441).addARGB(140, 0xFFEAC940).addARGB(178, 0xFFFFE05A).addARGB(216, 0xFFFFE990).addARGB(255, 0xFFFDF6D9).build());
		//psi materials
		buildMaterial(MaterialisMaterials.psimetal)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF7357BC).addARGB(102, 0xFF7372C1).addARGB(140, 0xFF9E92E1).addARGB(178, 0xFFB0ABF1).addARGB(216, 0xFFC5C9FF).addARGB(255, 0xFFE7E9FD).build());
		buildMaterial(MaterialisMaterials.ebonyPsimetal)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF100C0C).addARGB(102, 0xFF151111).addARGB(140, 0xFF191715).addARGB(178, 0xFF201C1B).addARGB(216, 0xFF272222).addARGB(255, 0xFF2F2E2E).build());
		buildMaterial(MaterialisMaterials.ivoryPsimetal)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFFB4B187).addARGB(102, 0xFFC4C3A4).addARGB(140, 0xFFD3D3C2).addARGB(178, 0xFFE3E5D8).addARGB(216, 0xFFF2F4E6).addARGB(255, 0xFFFCFCFC).build());
		//occultism materials
		buildMaterial(MaterialisMaterials.iesnium)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF233C57).addARGB(102, 0xFF345F7C).addARGB(140, 0xFF3E8C9D).addARGB(178, 0xFF56A7B9).addARGB(216, 0xFF7DCDD8).addARGB(255, 0xFFD0F9FC).build());
		//botania materials
		buildMaterial(MaterialisMaterials.livingwood)
		.meleeHarvest()
		.fallbacks("wood", "stick")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF130804).addARGB(102, 0xFF180904).addARGB(140, 0xFF1E0D07).addARGB(178, 0xFF271108).addARGB(216, 0xFF33140A).addARGB(255, 0xFF421909).build());
		buildMaterial(MaterialisMaterials.dreamwood)
		.meleeHarvest()
		.fallbacks("wood", "stick")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF83959B).addARGB(102, 0xFF95A5AB).addARGB(140, 0xFFA6B3B7).addARGB(178, 0xFFAFBDC1).addARGB(216, 0xFFBAC4C7).addARGB(255, 0xFFC1CCCC).build());
		buildMaterial(MaterialisMaterials.manasteel)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF083772).addARGB(102, 0xFF0A4998).addARGB(140, 0xFF0C5FC6).addARGB(178, 0xFF1776ED).addARGB(216, 0xFF5BA3FF).addARGB(255, 0xFFBDD9FF).build());
		buildMaterial(MaterialisMaterials.elementium)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFFBB0067).addARGB(102, 0xFFE90081).addARGB(140, 0xFFEE45A3).addARGB(178, 0xFFF26DB6).addARGB(216, 0xFFF592C8).addARGB(255, 0xFFFACAE4).build());
		buildMaterial(MaterialisMaterials.terrasteel)
		.meleeHarvest()
		.fallbacks("metal")
		.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF176600).addARGB(102, 0xFF259300).addARGB(140, 0xFF2EB800).addARGB(178, 0xFF35DA00).addARGB(216, 0xFF5DF82A).addARGB(255, 0xFFA7FF8B).build());
	}

	public static ResourceLocation getTexture(String path) {
		return new ResourceLocation(Materialis.modID, path);
	}
}
