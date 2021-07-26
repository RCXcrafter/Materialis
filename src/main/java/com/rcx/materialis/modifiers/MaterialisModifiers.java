package com.rcx.materialis.modifiers;

import com.rcx.materialis.Materialis;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ExtraModifier;
import slimeknights.tconstruct.library.modifiers.ExtraModifier.ExtraType;
import slimeknights.tconstruct.library.modifiers.ExtraModifier.ModifierSource;

public class MaterialisModifiers {
	public static final DeferredRegister<Modifier> MODIFIERS = DeferredRegister.create(Modifier.class, Materialis.modID);

	//traits
	public static final RegistryObject<Modifier> enhancedQuartzModifier = MODIFIERS.register("enhanced_quartz", () -> new ExtraModifier(0xFF8C80, ExtraType.UPGRADE, ModifierSource.TRAIT));
	public static final RegistryObject<Modifier> voidingModifier = MODIFIERS.register("voiding", VoidingModifier::new);
	public static final RegistryObject<Modifier> residualLightModifier = MODIFIERS.register("residual_light", ResidualLightModifier::new);
	public static final RegistryObject<Modifier> inertiaModifier = MODIFIERS.register("inertia", InertiaModifier::new);
	public static final RegistryObject<Modifier> featherweightModifier = MODIFIERS.register("featherweight", FeatherweightModifier::new);
	public static final RegistryObject<Modifier> arcaneModifier = MODIFIERS.register("arcane", ArcaneModifier::new);
	public static final RegistryObject<Modifier> neptunesBlessingModifier = MODIFIERS.register("neptunes_blessing", NeptunesBlessingModifier::new);
	public static final RegistryObject<Modifier> halfLifeModifier = MODIFIERS.register("half_life", HalfLifeModifier::new);
	public static final RegistryObject<Modifier> workHardenedModifier = MODIFIERS.register("work_hardened", WorkHardenedModifier::new);
	public static final RegistryObject<Modifier> polishedModifier = MODIFIERS.register("polished", PolishedModifier::new);
	public static final RegistryObject<Modifier> daredevilModifier = MODIFIERS.register("daredevil", DaredevilModifier::new);
	public static final RegistryObject<Modifier> decayModifier = MODIFIERS.register("decay", DecayModifier::new);
	public static final RegistryObject<Modifier> nocturnalModifier = MODIFIERS.register("nocturnal", NocturnalModifier::new);
	public static final RegistryObject<Modifier> feebleModifier = MODIFIERS.register("feeble", FeebleModifier::new);
	public static final RegistryObject<Modifier> overweightModifier = MODIFIERS.register("overweight", OverweightModifier::new);
	public static final RegistryObject<Modifier> economicalModifier = MODIFIERS.register("economical", EconomicalModifier::new);
	public static final RegistryObject<Modifier> oldTimerModifier = MODIFIERS.register("old_timer", OldTimerModifier::new);
	public static final RegistryObject<Modifier> freezingModifier = MODIFIERS.register("freezing", FreezingModifier::new);
	public static final RegistryObject<Modifier> cleansingModifier = MODIFIERS.register("cleansing", CleansingModifier::new);
	public static final RegistryObject<Modifier> underlordModifier = MODIFIERS.register("underlord", UnderlordModifier::new);
	public static final RegistryObject<Modifier> shortSightedModifier = MODIFIERS.register("short_sighted", ShortSightedModifier::new);
	public static final RegistryObject<Modifier> auxiliaryPowerModifier = MODIFIERS.register("auxiliary_power", AuxiliaryPowerModifier::new);
	public static final RegistryObject<Modifier> adrenalineModifier = MODIFIERS.register("adrenaline", AdrenalineModifier::new);
	public static final RegistryObject<Modifier> psionizingRadiationModifier = MODIFIERS.register("psionizing_radiation", PsionizingRadiationModifier::new);
	public static final RegistryObject<Modifier> psichoKillerModifier = MODIFIERS.register("psicho_killer", PsichoKillerModifier::new);
	public static final RegistryObject<Modifier> psichoDiggerModifier = MODIFIERS.register("psicho_digger", PsichoDiggerModifier::new);
	public static final RegistryObject<Modifier> brittleModifier = MODIFIERS.register("brittle", BrittleModifier::new);
	public static final RegistryObject<Modifier> refuelingModifier = MODIFIERS.register("refueling", RefuelingModifier::new);
	public static final RegistryObject<Modifier> quenchingModifier = MODIFIERS.register("quenching", QuenchingModifier::new);
	public static final RegistryObject<Modifier> otherworldly1Modifier = MODIFIERS.register("otherworldly_1", () -> new OtherworldlyModifier(1));
	public static final RegistryObject<Modifier> otherworldly2Modifier = MODIFIERS.register("otherworldly_2", () -> new OtherworldlyModifier(2));

	//upgrades & abilities
	public static final RegistryObject<Modifier> reapingModifier = MODIFIERS.register("reaping", ReapingModifier::new);
	public static final RegistryObject<Modifier> runedModifier = MODIFIERS.register("runed", RunedModifier::new);
	public static final RegistryObject<Modifier> wrenchingModifier = MODIFIERS.register("wrenching", () -> new WrenchingModifier(0x8A361E, 75));
	public static final RegistryObject<Modifier> thermalWrenchingModifier = MODIFIERS.register("thermal_wrenching", ThermalWrenchingModifier::new);
	public static final RegistryObject<Modifier> createWrenchingModifier = MODIFIERS.register("create_wrenching", CreateWrenchingModifier::new);
	public static final RegistryObject<Modifier> galvanizedModifier = MODIFIERS.register("galvanized", () -> new ExtraModifier(0xA8B4BB, ExtraType.ABILITY, ModifierSource.MULTI_LEVEL));
	public static final RegistryObject<Modifier> spellSocketModifier = MODIFIERS.register("spell_socket", SpellSocketModifier::new);
	public static final RegistryObject<Modifier> colorizedModifier = MODIFIERS.register("colorized", ColorizedModifier::new);

	//internal modifiers
	public static final RegistryObject<Modifier> wrenchingModifierHidden = MODIFIERS.register("wrenching_hidden", () -> new WrenchingModifier(0xFFFFFF, Integer.MIN_VALUE + 50));
}
