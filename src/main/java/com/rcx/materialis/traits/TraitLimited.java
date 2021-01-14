package com.rcx.materialis.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitLimited extends AbstractTrait {

	public static String id = "limited";
	public float modifier = 1.5F;

	public TraitLimited() {
		super(id, 0x64A7B5);
	}

	@Override
	public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
		if (!TinkerUtil.hasTrait(TagUtil.getTagSafe(tool), MaterialisTraits.unlimited.getIdentifier()));
			event.setNewSpeed(event.getOriginalSpeed() / modifier);
	}

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
		if (ToolHelper.getTraits(tool).contains(MaterialisTraits.unlimited))
			return newDamage;
		return newDamage / modifier;
	}
}
