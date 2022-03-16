package com.rcx.materialis.modifiers;

import javax.annotation.Nullable;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.botania.api.mana.ManaItemHandler;

public class ManashieldModifier extends Modifier {

	boolean enabled = ModList.get().isLoaded("botania");
	private static final int MANA_PER_DAMAGE = 60;

	@Override
	public int getPriority() {
		return 110; //before reinforced
	}

	@Override
	public int onDamageTool(IToolStackView tool, int level, int amount, @Nullable LivingEntity holder) {
		if (enabled && !tool.isBroken() && holder instanceof Player) {
			ItemStack toolStack = holder.getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();
			int dealt = 0;
			for (int i = 0; i < amount; i++) {
				if (RANDOM.nextFloat() >= 0.1f * level) {
					dealt++;
				} else {
					int cost = MANA_PER_DAMAGE;
					if (tool.getModifierLevel(MaterialisModifiers.elvenBeamModifier.get()) > 0)
						cost = ElvenBeamModifier.MANA_PER_BEAM;
					else if (tool.getModifierLevel(MaterialisModifiers.terrabeamModifier.get()) > 0)
						cost = TerrabeamModifier.MANA_PER_BEAM;

					if (!ManaItemHandler.instance().requestManaExactForTool(toolStack, (Player) holder, cost, true))
						dealt++;
				}
			}
			return dealt;
		}
		return amount;
	}
}