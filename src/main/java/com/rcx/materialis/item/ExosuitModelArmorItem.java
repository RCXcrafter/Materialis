package com.rcx.materialis.item;

import java.util.function.Consumer;

import javax.annotation.Nonnull;

import com.rcx.materialis.util.ExosuitModel;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.tools.definition.ModifiableArmorMaterial;
import slimeknights.tconstruct.library.tools.item.ModifiableArmorItem;
import slimeknights.tconstruct.tools.item.ArmorSlotType;

public class ExosuitModelArmorItem extends ModifiableArmorItem {

	public ExosuitModelArmorItem(ModifiableArmorMaterial material, ArmorSlotType slotType, Properties properties) {
		super(material, slotType, properties);
	}

	@Override
	public void initializeClient(Consumer<IItemRenderProperties> consumer) {
		consumer.accept(new IItemRenderProperties() {
			@Nonnull
			@Override
			public Model getBaseArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
				if (ModList.get().isLoaded("psi"))
					return ExosuitModel.getModel(entityLiving, itemStack, armorSlot, _default);
				return _default;
			}
		});
	}
}
