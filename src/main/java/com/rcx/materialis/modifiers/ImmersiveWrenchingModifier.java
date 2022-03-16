package com.rcx.materialis.modifiers;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.impl.SingleUseModifier;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import blusunrize.immersiveengineering.common.register.IEItems;

public class ImmersiveWrenchingModifier extends SingleUseModifier {

	boolean enabled = ModList.get().isLoaded("immersiveengineering");

	@Override
	public InteractionResult beforeBlockUse(IToolStackView tool, int level, UseOnContext context, EquipmentSlot slot) {
		if (enabled && !tool.isBroken() && context.getPlayer() != null) {
			ItemStack toolStack = context.getPlayer().getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();
			InteractionResult result = IEItems.Tools.HAMMER.get().onItemUseFirst(toolStack, context);
			if (result == InteractionResult.SUCCESS)
				ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
			return result;
		}
		return InteractionResult.PASS;
	}
}
