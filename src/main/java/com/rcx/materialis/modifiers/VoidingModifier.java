package com.rcx.materialis.modifiers;

import java.util.ArrayList;
import java.util.List;

import com.rcx.materialis.MaterialisUtil;

import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class VoidingModifier extends SingleUseModifier {
	
	//Predicate<ItemStack> alwaysTrue = i -> true; 

	public VoidingModifier() {
		super(0x635D71);
		MinecraftForge.EVENT_BUS.addListener(this::onExperienceDrop);
	}

	@Override
	public List<ItemStack> processLoot(IModifierToolStack tool, int level, List<ItemStack> generatedLoot, LootContext context) {
		return new ArrayList<ItemStack>();
	}

	private void onExperienceDrop(LivingExperienceDropEvent event) {
		ToolStack tool = getHeldTool(event.getAttackingPlayer());
		if (tool != null) {
			if (tool.getModifierLevel(this) > 0) {
				float modifier = 1 + RANDOM.nextFloat() * MaterialisUtil.getEffectiveLuckLevel(tool, RANDOM);
				event.setDroppedExperience((int) (event.getDroppedExperience() * modifier + 0.4f));
			}
		}
	}
}