package com.rcx.materialis.modifiers;

import java.util.List;

import elucent.eidolon.Registry;
import elucent.eidolon.network.CrystallizeEffectPacket;
import elucent.eidolon.network.Networking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class ReapingModifier extends SingleUseModifier {

	boolean enabled = ModList.get().isLoaded("eidolon");

	public ReapingModifier() {
		super(0xE388DD);
	}

	@Override
	public int getPriority() {
		return 95; //hopefully after other loot modifying modifiers, but before voiding
	}

	@Override
	public List<ItemStack> processLoot(IModifierToolStack tool, int level, List<ItemStack> generatedLoot, LootContext context) {
		if (!enabled || !context.hasParam(LootParameters.DAMAGE_SOURCE)) {
			return generatedLoot;
		}

		Entity entity = context.getParamOrNull(LootParameters.THIS_ENTITY);
		if (entity != null && entity instanceof LivingEntity) {
			LivingEntity target = (LivingEntity) entity;
			if (target.isInvertedHealAndHarm()) {
				generatedLoot.clear();
				ItemStack drop = new ItemStack(Registry.SOUL_SHARD.get(), RANDOM.nextInt(2 + tool.getModifierLevel(TinkerModifiers.luck.get())));
				generatedLoot.add(drop);
				Networking.sendToTracking(target.level, target.blockPosition(), new CrystallizeEffectPacket(target.blockPosition()));
			}
		}
		return generatedLoot;
	}
}