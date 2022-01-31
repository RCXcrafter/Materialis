package com.rcx.materialis.util;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import com.rcx.materialis.MaterialisResources;
import com.rcx.materialis.modifiers.PsionizingRadiationModifierSensor;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;
import slimeknights.mantle.recipe.SizedIngredient;
import slimeknights.mantle.util.JsonHelper;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.recipe.modifiers.ModifierMatch;
import slimeknights.tconstruct.library.recipe.modifiers.adding.AbstractModifierRecipe;
import slimeknights.tconstruct.library.recipe.modifiers.adding.SwappableModifierRecipe;
import slimeknights.tconstruct.library.recipe.tinkerstation.ITinkerStationInventory;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.tools.SlotType.SlotCount;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.psi.api.exosuit.IExosuitSensor;

public class SensorModifierRecipe extends SwappableModifierRecipe {

	boolean enabled = ModList.get().isLoaded("psi");

	private final String value;

	public SensorModifierRecipe(ResourceLocation id, List<SizedIngredient> inputs, Ingredient toolRequirement, ModifierMatch requirements, String requirementsError, Modifier result, String value, @Nullable SlotCount slots) {
		super(id, inputs, toolRequirement, requirements, requirementsError, result, value, slots);
		this.value = value;
	}

	@Override
	public ValidatedResult getValidatedResult(ITinkerStationInventory inv) {
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

		//add sensor information
		if (enabled) {
			for (int i = 0; i < inv.getInputCount(); i++) {
				Item item = inv.getInput(i).getItem();
				if (item instanceof IExosuitSensor) {
					persistentData.put(PsionizingRadiationModifierSensor.SENSOR, inv.getInput(i).serializeNBT());
					break;
				}
			}
		}

		return ValidatedResult.success(tool.createStack());
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return MaterialisResources.sensorModifierSerializer.get();
	}

	public static class Serializer extends AbstractModifierRecipe.Serializer<SensorModifierRecipe> {

		@Override
		protected ModifierEntry readResult(JsonObject json) {
			JsonObject result = JSONUtils.getAsJsonObject(json, "result");
			return new ModifierEntry(ModifierEntry.deserializeModifier(result, "name"), 1);
		}

		@Override
		public SensorModifierRecipe read(ResourceLocation id, JsonObject json, Ingredient toolRequirement, ModifierMatch requirements, String requirementsError, ModifierEntry result, int maxLevel, int upgradeSlots, int abilitySlots) {
			throw new UnsupportedOperationException();
		}

		@Override
		public SensorModifierRecipe read(ResourceLocation id, PacketBuffer buffer, Ingredient toolRequirement, ModifierMatch requirements, String requirementsError, ModifierEntry result, int maxLevel, int upgradeSlots, int abilitySlots) {
			throw new UnsupportedOperationException();
		}

		@Override
		public SensorModifierRecipe read(ResourceLocation id, JsonObject json, Ingredient toolRequirement, ModifierMatch requirements, String requirementsError, ModifierEntry result, int maxLevel, @Nullable SlotCount slots) {
			List<SizedIngredient> ingredients = JsonHelper.parseList(json, "inputs", SizedIngredient::deserialize);
			String value = JSONUtils.getAsString(JSONUtils.getAsJsonObject(json, "result"), "value");
			return new SensorModifierRecipe(id, ingredients, toolRequirement, requirements, requirementsError, result.getModifier(), value, slots);
		}

		@Override
		public SensorModifierRecipe read(ResourceLocation id, PacketBuffer buffer, Ingredient toolRequirement, ModifierMatch requirements, String requirementsError, ModifierEntry result, int maxLevel, @Nullable SlotCount slots) {
			int size = buffer.readVarInt();
			ImmutableList.Builder<SizedIngredient> builder = ImmutableList.builder();
			for (int i = 0; i < size; i++) {
				builder.add(SizedIngredient.read(buffer));
			}
			String value = buffer.readUtf();
			return new SensorModifierRecipe(id, builder.build(), toolRequirement, requirements, requirementsError, result.getModifier(), value, slots);
		}

		@Override
		protected void writeSafe(PacketBuffer buffer, SensorModifierRecipe recipe) {
			super.writeSafe(buffer, recipe);
			buffer.writeVarInt(recipe.inputs.size());
			for (SizedIngredient ingredient : recipe.inputs) {
				ingredient.write(buffer);
			}
			buffer.writeUtf(recipe.value);
		}

		@Override
		public SensorModifierRecipe fromJson(ResourceLocation id, JsonObject json) {
			return super.read(id, json);
		}
	}
}
