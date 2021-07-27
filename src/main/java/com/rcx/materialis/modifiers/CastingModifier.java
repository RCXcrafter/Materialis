package com.rcx.materialis.modifiers;

import com.rcx.materialis.compat.TinkerToolSocketable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.core.handler.PlayerDataHandler.PlayerData;
import vazkii.psi.common.item.ItemCAD;

public class CastingModifier extends Modifier {

	boolean enabled = ModList.get().isLoaded("psi");

	public CastingModifier() {
		super(0x6C89E6);
	}

	@Override
	public int getPriority() {
		return -100; //after most other things
	}

	@Override
	public ValidatedResult validate(IModifierToolStack tool, int level) {
		return SpellSocketModifier.validateSockets(tool, level);
	}

	@Override
	public void addVolatileData(ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, int level, ModDataNBT volatileData) {
		if (!enabled)
			return;
		if (volatileData.contains(TinkerToolSocketable.SOCKETS, NBT.TAG_INT)) {
			volatileData.putInt(TinkerToolSocketable.SOCKETS, volatileData.getInt(TinkerToolSocketable.SOCKETS) + level);
		} else {
			volatileData.putInt(TinkerToolSocketable.SOCKETS, level);
		}
		volatileData.putBoolean(PsionizingRadiationModifier.SUPPRESS_TOOLCASTING, true);
		volatileData.putBoolean(TinkerToolSocketable.CAN_LOOPCAST, true);
		volatileData.putBoolean(TinkerToolSocketable.SHOW_PSI_BAR, true);
	}

	@Override
	public ActionResultType onToolUse(IModifierToolStack tool, int level, World world, PlayerEntity player, Hand hand) {
		if (enabled && !tool.isBroken()) {
			ItemStack toolStack = player.getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();
			PlayerData data = PlayerDataHandler.get(player);
			ItemStack playerCad = PsiAPI.getPlayerCAD(player);

			if (!playerCad.isEmpty()) {
				ItemStack bullet = ISocketable.socketable(toolStack).getSelectedBullet();
				ItemCAD.cast(player.getCommandSenderWorld(), player, data, bullet, playerCad, 40, 25, 0.5F, context -> context.castFrom = hand);
				ToolDamageUtil.damage(tool, 1, player, toolStack);
				return ActionResultType.CONSUME;
			}
		}
		return ActionResultType.PASS;
	}
}