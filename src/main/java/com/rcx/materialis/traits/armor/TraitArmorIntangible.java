package com.rcx.materialis.traits.armor;

import com.rcx.materialis.traits.TraitIntangible;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TraitArmorIntangible extends AbstractArmorTrait {

	public static float chance = 0.12f;

	public TraitArmorIntangible() {
		super(TraitIntangible.id, 0xBDBDBD);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onImpact(ProjectileImpactEvent event) {
		if (event.getRayTraceResult().typeOfHit.equals(RayTraceResult.Type.ENTITY)) {
			Entity entity = event.getRayTraceResult().entityHit;
			int armorpieces = 0;
			for (ItemStack armorStack : entity.getArmorInventoryList()) {
				if (this.isToolWithTrait(armorStack))
					armorpieces++;
			}
			if (armorpieces > 0 && random.nextFloat() < chance * armorpieces)
				event.setCanceled(true);
		}
	}
}
