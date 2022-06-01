package com.rcx.materialis.util;

import java.util.function.Supplier;

import com.rcx.materialis.modifiers.ElvenBeamModifier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.network.NetworkEvent;

public class PacketElvenBeam {

	public static void encode(PacketElvenBeam msg, FriendlyByteBuf buf) {}

	public static PacketElvenBeam decode(FriendlyByteBuf buf) {
		return new PacketElvenBeam();
	}

	public static void handle(PacketElvenBeam msg, Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection().getReceptionSide().isServer()) {
			ctx.get().enqueueWork(() -> ElvenBeamModifier.BurstHandler.trySpawnBurst(ctx.get().getSender(), InteractionHand.MAIN_HAND, true, true));
		}
		ctx.get().setPacketHandled(true);
	}
}
