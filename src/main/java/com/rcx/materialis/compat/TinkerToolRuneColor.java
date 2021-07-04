package com.rcx.materialis.compat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.rcx.materialis.modifiers.MaterialisModifiers;
import com.rcx.materialis.modifiers.RunedModifier;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.Constants.NBT;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.quark.api.IRuneColorProvider;
import vazkii.quark.api.QuarkCapabilities;

public class TinkerToolRuneColor implements ICapabilityProvider, IRuneColorProvider {

	protected final ItemStack tool;
	private final LazyOptional<?> capOptional;

	public TinkerToolRuneColor(ItemStack tool) {
		this.tool = tool;
		this.capOptional = LazyOptional.of(() -> this);
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (ToolStack.from(tool).getModifierLevel(MaterialisModifiers.runedModifier.get()) > 0 && (cap == QuarkCapabilities.RUNE_COLOR)) {
			return capOptional.cast();
		}
		return LazyOptional.empty();
	}

	@Override
	public int getRuneColor(ItemStack stack) {
		IModDataReadOnly persistentData = ToolStack.from(stack).getPersistentData();
		if (persistentData.contains(RunedModifier.RUNE_COLOR, NBT.TAG_INT))
			return persistentData.getInt(RunedModifier.RUNE_COLOR);
		return 16;
	}
}
