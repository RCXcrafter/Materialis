package com.rcx.materialis.modifiers;

import net.minecraft.nbt.NBTTagCompound;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;

public class ModInfinity extends Modifier {

	public ModInfinity() {
		super("infinity_catalyst");
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
