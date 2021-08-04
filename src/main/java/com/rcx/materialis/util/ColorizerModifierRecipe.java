package com.rcx.materialis.util;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.modifiers.ColorizedModifier;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;
import slimeknights.mantle.recipe.SizedIngredient;
import slimeknights.mantle.util.JsonHelper;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.recipe.modifiers.ModifierMatch;
import slimeknights.tconstruct.library.recipe.modifiers.adding.AbstractModifierRecipe;
import slimeknights.tconstruct.library.recipe.modifiers.adding.ModifierRecipe;
import slimeknights.tconstruct.library.recipe.tinkerstation.ITinkerStationInventory;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.tools.SlotType.SlotCount;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.psi.api.cad.ICADColorizer;

public class ColorizerModifierRecipe extends ModifierRecipe {

	boolean enabled = ModList.get().isLoaded("psi");

	private final List<SizedIngredient> inputs;

	public ColorizerModifierRecipe(ResourceLocation id, List<SizedIngredient> inputs, Ingredient toolRequirement, ModifierMatch requirements, String requirementsError, ModifierEntry result, int maxLevel, @Nullable SlotCount slots) {
		super(id, inputs, toolRequirement, requirements, requirementsError, result, maxLevel, slots);
		this.inputs = inputs;
	}

	@Override
	public ValidatedResult getValidatedResult(ITinkerStationInventory inv) {
		ToolStack tool = ToolStack.from(inv.getTinkerableStack());

		// common errors
		ValidatedResult commonError = validatePrerequisites(tool);
		if (commonError.hasError()) {
			return commonError;
		}

		// consume slots
		tool = tool.copy();
		ModDataNBT persistentData = tool.getPersistentData();
		SlotCount slots = getSlots();
		if (slots != null) {
			persistentData.addSlots(slots.getType(), -slots.getCount());
		}

		// add modifier
		tool.addModifier(result.getModifier(), result.getLevel());

		// ensure no modifier problems
		ValidatedResult toolValidation = tool.validate();
		if (toolValidation.hasError()) {
			return toolValidation;
		}

		//add colorizer information
		if (enabled) {
			for (int i = 0; i < inv.getInputCount(); i++) {
				Item item = inv.getInput(i).getItem();
				if (item instanceof ICADColorizer) {
					persistentData.put(ColorizedModifier.COLORIZER, inv.getInput(i).serializeNBT());
					break;
				}
			}
		}

		return ValidatedResult.success(tool.createStack());
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return MaterialisResources.colorizerModifierSerializer.get();
	}

	public static class Serializer extends AbstractModifierRecipe.Serializer<ColorizerModifierRecipe> {

		@Override
		public ColorizerModifierRecipe read(ResourceLocation id, JsonObject json, Ingredient toolRequirement, ModifierMatch requirements,
				String requirementsError, ModifierEntry result, int maxLevel, @Nullable SlotCount slots) {
			List<SizedIngredient> ingredients = JsonHelper.parseList(json, "inputs", SizedIngredient::deserialize);
			return new ColorizerModifierRecipe(id, ingredients, toolRequirement, requirements, requirementsError, result, maxLevel, slots);
		}

		@Override
		public ColorizerModifierRecipe read(ResourceLocation id, PacketBuffer buffer, Ingredient toolRequirement, ModifierMatch requirements, String requirementsError, ModifierEntry result, int maxLevel, @Nullable SlotCount slots) {
			int size = buffer.readVarInt();
			ImmutableList.Builder<SizedIngredient> builder = ImmutableList.builder();
			for (int i = 0; i < size; i++) {
				builder.add(SizedIngredient.read(buffer));
			}
			return new ColorizerModifierRecipe(id, builder.build(), toolRequirement, requirements, requirementsError, result, maxLevel, slots);
		}

		@Override
		@Deprecated
		public ColorizerModifierRecipe read(ResourceLocation id, JsonObject json, Ingredient toolRequirement, ModifierMatch requirements, String requirementsError, ModifierEntry result, int maxLevel, int upgradeSlots, int abilitySlots) {
			throw new UnsupportedOperationException();
		}

		@Override
		@Deprecated
		public ColorizerModifierRecipe read(ResourceLocation id, PacketBuffer buffer, Ingredient toolRequirement, ModifierMatch requirements, String requirementsError, ModifierEntry result, int maxLevel, int upgradeSlots, int abilitySlots) {
			throw new UnsupportedOperationException();
		}

		@Override
		protected void writeSafe(PacketBuffer buffer, ColorizerModifierRecipe recipe) {
			super.writeSafe(buffer, recipe);
			buffer.writeVarInt(recipe.inputs.size());
			for (SizedIngredient ingredient : recipe.inputs) {
				ingredient.write(buffer);
			}
		}

		@Override
		public ColorizerModifierRecipe fromJson(ResourceLocation id, JsonObject json) {
			return super.read(id, json);
		}
	}
}
