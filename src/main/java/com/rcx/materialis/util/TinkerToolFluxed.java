package com.rcx.materialis.util;

import java.util.function.Supplier;

import com.rcx.materialis.Materialis;

import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import slimeknights.tconstruct.library.tools.capability.ToolCapabilityProvider.IToolCapabilityProvider;
import slimeknights.tconstruct.library.tools.nbt.IModDataView;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public class TinkerToolFluxed implements IToolCapabilityProvider, IEnergyStorage {

	protected final Supplier<? extends IToolStackView> tool;
	private final LazyOptional<IEnergyStorage> capOptional;
	public static final String STORED_ENERGY_KEY = "tooltip.materialis.stored_enegry";
	public static final ResourceLocation MAX_ENERGY = new ResourceLocation(Materialis.modID, "max_energy");
	public static final ResourceLocation STORED_ENERGY = new ResourceLocation(Materialis.modID, "stored_energy");
	public static final ResourceLocation ENERGY_OWNER = new ResourceLocation(Materialis.modID, "energy_owner");
	private static final int MAX_TRANSFER_RATE = 1000;

	public TinkerToolFluxed(ItemStack stack, Supplier<? extends IToolStackView> toolStack) {
		this.tool = toolStack;
		this.capOptional = LazyOptional.of(() -> this);
	}

	@Override
	public <T> LazyOptional<T> getCapability(IToolStackView tool, Capability<T> cap) {
		if (tool.getVolatileData().getInt(MAX_ENERGY) > 0 && (cap == CapabilityEnergy.ENERGY)) {
			return CapabilityEnergy.ENERGY.orEmpty(cap, capOptional);
		}
		return LazyOptional.empty();
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		return receiveEnergy(tool.get(), maxReceive, simulate);
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored() {
		return getEnergyStored(tool.get());
	}

	@Override
	public int getMaxEnergyStored() {
		return getMaxEnergyStored(tool.get());
	}

	public static int receiveEnergy(IToolStackView tool, int maxReceive, boolean simulate) {
		int energyStored = getEnergyStored(tool);
		int energyReceived = Math.min(getMaxEnergyStored(tool) - energyStored, Math.min(MAX_TRANSFER_RATE, maxReceive));
		if (!simulate) {
			ModDataNBT persistentData = tool.getPersistentData();
			persistentData.putInt(STORED_ENERGY, energyStored + energyReceived);
		}
		return energyReceived;
	}

	public static boolean removeEnergy(IToolStackView tool, int energyRemoved, boolean simulate, boolean drain) {
		int energyStored = getEnergyStored(tool);
		if (energyStored < energyRemoved) {
			if (drain && !simulate) {
				ModDataNBT persistentData = tool.getPersistentData();
				persistentData.putInt(STORED_ENERGY, 0);
			}
			return false;
		}
		if (!simulate) {
			ModDataNBT persistentData = tool.getPersistentData();
			persistentData.putInt(STORED_ENERGY, energyStored - energyRemoved);
		}
		return true;
	}

	public static int getEnergyStored(IToolStackView tool) {
		ModDataNBT persistentData = tool.getPersistentData();
		if (persistentData.contains(STORED_ENERGY, Tag.TAG_INT))
			return persistentData.getInt(STORED_ENERGY);
		return 0;
	}

	public static int getMaxEnergyStored(IToolStackView tool) {
		IModDataView volatileData = tool.getVolatileData();
		if (volatileData.contains(MAX_ENERGY, Tag.TAG_INT))
			return volatileData.getInt(MAX_ENERGY);
		return 0;
	}

	@Override
	public boolean canExtract() {
		return false;
	}

	@Override
	public boolean canReceive() {
		return true;
	}
}
