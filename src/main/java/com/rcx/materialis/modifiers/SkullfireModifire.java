package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.impl.SingleLevelModifier;
import slimeknights.tconstruct.library.recipe.modifiers.severing.SeveringRecipe;
import slimeknights.tconstruct.library.recipe.modifiers.severing.SeveringRecipeCache;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class SkullfireModifire extends SingleLevelModifier {

	boolean enabled = ModList.get().isLoaded("eidolon");

	@Override
	public int getPriority() {
		return 97; //after severing, but before voiding
	}

	@Override
	public List<ItemStack> processLoot(IToolStackView tool, int level, List<ItemStack> generatedLoot, LootContext context) {
		if (!enabled || !context.hasParam(LootContextParams.DAMAGE_SOURCE)) {
			return generatedLoot;
		}
		// must have an entity
		Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
		if (entity != null) {
			// ensure no head so far
			if (generatedLoot.stream().noneMatch(stack -> stack.is(Tags.Items.HEADS))) {
				// find proper recipe
				List<SeveringRecipe> recipes = SeveringRecipeCache.findRecipe(context.getLevel().getRecipeManager(), entity.getType());
				if (!recipes.isEmpty()) {
					for (SeveringRecipe recipe : recipes) {
						ItemStack result = recipe.getOutput(entity);

						if (result.getItem() == Items.WITHER_SKELETON_SKULL) {
							generatedLoot.add(result);
							return generatedLoot;
						}
						if (result.getItem() == Items.SKELETON_SKULL) {
							generatedLoot.add(new ItemStack(Items.WITHER_SKELETON_SKULL, result.getCount()));
							return generatedLoot;
						}
					}
				}
			} else {
				for (int i = 0; i < generatedLoot.size(); i++) {
					ItemStack stack = generatedLoot.get(i);
					if (stack.getItem() == Items.SKELETON_SKULL) {
						generatedLoot.set(i, new ItemStack(Items.WITHER_SKELETON_SKULL, stack.getCount()));
						return generatedLoot;
					}
				}
			}
		}
		return generatedLoot;
	}
}