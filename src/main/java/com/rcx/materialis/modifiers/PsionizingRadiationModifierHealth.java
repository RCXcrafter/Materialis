package com.rcx.materialis.modifiers;

import com.rcx.materialis.util.MaterialisUtil;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public class PsionizingRadiationModifierHealth extends Modifier {

	public PsionizingRadiationModifierHealth() {
		//if (PsionizingRadiationModifier.enabled)
			//MinecraftForge.EVENT_BUS.addListener(this::onPsiArmorEvent);
	}

	@Override
	public int getPriority() {
		return 200; //before most other things
	}

	@Override
	public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
		MaterialisUtil.addToVolatileInt(PsionizingRadiationModifier.RADIATION_LEVEL, volatileData, level);
		//if (PsionizingRadiationModifier.enabled)
			//volatileData.put(PsionizingRadiationModifierSensor.SENSOR, new ItemStack(ModItems.exosuitSensorStress).serializeNBT());
	}

	/*public void onPsiArmorEvent(PsiArmorEvent event) {
		MaterialisUtil.castOnArmorEvent(event, this, PsiArmorEvent.LOW_HP);
	}*/
}