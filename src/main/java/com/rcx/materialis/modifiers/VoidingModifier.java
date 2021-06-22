package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.helper.ModifierLootingHandler;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class VoidingModifier extends SingleUseModifier {

	public VoidingModifier() {
		super(0x635D71);
		MinecraftForge.EVENT_BUS.addListener(this::onExperienceDrop);
	}

	@Override
	public int getPriority() {
		return 93; //hopefully after other loot modifying modifiers, but before melting
	}

	@Override
	public List<ItemStack> processLoot(IModifierToolStack tool, int level, List<ItemStack> generatedLoot, LootContext context) {
		generatedLoot.clear();
		return generatedLoot;
	}

	private void onExperienceDrop(LivingExperienceDropEvent event) {
		ToolStack tool = getHeldTool(event.getAttackingPlayer(), ModifierLootingHandler.getLootingHand(event.getAttackingPlayer()));
		if (tool != null) {
			if (tool.getModifierLevel(this) > 0) {
				float modifier = 1 + RANDOM.nextFloat() * tool.getModifierLevel(TinkerModifiers.luck.get());
				event.setDroppedExperience((int) (event.getDroppedExperience() * modifier + 0.4f));
			}
		}
	}
}