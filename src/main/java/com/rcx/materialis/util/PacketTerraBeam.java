package com.rcx.materialis.util;

import java.util.function.Supplier;

import com.rcx.materialis.modifiers.TerrabeamModifier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PacketTerraBeam {

	public static void encode(PacketTerraBeam msg, PacketBuffer buf) {}

	public static PacketTerraBeam decode(PacketBuffer buf) {
		return new PacketTerraBeam();
	}

	public static void handle(PacketTerraBeam msg, Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection().getReceptionSide().isServer()) {
			ctx.get().enqueueWork(() -> TerrabeamModifier.BurstHandler.trySpawnBurst(ctx.get().getSender(), true));
		}
		ctx.get().setPacketHandled(true);
	}
}
