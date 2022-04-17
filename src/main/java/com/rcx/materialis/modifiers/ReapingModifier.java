package com.rcx.materialis.modifiers;

import java.util.List;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.impl.SingleUseModifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ReapingModifier extends SingleUseModifier {

	boolean enabled = ModList.get().isLoaded("eidolon");

	@Override
	public int getPriority() {
		return 95; //hopefully after other loot modifying modifiers, but before voiding
	}

	@Override
	public List<ItemStack> processLoot(IToolStackView tool, int level, List<ItemStack> generatedLoot, LootContext context) {
		if (!enabled || !context.hasParam(LootContextParams.DAMAGE_SOURCE)) {
			return generatedLoot;
		}

		Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
		if (entity != null && entity instanceof LivingEntity) {
			LivingEntity target = (LivingEntity) entity;
			if (target.isInvertedHealAndHarm()) {
				generatedLoot.clear();
				int luck = context.getLootingModifier();
				if (tool.getItem().builtInRegistryHolder().is(TinkerTags.Items.SCYTHES))
					luck++;
				/*ItemStack drop = new ItemStack(Registry.SOUL_SHARD.get(), RANDOM.nextInt(2 + luck));
				generatedLoot.add(drop);
				Networking.sendToTracking(target.level, target.blockPosition(), new CrystallizeEffectPacket(target.blockPosition()));*/
			}
		}
		return generatedLoot;
	}
}