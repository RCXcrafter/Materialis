package com.rcx.materialis.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rcx.materialis.compat.QuarkCompat;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import slimeknights.mantle.recipe.SizedIngredient;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.recipe.tinkerstation.modifier.AbstractModifierRecipeBuilder;

public class RuneModifierRecipeBuilder extends AbstractModifierRecipeBuilder<RuneModifierRecipeBuilder> {

	private final List<SizedIngredient> inputs = new ArrayList<>();

	protected RuneModifierRecipeBuilder(ModifierEntry result) {
		super(result);
	}

	/**
	 * Creates a new recipe for multiple levels of a modifier
	 * @param modifier  Modifier
	 * @return  Recipe for multiple levels of the modifier
	 */
	public static RuneModifierRecipeBuilder modifier(ModifierEntry modifier) {
		return new RuneModifierRecipeBuilder(modifier);
	}

	/**
	 * Creates a new recipe for 1 level of a modifier
	 * @param modifier  Modifier
	 * @return  Recipe for 1 level of the modifier
	 */
	public static RuneModifierRecipeBuilder modifier(Modifier modifier) {
		return modifier(new ModifierEntry(modifier, 1));
	}


	/* Inputs */

	/**
	 * Adds an input to the recipe
	 * @param ingredient  Input
	 * @return  Builder instance
	 */
	public RuneModifierRecipeBuilder addInput(SizedIngredient ingredient) {
		this.inputs.add(ingredient);
		return this;
	}

	/**
	 * Adds an input to the recipe
	 * @param item    Item input
	 * @param amount  Amount required
	 * @return  Builder instance
	 */
	public RuneModifierRecipeBuilder addInput(IItemProvider item, int amount) {
		return addInput(SizedIngredient.fromItems(amount, item));
	}

	/**
	 * Adds an input to the recipe
	 * @param item    Item input
	 * @return  Builder instance
	 */
	public RuneModifierRecipeBuilder addInput(IItemProvider item) {
		return addInput(item, 1);
	}

	/**
	 * Adds an input to the recipe
	 * @param tag     Tag input
	 * @param amount  Amount required
	 * @return  Builder instance
	 */
	public RuneModifierRecipeBuilder addInput(ITag<Item> tag, int amount) {
		return addInput(SizedIngredient.fromTag(tag, amount));
	}

	/**
	 * Adds an input to the recipe
	 * @param tag     Tag input
	 * @return  Builder instance
	 */
	public RuneModifierRecipeBuilder addInput(ITag<Item> tag) {
		return addInput(tag, 1);
	}

	@Override
	public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation id) {
		if (inputs.isEmpty()) {
			throw new IllegalStateException("Must have at least 1 input");
		}
		ResourceLocation advancementId = buildOptionalAdvancement(id, "modifiers");
		consumer.accept(new FinishedRecipe(id, advancementId));
	}

	private class FinishedRecipe extends ModifierFinishedRecipe {
		public FinishedRecipe(ResourceLocation ID, @Nullable ResourceLocation advancementID) {
			super(ID, advancementID);
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			JsonArray array = new JsonArray();
			for (SizedIngredient ingredient : inputs) {
				array.add(ingredient.serialize());
			}
			json.add("inputs", array);
			super.serializeRecipeData(json);
		}

		@Override
		public IRecipeSerializer<?> getType() {
			return QuarkCompat.runeModifierSerializer.get();
		}
	}
}
