package com.rcx.materialis.util;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.modifiers.RunedModifier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fml.ModList;
import slimeknights.mantle.recipe.ingredient.SizedIngredient;
import slimeknights.mantle.util.JsonHelper;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.recipe.modifiers.ModifierMatch;
import slimeknights.tconstruct.library.recipe.modifiers.adding.AbstractModifierRecipe;
import slimeknights.tconstruct.library.recipe.modifiers.adding.SwappableModifierRecipe;
import slimeknights.tconstruct.library.recipe.tinkerstation.ITinkerStationContainer;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.tools.SlotType.SlotCount;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.quark.api.IRuneColorProvider;

public class RuneModifierRecipe extends SwappableModifierRecipe {

	boolean enabled = ModList.get().isLoaded("quark");

	private final String value;

	public RuneModifierRecipe(ResourceLocation id, List<SizedIngredient> inputs, Ingredient toolRequirement, int maxToolSize, ModifierMatch requirements, String requirementsError, Modifier result, String value, @Nullable SlotCount slots) {
		super(id, inputs, toolRequirement, maxToolSize, requirements, requirementsError, result, value, slots);
		this.value = value;
	}

	@Override
	public ValidatedResult getValidatedResult(ITinkerStationContainer inv) {
		ToolStack tool = ToolStack.from(inv.getTinkerableStack());

		// if the tool has the modifier already, can skip most requirements
		Modifier modifier = result.getModifier();

		ValidatedResult commonError;
		boolean needsModifier;
		if (tool.getUpgrades().getLevel(modifier) == 0) {
			needsModifier = true;
			commonError = validatePrerequisites(tool);
		} else {
			needsModifier = false;
			commonError = validateRequirements(tool);
		}
		if (commonError.hasError()) {
			return commonError;
		}

		// consume slots
		tool = tool.copy();
		ModDataNBT persistentData = tool.getPersistentData();
		if (needsModifier) {
			SlotCount slots = getSlots();
			if (slots != null) {
				persistentData.addSlots(slots.getType(), -slots.getCount());
			}
		}

		// set the new value to the modifier
		persistentData.putString(modifier.getId(), value);

		// add modifier if needed
		if (needsModifier) {
			tool.addModifier(result.getModifier(), 1);
		} else {
			tool.rebuildStats();
		}

		// ensure no modifier problems
		ValidatedResult toolValidation = tool.validate();
		if (toolValidation.hasError()) {
			return toolValidation;
		}

		//add rune color
		if (enabled) {
			for (int i = 0; i < inv.getInputCount(); i++) {
				if (inv.getInput(i).getItem() instanceof IRuneColorProvider) {
					persistentData.put(RunedModifier.RUNE, inv.getInput(i).serializeNBT());
					break;
				}
			}
		}

		return ValidatedResult.success(tool.createStack());
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return MaterialisResources.runeModifierSerializer.get();
	}

	public static class Serializer extends AbstractModifierRecipe.Serializer<RuneModifierRecipe> {

		@Override
		protected ModifierEntry readResult(JsonObject json) {
			JsonObject result = GsonHelper.getAsJsonObject(json, "result");
			return new ModifierEntry(ModifierEntry.deserializeModifier(result, "name"), 1);
		}

		@Override
		public RuneModifierRecipe fromJson(ResourceLocation id, JsonObject json, Ingredient toolRequirement, int maxToolSize, ModifierMatch requirements,
				String requirementsError, ModifierEntry result, int maxLevel, @Nullable SlotCount slots) {
			List<SizedIngredient> ingredients = JsonHelper.parseList(json, "inputs", SizedIngredient::deserialize);
			String value = GsonHelper.getAsString(GsonHelper.getAsJsonObject(json, "result"), "value");
			return new RuneModifierRecipe(id, ingredients, toolRequirement, maxToolSize, requirements, requirementsError, result.getModifier(), value, slots);
		}

		@Override
		public RuneModifierRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer, Ingredient toolRequirement, int maxToolSize, ModifierMatch requirements,
				String requirementsError, ModifierEntry result, int maxLevel, @Nullable SlotCount slots) {
			int size = buffer.readVarInt();
			ImmutableList.Builder<SizedIngredient> builder = ImmutableList.builder();
			for (int i = 0; i < size; i++) {
				builder.add(SizedIngredient.read(buffer));
			}
			String value = buffer.readUtf();
			return new RuneModifierRecipe(id, builder.build(), toolRequirement, maxToolSize, requirements, requirementsError, result.getModifier(), value, slots);
		}

		@Override
		protected void toNetworkSafe(FriendlyByteBuf buffer, RuneModifierRecipe recipe) {
			super.toNetworkSafe(buffer, recipe);
			buffer.writeVarInt(recipe.inputs.size());
			for (SizedIngredient ingredient : recipe.inputs) {
				ingredient.write(buffer);
			}
			buffer.writeUtf(recipe.value);
		}
	}
}
