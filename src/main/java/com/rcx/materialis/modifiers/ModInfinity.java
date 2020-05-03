package com.rcx.materialis.modifiers;

import net.minecraft.nbt.NBTTagCompound;

import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;
import slimeknights.tconstruct.tools.modifiers.ToolModifier;

public class ModInfinity extends ToolModifier {

	public ModInfinity() {
		super("infinity_catalyst", 0xFF5555);
	}

	@Override
	public boolean isHidden() {
		return true;
	}

	@Override
	public void updateNBT(NBTTagCompound modifierTag) {
		ModifierNBT data = ModifierNBT.readTag(modifierTag);
		data.level += 5;
		data.write(modifierTag);
	}

	@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
		NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
		ModifierNBT data = ModifierNBT.readTag(modifierTag);
		int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + data.level;
		toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
	}
}
