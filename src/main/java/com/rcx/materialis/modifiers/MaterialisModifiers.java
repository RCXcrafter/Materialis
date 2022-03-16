package com.rcx.materialis.modifiers;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisResources;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.impl.ExtraModifier;
import slimeknights.tconstruct.library.modifiers.impl.ExtraModifier.ModifierSource;
import slimeknights.tconstruct.library.tools.SlotType;

public class MaterialisModifiers {
	public static final DeferredRegister<Modifier> MODIFIERS = DeferredRegister.create(Modifier.class, Materialis.modID);

	//traits
	public static final RegistryObject<Modifier> enhancedQuartzModifier = MODIFIERS.register("enhanced_quartz", () -> new ExtraModifier(SlotType.UPGRADE, ModifierSource.TRAIT));
	public static final RegistryObject<Modifier> voidingModifier = MODIFIERS.register("voiding", VoidingModifier::new);
	public static final RegistryObject<Modifier> residualLightModifier = MODIFIERS.register("residual_light", ResidualLightModifier::new);
	public static final RegistryObject<Modifier> inertiaModifier = MODIFIERS.register("inertia", InertiaModifier::new);
	public static final RegistryObject<Modifier> featherweightModifier = MODIFIERS.register("featherweight", FeatherweightModifier::new);
	public static final RegistryObject<Modifier> arcaneModifier = MODIFIERS.register("arcane", ArcaneModifier::new);
	public static final RegistryObject<Modifier> neptunesBlessingModifier = MODIFIERS.register("neptunes_blessing", NeptunesBlessingModifier::new);
	public static final RegistryObject<Modifier> halfLifeModifier = MODIFIERS.register("half_life", HalfLifeModifier::new);
	public static final RegistryObject<Modifier> workHardenedModifier = MODIFIERS.register("work_hardened", WorkHardenedModifier::new); //unused
	public static final RegistryObject<Modifier> polishedModifier = MODIFIERS.register("polished", PolishedModifier::new);
	public static final RegistryObject<Modifier> daredevilModifier = MODIFIERS.register("daredevil", DaredevilModifier::new);
	public static final RegistryObject<Modifier> decayModifier = MODIFIERS.register("decay", DecayModifier::new);
	public static final RegistryObject<Modifier> nocturnalModifier = MODIFIERS.register("nocturnal", NocturnalModifier::new);
	public static final RegistryObject<Modifier> feebleModifier = MODIFIERS.register("feeble", FeebleModifier::new);
	public static final RegistryObject<Modifier> overweightModifier = MODIFIERS.register("overweight", OverweightModifier::new); //unused
	public static final RegistryObject<Modifier> economicalModifier = MODIFIERS.register("economical", EconomicalModifier::new);
	public static final RegistryObject<Modifier> oldTimerModifier = MODIFIERS.register("old_timer", OldTimerModifier::new);
	public static final RegistryObject<Modifier> freezingModifier = MODIFIERS.register("freezing", FreezingModifier::new);
	public static final RegistryObject<Modifier> cleansingModifier = MODIFIERS.register("cleansing", CleansingModifier::new);
	public static final RegistryObject<Modifier> underlordModifier = MODIFIERS.register("underlord", UnderlordModifier::new);
	public static final RegistryObject<Modifier> shortSightedModifier = MODIFIERS.register("short_sighted", ShortSightedModifier::new);
	public static final RegistryObject<Modifier> auxiliaryPowerModifier = MODIFIERS.register("auxiliary_power", AuxiliaryPowerModifier::new);
	public static final RegistryObject<Modifier> adrenalineModifier = MODIFIERS.register("adrenaline", AdrenalineModifier::new); //unused
	public static final RegistryObject<Modifier> psionizingRadiationModifier = MODIFIERS.register("psionizing_radiation", PsionizingRadiationModifier::new);
	public static final RegistryObject<Modifier> lesserPsionizingRadiationModifier = MODIFIERS.register("lesser_psionizing_radiation", PsionizingRadiationModifierLesser::new);
	public static final RegistryObject<Modifier> psionizingRadiationModifierBreakBlock = MODIFIERS.register("psionizing_radiation_break_block", PsionizingRadiationModifierBreakBlock::new); //unused
	public static final RegistryObject<Modifier> psionizingRadiationModifierAttack = MODIFIERS.register("psionizing_radiation_attack", PsionizingRadiationModifierAttack::new); //unused
	public static final RegistryObject<Modifier> psionizingRadiationModifierDamage = MODIFIERS.register("psionizing_radiation_damage", PsionizingRadiationModifierDamage::new);
	public static final RegistryObject<Modifier> psionizingRadiationModifierTick = MODIFIERS.register("psionizing_radiation_tick", PsionizingRadiationModifierTick::new);
	public static final RegistryObject<Modifier> psionizingRadiationModifierJump = MODIFIERS.register("psionizing_radiation_jump", PsionizingRadiationModifierJump::new);
	public static final RegistryObject<Modifier> psionizingRadiationModifierFire = MODIFIERS.register("psionizing_radiation_fire", PsionizingRadiationModifierFire::new); //unused
	public static final RegistryObject<Modifier> psionizingRadiationModifierHealth = MODIFIERS.register("psionizing_radiation_health", PsionizingRadiationModifierHealth::new); //unused
	public static final RegistryObject<Modifier> psionizingRadiationModifierLight = MODIFIERS.register("psionizing_radiation_light", PsionizingRadiationModifierLight::new); //unused
	public static final RegistryObject<Modifier> psionizingRadiationModifierWater = MODIFIERS.register("psionizing_radiation_water", PsionizingRadiationModifierWater::new); //unused
	public static final RegistryObject<Modifier> psionizingRadiationModifierDetonate = MODIFIERS.register("psionizing_radiation_detonate", PsionizingRadiationModifierDetonate::new); //unused
	public static final RegistryObject<Modifier> psichoKillerModifier = MODIFIERS.register("psicho_killer", PsichoKillerModifier::new);
	public static final RegistryObject<Modifier> psichoDiggerModifier = MODIFIERS.register("psicho_digger", PsichoDiggerModifier::new);
	public static final RegistryObject<Modifier> brittleModifier = MODIFIERS.register("brittle", BrittleModifier::new); //unused
	public static final RegistryObject<Modifier> refuelingModifier = MODIFIERS.register("refueling", RefuelingModifier::new);
	public static final RegistryObject<Modifier> quenchingModifier = MODIFIERS.register("quenching", QuenchingModifier::new); //unused
	public static final RegistryObject<Modifier> otherworldly1Modifier = MODIFIERS.register("otherworldly_1", () -> new OtherworldlyModifier(1)); //unused
	public static final RegistryObject<Modifier> otherworldly2Modifier = MODIFIERS.register("otherworldly_2", () -> new OtherworldlyModifier(2));
	public static final RegistryObject<Modifier> overeatingModifier = MODIFIERS.register("overeating", OvereatingModifier::new);
	public static final RegistryObject<Modifier> psishieldModifier = MODIFIERS.register("psishield", PsishieldModifier::new); //unused
	public static final RegistryObject<Modifier> manaripperModifier = MODIFIERS.register("manaripper", ManaripperModifier::new);
	public static final RegistryObject<Modifier> manaburnerModifier = MODIFIERS.register("manaburner", ManaburnerModifier::new);
	public static final RegistryObject<Modifier> manashieldModifier = MODIFIERS.register("manashield", ManashieldModifier::new);
	public static final RegistryObject<Modifier> pixiecallerModifier = MODIFIERS.register("pixiecaller", PixiecallerModifier::new);
	public static final RegistryObject<Modifier> terrabeamModifier = MODIFIERS.register("terrabeam", TerrabeamModifier::new);
	public static final RegistryObject<Modifier> elvenBeamModifier = MODIFIERS.register("elven_beam", ElvenBeamModifier::new);
	public static final RegistryObject<Modifier> fluxripperModifier = MODIFIERS.register("fluxripper", FluxripperModifier::new);
	public static final RegistryObject<Modifier> fluxburnerModifier = MODIFIERS.register("fluxburner", FluxburnerModifier::new);
	public static final RegistryObject<Modifier> fluxshieldModifier = MODIFIERS.register("fluxshield", FluxshieldModifier::new);
	public static final RegistryObject<Modifier> powerHungryModifier = MODIFIERS.register("power_hungry", PowerHungryModifier::new);

	//upgrades & abilities
	public static final RegistryObject<Modifier> reapingModifier = MODIFIERS.register("reaping", ReapingModifier::new);
	public static final RegistryObject<Modifier> runedModifier = MODIFIERS.register("runed", RunedModifier::new);
	public static final RegistryObject<Modifier> wrenchingModifier = MODIFIERS.register("wrenching", () -> new WrenchingModifier(75));
	public static final RegistryObject<Modifier> thermalWrenchingModifier = MODIFIERS.register("thermal_wrenching", ThermalWrenchingModifier::new);
	public static final RegistryObject<Modifier> createWrenchingModifier = MODIFIERS.register("create_wrenching", CreateWrenchingModifier::new);
	public static final RegistryObject<Modifier> immersiveWrenchingModifier = MODIFIERS.register("immersive_wrenching", ImmersiveWrenchingModifier::new);
	public static final RegistryObject<Modifier> pipeWrenchingModifier = MODIFIERS.register("pipe_wrenching", PipeWrenchingModifier::new);
	public static final RegistryObject<Modifier> galvanizedModifier = MODIFIERS.register("galvanized", () -> new ExtraModifier(SlotType.ABILITY, ModifierSource.MULTI_LEVEL));
	public static final RegistryObject<Modifier> spellSocketModifier = MODIFIERS.register("spell_socket", () -> new SpellSocketModifier());
	public static final RegistryObject<Modifier> spellCastingModifier = MODIFIERS.register("spell_casting", SpellCastingModifier::new);
	public static final RegistryObject<Modifier> lesserSpellCastingModifier = MODIFIERS.register("lesser_spell_casting", SpellCastingModifierLesser::new); //unused
	public static final RegistryObject<Modifier> colorizedModifier = MODIFIERS.register("colorized", ColorizedModifier::new);
	public static final RegistryObject<Modifier> psionizingRadiationModifierSensor = MODIFIERS.register("psionizing_radiation_sensor", PsionizingRadiationModifierSensor::new);
	public static final RegistryObject<Modifier> processorModifier = MODIFIERS.register("processor", () -> new ExtraModifier(MaterialisResources.SENSOR_SLOT, ModifierSource.SINGLE_LEVEL)); //unused
	public static final RegistryObject<Modifier> capacitorModifier = MODIFIERS.register("capacitor", () -> new CapacitorModifier());
	public static final RegistryObject<Modifier> engineersGogglesModifier = MODIFIERS.register("engineers_goggles", EngineersGogglesModifier::new);

	//internal modifiers
	public static final RegistryObject<Modifier> wrenchingModifierHidden = MODIFIERS.register("wrenching_hidden", () -> new WrenchingModifier(Integer.MIN_VALUE + 50));
}
