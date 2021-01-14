package com.rcx.materialis.traits.armor;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class TraitArmorUntamed extends AbstractArmorTrait {

	public static String id = "untamed";

	public TraitArmorUntamed() {
		super(id, 0x917341);
	}

	@Override
	public void onAbilityTick(int level, World world, EntityPlayer player) {
		if (random.nextInt(80) == 0 && player.shouldHeal())
			player.heal(1);
	}
}
