package com.rcx.materialis.util;

import java.util.function.Supplier;

import com.rcx.materialis.datagen.MaterialisModifiers;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;


public class PacketReactiveSwing {

	public static void encode(PacketReactiveSwing msg, FriendlyByteBuf buf) {}

	public static PacketReactiveSwing decode(FriendlyByteBuf buf) {
		return new PacketReactiveSwing();
	}

	public static void handle(PacketReactiveSwing msg, Supplier<NetworkEvent.Context> ctx) {
		if (ctx.get().getDirection().getReceptionSide().isServer()) {
			ctx.get().enqueueWork(() -> MaterialisModifiers.reactiveModifier.get().recieveLeftClick(ctx.get().getSender()));
		}
		ctx.get().setPacketHandled(true);
	}
}
