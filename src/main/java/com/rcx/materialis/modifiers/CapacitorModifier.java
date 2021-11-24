package com.rcx.materialis.modifiers;

import java.util.List;

import com.rcx.materialis.util.TinkerToolFluxed;

import net.minecraft.item.Item;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants.NBT;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.utils.TooltipFlag;

public class CapacitorModifier extends Modifier {

	public CapacitorModifier(int color) {
		super(color);
	}

	@Override
	public ValidatedResult validate(IModifierToolStack tool, int level) {
		int max = tool.getVolatileData().getInt(TinkerToolFluxed.MAX_ENERGY);
		if (tool.getPersistentData().getInt(TinkerToolFluxed.STORED_ENERGY) > max)
			tool.getPersistentData().putInt(TinkerToolFluxed.STORED_ENERGY, max);
		return ValidatedResult.PASS;
	}

	@Override
	public void onRemoved(IModifierToolStack tool) {
		if (tool.getVolatileData().getInt(TinkerToolFluxed.MAX_ENERGY) == 0)
			tool.getPersistentData().remove(TinkerToolFluxed.STORED_ENERGY);
	}

	@Override
	public void addVolatileData(Item item, ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, int level, ModDataNBT volatileData) {
		if (volatileData.contains(TinkerToolFluxed.MAX_ENERGY, NBT.TAG_INT)) {
			volatileData.putInt(TinkerToolFluxed.MAX_ENERGY, volatileData.getInt(TinkerToolFluxed.MAX_ENERGY) + getCapacity() * level);
		} else {
			volatileData.putInt(TinkerToolFluxed.MAX_ENERGY, getCapacity() * level);
		}
		if (!volatileData.contains(TinkerToolFluxed.ENERGY_OWNER, NBT.TAG_STRING))
			volatileData.putString(TinkerToolFluxed.ENERGY_OWNER, getId().toString());
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, TooltipFlag flag) {
		if (tool instanceof ToolStack && isOwner(tool.getVolatileData())) {
			tooltip.add(new TranslationTextComponent(TinkerToolFluxed.STORED_ENERGY_KEY, tool.getPersistentData().getInt(TinkerToolFluxed.STORED_ENERGY), tool.getVolatileData().getInt(TinkerToolFluxed.MAX_ENERGY)).withStyle(style -> style.withColor(Color.fromRgb(getColor()))));
		}
	}

	public int getCapacity() {
		return 10000;
	}

	public boolean isOwner(IModDataReadOnly volatileData) {
		return getId().toString().equals(volatileData.getString(TinkerToolFluxed.ENERGY_OWNER));
	}
}