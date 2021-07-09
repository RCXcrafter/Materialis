package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.MaterialisResources.IngotWithBlockNNugget;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.TinkerTags;

public class MaterialisItemTags extends ItemTagsProvider {

	//custom casts
	public static final INamedTag<Item> INLAY_CAST = ItemTags.bind(Materialis.modID + ":casts/multi_use/inlay");
	public static final INamedTag<Item> INLAY_CAST_SINGLE = ItemTags.bind(Materialis.modID + ":casts/single_use/inlay");
	public static final INamedTag<Item> WRENCH_HEAD_CAST = ItemTags.bind(Materialis.modID + ":casts/multi_use/wrench_head");
	public static final INamedTag<Item> WRENCH_HEAD_CAST_SINGLE = ItemTags.bind(Materialis.modID + ":casts/single_use/wrench_head");

	//wrench
	public static final INamedTag<Item> TOOLS = ItemTags.bind("forge:tools");
	public static final INamedTag<Item> WRENCH = ItemTags.bind("forge:tools/wrench");
	public static final INamedTag<Item> WRENCHING = ItemTags.bind(Materialis.modID + ":wrench");

	//create stuff
	public static final INamedTag<Item> REFINED_RADIANCE_INGOT = ItemTags.bind("forge:ingots/refined_radiance");
	public static final INamedTag<Item> SHADOW_STEEL_INGOT = ItemTags.bind("forge:ingots/shadow_steel");

	//eidolon stuff
	public static final INamedTag<Item> INLAYS = ItemTags.bind("forge:inlays");
	public static final INamedTag<Item> PEWTER_INLAY = ItemTags.bind("forge:inlays/pewter");
	public static final INamedTag<Item> ARCANE_GOLD_INLAY = ItemTags.bind("forge:inlays/arcane_gold");

	//astral sorcery stuff
	public static final INamedTag<Item> STARMETAL_INGOT = ItemTags.bind("forge:ingots/starmetal");
	public static final INamedTag<Item> STARMETAL_BLOCK = ItemTags.bind("forge:storage_blocks/starmetal");
	public static final INamedTag<Item> STARMETAL_ORE = ItemTags.bind("forge:ores/starmetal");

	//industrial foregoing stuff
	public static final INamedTag<Item> PINK_SLIME_INGOT = ItemTags.bind("forge:ingots/pink_slime");
	public static final INamedTag<Item> PINK_SLIME = ItemTags.bind(Materialis.modID + ":pink_slime");

	public MaterialisItemTags(DataGenerator gen, BlockTagsProvider blockTags, ExistingFileHelper existingFileHelper) {
		super(gen, blockTags, Materialis.modID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		for (IngotWithBlockNNugget material : MaterialisResources.materialList) {
			INamedTag<Item> INGOT = ItemTags.bind("forge:ingots/" + material.name);
			INamedTag<Item> BLOCK = ItemTags.bind("forge:storage_blocks/" + material.name);
			INamedTag<Item> NUGGET = ItemTags.bind("forge:nuggets/" + material.name);

			tag(INGOT).add(material.INGOT.get());
			tag(Tags.Items.INGOTS).addTag(INGOT);
			tag(BLOCK).add(material.BLOCK_ITEM.get());
			tag(Tags.Items.STORAGE_BLOCKS).addTag(BLOCK);
			tag(TinkerTags.Items.ANVIL_METAL).addTag(BLOCK);
			tag(NUGGET).add(material.NUGGET.get());
			tag(Tags.Items.NUGGETS).addTag(NUGGET);
		}

		//custom casts
		tag(INLAY_CAST).add(MaterialisResources.INLAY_CAST.get());
		tag(INLAY_CAST_SINGLE).add(MaterialisResources.INLAY_CAST.getSand());
		tag(INLAY_CAST_SINGLE).add(MaterialisResources.INLAY_CAST.getRedSand());
		tag(TinkerTags.Items.GOLD_CASTS).add(MaterialisResources.INLAY_CAST.get());
		tag(TinkerTags.Items.SAND_CASTS).add(MaterialisResources.INLAY_CAST.getSand());
		tag(TinkerTags.Items.RED_SAND_CASTS).add(MaterialisResources.INLAY_CAST.getRedSand());
		tag(TinkerTags.Items.MULTI_USE_CASTS).addTag(INLAY_CAST);
		tag(TinkerTags.Items.SINGLE_USE_CASTS).addTag(INLAY_CAST_SINGLE);
		tag(WRENCH_HEAD_CAST).add(MaterialisResources.WRENCH_HEAD_CAST.get());
		tag(WRENCH_HEAD_CAST_SINGLE).add(MaterialisResources.WRENCH_HEAD_CAST.getSand());
		tag(WRENCH_HEAD_CAST_SINGLE).add(MaterialisResources.WRENCH_HEAD_CAST.getRedSand());
		tag(TinkerTags.Items.GOLD_CASTS).add(MaterialisResources.WRENCH_HEAD_CAST.get());
		tag(TinkerTags.Items.SAND_CASTS).add(MaterialisResources.WRENCH_HEAD_CAST.getSand());
		tag(TinkerTags.Items.RED_SAND_CASTS).add(MaterialisResources.WRENCH_HEAD_CAST.getRedSand());
		tag(TinkerTags.Items.MULTI_USE_CASTS).addTag(WRENCH_HEAD_CAST);
		tag(TinkerTags.Items.SINGLE_USE_CASTS).addTag(WRENCH_HEAD_CAST_SINGLE);

		//wrench
		tag(TOOLS).addTag(WRENCH);
		tag(TinkerTags.Items.TOOL_PARTS).add(MaterialisResources.WRENCH_HEAD.get());
		tag(TinkerTags.Items.MELEE).add(MaterialisResources.WRENCH.get());
		tag(TinkerTags.Items.ONE_HANDED).add(MaterialisResources.WRENCH.get());
		tag(TinkerTags.Items.MULTIPART_TOOL).add(MaterialisResources.WRENCH.get());
		tag(WRENCH).add(MaterialisResources.WRENCH.get());
		tag(WRENCHING).add(MaterialisResources.WRENCH.get());
		tag(TinkerTags.Items.STRUCTURE_DEBUG).add(MaterialisResources.WRENCH.get());
		tag(TinkerTags.Items.MELEE_PRIMARY).add(MaterialisResources.BATTLEWRENCH.get());
		tag(TinkerTags.Items.TWO_HANDED).add(MaterialisResources.BATTLEWRENCH.get());
		tag(TinkerTags.Items.MULTIPART_TOOL).add(MaterialisResources.BATTLEWRENCH.get());
		tag(WRENCH).add(MaterialisResources.BATTLEWRENCH.get());
		tag(WRENCHING).add(MaterialisResources.BATTLEWRENCH.get());
		tag(TinkerTags.Items.STRUCTURE_DEBUG).add(MaterialisResources.BATTLEWRENCH.get());

		//create ingots
		tag(REFINED_RADIANCE_INGOT).addOptional(new ResourceLocation("create", "refined_radiance"));
		tag(Tags.Items.INGOTS).addTag(REFINED_RADIANCE_INGOT);
		tag(SHADOW_STEEL_INGOT).addOptional(new ResourceLocation("create", "shadow_steel"));
		tag(Tags.Items.INGOTS).addTag(SHADOW_STEEL_INGOT);

		//eidolon stuff
		tag(PEWTER_INLAY).addOptional(new ResourceLocation("eidolon", "pewter_inlay"));
		tag(INLAYS).addTag(PEWTER_INLAY);
		tag(ARCANE_GOLD_INLAY).addOptional(new ResourceLocation("eidolon", "gold_inlay"));
		tag(INLAYS).addTag(ARCANE_GOLD_INLAY);

		//astral sorcery stuff
		tag(STARMETAL_INGOT).addOptional(new ResourceLocation("astralsorcery", "starmetal_ingot"));
		tag(Tags.Items.INGOTS).addTag(STARMETAL_INGOT);
		tag(STARMETAL_BLOCK).addOptional(new ResourceLocation("astralsorcery", "starmetal"));
		tag(Tags.Items.STORAGE_BLOCKS).addTag(STARMETAL_BLOCK);
		tag(STARMETAL_ORE).addOptional(new ResourceLocation("astralsorcery", "starmetal_ore"));
		tag(Tags.Items.ORES).addTag(STARMETAL_ORE);

		//industrial foregoing stuff
		tag(PINK_SLIME_INGOT).addOptional(new ResourceLocation("industrialforegoing", "pink_slime_ingot"));
		tag(Tags.Items.INGOTS).addTag(PINK_SLIME_INGOT);
		tag(PINK_SLIME).addOptional(new ResourceLocation("industrialforegoing", "pink_slime"));

		//psi stuff
		tag(TinkerTags.Items.ANVIL_METAL).addOptionalTag(new ResourceLocation("forge", "storage_blocks/ebony_psimetal"));
		tag(TinkerTags.Items.ANVIL_METAL).addOptionalTag(new ResourceLocation("forge", "storage_blocks/ivory_psimetal"));
	}
}
