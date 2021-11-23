package com.rcx.materialis.util;

import java.util.function.Supplier;

import com.rcx.materialis.modifiers.ElvenBeamModifier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PacketElvenBeam {

	public static void encode(PacketElvenBeam msg, PacketBuffer buf) {}

	public static PacketElvenBeam decode(PacketBuffer buf) {
		return new PacketElvenBeam();
	}

	public static void handle(PacketElvenBeam msg, Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection().getReceptionSide().isServer()) {
			ctx.get().enqueueWork(() -> ElvenBeamModifier.BurstHandler.trySpawnBurst(ctx.get().getSender(), true));
		}
		ctx.get().setPacketHandled(true);
	}
}
