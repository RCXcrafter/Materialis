/*package com.rcx.materialis.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.rcx.materialis.Materialis;
import com.rcx.materialis.modifiers.ColorizedModifier;
import com.rcx.materialis.modifiers.MaterialisModifiers;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.data.ISafeManagerReloadListener;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.client.ArmorModelWrapper;
import slimeknights.tconstruct.tools.data.material.MaterialIds;
import vazkii.psi.api.cad.ICADColorizer;
import vazkii.psi.common.item.component.ItemCADColorizerPsi;

public class ExosuitModel<T extends LivingEntity> extends ArmorModelWrapper<T> {

	/** Singleton instance * /
	private static final ExosuitModel<LivingEntity> INSTANCE = new ExosuitModel<>();
	/** Cache of armor render types * /
	private static final Map<String, RenderType> ARMOR_RENDER_CACHE = new HashMap<>();
	/** Gets the armor texture for a material * /
	private static ResourceLocation getArmorTexture(String material) {
		ResourceLocation location = ResourceLocation.tryParse(material);
		if (location == null) {
			location = MaterialIds.tinkersBronze;
		}
		if (ModList.get().isLoaded("magipsi"))
			return new ResourceLocation(Materialis.modID, String.format("textures/models/armor/exosuit_magical/accent_%s_%s.png", location.getNamespace(), location.getPath()));
		return new ResourceLocation(Materialis.modID, String.format("textures/models/armor/exosuit/accent_%s_%s.png", location.getNamespace(), location.getPath()));
	}
	/** Overlay texture for colorizer * /
	private static final ResourceLocation OVERLAY_COLORIZER = new ResourceLocation(Materialis.modID, ModList.get().isLoaded("magipsi") ? "textures/models/armor/psimetal_exosuit_magical_colorizer.png" : "textures/models/armor/psimetal_exosuit_colorizer.png");
	/** Function to get armor render type * /
	private static final Function<String, RenderType> ARMOR_GETTER = mat -> RenderType.armorCutoutNoCull(getArmorTexture(mat));

	/** Listener to clear caches * /
	public static final ISafeManagerReloadListener RELOAD_LISTENER = manager -> {
		ARMOR_RENDER_CACHE.clear();
	};

	/**
	 * Gets the model for a given entity
	 * /
	@SuppressWarnings("unchecked")
	public static <A extends BipedModel<?>> A getModel(ItemStack stack, EquipmentSlotType slot, A baseModel) {
		INSTANCE.setup(stack, slot, baseModel);
		return (A) INSTANCE;
	}

	private String material = "";
	private ItemStack colorizer = ItemStack.EMPTY;

	@Override
	public void renderToBuffer(MatrixStack matrices, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		if (base != null) {
			copyToBase();
			base.renderToBuffer(matrices, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			if (!material.isEmpty() && buffer != null) {
				IVertexBuilder overlayBuffer = buffer.getBuffer(ARMOR_RENDER_CACHE.computeIfAbsent(material, ARMOR_GETTER));
				base.renderToBuffer(matrices, overlayBuffer, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			}
			if (!colorizer.isEmpty() && buffer != null) {
				int color = getColorizerColor(colorizer);
				float newRed = (float)(color >> 16 & 255) / 255.0F;
				float newGreen = (float)(color >> 8 & 255) / 255.0F;
				float newBlue = (float)(color & 255) / 255.0F;
				IVertexBuilder overlayBuffer = buffer.getBuffer(RenderType.armorCutoutNoCull(OVERLAY_COLORIZER));
				base.renderToBuffer(matrices, overlayBuffer, 12583120, packedOverlayIn, newRed, newGreen, newBlue, alpha);
			}
		}
	}

	private void setup(ItemStack stack, EquipmentSlotType slot, BipedModel<?> base) {
		this.base = base;
		ToolStack tool = ToolStack.from(stack);
		if (tool.getModifierLevel(TinkerModifiers.golden.get()) > 0) {
			this.material = MaterialIds.gold.toString();
		} else {
			this.material = tool.getPersistentData().getString(TinkerModifiers.embellishment.getId());
		}
		if (tool.getModifierLevel(MaterialisModifiers.colorizedModifier.get()) > 0) {
			this.colorizer = ItemStack.of(tool.getPersistentData().getCompound(ColorizedModifier.COLORIZER));
		} else {
			this.colorizer = ItemStack.EMPTY;
		}
	}

	public int getColorizerColor(ItemStack stack) {
		if (stack.getItem() instanceof ItemCADColorizerPsi)
			return ColorizedModifier.getPsiColor(); //I am against paid features in mods, even purely cosmetic ones, and I will not let such features carry over into my addon
		if (stack.getItem() instanceof ICADColorizer)
			return ((ICADColorizer) stack.getItem()).getColor(stack);
		return 0xFFFFFF;
	}
}*/