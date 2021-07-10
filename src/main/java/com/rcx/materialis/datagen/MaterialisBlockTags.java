package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.MaterialisResources.IngotWithBlockNNugget;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.TinkerTags;

public class MaterialisBlockTags extends BlockTagsProvider {

	//astral sorcery stuff
	public static final INamedTag<Block> STARMETAL_BLOCK = BlockTags.bind("forge:storage_blocks/starmetal");
	public static final INamedTag<Block> STARMETAL_ORE = BlockTags.bind("forge:ores/starmetal");

	//wrench
	public static final INamedTag<Block> CREATE_WRENCH_PICKUP = BlockTags.bind("create:wrench_pickup");

	public MaterialisBlockTags(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, Materialis.modID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		for (IngotWithBlockNNugget material : MaterialisResources.materialList) {
			addBlockTag(material.BLOCK.get(), BlockTags.bind("forge:storage_blocks/" + material.name));
		}

		//astral sorcery stuff
		tag(STARMETAL_BLOCK).addOptional(new ResourceLocation("astralsorcery", "starmetal"));
		tag(BlockTags.BEACON_BASE_BLOCKS).addTag(STARMETAL_BLOCK);
		tag(Tags.Blocks.STORAGE_BLOCKS).addTag(STARMETAL_BLOCK);

		tag(STARMETAL_ORE).addOptional(new ResourceLocation("astralsorcery", "starmetal_ore"));
		tag(Tags.Blocks.ORES).addTag(STARMETAL_ORE);

		//psi stuff
		tag(TinkerTags.Blocks.ANVIL_METAL).addOptionalTag(new ResourceLocation("forge", "storage_blocks/ebony_psimetal"));
		tag(TinkerTags.Blocks.ANVIL_METAL).addOptionalTag(new ResourceLocation("forge", "storage_blocks/ivory_psimetal"));

		/*tag(BlockTags.bind(new ResourceLocation(Materialis.MODID, "test").toString()))
		.add(Blocks.DIAMOND_BLOCK)
		.addTag(BlockTags.STONE_BRICKS)
		.addTag(net.minecraftforge.common.Tags.Blocks.COBBLESTONE)
		.addOptional(new ResourceLocation("chisel", "marble/raw"))
		.addOptionalTag(new ResourceLocation("forge", "storage_blocks/ruby"));

		// Hopefully sorting issues
		tag(BlockTags.bind(new ResourceLocation(Materialis.MODID, "thing/one").toString()))
		.add(Blocks.COBBLESTONE);
		tag(BlockTags.bind(new ResourceLocation(Materialis.MODID, "thing/two").toString()))
		.add(Blocks.DIORITE);
		tag(BlockTags.bind(new ResourceLocation(Materialis.MODID, "thing/three").toString()))
		.add(Blocks.ANDESITE);

		tag(BlockTags.bind(new ResourceLocation(Materialis.MODID, "things").toString()))
		.add(Blocks.COBBLESTONE)
		.add(Blocks.DIORITE)
		.add(Blocks.ANDESITE);*/
	}

	private void addBlockTag(Block block, INamedTag<Block> tag) {
		tag(tag).add(block);
		tag(BlockTags.BEACON_BASE_BLOCKS).addTag(tag);
		tag(Tags.Blocks.STORAGE_BLOCKS).addTag(tag);
		tag(TinkerTags.Blocks.ANVIL_METAL).addTag(tag);
	}
}
