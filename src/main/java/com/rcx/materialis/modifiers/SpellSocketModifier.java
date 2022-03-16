package com.rcx.materialis.modifiers;

import java.util.List;

import com.rcx.materialis.Materialis;

import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IModDataView;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.utils.TooltipKey;
import slimeknights.tconstruct.library.utils.Util;

public class SpellSocketModifier extends Modifier {

	public static boolean enabled = ModList.get().isLoaded("psi");
	public static final ResourceLocation SOCKET_OWNER = new ResourceLocation(Materialis.modID, "socket_owner");
	public static final ValidatedResult SLOT_NOT_EMPTY = ValidatedResult.failure(Util.makeTranslationKey("recipe", new ResourceLocation(Materialis.modID, "remove_modifier.spell_slot_not_empty")));
	public static final ValidatedResult TOO_MANY_SLOTS = ValidatedResult.failure(Util.makeTranslationKey("recipe", new ResourceLocation(Materialis.modID, "add_modifier.too_many_spell_slots")));

	@Override
	public ValidatedResult validate(IToolStackView tool, int level) {
		if (enabled) {
			//check if there are still spells in the sockets that are being removed or if too many sockets are being added
			/*int sockets = tool.getVolatileData().contains(TinkerToolSocketable.SOCKETS, Tag.TAG_INT) ? tool.getVolatileData().getInt(TinkerToolSocketable.SOCKETS) : 0;
			if (sockets > ISocketable.MAX_ASSEMBLER_SLOTS)
				return SpellSocketModifier.TOO_MANY_SLOTS;

			for (int l = sockets; l < ISocketable.MAX_ASSEMBLER_SLOTS; l++) {
				if (tool.getPersistentData().contains(TinkerToolSocketable.SPELL_SLOTS[l], Tag.TAG_COMPOUND))
					return SpellSocketModifier.SLOT_NOT_EMPTY;
			}*/
		}
		return ValidatedResult.PASS;
	}

	@Override
	public void onRemoved(IToolStackView tool) {
		if (enabled) {
			//remove tags if all sockets are removed
			/*if (tool.getVolatileData().getInt(TinkerToolSocketable.SOCKETS) == 0) {
				tool.getPersistentData().remove(TinkerToolSocketable.SELECTED_SPELL);
			}*/
		}
	}

	@Override
	public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
		if (!enabled)
			return;
		//MaterialisUtil.addToVolatileInt(TinkerToolSocketable.SOCKETS, volatileData, level);
		if (!volatileData.contains(SOCKET_OWNER, Tag.TAG_STRING))
			volatileData.putString(SOCKET_OWNER, getId().toString());
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		if (enabled && tool instanceof ToolStack && isOwner(tool.getVolatileData())) {
			//Component componentName = ISocketable.getSocketedItemName(((ToolStack) tool).createStack(), "psimisc.none");
			//tooltip.add(new TranslatableComponent("psimisc.spell_selected", componentName));
		}
	}

	public boolean isOwner(IModDataView volatileData) {
		return getId().toString().equals(volatileData.getString(SOCKET_OWNER));
	}
}