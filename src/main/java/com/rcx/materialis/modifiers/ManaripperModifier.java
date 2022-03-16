package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipKey;
import vazkii.botania.api.mana.ManaItemHandler;

public class ManaripperModifier extends Modifier {

	boolean enabled = ModList.get().isLoaded("botania");
	private static final int MANA_COST = 80;

	@Override
	public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (enabled && !tool.isBroken() && context.getAttacker() instanceof Player) {
			ItemStack toolStack = context.getAttacker().getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();

			if (ManaItemHandler.instance().requestManaExactForTool(toolStack, (Player) context.getAttacker(), MANA_COST * level, true))
				return damage + 1.5f * level * tool.getMultiplier(ToolStats.ATTACK_DAMAGE);
		}
		return damage;
	}

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		addStatTooltip(tool, ToolStats.ATTACK_DAMAGE, TinkerTags.Items.MELEE, 1.5f * level, tooltip);
	}
}