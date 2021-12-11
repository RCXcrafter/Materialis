package com.rcx.materialis.modifiers;

import com.rcx.materialis.compat.TinkerToolSocketable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.api.spell.ISpellAcceptor;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.core.handler.PlayerDataHandler.PlayerData;
import vazkii.psi.common.item.ItemCAD;

public class SpellCastingModifierLesser extends Modifier {

	public SpellCastingModifierLesser() {
		super(0x6C89E6);
	}

	@Override
	public int getPriority() {
		return -100; //after most other things
	}

	@Override
	public void addVolatileData(Item item, ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, int level, ModDataNBT volatileData) {
		super.addVolatileData(item, toolDefinition, baseStats, persistentData, level, volatileData);
		volatileData.putBoolean(PsionizingRadiationModifier.SUPPRESS_TOOLCASTING, true);
		volatileData.putBoolean(TinkerToolSocketable.CAN_LOOPCAST, true);
		volatileData.putBoolean(TinkerToolSocketable.SHOW_PSI_BAR, true);
	}

	@Override
	public ActionResultType onToolUse(IModifierToolStack tool, int level, World world, PlayerEntity player, Hand hand) {
		if (SpellSocketModifier.enabled && !tool.isBroken()) {
			ItemStack toolStack = player.getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();
			PlayerData data = PlayerDataHandler.get(player);
			ItemStack playerCad = PsiAPI.getPlayerCAD(player);

			if (!playerCad.isEmpty()) {
				ItemStack bullet = ISocketable.socketable(toolStack).getSelectedBullet();
				if (!data.overflowed && data.getAvailablePsi() > 0 && !bullet.isEmpty() && ISpellAcceptor.hasSpell(bullet) && ItemCAD.isTruePlayer(player)) {
					int timesCast = tool.getPersistentData().getInt(TinkerToolSocketable.TIMES_CAST);
					ItemCAD.cast(player.getCommandSenderWorld(), player, data, bullet, playerCad, 40, 25, 0.5F, context -> {
						context.castFrom = hand;
						context.loopcastIndex = timesCast;
					});
					ToolDamageUtil.damage(tool, 1, player, toolStack);
					tool.getPersistentData().putInt(TinkerToolSocketable.TIMES_CAST, timesCast + 1);
					return ActionResultType.CONSUME;
				}
			}
		}
		return ActionResultType.PASS;
	}
}