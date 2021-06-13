package com.rcx.materialis.compat;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.util.RuneModifierRecipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.tconstruct.library.tools.item.ToolCore;

public class QuarkCompat {

	public static final ResourceLocation RUNED_CAPABILITY = new ResourceLocation(Materialis.modID, "runed_capability");

	public void attachCapabilities(final AttachCapabilitiesEvent<ItemStack> event) {
		ItemStack stack = event.getObject();
		if (stack.getItem() instanceof ToolCore) {
			event.addCapability(RUNED_CAPABILITY, new TinkerToolRuneColor(stack));
		}
	}
	
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Materialis.modID);
	
	public static final RegistryObject<RuneModifierRecipe.Serializer> runeModifierSerializer = RECIPE_SERIALIZERS.register("rune_modifier", RuneModifierRecipe.Serializer::new);
}
