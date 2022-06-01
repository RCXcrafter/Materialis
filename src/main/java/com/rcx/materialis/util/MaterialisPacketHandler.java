package com.rcx.materialis.util;

import com.rcx.materialis.Materialis;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class MaterialisPacketHandler {

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(Materialis.modID, "main"),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals
			);

	static int id = 0;

	public static void init() {
		if (ModList.get().isLoaded("botania")) {
			INSTANCE.registerMessage(id++, PacketTerraBeam.class, PacketTerraBeam::encode, PacketTerraBeam::decode, PacketTerraBeam::handle);
			INSTANCE.registerMessage(id++, PacketElvenBeam.class, PacketElvenBeam::encode, PacketElvenBeam::decode, PacketElvenBeam::handle);
		}
		if (ModList.get().isLoaded("ars_nouveau"))
			INSTANCE.registerMessage(id++, PacketReactiveSwing.class, PacketReactiveSwing::encode, PacketReactiveSwing::decode, PacketReactiveSwing::handle);
	}
}