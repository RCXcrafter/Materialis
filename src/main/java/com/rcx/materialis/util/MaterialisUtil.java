package com.rcx.materialis.util;

import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public class MaterialisUtil {

	public static void addToVolatileInt(ResourceLocation tag, ModDataNBT volatileData, int add) {
		if (volatileData.contains(tag, Tag.TAG_INT)) {
			volatileData.putInt(tag, volatileData.getInt(tag) + add);
		} else {
			volatileData.putInt(tag, add);
		}
	}

	public static boolean psiLoaded = ModList.get().isLoaded("psi");

	public static void castOnBlockBreak(IToolStackView tool, int level, ToolHarvestContext context, boolean checkTag) {
		/*if (psiLoaded && !tool.isBroken() && context.getPlayer() != null && !tool.getVolatileData().getBoolean(PsionizingRadiationModifier.SUPPRESS_TOOLCASTING)) {
			//level 2 unlocks aoe harvest casting
			if (context.isAOE() && tool.getVolatileData().getInt(PsionizingRadiationModifier.RADIATION_LEVEL) < 2) {
				return;
			}
			ItemStack toolStack = context.getPlayer().getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();
			if (checkTag && !TinkerTags.Items.HARVEST_PRIMARY.getValues().contains(toolStack.getItem()))
				return;
			PlayerData data = PlayerDataHandler.get(context.getPlayer());
			ItemStack playerCad = PsiAPI.getPlayerCAD(context.getPlayer());

			if (!playerCad.isEmpty()) {
				ItemStack bullet = ISocketable.socketable(toolStack).getSelectedBullet();
				final ItemStack finalTool = toolStack;
				Direction sideHit = BlockSideHitListener.getSideHit(context.getPlayer());
				Vector3d hit = new Vector3d((double)context.getPos().getX() + 0.5D - sideHit.getStepX() * 0.5D, context.getPos().getY() + 0.5D - sideHit.getStepY() * 0.5D, context.getPos().getZ() + 0.5D - sideHit.getStepZ() * 0.5D);
				ItemCAD.cast(context.getPlayer().getCommandSenderWorld(), context.getPlayer(), data, bullet, playerCad, 5, 10, 0.05F, (SpellContext spellContext) -> {
					spellContext.tool = finalTool;
					spellContext.positionBroken = new BlockRayTraceResult(hit, sideHit, context.getPos(), false);
				});
			}
		}*/
	}

	public static void castOnEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt, boolean checkTag) {
		/*if (psiLoaded && !tool.isBroken() && context.getPlayerAttacker() != null && context.getLivingTarget() != null && !tool.getVolatileData().getBoolean(PsionizingRadiationModifier.SUPPRESS_TOOLCASTING)) {
			//level 2 unlocks aoe attack casting
			if (context.isExtraAttack() && tool.getVolatileData().getInt(PsionizingRadiationModifier.RADIATION_LEVEL) < 2) {
				return;
			}
			ItemStack toolStack = context.getPlayerAttacker().getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();
			if (checkTag && !TinkerTags.Items.MELEE_PRIMARY.getValues().contains(toolStack.getItem()))
				return;
			PlayerData data = PlayerDataHandler.get(context.getPlayerAttacker());
			ItemStack playerCad = PsiAPI.getPlayerCAD(context.getPlayerAttacker());

			if (!playerCad.isEmpty()) {
				ItemStack bullet = ISocketable.socketable(toolStack).getSelectedBullet();
				final ItemStack finalTool = toolStack;
				ItemCAD.cast(context.getPlayerAttacker().getCommandSenderWorld(), context.getPlayerAttacker(), data, bullet, playerCad, 5, 10, 0.05F, (SpellContext spellContext) -> {
					spellContext.attackedEntity = context.getLivingTarget();
					spellContext.tool = finalTool;
				});
			}
		}*/
	}

	/*public static void castOnArmorEvent(PsiArmorEvent event, Modifier modifier, String type) {
		if (event.type.equals(type) && !event.getPlayer().isSpectator()) {
			for (int i = 0; i < 4; i++) {
				ItemStack armor = event.getPlayer().inventory.armor.get(i);
				ToolStack armorTool = ToolStack.copyFrom(armor);
				if (armorTool.getDefinition() != ToolDefinition.EMPTY && !armorTool.isBroken() && armorTool.getModifierLevel(modifier) > 0 && !armorTool.getVolatileData().getBoolean(PsionizingRadiationModifier.SUPPRESS_TOOLCASTING)) {
					PlayerData data = PlayerDataHandler.get(event.getPlayer());
					ItemStack playerCad = PsiAPI.getPlayerCAD(event.getPlayer());

					if (!playerCad.isEmpty()) {
						int timesCast = armorTool.getPersistentData().getInt(TinkerToolSocketable.TIMES_CAST);
						ItemStack bullet = ISocketable.socketable(armor).getSelectedBullet();
						ItemCAD.cast(event.getPlayer().getCommandSenderWorld(), event.getPlayer(), data, bullet, playerCad, 5, 0, 0.025F, (SpellContext spellContext) -> {
							spellContext.tool = armor;
							spellContext.loopcastIndex = timesCast;
						});
						armorTool.getPersistentData().putInt(TinkerToolSocketable.TIMES_CAST, timesCast + 1);
					}
				}
			}
		}
	}*/
}
