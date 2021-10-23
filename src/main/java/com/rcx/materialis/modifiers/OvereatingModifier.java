package com.rcx.materialis.modifiers;

import com.rcx.materialis.Materialis;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

public class OvereatingModifier extends Modifier {

	private static final ResourceLocation IS_EATING = new ResourceLocation(Materialis.modID, "eating_overslime");
	public static int overslimeAmount = 20;

	public OvereatingModifier() {
		super(0xFF9FEF);
	}

	@Override
	public void addVolatileData(Item item, ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, int level, ModDataNBT volatileData) {
		OverslimeModifier overslime = TinkerModifiers.overslime.get();
		overslime.setFriend(volatileData);
	}

	@Override
	public ActionResultType onToolUse(IModifierToolStack tool, int level, World world, PlayerEntity player, Hand hand) {
		OverslimeModifier overslime = TinkerModifiers.overslime.get();
		if (!tool.isBroken() && overslime.getOverslime(tool) >= overslimeAmount * level && player.canEat(true)) {
			player.startUsingItem(hand);
			// of in the cold food
			tool.getPersistentData().putBoolean(IS_EATING, true);
			return ActionResultType.CONSUME;
		} else {
			// clear eating boolean if we cannot eat, prevents messing with other modifier's animations
			tool.getPersistentData().remove(IS_EATING);
		}
		return ActionResultType.PASS;
	}

	@Override
	public boolean onStoppedUsing(IModifierToolStack tool, int level, World world, LivingEntity entity, int timeLeft) {
		tool.getPersistentData().remove(IS_EATING);
		return false;
	}

	@Override
	public boolean onFinishUsing(IModifierToolStack tool, int level, World world, LivingEntity entity) {
		// remove is eating tag to prevent from messing with other modifiers
		ModDataNBT persistentData = tool.getPersistentData();
		OverslimeModifier overslime = TinkerModifiers.overslime.get();
		boolean wasEating = persistentData.getBoolean(IS_EATING);
		persistentData.remove(IS_EATING);

		if (!tool.isBroken() && overslime.getOverslime(tool) >= overslimeAmount * level && wasEating && entity instanceof PlayerEntity) {
			// clear eating marker
			PlayerEntity player = (PlayerEntity) entity;
			if (player.canEat(false)) {
				// hot eat the food
				player.getFoodData().eat(level, level * 0.1f);
				player.awardStat(Stats.ITEM_USED.get(tool.getItem()));
				world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EAT, SoundCategory.NEUTRAL, 1.0F, 1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F);
				world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundCategory.NEUTRAL, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);

				//don't bother with the breaking animation, we're only subtracting overslime anyways
				overslime.onDamageTool(tool, 1, overslimeAmount * level);
				//ToolDamageUtil.damage(tool, overslimeAmount * level, player, player.getUseItem());
				return true;
			}
		}
		return false;
	}

	@Override
	public UseAction getUseAction(IModifierToolStack tool, int level) {
		return tool.getPersistentData().getBoolean(IS_EATING) ? UseAction.EAT : UseAction.NONE;
	}

	@Override
	public int getUseDuration(IModifierToolStack tool, int level) {
		return tool.getPersistentData().getBoolean(IS_EATING) ? 32 : 0;
	}
}