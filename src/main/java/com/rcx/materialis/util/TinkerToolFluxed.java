package com.rcx.materialis.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.rcx.materialis.Materialis;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class TinkerToolFluxed implements ICapabilityProvider, IEnergyStorage {

	protected final ItemStack tool;
	private final LazyOptional<IEnergyStorage> capOptional;
	public static final String STORED_ENERGY_KEY = "tooltip.materialis.stored_enegry";
	public static final ResourceLocation MAX_ENERGY = new ResourceLocation(Materialis.modID, "max_energy");
	public static final ResourceLocation STORED_ENERGY = new ResourceLocation(Materialis.modID, "stored_energy");
	public static final ResourceLocation ENERGY_OWNER = new ResourceLocation(Materialis.modID, "energy_owner");
	private static final int MAX_TRANSFER_RATE = 1000;

	public TinkerToolFluxed(ItemStack tool) {
		this.tool = tool;
		this.capOptional = LazyOptional.of(() -> this);
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		IModDataReadOnly volatileData = ToolStack.from(tool).getVolatileData();
		if (volatileData.contains(MAX_ENERGY, NBT.TAG_INT) && volatileData.getInt(MAX_ENERGY) > 0 && (cap == CapabilityEnergy.ENERGY)) {
			return CapabilityEnergy.ENERGY.orEmpty(cap, capOptional);
		}
		return LazyOptional.empty();
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		return receiveEnergy(ToolStack.from(tool), maxReceive, simulate);
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored() {
		return getEnergyStored(ToolStack.from(tool));
	}

	@Override
	public int getMaxEnergyStored() {
		return getMaxEnergyStored(ToolStack.from(tool));
	}

	public static int receiveEnergy(IModifierToolStack tool, int maxReceive, boolean simulate) {
		int energyReceived = Math.min(getMaxEnergyStored(tool) - getEnergyStored(tool), Math.min(MAX_TRANSFER_RATE, maxReceive));
		if (!simulate) {
			ModDataNBT persistentData = tool.getPersistentData();
			persistentData.putInt(STORED_ENERGY, getEnergyStored(tool) + energyReceived);
		}
		return energyReceived;
	}

	public static boolean removeEnergy(IModifierToolStack tool, int energyRemoved, boolean simulate) {
		if (getEnergyStored(tool) < energyRemoved)
			return false;
		if (!simulate) {
			ModDataNBT persistentData = tool.getPersistentData();
			persistentData.putInt(STORED_ENERGY, getEnergyStored(tool) - energyRemoved);
		}
		return true;
	}

	public static int getEnergyStored(IModifierToolStack tool) {
		ModDataNBT persistentData = tool.getPersistentData();
		if (persistentData.contains(STORED_ENERGY, NBT.TAG_INT))
			return persistentData.getInt(STORED_ENERGY);
		return 0;
	}

	public static int getMaxEnergyStored(IModifierToolStack tool) {
		IModDataReadOnly volatileData = tool.getVolatileData();
		if (volatileData.contains(MAX_ENERGY, NBT.TAG_INT))
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
