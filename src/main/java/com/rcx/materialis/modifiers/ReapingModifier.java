package com.rcx.materialis.modifiers;

import elucent.eidolon.Registry;
import elucent.eidolon.network.CrystallizeEffectPacket;
import elucent.eidolon.network.Networking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class ReapingModifier extends SingleUseModifier {

	public ReapingModifier() {
		super(0xE388DD);
		MinecraftForge.EVENT_BUS.addListener(this::onLivingDrop);
	}

	private void onLivingDrop(LivingDropsEvent event) {
		LivingEntity target = event.getEntityLiving();
		if (target.level.isClientSide() || !(event.getSource().getEntity() instanceof LivingEntity))
			return;
		LivingEntity source = (LivingEntity) event.getSource().getEntity();
		ToolStack tool = getHeldTool(source);
		if (tool != null) {
			if (tool.getModifierLevel(this) > 0 && target.isInvertedHealAndHarm()) {
				event.getDrops().clear();
				ItemEntity drop = new ItemEntity(source.level, target.getX(), target.getY(), target.getZ(), new ItemStack(Registry.SOUL_SHARD.get(), RANDOM.nextInt(2 + tool.getModifierLevel(TinkerModifiers.luck.get()))));
				drop.setDefaultPickUpDelay();
				event.getDrops().add(drop);
				Networking.sendToTracking(target.level, target.blockPosition(), new CrystallizeEffectPacket(target.blockPosition()));
			}
		}
	}
}