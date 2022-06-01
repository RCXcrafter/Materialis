package com.rcx.materialis.util;

import static com.hollingsworth.arsnouveau.api.enchanting_apparatus.ReactiveEnchantmentRecipe.getParchment;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hollingsworth.arsnouveau.api.enchanting_apparatus.EnchantingApparatusRecipe;
import com.hollingsworth.arsnouveau.api.spell.ISpellCaster;
import com.hollingsworth.arsnouveau.api.util.CasterUtil;
import com.hollingsworth.arsnouveau.common.block.tile.EnchantingApparatusTile;
import com.hollingsworth.arsnouveau.common.spell.casters.ReactiveCaster;
import com.rcx.materialis.Materialis;
import com.rcx.materialis.datagen.MaterialisModifiers;

import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistryEntry;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class TinkerSpellWriteRecipe extends EnchantingApparatusRecipe {

	public static final RecipeSerializer<TinkerSpellWriteRecipe> SERIALIZER = new TinkerSpellWriteRecipe.Serializer();
	public static final RecipeType<TinkerSpellWriteRecipe> SPELL_WRITE_TYPE = new ModRecipeType<>();

	private static class ModRecipeType<T extends Recipe<?>> implements RecipeType<T> {
		@Override
		public String toString() {
			return Registry.RECIPE_TYPE.getKey(this).toString();
		}
	}

	public TinkerSpellWriteRecipe(ResourceLocation id, List<Ingredient> pedestalItems, int cost) {
		this.pedestalItems = pedestalItems;
		this.id = id;
		this.sourceCost = cost;
	}

	public TinkerSpellWriteRecipe(List<Ingredient> pedestalItems) {
		this.pedestalItems = pedestalItems;
		this.id = new ResourceLocation(Materialis.modID, "tinker_spell_write");
	}

	public static final String RECIPE_ID = "tinker_spell_write";

	@Override
	public boolean isMatch(List<ItemStack> pedestalItems, ItemStack reagent, EnchantingApparatusTile enchantingApparatusTile, @Nullable Player player) {
		int level = ToolStack.from(reagent).getModifierLevel(MaterialisModifiers.reactiveModifier.get());
		ItemStack parchment = getParchment(pedestalItems);
		return !parchment.isEmpty() && !CasterUtil.getCaster(parchment).getSpell().isEmpty() && level > 0 && super.isMatch(pedestalItems, reagent, enchantingApparatusTile, player);
	}

	@Override
	public boolean doesReagentMatch(ItemStack reag) {
		return true;
	}

	@Override
	public ItemStack getResult(List<ItemStack> pedestalItems, ItemStack reagent, EnchantingApparatusTile enchantingApparatusTile) {
		ItemStack parchment = getParchment(pedestalItems);
		ISpellCaster caster = CasterUtil.getCaster(parchment);
		ReactiveCaster reactiveCaster = new ReactiveCaster(reagent);
		reactiveCaster.setSpell(caster.getSpell());
		return reagent.copy();
	}

	@Override
	public JsonElement asRecipe() {
		JsonObject jsonobject = new JsonObject();
		jsonobject.addProperty("type", "materialis:" + RECIPE_ID);
		jsonobject.addProperty("sourceCost", getSourceCost());
		JsonArray pedestalArr = new JsonArray();
		for (Ingredient i : this.pedestalItems) {
			JsonObject object = new JsonObject();
			object.add("item", i.toJson());
			pedestalArr.add(object);
		}
		jsonobject.add("pedestalItems", pedestalArr);
		return jsonobject;
	}

	@Override
	public RecipeType<?> getType() {
		return Registry.RECIPE_TYPE.get(new ResourceLocation(Materialis.modID, RECIPE_ID));
	}

	public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<TinkerSpellWriteRecipe> {

		@Override
		public TinkerSpellWriteRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			int cost = json.has("sourceCost") ? GsonHelper.getAsInt(json, "sourceCost") : 0;
			JsonArray pedestalItems = GsonHelper.getAsJsonArray(json, "pedestalItems");
			List<Ingredient> stacks = new ArrayList<> ();

			for (JsonElement e : pedestalItems) {
				JsonObject obj = e.getAsJsonObject();
				Ingredient input = null;
				if (GsonHelper.isArrayNode(obj, "item")) {
					input = Ingredient.fromJson(GsonHelper.getAsJsonArray(obj, "item"));
				} else { 
					input = Ingredient.fromJson(GsonHelper.getAsJsonObject(obj, "item"));
				}
				stacks.add(input);
			}
			return new TinkerSpellWriteRecipe(recipeId, stacks, cost);
		}

		@Nullable
		@Override
		public TinkerSpellWriteRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
			int length = buffer.readInt();
			List<Ingredient> stacks = new ArrayList<>();

			for (int i = 0; i < length; i++){
				try { stacks.add(Ingredient.fromNetwork(buffer)); } catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
			int cost = buffer.readInt();
			return new TinkerSpellWriteRecipe(recipeId, stacks, cost);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buf, TinkerSpellWriteRecipe recipe) {
			buf.writeInt(recipe.pedestalItems.size());
			for (Ingredient i : recipe.pedestalItems) {
				i.toNetwork(buf);
			}
			buf.writeInt(recipe.sourceCost);
		}
	}
}