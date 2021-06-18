package com.rcx.materialis.modifiers;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class UnderlordModifier extends Modifier {

	public UnderlordModifier() {
		super(0x6CD7AA);
	}

	@Override
	public float applyLivingDamage(IModifierToolStack tool, int level, LivingEntity attacker, LivingEntity target, float baseDamage, float damage, boolean isCritical, boolean fullyCharged) {
		if (target.getType().getRegistryName().getNamespace().equals("undergarden") && target.canChangeDimensions())
			return super.applyLivingDamage(tool, level, attacker, target, baseDamage, damage, isCritical, fullyCharged) * (1.0f + 0.5f * level);
		return super.applyLivingDamage(tool, level, attacker, target, baseDamage, damage, isCritical, fullyCharged);
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		BlockState state = event.getState();
		if (isEffective && state != null && state.getBlock().getRegistryName().getNamespace().equals("undergarden"))
			event.setNewSpeed(event.getNewSpeed() * (1.0f + 0.25f * level));
	}
}