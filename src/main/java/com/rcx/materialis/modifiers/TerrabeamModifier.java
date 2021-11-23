package com.rcx.materialis.modifiers;

import java.util.Random;

import com.rcx.materialis.util.PacketTerraBeam;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.handler.ModSounds;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.network.PacketHandler;

public class TerrabeamModifier extends Modifier {

	public static boolean enabled = ModList.get().isLoaded("botania");
	public static int MANA_PER_BEAM = 100;
	public static float CHANCE = 0.4f;
	public static Random rand = new Random();

	public TerrabeamModifier() {
		super(0x53F900);
		if (enabled)
			MinecraftForge.EVENT_BUS.addListener(this::leftClick);
	}

	private void leftClick(PlayerInteractEvent.LeftClickEmpty event) {
		if (enabled && !event.getItemStack().isEmpty()) {
			ToolStack tool = getHeldTool(event.getEntityLiving(), Hand.MAIN_HAND);
			if (tool != null && tool.getModifierLevel(this) > 0) {
				PacketHandler.sendToServer(new PacketTerraBeam());
			}
		}
	}

	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (enabled && context.getAttacker() instanceof PlayerEntity)
			BurstHandler.trySpawnBurst((PlayerEntity) context.getAttacker(), false);
		return 0;
	}

	public static class BurstHandler { //this is in a separate class to make sure it doesn't try to load botania classes when botania isn't installed

		public static ItemStack sword = new ItemStack(ModItems.terraSword);

		public static void trySpawnBurst(PlayerEntity player, boolean nothingForFree) {
			if (enabled && !player.getMainHandItem().isEmpty() && player.getAttackStrengthScale(0) == 1) {
				ToolStack tool = getHeldTool(player, Hand.MAIN_HAND);
				if (tool != null) {
					int level = tool.getModifierLevel(MaterialisModifiers.terrabeamModifier.get());
					if (level > 0 && (level * CHANCE > 1.0f || rand.nextFloat() < level * CHANCE)) {
						EntityManaBurst burst = BurstHandler.getBurst(player);
						player.level.addFreshEntity(burst);
						if (nothingForFree)
							if (!ManaItemHandler.instance().requestManaExactForTool(player.getMainHandItem(), player, MANA_PER_BEAM, true))
								player.getMainHandItem().hurtAndBreak(1, player, p -> p.broadcastBreakEvent(Hand.MAIN_HAND));
						player.level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.terraBlade, SoundCategory.PLAYERS, 0.4F, 1.4F);
					}
				}
			}
		}

		public static EntityManaBurst getBurst(PlayerEntity player) {
			EntityManaBurst burst = new EntityManaBurst(player);
			float motionModifier = 7F;
			burst.setColor(0x20FF20);
			burst.setMana(MANA_PER_BEAM);
			burst.setStartingMana(MANA_PER_BEAM);
			burst.setMinManaLoss(40);
			burst.setManaLossPerTick(4F);
			burst.setGravity(0F);
			burst.setBurstMotion(burst.getDeltaMovement().x() * motionModifier, burst.getDeltaMovement().y() * motionModifier, burst.getDeltaMovement().z() * motionModifier);
			burst.setSourceLens(sword);
			return burst;
		}
	}
}