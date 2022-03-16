package com.rcx.materialis.datagen;

import static slimeknights.tconstruct.common.TinkerTags.Items.*;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.MaterialisResources.IngotWithBlockNNugget;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.mantle.registration.object.EnumObject;
import slimeknights.tconstruct.tools.item.ArmorSlotType;

public class MaterialisItemTags extends ItemTagsProvider {

	//custom casts
	public static final Tag.Named<Item> INLAY_CAST = ItemTags.bind(Materialis.modID + ":casts/multi_use/inlay");
	public static final Tag.Named<Item> INLAY_CAST_SINGLE = ItemTags.bind(Materialis.modID + ":casts/single_use/inlay");
	public static final Tag.Named<Item> WRENCH_HEAD_CAST = ItemTags.bind(Materialis.modID + ":casts/multi_use/wrench_head");
	public static final Tag.Named<Item> WRENCH_HEAD_CAST_SINGLE = ItemTags.bind(Materialis.modID + ":casts/single_use/wrench_head");

	//wrench
	public static final Tag.Named<Item> TOOLS = ItemTags.bind("forge:tools");
	public static final Tag.Named<Item> WRENCH = ItemTags.bind("forge:tools/wrench");
	public static final Tag.Named<Item> WRENCHING = ItemTags.bind(Materialis.modID + ":wrench");
	public static final Tag.Named<Item> GALVANIZABLE = ItemTags.bind(Materialis.modID + ":galvanizable");

	//create stuff
	public static final Tag.Named<Item> REFINED_RADIANCE_INGOT = ItemTags.bind("forge:ingots/refined_radiance");
	public static final Tag.Named<Item> SHADOW_STEEL_INGOT = ItemTags.bind("forge:ingots/shadow_steel");

	//eidolon stuff
	public static final Tag.Named<Item> INLAYS = ItemTags.bind("forge:inlays");
	public static final Tag.Named<Item> PEWTER_INLAY = ItemTags.bind("forge:inlays/pewter");
	public static final Tag.Named<Item> ARCANE_GOLD_INLAY = ItemTags.bind("forge:inlays/arcane_gold");

	//astral sorcery stuff
	public static final Tag.Named<Item> STARMETAL_INGOT = ItemTags.bind("forge:ingots/starmetal");
	public static final Tag.Named<Item> STARMETAL_BLOCK = ItemTags.bind("forge:storage_blocks/starmetal");
	public static final Tag.Named<Item> STARMETAL_ORE = ItemTags.bind("forge:ores/starmetal");

	//industrial foregoing stuff
	public static final Tag.Named<Item> PINK_SLIME_INGOT = ItemTags.bind("forge:ingots/pink_slime");
	public static final Tag.Named<Item> PINK_SLIME = ItemTags.bind(Materialis.modID + ":pink_slime");

	//psi stuff
	public static final Tag.Named<Item> SENSOR_SLOTTABLE = ItemTags.bind(Materialis.modID + ":sensor_slottable");
	public static final Tag.Named<Item> SENSOR = ItemTags.bind(Materialis.modID + ":psi_sensor");

	//mythic botany stuff
	public static final Tag.Named<Item> ALFSTEEL_INGOT = ItemTags.bind("forge:ingots/alfsteel");
	public static final Tag.Named<Item> ALFSTEEL_NUGGET = ItemTags.bind("forge:nuggets/alfsteel");
	public static final Tag.Named<Item> ALFSTEEL_BLOCK = ItemTags.bind("forge:storage_blocks/alfsteel");

	//redstone arsenal stuff
	public static final Tag.Named<Item> FLUX_INFUSED_INGOT = ItemTags.bind("forge:ingots/flux_infused");
	public static final Tag.Named<Item> FLUX_INFUSED_NUGGET = ItemTags.bind("forge:nuggets/flux_infused");
	public static final Tag.Named<Item> FLUX_INFUSED_DUST = ItemTags.bind("forge:dusts/flux_infused");
	public static final Tag.Named<Item> FLUX_INFUSED_GEAR = ItemTags.bind("forge:gears/flux_infused");
	public static final Tag.Named<Item> GEARS = ItemTags.bind("forge:gears");
	public static final Tag.Named<Item> FLUX_INFUSED_BLOCK = ItemTags.bind("forge:storage_blocks/flux_infused");

	public MaterialisItemTags(DataGenerator gen, BlockTagsProvider blockTags, ExistingFileHelper existingFileHelper) {
		super(gen, blockTags, Materialis.modID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		for (IngotWithBlockNNugget material : MaterialisResources.materialList) {
			Tag.Named<Item> INGOT = ItemTags.bind("forge:ingots/" + material.name);
			Tag.Named<Item> BLOCK = ItemTags.bind("forge:storage_blocks/" + material.name);
			Tag.Named<Item> NUGGET = ItemTags.bind("forge:nuggets/" + material.name);

			tag(INGOT).add(material.INGOT.get());
			tag(Tags.Items.INGOTS).addTag(INGOT);
			tag(BLOCK).add(material.BLOCK_ITEM.get());
			tag(Tags.Items.STORAGE_BLOCKS).addTag(BLOCK);
			tag(ANVIL_METAL).addTag(BLOCK);
			tag(NUGGET).add(material.NUGGET.get());
			tag(Tags.Items.NUGGETS).addTag(NUGGET);
		}

		//custom casts
		tag(INLAY_CAST).add(MaterialisResources.INLAY_CAST.get());
		tag(INLAY_CAST_SINGLE).add(MaterialisResources.INLAY_CAST.getSand());
		tag(INLAY_CAST_SINGLE).add(MaterialisResources.INLAY_CAST.getRedSand());
		tag(GOLD_CASTS).add(MaterialisResources.INLAY_CAST.get());
		tag(SAND_CASTS).add(MaterialisResources.INLAY_CAST.getSand());
		tag(RED_SAND_CASTS).add(MaterialisResources.INLAY_CAST.getRedSand());
		tag(MULTI_USE_CASTS).addTag(INLAY_CAST);
		tag(SINGLE_USE_CASTS).addTag(INLAY_CAST_SINGLE);
		tag(WRENCH_HEAD_CAST).add(MaterialisResources.WRENCH_HEAD_CAST.get());
		tag(WRENCH_HEAD_CAST_SINGLE).add(MaterialisResources.WRENCH_HEAD_CAST.getSand());
		tag(WRENCH_HEAD_CAST_SINGLE).add(MaterialisResources.WRENCH_HEAD_CAST.getRedSand());
		tag(GOLD_CASTS).add(MaterialisResources.WRENCH_HEAD_CAST.get());
		tag(SAND_CASTS).add(MaterialisResources.WRENCH_HEAD_CAST.getSand());
		tag(RED_SAND_CASTS).add(MaterialisResources.WRENCH_HEAD_CAST.getRedSand());
		tag(MULTI_USE_CASTS).addTag(WRENCH_HEAD_CAST);
		tag(SINGLE_USE_CASTS).addTag(WRENCH_HEAD_CAST_SINGLE);

		//wrench
		tag(TOOLS).addTag(WRENCH);
		tag(TOOL_PARTS).add(MaterialisResources.WRENCH_HEAD.get());
		addAllTags(MaterialisResources.WRENCH, MULTIPART_TOOL, DURABILITY, HARVEST_PRIMARY, MELEE, ONE_HANDED, WRENCH, WRENCHING, GALVANIZABLE, SMELTERY_DEBUG, FOUNDRY_DEBUG);
		addAllTags(MaterialisResources.BATTLEWRENCH, MULTIPART_TOOL, DURABILITY, HARVEST_PRIMARY, MELEE_PRIMARY, TWO_HANDED, WRENCH, WRENCHING, SMELTERY_DEBUG, FOUNDRY_DEBUG);

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
		tag(ANVIL_METAL).addOptionalTag(new ResourceLocation("forge", "storage_blocks/ebony_psimetal"));
		tag(ANVIL_METAL).addOptionalTag(new ResourceLocation("forge", "storage_blocks/ivory_psimetal"));
		/*addArmorTags(MaterialisResources.PSIMETAL_EXOSUIT, DURABILITY);
		tag(SENSOR_SLOTTABLE).add(MaterialisResources.PSIMETAL_EXOSUIT.get(ArmorSlotType.HELMET));
		tag(SENSOR).addOptional(new ResourceLocation("psi", "exosuit_sensor_light"));
		tag(SENSOR).addOptional(new ResourceLocation("psi", "exosuit_sensor_heat"));
		tag(SENSOR).addOptional(new ResourceLocation("psi", "exosuit_sensor_stress"));
		tag(SENSOR).addOptional(new ResourceLocation("psi", "exosuit_sensor_water"));
		tag(SENSOR).addOptional(new ResourceLocation("psi", "exosuit_sensor_trigger"));*/

		//botania stuff
		tag(ANVIL_METAL).addOptionalTag(new ResourceLocation("forge", "storage_blocks/terrasteel"));

		//mythicbotany stuff
		tag(ALFSTEEL_INGOT).addOptional(new ResourceLocation("mythicbotany", "alfsteel_ingot"));
		tag(Tags.Items.INGOTS).addTag(ALFSTEEL_INGOT);
		tag(ALFSTEEL_NUGGET).addOptional(new ResourceLocation("mythicbotany", "alfsteel_nugget"));
		tag(Tags.Items.NUGGETS).addTag(ALFSTEEL_NUGGET);
		tag(ALFSTEEL_BLOCK).addOptional(new ResourceLocation("mythicbotany", "alfsteel_block"));
		tag(Tags.Items.STORAGE_BLOCKS).addTag(ALFSTEEL_BLOCK);
		tag(ANVIL_METAL).addTag(ALFSTEEL_BLOCK);

		//redstone arsenal stuff
		tag(FLUX_INFUSED_INGOT).addOptional(new ResourceLocation("redstone_arsenal", "flux_ingot"));
		tag(Tags.Items.INGOTS).addTag(FLUX_INFUSED_INGOT);
		tag(FLUX_INFUSED_NUGGET).addOptional(new ResourceLocation("redstone_arsenal", "flux_nugget"));
		tag(Tags.Items.NUGGETS).addTag(FLUX_INFUSED_NUGGET);
		tag(FLUX_INFUSED_DUST).addOptional(new ResourceLocation("redstone_arsenal", "flux_dust"));
		tag(Tags.Items.DUSTS).addTag(FLUX_INFUSED_DUST);
		tag(FLUX_INFUSED_GEAR).addOptional(new ResourceLocation("redstone_arsenal", "flux_gear"));
		tag(GEARS).addTag(FLUX_INFUSED_GEAR);
		tag(FLUX_INFUSED_BLOCK).addOptional(new ResourceLocation("redstone_arsenal", "flux_metal_block"));
		tag(Tags.Items.STORAGE_BLOCKS).addTag(FLUX_INFUSED_BLOCK);
		tag(ANVIL_METAL).addTag(FLUX_INFUSED_BLOCK);
	}

	@SafeVarargs
	public final void addAllTags(ItemLike provider, Tag.Named<Item>... tags) {
		Item item = provider.asItem();
		for (Tag.Named<Item> tag : tags) {
			tag(tag).add(item);
		}
	}

	private Tag.Named<Item> getArmorTag(ArmorSlotType slotType) {
		switch (slotType) {
		case BOOTS: return BOOTS;
		case LEGGINGS: return LEGGINGS;
		case CHESTPLATE: return CHESTPLATES;
		case HELMET: return HELMETS;
		}
		return ARMOR;
	}

	@SafeVarargs
	private final void addArmorTags(EnumObject<ArmorSlotType, ? extends Item> armor, Tag.Named<Item>... tags) {
		armor.forEach((type, item) -> {
			for (Tag.Named<Item> tag : tags) {
				this.tag(tag).add(item);
			}
			this.tag(getArmorTag(type)).add(item);
		});
	}
}
