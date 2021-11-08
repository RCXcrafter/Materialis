package com.rcx.materialis.modifiers;

import javax.annotation.Nullable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.botania.api.mana.ManaItemHandler;

public class ManashieldModifier extends Modifier {

	boolean enabled = ModList.get().isLoaded("botania");
	private static final int MANA_PER_DAMAGE = 60;

	public ManashieldModifier() {
		super(0x5CD6FF);
	}

	@Override
	public int onDamageTool(IModifierToolStack tool, int level, int amount, @Nullable LivingEntity holder) {
		if (enabled && !tool.isBroken() && holder != null && holder instanceof PlayerEntity) {
			ItemStack toolStack = holder.getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();
			int dealt = 0;
			for (int i = 0; i < amount; i++) {
				if (RANDOM.nextFloat() >= 0.2f * level) {
					dealt++;
				} else {
					if (!ManaItemHandler.instance().requestManaExactForTool(toolStack, (PlayerEntity) holder, MANA_PER_DAMAGE, true))
						dealt++;
				}
			}
			return dealt;
		}
		return amount;
	}
}