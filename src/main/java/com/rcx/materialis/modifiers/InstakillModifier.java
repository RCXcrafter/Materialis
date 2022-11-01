package com.rcx.materialis.modifiers;

import morph.avaritia.handler.ArmorHandler;
import morph.avaritia.init.AvaritiaModContent;
import morph.avaritia.util.InfinityDamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class InstakillModifier extends NoLevelsModifier {

	boolean enabled = ModList.get().isLoaded("avaritia");

	@Override
	public int getPriority() {
		return 903; //cosmic modifier priority
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		if (!enabled || context.getAttacker().level.isClientSide)
			return 0;
		AvaritiaStuff.hurtInfinitely(tool, level, context, damageDealt);
		return 0;
	}

	public class AvaritiaStuff { //this is in a separate class to make sure it doesn't try to load avaritia classes when avaritia isn't installed

		public static void hurtInfinitely(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
			if (context.getTarget() instanceof Player) {
				Player pvp = (Player) context.getTarget();
				if (ArmorHandler.isInfinite(pvp)) {
					context.getTarget().hurt((new InfinityDamageSource(context.getAttacker())).bypassArmor(), 4.0F);
					return;
				}
				if (pvp.getItemInHand(InteractionHand.MAIN_HAND).getItem() == AvaritiaModContent.INFINITY_SWORD.get() && pvp.isUsingItem()) {
					return;
				}
			}
			context.getTarget().hurt(new InfinityDamageSource(context.getAttacker()), Float.MAX_VALUE);
		}
	}
}