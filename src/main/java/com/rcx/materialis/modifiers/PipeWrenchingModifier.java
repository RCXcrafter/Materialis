package com.rcx.materialis.modifiers;

import de.ellpeck.prettypipes.Registry;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class PipeWrenchingModifier extends NoLevelsModifier {

	boolean enabled = ModList.get().isLoaded("prettypipes");

	@Override
	public InteractionResult beforeBlockUse(IToolStackView tool, int level, UseOnContext context, EquipmentSlot slot) {
		if (enabled && !tool.isBroken() && context.getPlayer() != null) {
			InteractionResult result = Registry.wrenchItem.useOn(context);
			if (result == InteractionResult.SUCCESS)
				ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
			return result;
		}
		return InteractionResult.PASS;
	}
}
