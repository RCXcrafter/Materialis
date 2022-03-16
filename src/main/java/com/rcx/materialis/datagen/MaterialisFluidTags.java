package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.MaterialisResources.FluidWithBlockNBucket;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.TinkerTags;

public class MaterialisFluidTags extends FluidTagsProvider {

	public static final Tag.Named<Fluid> LIQUID_PINK_SLIME = FluidTags.bind(Materialis.modID + ":pink_slime");
	public static final Tag.Named<Fluid> VIRULENT_MIX = FluidTags.bind(Materialis.modID + ":virulent_mix");
	public static final Tag.Named<Fluid> LIQUID_STARLIGHT = FluidTags.bind(Materialis.modID + ":liquid_starlight");

	public MaterialisFluidTags(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, Materialis.modID, existingFileHelper);
	}

	@Override
	public void addTags() {
		for (FluidWithBlockNBucket fluid : MaterialisResources.fluidList) {
			tag(fluid.OBJECT.getLocalTag()).add(fluid.FLUID.get());
			tag(fluid.OBJECT.getForgeTag()).add(fluid.FLUID.get());
			tag(TinkerTags.Fluids.METAL_TOOLTIPS).addTag(fluid.OBJECT.getForgeTag());
		}

		tag(LIQUID_PINK_SLIME).addOptional(new ResourceLocation("industrialforegoing", "pink_slime"));
		tag(VIRULENT_MIX).addOptional(new ResourceLocation("undergarden", "virulent_mix_source"));
		tag(LIQUID_STARLIGHT).addOptional(new ResourceLocation("astralsorcery", "liquid_starlight"));

		tag(TinkerTags.Fluids.CHEAP_METAL_SPILLING)
		.addTag(MaterialisResources.QUICKSILVER_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.CLOGGRUM_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.FROSTSTEEL_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.UTHERIUM_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.REGALIUM_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.IESNIUM_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.MANASTEEL_FLUID.OBJECT.getForgeTag());
		tag(TinkerTags.Fluids.AVERAGE_METAL_SPILLING)
		.addTag(MaterialisResources.ARCANE_GOLD_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.STARMETAL_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.PINK_SLIME_FLUID.OBJECT.getForgeTag())
		//.addTag(MaterialisResources.REFINED_OBSIDIAN_FLUID.OBJECT.getForgeTag())
		//.addTag(MaterialisResources.REFINED_GLOWSTONE_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.PSIMETAL_FLUID.OBJECT.getForgeTag());
		tag(TinkerTags.Fluids.EXPENSIVE_METAL_SPILLING)
		.addTag(MaterialisResources.REFINED_RADIANCE_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.SHADOW_STEEL_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.NEPTUNIUM_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.FORGOTTEN_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.EBONY_PSIMETAL_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.IVORY_PSIMETAL_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.ELEMENTIUM_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.TERRASTEEL_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.ALFSTEEL_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.DRACONIUM_FLUID.OBJECT.getForgeTag())
		.addTag(MaterialisResources.AWAKENED_DRACONIUM_FLUID.OBJECT.getForgeTag());
	}
}
