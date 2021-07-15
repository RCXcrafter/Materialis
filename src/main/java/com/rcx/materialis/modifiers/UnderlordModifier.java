package com.rcx.materialis.modifiers;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class UnderlordModifier extends Modifier {

	public UnderlordModifier() {
		super(0x6CD7AA);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (context.getTarget().getType().getRegistryName().getNamespace().equals("undergarden") && context.getTarget().canChangeDimensions())
			return damage * (1.0f + 0.5f * level);
		return damage;
	}

	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		BlockState state = event.getState();
		if (isEffective && state != null && state.getBlock().getRegistryName().getNamespace().equals("undergarden"))
			event.setNewSpeed(event.getNewSpeed() * (1.0f + 0.25f * level));
	}
}