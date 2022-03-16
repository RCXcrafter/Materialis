package com.rcx.materialis.modifiers;

import com.rcx.materialis.Materialis;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

public class OvereatingModifier extends Modifier {

	private static final ResourceLocation IS_EATING = new ResourceLocation(Materialis.modID, "eating_overslime");
	public static int overslimeAmount = 20;

	@Override
	public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
		OverslimeModifier overslime = TinkerModifiers.overslime.get();
		overslime.setFriend(volatileData);
	}

	@Override
	public InteractionResult onToolUse(IToolStackView tool, int level, Level world, Player player, InteractionHand hand, EquipmentSlot slot) {
		OverslimeModifier overslime = TinkerModifiers.overslime.get();
		if (!tool.isBroken() && overslime.getOverslime(tool) >= overslimeAmount * level && player.canEat(true)) {
			player.startUsingItem(hand);
			// of in the cold food
			tool.getPersistentData().putBoolean(IS_EATING, true);
			return InteractionResult.CONSUME;
		} else {
			// clear eating boolean if we cannot eat, prevents messing with other modifier's animations
			tool.getPersistentData().remove(IS_EATING);
		}
		return InteractionResult.PASS;
	}

	@Override
	public boolean onStoppedUsing(IToolStackView tool, int level, Level world, LivingEntity entity, int timeLeft) {
		tool.getPersistentData().remove(IS_EATING);
		return false;
	}

	@Override
	public boolean onFinishUsing(IToolStackView tool, int level, Level world, LivingEntity entity) {
		// remove is eating tag to prevent from messing with other modifiers
		ModDataNBT persistentData = tool.getPersistentData();
		OverslimeModifier overslime = TinkerModifiers.overslime.get();
		boolean wasEating = persistentData.getBoolean(IS_EATING);
		persistentData.remove(IS_EATING);

		if (!tool.isBroken() && overslime.getOverslime(tool) >= overslimeAmount * level && wasEating && entity instanceof Player) {
			// clear eating marker
			Player player = (Player) entity;
			if (player.canEat(false)) {
				// hot eat the food
				player.getFoodData().eat(level, level * 0.1f);
				player.awardStat(Stats.ITEM_USED.get(tool.getItem()));
				world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL, 1.0F, 1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F);
				world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.NEUTRAL, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);

				//don't bother with the breaking animation, we're only subtracting overslime anyways
				overslime.onDamageTool(tool, 1, overslimeAmount * level, entity);
				//ToolDamageUtil.damage(tool, overslimeAmount * level, player, player.getUseItem());
				return true;
			}
		}
		return false;
	}

	@Override
	public UseAnim getUseAction(IToolStackView tool, int level) {
		return tool.getPersistentData().getBoolean(IS_EATING) ? UseAnim.EAT : UseAnim.NONE;
	}

	@Override
	public int getUseDuration(IToolStackView tool, int level) {
		return tool.getPersistentData().getBoolean(IS_EATING) ? 32 : 0;
	}
}