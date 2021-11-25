package com.rcx.materialis.modifiers;

import de.ellpeck.prettypipes.Registry;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class PipeWrenchingModifier extends SingleUseModifier {

	boolean enabled = ModList.get().isLoaded("prettypipes");

	public PipeWrenchingModifier() {
		super(0xD01F1F);
	}

	@Override
	public ActionResultType beforeBlockUse(IModifierToolStack tool, int level, ItemUseContext context) {
		if (enabled && !tool.isBroken() && context.getPlayer() != null) {
			ActionResultType result = Registry.wrenchItem.useOn(context);
			if (result == ActionResultType.SUCCESS)
				ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
			return result;
		}
		return ActionResultType.PASS;
	}
}