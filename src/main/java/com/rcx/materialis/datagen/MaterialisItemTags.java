package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;

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

	public final INamedTag<Item> FAIRY_INGOT = ItemTags.bind("forge:ingots/fairy");
	public final INamedTag<Item> FAIRY_BLOCK = ItemTags.bind("forge:storage_blocks/fairy");
	public final INamedTag<Item> FAIRY_NUGGET = ItemTags.bind("forge:nuggets/fairy");

	//custom casts
	public final INamedTag<Item> INLAY_CAST = ItemTags.bind("materialis:casts/multi_use/inlay");
	public final INamedTag<Item> INLAY_CAST_SINGLE = ItemTags.bind("materialis:casts/single_use/inlay");

	//create ingots
	public final INamedTag<Item> REFINED_RADIANCE_INGOT = ItemTags.bind("forge:ingots/refined_radiance");
	public final INamedTag<Item> SHADOW_STEEL_INGOT = ItemTags.bind("forge:ingots/shadow_steel");

	//eidolon stuff
	public final INamedTag<Item> INLAYS = ItemTags.bind("materialis:inlays");
	public final INamedTag<Item> PEWTER_INLAY = ItemTags.bind("materialis:inlays/pewter");
	public final INamedTag<Item> ARCANE_GOLD_INLAY = ItemTags.bind("materialis:inlays/arcane_gold");
	public final INamedTag<Item> PEWTER_BLEND = ItemTags.bind("materialis:pewter_blend");

	//astral sorcery stuff
	public final INamedTag<Item> STARMETAL_INGOT = ItemTags.bind("forge:ingots/starmetal");
	public final INamedTag<Item> STARMETAL_BLOCK = ItemTags.bind("forge:storage_blocks/starmetal");
	public final INamedTag<Item> STARMETAL_ORE = ItemTags.bind("forge:ores/starmetal");

	public MaterialisItemTags(DataGenerator gen, BlockTagsProvider blockTags, ExistingFileHelper existingFileHelper) {
		super(gen, blockTags, Materialis.modID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(FAIRY_INGOT).add(MaterialisResources.FAIRY_INGOT.get());
		this.tag(Tags.Items.INGOTS).addTag(FAIRY_INGOT);
		this.tag(FAIRY_BLOCK).add(MaterialisResources.FAIRY_BLOCK_ITEM.get());
		this.tag(Tags.Items.STORAGE_BLOCKS).addTag(FAIRY_BLOCK);
		this.tag(TinkerTags.Items.ANVIL_METAL).addTag(FAIRY_BLOCK);
		this.tag(FAIRY_NUGGET).add(MaterialisResources.FAIRY_NUGGET.get());
		this.tag(Tags.Items.NUGGETS).addTag(FAIRY_NUGGET);

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
	}
}
