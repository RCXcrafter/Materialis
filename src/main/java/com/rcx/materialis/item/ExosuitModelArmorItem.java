package com.rcx.materialis.item;

import javax.annotation.Nullable;

import com.rcx.materialis.Materialis;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.definition.ModifiableArmorMaterial;
import slimeknights.tconstruct.library.tools.item.ModifiableArmorItem;
import slimeknights.tconstruct.tools.item.ArmorSlotType;
import vazkii.psi.client.model.ModelPsimetalExosuit;

public class ExosuitModelArmorItem extends ModifiableArmorItem {

	private LazyValue<BipedModel<?>> model;
	public String texture;

	public ExosuitModelArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn, ToolDefinition toolDefinition) {
		super(materialIn, slot, builderIn, toolDefinition);
		if (ModList.get().isLoaded("psi")) {
			this.model = DistExecutor.runForDist(() -> () -> new LazyValue<>(() -> ExosuitModel.provideArmorModelForSlot(slot)), () -> () -> null);
			texture = Materialis.modID + ":textures/models/armor/" + new ResourceLocation(this.getMaterial().getName()).getPath() + ".png";
		}
	}

	public ExosuitModelArmorItem(ModifiableArmorMaterial material, ArmorSlotType slotType, Properties properties) {
		super(material, slotType, properties);
		if (ModList.get().isLoaded("psi")) {
			this.model = DistExecutor.runForDist(() -> () -> new LazyValue<>(() -> ExosuitModel.provideArmorModelForSlot(slotType.getEquipmentSlot())), () -> () -> null);
			texture = Materialis.modID + ":textures/models/armor/" + new ResourceLocation(this.getMaterial().getName()).getPath() + ".png";
		}
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		if (ModList.get().isLoaded("psi"))
			return texture;
		return super.getArmorTexture(stack, entity, slot, type);
	}

	@SuppressWarnings("unchecked")
	@Nullable
	@Override
	@OnlyIn(Dist.CLIENT)
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A base) {
		if (ModList.get().isLoaded("psi"))
			return (A) model.get();
		return super.getArmorModel(entityLiving, itemStack, armorSlot, base);
	}

	public static class ExosuitModel {

		@OnlyIn(Dist.CLIENT)
		public static BipedModel<?> provideArmorModelForSlot(EquipmentSlotType slot) {
			return new ModelPsimetalExosuit(slot);
		}
	}
}
