package com.rcx.materialis.datagen;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.modifiers.*;

import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.dynamic.ExtraModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import slimeknights.tconstruct.library.tools.SlotType;

public class MaterialisModifiers extends AbstractModifierProvider {
	public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(Materialis.modID);

	//traits
	//public static final RegistryObject<Modifier> enhancedQuartzModifier = MODIFIERS.register("enhanced_quartz", () -> new ExtraModifier(SlotType.UPGRADE, ModifierSource.TRAIT));
	public static final StaticModifier<Modifier> voidingModifier = MODIFIERS.register("voiding", VoidingModifier::new);
	public static final StaticModifier<Modifier> residualLightModifier = MODIFIERS.register("residual_light", ResidualLightModifier::new);
	public static final StaticModifier<Modifier> inertiaModifier = MODIFIERS.register("inertia", InertiaModifier::new);
	public static final StaticModifier<Modifier> featherweightModifier = MODIFIERS.register("featherweight", FeatherweightModifier::new);
	public static final StaticModifier<Modifier> arcaneModifier = MODIFIERS.register("arcane", ArcaneModifier::new);
	public static final StaticModifier<Modifier> neptunesBlessingModifier = MODIFIERS.register("neptunes_blessing", NeptunesBlessingModifier::new);
	public static final StaticModifier<Modifier> halfLifeModifier = MODIFIERS.register("half_life", HalfLifeModifier::new);
	public static final StaticModifier<Modifier> workHardenedModifier = MODIFIERS.register("work_hardened", WorkHardenedModifier::new); //unused
	public static final StaticModifier<Modifier> polishedModifier = MODIFIERS.register("polished", PolishedModifier::new);
	public static final StaticModifier<Modifier> daredevilModifier = MODIFIERS.register("daredevil", DaredevilModifier::new);
	public static final StaticModifier<Modifier> decayModifier = MODIFIERS.register("decay", DecayModifier::new);
	public static final StaticModifier<Modifier> nocturnalModifier = MODIFIERS.register("nocturnal", NocturnalModifier::new);
	public static final StaticModifier<Modifier> feebleModifier = MODIFIERS.register("feeble", FeebleModifier::new);
	public static final StaticModifier<Modifier> overweightModifier = MODIFIERS.register("overweight", OverweightModifier::new); //unused
	public static final StaticModifier<Modifier> economicalModifier = MODIFIERS.register("economical", EconomicalModifier::new);
	public static final StaticModifier<Modifier> oldTimerModifier = MODIFIERS.register("old_timer", OldTimerModifier::new);
	public static final StaticModifier<Modifier> freezingModifier = MODIFIERS.register("freezing", FreezingModifier::new);
	public static final StaticModifier<Modifier> cleansingModifier = MODIFIERS.register("cleansing", CleansingModifier::new);
	public static final StaticModifier<Modifier> underlordModifier = MODIFIERS.register("underlord", UnderlordModifier::new);
	public static final StaticModifier<Modifier> shortSightedModifier = MODIFIERS.register("short_sighted", ShortSightedModifier::new);
	public static final StaticModifier<Modifier> auxiliaryPowerModifier = MODIFIERS.register("auxiliary_power", AuxiliaryPowerModifier::new);
	public static final StaticModifier<Modifier> adrenalineModifier = MODIFIERS.register("adrenaline", AdrenalineModifier::new); //unused
	public static final StaticModifier<Modifier> psionizingRadiationModifier = MODIFIERS.register("psionizing_radiation", PsionizingRadiationModifier::new);
	public static final StaticModifier<Modifier> lesserPsionizingRadiationModifier = MODIFIERS.register("lesser_psionizing_radiation", PsionizingRadiationModifierLesser::new);
	public static final StaticModifier<Modifier> psionizingRadiationModifierBreakBlock = MODIFIERS.register("psionizing_radiation_break_block", PsionizingRadiationModifierBreakBlock::new); //unused
	public static final StaticModifier<Modifier> psionizingRadiationModifierAttack = MODIFIERS.register("psionizing_radiation_attack", PsionizingRadiationModifierAttack::new); //unused
	public static final StaticModifier<Modifier> psionizingRadiationModifierDamage = MODIFIERS.register("psionizing_radiation_damage", PsionizingRadiationModifierDamage::new);
	public static final StaticModifier<Modifier> psionizingRadiationModifierTick = MODIFIERS.register("psionizing_radiation_tick", PsionizingRadiationModifierTick::new);
	public static final StaticModifier<Modifier> psionizingRadiationModifierJump = MODIFIERS.register("psionizing_radiation_jump", PsionizingRadiationModifierJump::new);
	public static final StaticModifier<Modifier> psionizingRadiationModifierFire = MODIFIERS.register("psionizing_radiation_fire", PsionizingRadiationModifierFire::new); //unused
	public static final StaticModifier<Modifier> psionizingRadiationModifierHealth = MODIFIERS.register("psionizing_radiation_health", PsionizingRadiationModifierHealth::new); //unused
	public static final StaticModifier<Modifier> psionizingRadiationModifierLight = MODIFIERS.register("psionizing_radiation_light", PsionizingRadiationModifierLight::new); //unused
	public static final StaticModifier<Modifier> psionizingRadiationModifierWater = MODIFIERS.register("psionizing_radiation_water", PsionizingRadiationModifierWater::new); //unused
	public static final StaticModifier<Modifier> psionizingRadiationModifierDetonate = MODIFIERS.register("psionizing_radiation_detonate", PsionizingRadiationModifierDetonate::new); //unused
	public static final StaticModifier<Modifier> psichoKillerModifier = MODIFIERS.register("psicho_killer", PsichoKillerModifier::new);
	public static final StaticModifier<Modifier> psichoDiggerModifier = MODIFIERS.register("psicho_digger", PsichoDiggerModifier::new);
	public static final StaticModifier<Modifier> brittleModifier = MODIFIERS.register("brittle", BrittleModifier::new); //unused
	public static final StaticModifier<Modifier> refuelingModifier = MODIFIERS.register("refueling", RefuelingModifier::new);
	public static final StaticModifier<Modifier> quenchingModifier = MODIFIERS.register("quenching", QuenchingModifier::new); //unused
	//public static final StaticModifier<Modifier> otherworldly1Modifier = MODIFIERS.register("otherworldly_1", () -> new OtherworldlyModifier(1)); //unused
	//public static final StaticModifier<Modifier> otherworldly2Modifier = MODIFIERS.register("otherworldly_2", () -> new OtherworldlyModifier(2));
	public static final StaticModifier<Modifier> overeatingModifier = MODIFIERS.register("overeating", OvereatingModifier::new);
	public static final StaticModifier<Modifier> psishieldModifier = MODIFIERS.register("psishield", PsishieldModifier::new); //unused
	public static final StaticModifier<Modifier> manaripperModifier = MODIFIERS.register("manaripper", ManaripperModifier::new);
	public static final StaticModifier<Modifier> manaburnerModifier = MODIFIERS.register("manaburner", ManaburnerModifier::new);
	public static final StaticModifier<Modifier> manashieldModifier = MODIFIERS.register("manashield", ManashieldModifier::new);
	public static final StaticModifier<Modifier> manadrawModifier = MODIFIERS.register("manadraw", ManadrawModifier::new);
	public static final StaticModifier<Modifier> manashotModifier = MODIFIERS.register("manashot", ManashotModifier::new);
	public static final StaticModifier<Modifier> pixiecallerModifier = MODIFIERS.register("pixiecaller", PixiecallerModifier::new);
	public static final StaticModifier<Modifier> terrabeamModifier = MODIFIERS.register("terrabeam", TerrabeamModifier::new);
	public static final StaticModifier<Modifier> elvenBeamModifier = MODIFIERS.register("elven_beam", ElvenBeamModifier::new);
	public static final StaticModifier<Modifier> fluxripperModifier = MODIFIERS.register("fluxripper", FluxripperModifier::new);
	public static final StaticModifier<Modifier> fluxburnerModifier = MODIFIERS.register("fluxburner", FluxburnerModifier::new);
	public static final StaticModifier<Modifier> fluxshieldModifier = MODIFIERS.register("fluxshield", FluxshieldModifier::new);
	public static final StaticModifier<Modifier> fluxdrawModifier = MODIFIERS.register("fluxdraw", FluxdrawModifier::new);
	public static final StaticModifier<Modifier> fluxshotModifier = MODIFIERS.register("fluxshot", FluxshotModifier::new);
	public static final StaticModifier<Modifier> powerHungryModifier = MODIFIERS.register("power_hungry", PowerHungryModifier::new);
	public static final StaticModifier<Modifier> crystallineModifier = MODIFIERS.register("crystalline", CrystallineModifier::new);
	public static final StaticModifier<Modifier> supermassiveModifier = MODIFIERS.register("supermassive", SupermassiveModifier::new);
	public static final StaticModifier<Modifier> cosmicUnbreakableModifier = MODIFIERS.register("cosmic_unbreakable", CosmicUnbreakableModifier::new);
	public static final StaticModifier<Modifier> instamineModifier = MODIFIERS.register("instamine", InstamineModifier::new);
	public static final StaticModifier<Modifier> instakillModifier = MODIFIERS.register("instakill", InstakillModifier::new);
	public static final StaticModifier<Modifier> bedrockBreakerModifier = MODIFIERS.register("bedrock_breaker", BedrockBreakerModifier::new);
	public static final StaticModifier<Modifier> cosmicLuckModifier = MODIFIERS.register("cosmic_luck", CosmicLuckModifier::new);

	//upgrades & abilities
	public static final StaticModifier<Modifier> reapingModifier = MODIFIERS.register("reaping", ReapingModifier::new);
	public static final StaticModifier<Modifier> runedModifier = MODIFIERS.register("runed", RunedModifier::new);
	public static final StaticModifier<Modifier> wrenchingModifier = MODIFIERS.register("wrenching", WrenchingModifier::new);
	public static final StaticModifier<Modifier> thermalWrenchingModifier = MODIFIERS.register("thermal_wrenching", ThermalWrenchingModifier::new);
	public static final StaticModifier<Modifier> createWrenchingModifier = MODIFIERS.register("create_wrenching", CreateWrenchingModifier::new);
	public static final StaticModifier<Modifier> immersiveWrenchingModifier = MODIFIERS.register("immersive_wrenching", ImmersiveWrenchingModifier::new);
	public static final StaticModifier<Modifier> pipeWrenchingModifier = MODIFIERS.register("pipe_wrenching", PipeWrenchingModifier::new);
	//public static final StaticModifier<Modifier> galvanizedModifier = MODIFIERS.register("galvanized", () -> new ExtraModifier(SlotType.ABILITY, ModifierSource.MULTI_LEVEL));
	public static final StaticModifier<Modifier> spellSocketModifier = MODIFIERS.register("spell_socket", () -> new SpellSocketModifier());
	public static final StaticModifier<Modifier> spellCastingModifier = MODIFIERS.register("spell_casting", SpellCastingModifier::new);
	public static final StaticModifier<Modifier> lesserSpellCastingModifier = MODIFIERS.register("lesser_spell_casting", SpellCastingModifierLesser::new); //unused
	public static final StaticModifier<Modifier> colorizedModifier = MODIFIERS.register("colorized", ColorizedModifier::new);
	public static final StaticModifier<Modifier> psionizingRadiationModifierSensor = MODIFIERS.register("psionizing_radiation_sensor", PsionizingRadiationModifierSensor::new);
	//public static final StaticModifier<Modifier> processorModifier = MODIFIERS.register("processor", () -> new ExtraModifier(MaterialisResources.SENSOR_SLOT, ModifierSource.SINGLE_LEVEL)); //unused
	public static final StaticModifier<Modifier> capacitorModifier = MODIFIERS.register("capacitor", () -> new CapacitorModifier());
	public static final StaticModifier<Modifier> engineersGogglesModifier = MODIFIERS.register("engineers_goggles", EngineersGogglesModifier::new);
	public static final StaticModifier<Modifier> otherworldGogglesModifier = MODIFIERS.register("otherworld_goggles", OtherworldGogglesModifier::new);
	public static final StaticModifier<ReactiveModifier> reactiveModifier = MODIFIERS.register("reactive", ReactiveModifier::new);
	public static final StaticModifier<Modifier> cataclysmicModifier = MODIFIERS.register("cataclysmic", CataclysmicModifier::new);
	public static final StaticModifier<Modifier> skullfireModifire = MODIFIERS.register("skullfire", SkullfireModifire::new);

	//modifier ids
	public static final ModifierId enhancedQuartzModifier = new ModifierId(Materialis.modID, "enhanced_quartz");
	public static final ModifierId galvanizedModifier = new ModifierId(Materialis.modID, "galvanized");
	public static final ModifierId otherworldly1Modifier = new ModifierId(Materialis.modID, "otherworldly_1");
	public static final ModifierId otherworldly2Modifier = new ModifierId(Materialis.modID, "otherworldly_2");
	//public static final ModifierId pixiecallerModifier = new ModifierId(Materialis.modID, "pixiecaller");

	public MaterialisModifiers(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void addModifiers() {
		addModifier(enhancedQuartzModifier, ExtraModifier.builder(SlotType.UPGRADE).alwaysShow().display(ModifierLevelDisplay.DEFAULT).build());
		addModifier(galvanizedModifier, ExtraModifier.builder(SlotType.ABILITY).display(ModifierLevelDisplay.DEFAULT).build());
		addModifier(otherworldly1Modifier, new OtherworldlyModifier(1));
		addModifier(otherworldly2Modifier, new OtherworldlyModifier(2));

		//janky solution to use a botania attribute without actually loading botania
		/*Attribute pixieChanceDummy = new Attribute("attribute.name.botania.pixieSpawnChance", 0) {};
		pixieChanceDummy.setRegistryName(new ResourceLocation("botania", "pixie_spawn_chance"));
		addModifier(pixiecallerModifier, StatBoostModifier.builder()
				.attribute("materialis.modifier.pixie.mainhand", pixieChanceDummy, Operation.ADDITION, 0.03F, EquipmentSlot.MAINHAND)
				.attribute("materialis.modifier.pixie.offhand", pixieChanceDummy, Operation.ADDITION, 0.03F, EquipmentSlot.OFFHAND).build());*/
	}

	@Override
	public String getName() {
		return "Materialis Modifiers";
	}
}
