package com.rcx.materialis.modifiers;

import com.rcx.materialis.Materialis;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.tools.modifiers.shared.ExtraModifier;
import slimeknights.tconstruct.tools.modifiers.shared.ExtraModifier.ExtraType;
import slimeknights.tconstruct.tools.modifiers.shared.ExtraModifier.ModifierSource;

public class MaterialisModifiers {
	public static final DeferredRegister<Modifier> MODIFIERS = DeferredRegister.create(Modifier.class, Materialis.modID);

	public static final RegistryObject<Modifier> enhancedQuartzModifier = MODIFIERS.register("enhanced_quartz", () -> new ExtraModifier(0xFF8C80, ExtraType.UPGRADE, ModifierSource.TRAIT));
	public static final RegistryObject<Modifier> voidingModifier = MODIFIERS.register("voiding", VoidingModifier::new);
	public static final RegistryObject<Modifier> residualLightModifier = MODIFIERS.register("residual_light", ResidualLightModifier::new);
	public static final RegistryObject<Modifier> inertiaModifier = MODIFIERS.register("inertia", InertiaModifier::new);
	public static final RegistryObject<Modifier> featherweightModifier = MODIFIERS.register("featherweight", FeatherweightModifier::new);
	public static final RegistryObject<Modifier> arcaneModifier = MODIFIERS.register("arcane", ArcaneModifier::new);
	public static final RegistryObject<Modifier> reapingModifier = MODIFIERS.register("reaping", ReapingModifier::new);
	public static final RegistryObject<Modifier> neptunesBlessingModifier = MODIFIERS.register("neptunes_blessing", NeptunesBlessingModifier::new);
	public static final RegistryObject<Modifier> halfLifeModifier = MODIFIERS.register("half_life", HalfLifeModifier::new);
	public static final RegistryObject<Modifier> workHardenedModifier = MODIFIERS.register("work_hardened", WorkHardenedModifier::new);
}
