package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.helper.ModifierLootingHandler;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.data.ModifierIds;

public class VoidingModifier extends NoLevelsModifier {

	public VoidingModifier() {
		super();
		MinecraftForge.EVENT_BUS.addListener(this::onExperienceDrop);
	}

	@Override
	public int getPriority() {
		return 93; //hopefully after other loot modifying modifiers, but before melting
	}

	@Override
	public List<ItemStack> processLoot(IToolStackView tool, int level, List<ItemStack> generatedLoot, LootContext context) {
		generatedLoot.clear();
		return generatedLoot;
	}

	private void onExperienceDrop(LivingExperienceDropEvent event) {
		ToolStack tool = getHeldTool(event.getAttackingPlayer(), ModifierLootingHandler.getLootingSlot(event.getAttackingPlayer()));
		if (tool != null) {
			if (tool.getModifierLevel(this) > 0) {
				float modifier = 1 + RANDOM.nextFloat() * tool.getModifierLevel(ModifierIds.luck);
				event.setDroppedExperience((int) (event.getDroppedExperience() * modifier + 0.4f));
			}
		}
	}
}