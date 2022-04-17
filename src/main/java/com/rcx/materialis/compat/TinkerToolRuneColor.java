package com.rcx.materialis.compat;

import java.util.function.Supplier;

import com.rcx.materialis.datagen.MaterialisModifiers;
import com.rcx.materialis.modifiers.RunedModifier;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import slimeknights.tconstruct.library.tools.capability.ToolCapabilityProvider.IToolCapabilityProvider;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.quark.api.IRuneColorProvider;
import vazkii.quark.api.QuarkCapabilities;

public class TinkerToolRuneColor implements IToolCapabilityProvider, IRuneColorProvider {

	private final LazyOptional<?> capOptional;

	public TinkerToolRuneColor(ItemStack stack, Supplier<? extends IToolStackView> toolStack) {
		this.capOptional = LazyOptional.of(() -> this);
	}

	@Override
	public <T> LazyOptional<T> getCapability(IToolStackView tool, Capability<T> cap) {
		if (tool.getModifierLevel(MaterialisModifiers.runedModifier.get()) > 0 && (cap == QuarkCapabilities.RUNE_COLOR)) {
			return capOptional.cast();
		}
		return LazyOptional.empty();
	}

	@Override
	public int getRuneColor(ItemStack stack) {
		return RunedModifier.getColor(ToolStack.from(stack));
	}
}
