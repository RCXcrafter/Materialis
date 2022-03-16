package com.rcx.materialis.modifiers;

import java.util.List;

import com.rcx.materialis.util.TinkerToolFluxed;

import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IModDataView;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.utils.TooltipKey;

public class CapacitorModifier extends Modifier {

	@Override
	public ValidatedResult validate(IToolStackView tool, int level) {
		int max = tool.getVolatileData().getInt(TinkerToolFluxed.MAX_ENERGY);
		if (tool.getPersistentData().getInt(TinkerToolFluxed.STORED_ENERGY) > max)
			tool.getPersistentData().putInt(TinkerToolFluxed.STORED_ENERGY, max);
		return ValidatedResult.PASS;
	}

	@Override
	public void onRemoved(IToolStackView tool) {
		if (tool.getVolatileData().getInt(TinkerToolFluxed.MAX_ENERGY) == 0)
			tool.getPersistentData().remove(TinkerToolFluxed.STORED_ENERGY);
	}

	@Override
	public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
		if (volatileData.contains(TinkerToolFluxed.MAX_ENERGY, Tag.TAG_INT)) {
			volatileData.putInt(TinkerToolFluxed.MAX_ENERGY, volatileData.getInt(TinkerToolFluxed.MAX_ENERGY) + getCapacity() * level);
		} else {
			volatileData.putInt(TinkerToolFluxed.MAX_ENERGY, getCapacity() * level);
		}
		if (!volatileData.contains(TinkerToolFluxed.ENERGY_OWNER, Tag.TAG_STRING))
			volatileData.putString(TinkerToolFluxed.ENERGY_OWNER, getId().toString());
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		if (tool instanceof ToolStack && isOwner(tool.getVolatileData())) {
			tooltip.add(new TranslatableComponent(TinkerToolFluxed.STORED_ENERGY_KEY, tool.getPersistentData().getInt(TinkerToolFluxed.STORED_ENERGY), tool.getVolatileData().getInt(TinkerToolFluxed.MAX_ENERGY)).withStyle(style -> style.withColor(TextColor.fromRgb(getColor()))));
		}
	}

	public int getCapacity() {
		return 10000;
	}

	public boolean isOwner(IModDataView volatileData) {
		return getId().toString().equals(volatileData.getString(TinkerToolFluxed.ENERGY_OWNER));
	}
}