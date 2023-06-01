package com.rcx.materialis.modifiers;

import com.rcx.materialis.util.MaterialisUtil;

import net.minecraftforge.common.MinecraftForge;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import vazkii.psi.api.exosuit.PsiArmorEvent;

public class PsionizingRadiationModifierJump extends Modifier {

	public PsionizingRadiationModifierJump() {
		if (PsionizingRadiationModifier.enabled)
			MinecraftForge.EVENT_BUS.addListener(this::onPsiArmorEvent);
	}

	@Override
	public int getPriority() {
		return 200; //before most other things
	}

	@Override
	public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
		MaterialisUtil.addToVolatileInt(PsionizingRadiationModifier.RADIATION_LEVEL, volatileData, level);
	}

	public void onPsiArmorEvent(PsiArmorEvent event) {
		MaterialisUtil.castOnArmorEvent(event, this, PsiArmorEvent.JUMP);
	}
}