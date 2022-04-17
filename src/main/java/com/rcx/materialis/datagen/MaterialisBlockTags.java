package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.MaterialisResources.IngotWithBlockNNugget;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.TinkerTags;

public class MaterialisBlockTags extends BlockTagsProvider {

	public static final TagKey<Block> WRENCH_BLACKLIST = BlockTags.create(new ResourceLocation(Materialis.modID, "wrench_blacklist"));
	public static final TagKey<Block> MINABLE_WITH_WRENCH = BlockTags.create(new ResourceLocation(Materialis.modID, "mineable/wrench"));

	//astral sorcery stuff
	public static final TagKey<Block> STARMETAL_BLOCK = BlockTags.create(new ResourceLocation("forge", "storage_blocks/starmetal"));
	public static final TagKey<Block> STARMETAL_ORE = BlockTags.create(new ResourceLocation("forge", "ores/starmetal"));

	public MaterialisBlockTags(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, Materialis.modID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		for (IngotWithBlockNNugget material : MaterialisResources.materialList) {
			addBlockTag(material.BLOCK.get(), BlockTags.create(new ResourceLocation("forge", "storage_blocks/" + material.name)));
		}
		tag(BlockTags.NEEDS_IRON_TOOL).addTag(BlockTags.create(new ResourceLocation("forge", "storage_blocks/fairy")));
		
		//wrench
		tag(MINABLE_WITH_WRENCH)
		.addOptionalTag(new ResourceLocation("forge", "mineable/wrench"))
		.addOptionalTag(new ResourceLocation("forge", "mineable/hammer"))
		.addTag(Tags.Blocks.STORAGE_BLOCKS)
		.addTag(Tags.Blocks.BARRELS)
		.addTag(Tags.Blocks.CHESTS)
		.addTag(Tags.Blocks.GLASS)
		.addTag(Tags.Blocks.GLASS_PANES)
		.addTag(BlockTags.BUTTONS)
		.addTag(BlockTags.DOORS)
		.addTag(BlockTags.TRAPDOORS)
		.addTag(BlockTags.PRESSURE_PLATES)
		.addTag(BlockTags.BANNERS)
		.addTag(BlockTags.ANVIL)
		.addTag(BlockTags.RAILS)
		.addTag(BlockTags.BEDS)
		.addTag(BlockTags.SIGNS)
		.addTag(BlockTags.BEACON_BASE_BLOCKS);

		//astral sorcery stuff
		tag(STARMETAL_BLOCK).addOptional(new ResourceLocation("astralsorcery", "starmetal"));
		tag(BlockTags.BEACON_BASE_BLOCKS).addTag(STARMETAL_BLOCK);
		tag(Tags.Blocks.STORAGE_BLOCKS).addTag(STARMETAL_BLOCK);

		tag(STARMETAL_ORE).addOptional(new ResourceLocation("astralsorcery", "starmetal_ore"));
		tag(Tags.Blocks.ORES).addTag(STARMETAL_ORE);

		//psi stuff
		tag(TinkerTags.Blocks.ANVIL_METAL).addOptionalTag(new ResourceLocation("forge", "storage_blocks/ebony_psimetal"));
		tag(TinkerTags.Blocks.ANVIL_METAL).addOptionalTag(new ResourceLocation("forge", "storage_blocks/ivory_psimetal"));

		//immersive engineering stuff
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "workbench"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "circuit_table"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "watermill"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "blastfurnace_preheater"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "fluid_pump"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "sample_drill"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "tesla_coil"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "turret_chem"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "turret_gun"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "cloche"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "coke_oven"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "blast_furnace"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "alloy_smelter"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "advanced_blast_furnace"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "crusher"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "sawmill"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "silo"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "tank"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "arc_furnace"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "assembler"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "auto_workbench"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "bucket_wheel"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "excavator"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "metal_press"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "bottling_machine"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "fermenter"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "squeezer"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "mixer"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "refinery"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "diesel_generator"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "lightning_rod"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "transformer"));
		tag(WRENCH_BLACKLIST).addOptional(new ResourceLocation("immersiveengineering", "transformer_hv"));
	}

	private void addBlockTag(Block block, TagKey<Block> tag) {
		tag(tag).add(block);
		tag(BlockTags.BEACON_BASE_BLOCKS).addTag(tag);
		tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(tag);
		tag(Tags.Blocks.STORAGE_BLOCKS).addTag(tag);
		tag(TinkerTags.Blocks.ANVIL_METAL).addTag(tag);
	}
}
