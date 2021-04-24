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

public class MaterialisItemTags extends ItemTagsProvider {

	public final INamedTag<Item> FAIRY_INGOT = ItemTags.bind(new ResourceLocation("forge", "ingots/fairy").toString());
	public final INamedTag<Item> FAIRY_BLOCK = ItemTags.bind(new ResourceLocation("forge", "storage_blocks/fairy").toString());
	public final INamedTag<Item> FAIRY_NUGGET = ItemTags.bind(new ResourceLocation("forge", "nuggets/fairy").toString());

	//create ingots
	public final INamedTag<Item> REFINED_RADIANCE_INGOT = ItemTags.bind(new ResourceLocation("forge", "ingots/refined_radiance").toString());
	public final INamedTag<Item> SHADOW_STEEL_INGOT = ItemTags.bind(new ResourceLocation("forge", "ingots/shadow_steel").toString());

	public MaterialisItemTags(DataGenerator gen, BlockTagsProvider blockTags, ExistingFileHelper existingFileHelper) {
		super(gen, blockTags, Materialis.modID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(FAIRY_INGOT).add(MaterialisResources.FAIRY_INGOT.get());
		this.tag(Tags.Items.INGOTS).addTag(FAIRY_INGOT);
		this.tag(FAIRY_BLOCK).add(MaterialisResources.FAIRY_BLOCK_ITEM.get());
		this.tag(Tags.Items.NUGGETS).addTag(FAIRY_NUGGET);
		this.tag(FAIRY_NUGGET).add(MaterialisResources.FAIRY_NUGGET.get());

		//create ingots
		this.tag(REFINED_RADIANCE_INGOT).addOptional(new ResourceLocation("create", "refined_radiance"));
		this.tag(Tags.Items.INGOTS).addTag(REFINED_RADIANCE_INGOT);
		this.tag(SHADOW_STEEL_INGOT).addOptional(new ResourceLocation("create", "shadow_steel"));
		this.tag(Tags.Items.INGOTS).addTag(SHADOW_STEEL_INGOT);
	}
}
