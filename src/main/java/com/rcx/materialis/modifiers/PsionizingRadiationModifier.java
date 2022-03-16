package com.rcx.materialis.modifiers;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.util.MaterialisUtil;

import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public class PsionizingRadiationModifier extends SpellSocketModifier {

	public static final ResourceLocation SUPPRESS_TOOLCASTING = new ResourceLocation(Materialis.modID, "suppress_toolcasting");
	public static final ResourceLocation RADIATION_LEVEL = new ResourceLocation(Materialis.modID, "psi_radiation_level");

	@Override
	public int getPriority() {
		return 200; //before most other things
	}

	@Override
	public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
		super.addVolatileData(context, level, volatileData);
		MaterialisUtil.addToVolatileInt(PsionizingRadiationModifier.RADIATION_LEVEL, volatileData, level);
	}

	@Override
	public Boolean removeBlock(IToolStackView tool, int level, ToolHarvestContext context) {
		MaterialisUtil.castOnBlockBreak(tool, level, context, true);
		return null;
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		MaterialisUtil.castOnEntityHit(tool, level, context, damageDealt, true);
		return 0;
	}
}