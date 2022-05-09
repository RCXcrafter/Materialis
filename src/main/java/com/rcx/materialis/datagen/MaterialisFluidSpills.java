package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import slimeknights.tconstruct.library.data.tinkering.AbstractSpillingFluidProvider;
import slimeknights.tconstruct.library.modifiers.spilling.effects.EffectSpillingEffect;
import slimeknights.tconstruct.library.recipe.FluidValues;

public class MaterialisFluidSpills extends AbstractSpillingFluidProvider {

	public MaterialisFluidSpills(DataGenerator generator) {
		super(generator, Materialis.modID);
	}

	@Override
	protected void addFluids() {
		addFluid(MaterialisResources.REFINED_RADIANCE_FLUID.OBJECT.getForgeTag(), FluidValues.NUGGET)
		.condition(new ModLoadedCondition("create"))
		.addEffect(new EffectSpillingEffect(MobEffects.GLOWING, 800, 1));
		addFluid(MaterialisFluidTags.LIQUID_STARLIGHT, 50)
		.condition(new ModLoadedCondition("astralsorcery"))
		.addEffect(new EffectSpillingEffect(MobEffects.NIGHT_VISION, 100, 1));
		addFluid(MaterialisFluidTags.VIRULENT_MIX, 50)
		.condition(new ModLoadedCondition("undergarden"))
		.addEffect(new EffectSpillingEffect(MobEffects.POISON, 50, 1));
	}

	@Override
	public String getName() {
		return "Materialis Spilling Fluid Provider";
	}
}
