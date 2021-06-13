package com.rcx.materialis.util;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.rcx.materialis.compat.QuarkCompat;
import com.rcx.materialis.modifiers.RunedModifier;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;
import slimeknights.mantle.recipe.SizedIngredient;
import slimeknights.mantle.util.JsonHelper;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.recipe.tinkerstation.ITinkerStationInventory;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.recipe.tinkerstation.modifier.AbstractModifierRecipe;
import slimeknights.tconstruct.library.recipe.tinkerstation.modifier.ModifierMatch;
import slimeknights.tconstruct.library.recipe.tinkerstation.modifier.ModifierRecipe;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.quark.api.IRuneColorProvider;

public class RuneModifierRecipe extends ModifierRecipe {

	boolean enabled = ModList.get().isLoaded("quark");

	private final List<SizedIngredient> inputs;

	public RuneModifierRecipe(ResourceLocation id, List<SizedIngredient> inputs, Ingredient toolRequirement, ModifierMatch requirements, String requirementsError, ModifierEntry result, int maxLevel, int upgradeSlots, int abilitySlots) {
		super(id, inputs, toolRequirement, requirements, requirementsError, result, maxLevel, upgradeSlots, abilitySlots);
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
		persistentData.addUpgrades(-getUpgradeSlots());
		persistentData.addAbilities(-getAbilitySlots());

		// add modifier
		tool.addModifier(result.getModifier(), result.getLevel());

		// ensure no modifier problems
		ValidatedResult toolValidation = tool.validate();
		if (toolValidation.hasError()) {
			return toolValidation;
		}

		//add rune color
		if (enabled) {
			for (int i = 0; i < inv.getInputCount(); i++) {
				Item item = inv.getInput(i).getItem();
				if (item instanceof IRuneColorProvider) {
					persistentData.putInt(RunedModifier.RUNE_COLOR, ((IRuneColorProvider) item).getRuneColor(inv.getInput(i)));
					break;
				}
			}
		}

		return ValidatedResult.success(tool.createStack());
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return QuarkCompat.runeModifierSerializer.get();
	}

	public static class Serializer extends AbstractModifierRecipe.Serializer<RuneModifierRecipe> {
		@Override
		public RuneModifierRecipe read(ResourceLocation id, JsonObject json, Ingredient toolRequirement, ModifierMatch requirements,
				String requirementsError, ModifierEntry result, int maxLevel, int upgradeSlots, int abilitySlots) {
			List<SizedIngredient> ingredients = JsonHelper.parseList(json, "inputs", SizedIngredient::deserialize);
			return new RuneModifierRecipe(id, ingredients, toolRequirement, requirements, requirementsError, result, maxLevel, upgradeSlots, abilitySlots);
		}

		@Override
		public RuneModifierRecipe read(ResourceLocation id, PacketBuffer buffer, Ingredient toolRequirement, ModifierMatch requirements,
				String requirementsError, ModifierEntry result, int maxLevel, int upgradeSlots, int abilitySlots) {
			int size = buffer.readVarInt();
			ImmutableList.Builder<SizedIngredient> builder = ImmutableList.builder();
			for (int i = 0; i < size; i++) {
				builder.add(SizedIngredient.read(buffer));
			}
			return new RuneModifierRecipe(id, builder.build(), toolRequirement, requirements, requirementsError, result, maxLevel, upgradeSlots, abilitySlots);
		}

		@Override
		public void write(PacketBuffer buffer, RuneModifierRecipe recipe) {
			super.write(buffer, recipe);
			buffer.writeVarInt(recipe.inputs.size());
			for (SizedIngredient ingredient : recipe.inputs) {
				ingredient.write(buffer);
			}
		}

		@Override
		public final RuneModifierRecipe fromJson(ResourceLocation id, JsonObject json) {
			Ingredient toolRequirement = Ingredient.fromJson(json.get("tools"));
			ModifierMatch requirements = ModifierMatch.ALWAYS;
			String requirementsError = "";
			if (json.has("requirements")) {
				JsonObject reqJson = JSONUtils.getAsJsonObject(json, "requirements");
				requirements = ModifierMatch.deserialize(reqJson);
				requirementsError = JSONUtils.getAsString(reqJson, "error", "");
			}
			ModifierEntry result = ModifierEntry.fromJson(JSONUtils.getAsJsonObject(json, "result"));
			int maxLevel = JSONUtils.getAsInt(json, "max_level", 0);
			if (maxLevel < 0) {
				throw new JsonSyntaxException("max must be non-negative");
			}
			int upgradeSlots = JSONUtils.getAsInt(json, "upgrade_slots", 0);
			if (upgradeSlots < 0) {
				throw new JsonSyntaxException("upgrade_slots must be non-negative");
			}
			int abilitySlots = JSONUtils.getAsInt(json, "ability_slots", 0);
			if (abilitySlots < 0) {
				throw new JsonSyntaxException("ability_slots must be non-negative");
			}
			if (upgradeSlots > 0 && abilitySlots > 0) {
				throw new JsonSyntaxException("Cannot set both upgrade_slots and ability_slots");
			}
			return read(id, json, toolRequirement, requirements, requirementsError, result, maxLevel, upgradeSlots, abilitySlots);
		}

		@Override
		public final RuneModifierRecipe fromNetwork(ResourceLocation id, PacketBuffer buffer) {
			Ingredient toolRequirement = Ingredient.fromNetwork(buffer);
			ModifierMatch requirements = ModifierMatch.read(buffer);
			String requirementsError = buffer.readUtf(Short.MAX_VALUE);
			ModifierEntry result = ModifierEntry.read(buffer);
			int maxLevel = buffer.readVarInt();
			int upgradeSlots = buffer.readVarInt();
			int abilitySlots = buffer.readVarInt();
			return read(id, buffer, toolRequirement, requirements, requirementsError, result, maxLevel, upgradeSlots, abilitySlots);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, RuneModifierRecipe recipe) {
			write(buffer, recipe);
		}
	}
}
