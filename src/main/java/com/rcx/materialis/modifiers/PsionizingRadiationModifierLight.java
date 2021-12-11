package com.rcx.materialis.modifiers;

import com.rcx.materialis.util.MaterialisUtil;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import vazkii.psi.api.exosuit.PsiArmorEvent;

public class PsionizingRadiationModifierLight extends Modifier {

	public PsionizingRadiationModifierLight() {
		super(0xB6A9E7);
		if (PsionizingRadiationModifier.enabled)
			MinecraftForge.EVENT_BUS.addListener(this::onPsiArmorEvent);
	}

	@Override
	public int getPriority() {
		return 200; //before most other things
	}

	@Override
	public void addVolatileData(Item item, ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, int level, ModDataNBT volatileData) {
		MaterialisUtil.addToVolatileInt(PsionizingRadiationModifier.RADIATION_LEVEL, volatileData, level);
	}

	public void onPsiArmorEvent(PsiArmorEvent event) {
		MaterialisUtil.castOnArmorEvent(event, this, PsiArmorEvent.LOW_LIGHT);
	}
}