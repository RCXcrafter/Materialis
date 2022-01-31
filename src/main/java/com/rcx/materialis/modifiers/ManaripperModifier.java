package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipFlag;
import vazkii.botania.api.mana.ManaItemHandler;

public class ManaripperModifier extends Modifier {

	boolean enabled = ModList.get().isLoaded("botania");
	private static final int MANA_COST = 80;

	public ManaripperModifier() {
		super(0x964726);
	}

	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (enabled && !tool.isBroken() && context.getAttacker() instanceof PlayerEntity) {
			ItemStack toolStack = context.getAttacker().getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();

			if (ManaItemHandler.instance().requestManaExactForTool(toolStack, (PlayerEntity) context.getAttacker(), MANA_COST * level, true))
				return damage + 1.5f * level * tool.getModifier(ToolStats.ATTACK_DAMAGE);
		}
		return damage;
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, TooltipFlag flag) {
		addStatTooltip(tool, ToolStats.ATTACK_DAMAGE, TinkerTags.Items.MELEE, 1.5f * level, tooltip);
	}
}