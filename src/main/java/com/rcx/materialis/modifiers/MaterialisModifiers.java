package com.rcx.materialis.modifiers;

import com.rcx.materialis.Materialis;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import slimeknights.tconstruct.library.modifiers.Modifier;

public class MaterialisModifiers {
	public static final DeferredRegister<Modifier> MODIFIERS = DeferredRegister.create(Modifier.class, Materialis.modID);

	public static final RegistryObject<Modifier> voidingModifier = MODIFIERS.register("voiding", VoidingModifier::new);
	public static final RegistryObject<Modifier> residualLightModifier = MODIFIERS.register("residual_light", ResidualLightModifier::new);
	public static final RegistryObject<Modifier> inertiaModifier = MODIFIERS.register("inertia", InertiaModifier::new);
	public static final RegistryObject<Modifier> featherweightModifier = MODIFIERS.register("featherweight", FeatherweightModifier::new);
	public static final RegistryObject<Modifier> arcaneModifier = MODIFIERS.register("arcane", ArcaneModifier::new);
	public static final RegistryObject<Modifier> reapingModifier = MODIFIERS.register("reaping", ReapingModifier::new);
	public static final RegistryObject<Modifier> neptunesBlessingModifier = MODIFIERS.register("neptunes_blessing", NeptunesBlessingModifier::new);
}
