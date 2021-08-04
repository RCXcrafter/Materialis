package com.rcx.materialis.modifiers;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.compat.TinkerToolSocketable;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import vazkii.psi.api.cad.ISocketable;

public class SpellSocketModifier extends Modifier {

	static boolean enabled = ModList.get().isLoaded("psi");
	public static final ValidatedResult SLOT_NOT_EMPTY = ValidatedResult.failure(Util.makeDescriptionId("recipe", new ResourceLocation(Materialis.modID, "remove_modifier.spell_slot_not_empty")));
	public static final ValidatedResult TOO_MANY_SLOTS = ValidatedResult.failure(Util.makeDescriptionId("recipe", new ResourceLocation(Materialis.modID, "add_modifier.too_many_spell_slots")));

	public SpellSocketModifier() {
		super(0x3D3D3D);
	}

	@Override
	public ValidatedResult validate(IModifierToolStack tool, int level) {
		return validateSockets(tool, level);
	}

	public static ValidatedResult validateSockets(IModifierToolStack tool, int level) {
		int sockets = tool.getVolatileData().contains(TinkerToolSocketable.SOCKETS, NBT.TAG_INT) ? tool.getVolatileData().getInt(TinkerToolSocketable.SOCKETS) : 0;
		//check if there are still spells in the sockets that are being removed or if too many sockets are being added
		if (enabled) {
			if (sockets > ISocketable.MAX_ASSEMBLER_SLOTS)
				return SpellSocketModifier.TOO_MANY_SLOTS;

			for (int l = sockets; l < ISocketable.MAX_ASSEMBLER_SLOTS; l++) {
				if (tool.getPersistentData().contains(TinkerToolSocketable.SPELL_SLOTS[l], NBT.TAG_COMPOUND))
					return SpellSocketModifier.SLOT_NOT_EMPTY;
			}
		}
		return ValidatedResult.PASS;
	}

	@Override
	public void onRemoved(IModifierToolStack tool) {
		//remove tags if all sockets are removed
		if (tool.getVolatileData().getInt(TinkerToolSocketable.SOCKETS) == 0) {
			tool.getPersistentData().remove(TinkerToolSocketable.SELECTED_SPELL);
		}
	}

	@Override
	public void addVolatileData(Item item, ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, int level, ModDataNBT volatileData) {
		if (!enabled)
			return;
		if (volatileData.contains(TinkerToolSocketable.SOCKETS, NBT.TAG_INT)) {
			volatileData.putInt(TinkerToolSocketable.SOCKETS, volatileData.getInt(TinkerToolSocketable.SOCKETS) + level);
		} else {
			volatileData.putInt(TinkerToolSocketable.SOCKETS, level);
		}
	}
}