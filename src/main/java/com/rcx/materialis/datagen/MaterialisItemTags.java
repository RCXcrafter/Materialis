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

	//create stuff
	public static final INamedTag<Item> ROSE_QUARTZ = ItemTags.bind(Materialis.modID + ":rose_quartz");
	public static final INamedTag<Item> REFINED_RADIANCE_INGOT = ItemTags.bind("forge:ingots/refined_radiance");
	public static final INamedTag<Item> SHADOW_STEEL_INGOT = ItemTags.bind("forge:ingots/shadow_steel");

	//eidolon stuff
	public static final INamedTag<Item> INLAYS = ItemTags.bind(Materialis.modID + ":inlays");
	public static final INamedTag<Item> PEWTER_INLAY = ItemTags.bind(Materialis.modID + ":inlays/pewter");
	public static final INamedTag<Item> ARCANE_GOLD_INLAY = ItemTags.bind(Materialis.modID + ":inlays/arcane_gold");
	public static final INamedTag<Item> PEWTER_BLEND = ItemTags.bind(Materialis.modID + ":pewter_blend");

	//astral sorcery stuff
	public static final INamedTag<Item> STARMETAL_INGOT = ItemTags.bind("forge:ingots/starmetal");
	public static final INamedTag<Item> STARMETAL_BLOCK = ItemTags.bind("forge:storage_blocks/starmetal");
	public static final INamedTag<Item> STARMETAL_ORE = ItemTags.bind("forge:ores/starmetal");

	//industrial foregoing stuff
	public static final INamedTag<Item> PINK_SLIME_INGOT = ItemTags.bind("forge:ingots/pink_slime");
	public static final INamedTag<Item> PINK_SLIME = ItemTags.bind(Materialis.modID + ":pink_slime");

	//undergarden stuff
	public static final INamedTag<Item> UTHERIC_SHARD = ItemTags.bind(Materialis.modID + ":utheric_shard");

	public MaterialisItemTags(DataGenerator gen, BlockTagsProvider blockTags, ExistingFileHelper existingFileHelper) {
		super(gen, blockTags, Materialis.modID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		for (IngotWithBlockNNugget material : MaterialisResources.materialList) {
			INamedTag<Item> INGOT = ItemTags.bind("forge:ingots/" + material.name);
			INamedTag<Item> BLOCK = ItemTags.bind("forge:storage_blocks/" + material.name);
			INamedTag<Item> NUGGET = ItemTags.bind("forge:nuggets/" + material.name);

			this.tag(INGOT).add(material.INGOT.get());
			this.tag(Tags.Items.INGOTS).addTag(INGOT);
			this.tag(BLOCK).add(material.BLOCK_ITEM.get());
			this.tag(Tags.Items.STORAGE_BLOCKS).addTag(BLOCK);
			this.tag(TinkerTags.Items.ANVIL_METAL).addTag(BLOCK);
			this.tag(NUGGET).add(material.NUGGET.get());
			this.tag(Tags.Items.NUGGETS).addTag(NUGGET);
		}

		//custom casts
		this.tag(INLAY_CAST).add(MaterialisResources.INLAY_CAST.get());
		this.tag(INLAY_CAST_SINGLE).add(MaterialisResources.INLAY_CAST.getSand());
		this.tag(INLAY_CAST_SINGLE).add(MaterialisResources.INLAY_CAST.getRedSand());
		this.tag(TinkerTags.Items.GOLD_CASTS).add(MaterialisResources.INLAY_CAST.get());
		this.tag(TinkerTags.Items.SAND_CASTS).add(MaterialisResources.INLAY_CAST.getSand());
		this.tag(TinkerTags.Items.RED_SAND_CASTS).add(MaterialisResources.INLAY_CAST.getRedSand());
		this.tag(TinkerTags.Items.MULTI_USE_CASTS).addTag(INLAY_CAST);
		this.tag(TinkerTags.Items.SINGLE_USE_CASTS).addTag(INLAY_CAST_SINGLE);

		//create ingots
		this.tag(ROSE_QUARTZ).addOptional(new ResourceLocation("create", "polished_rose_quartz"));
		this.tag(REFINED_RADIANCE_INGOT).addOptional(new ResourceLocation("create", "refined_radiance"));
		this.tag(Tags.Items.INGOTS).addTag(REFINED_RADIANCE_INGOT);
		this.tag(SHADOW_STEEL_INGOT).addOptional(new ResourceLocation("create", "shadow_steel"));
		this.tag(Tags.Items.INGOTS).addTag(SHADOW_STEEL_INGOT);

		//eidolon stuff
		this.tag(PEWTER_INLAY).addOptional(new ResourceLocation("eidolon", "pewter_inlay"));
		this.tag(INLAYS).addTag(PEWTER_INLAY);
		this.tag(ARCANE_GOLD_INLAY).addOptional(new ResourceLocation("eidolon", "gold_inlay"));
		this.tag(INLAYS).addTag(ARCANE_GOLD_INLAY);
		this.tag(PEWTER_BLEND).addOptional(new ResourceLocation("eidolon", "pewter_blend"));

		//astral sorcery stuff
		this.tag(STARMETAL_INGOT).addOptional(new ResourceLocation("astralsorcery", "starmetal_ingot"));
		this.tag(Tags.Items.INGOTS).addTag(STARMETAL_INGOT);
		this.tag(STARMETAL_BLOCK).addOptional(new ResourceLocation("astralsorcery", "starmetal"));
		this.tag(Tags.Items.STORAGE_BLOCKS).addTag(STARMETAL_BLOCK);
		this.tag(STARMETAL_ORE).addOptional(new ResourceLocation("astralsorcery", "starmetal_ore"));
		this.tag(Tags.Items.ORES).addTag(STARMETAL_ORE);

		//industrial foregoing stuff
		this.tag(PINK_SLIME_INGOT).addOptional(new ResourceLocation("industrialforegoing", "pink_slime_ingot"));
		this.tag(Tags.Items.INGOTS).addTag(PINK_SLIME_INGOT);
		this.tag(PINK_SLIME).addOptional(new ResourceLocation("industrialforegoing", "pink_slime"));

		//undergarden stuff
		this.tag(UTHERIC_SHARD).addOptional(new ResourceLocation("undergarden", "utheric_shard"));
	}
}
