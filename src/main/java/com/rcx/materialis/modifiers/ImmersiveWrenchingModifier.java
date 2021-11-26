package com.rcx.materialis.modifiers;

import blusunrize.immersiveengineering.common.items.IEItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class ImmersiveWrenchingModifier extends SingleUseModifier {

	boolean enabled = ModList.get().isLoaded("immersiveengineering");

	public ImmersiveWrenchingModifier() {
		super(0x8F5034);
	}

	@Override
	public ActionResultType beforeBlockUse(IModifierToolStack tool, int level, ItemUseContext context) {
		if (enabled && !tool.isBroken() && context.getPlayer() != null) {
			ItemStack toolStack = context.getPlayer().getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();
			ActionResultType result = IEItems.Tools.hammer.onItemUseFirst(toolStack, context);
			if (result == ActionResultType.SUCCESS)
				ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
			return result;
		}
		return ActionResultType.PASS;
	}
}
