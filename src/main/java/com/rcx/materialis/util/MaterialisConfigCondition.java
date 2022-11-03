package com.rcx.materialis.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.BooleanSupplier;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import com.rcx.materialis.Materialis;
import com.rcx.materialis.MaterialisConfig;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

//just lazily copied this from tinkers construct
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MaterialisConfigCondition implements ICondition {
	public static final ResourceLocation ID = new ResourceLocation(Materialis.modID, "config");
	public static final ConfigSerializer SERIALIZER = new ConfigSerializer();
	/* Map of config names to condition cache */
	private static final Map<String,MaterialisConfigCondition> PROPS = new HashMap<>();

	private final String configName;
	private final BooleanSupplier supplier;

	@Override
	public ResourceLocation getID() {
		return ID;
	}

	@Override
	public boolean test() {
		return supplier.getAsBoolean();
	}

	@Override
	public boolean test(IContext context) {
		return supplier.getAsBoolean();
	}

	private static class ConfigSerializer implements Serializer<MaterialisConfigCondition>, IConditionSerializer<MaterialisConfigCondition> {
		@Override
		public ResourceLocation getID() {
			return ID;
		}

		@Override
		public void write(JsonObject json, MaterialisConfigCondition value) {
			json.addProperty("prop", value.configName);
		}

		@Override
		public MaterialisConfigCondition read(JsonObject json) {
			String prop = GsonHelper.getAsString(json, "prop");
			MaterialisConfigCondition config = PROPS.get(prop.toLowerCase(Locale.ROOT));
			if (config == null) {
				throw new JsonSyntaxException("Invalid property name '" + prop + "'");
			}
			return config;
		}

		@Override
		public void serialize(JsonObject json, MaterialisConfigCondition condition, JsonSerializationContext context) {
			write(json, condition);
		}

		@Override
		public MaterialisConfigCondition deserialize(JsonObject json, JsonDeserializationContext context) {
			return read(json);
		}
	}

	/**
	 * Adds a condition
	 * @param prop     Property name
	 * @param supplier Boolean supplier
	 * @return Added condition
	 */
	private static MaterialisConfigCondition add(String prop, BooleanSupplier supplier) {
		MaterialisConfigCondition conf = new MaterialisConfigCondition(prop, supplier);
		PROPS.put(prop.toLowerCase(Locale.ROOT), conf);
		return conf;
	}

	/**
	 * Adds a condition
	 * @param prop     Property name
	 * @param supplier Config value
	 * @return Added condition
	 */
	private static MaterialisConfigCondition add(String prop, BooleanValue supplier) {
		return add(prop, supplier::get);
	}

	@Override
	public String toString() {
		return "config_setting_enabled(\"" + this.configName + "\")";
	}

	/* Properties */
	public static final MaterialisConfigCondition CHROMATIC_MATERIALS = add("chromatic_materials", MaterialisConfig.ENABLE_CHROMATIC_MATERIALS);
	public static final MaterialisConfigCondition CHROMATIC_RECIPE = add("chromatic_recipe", MaterialisConfig.ADD_CHROMATIC_RECIPE);
}