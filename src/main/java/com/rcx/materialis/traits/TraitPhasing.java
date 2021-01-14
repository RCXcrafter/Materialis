package com.rcx.materialis.traits;

import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.TinkerProjectileImpactEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitPhasing extends AbstractTrait {

	public static String id = "phasing";

	public TraitPhasing() {
		super(id, 0xF1E9D4);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onImpact(TinkerProjectileImpactEvent event) {
		if(event.getEntity() != null && !event.getEntity().getEntityWorld().isRemote && this.isToolWithTrait(event.getTool())) {
			if (event.getRayTraceResult().typeOfHit.equals(RayTraceResult.Type.BLOCK)) {
				event.setCanceled(true);
			}
		}
	}
}
