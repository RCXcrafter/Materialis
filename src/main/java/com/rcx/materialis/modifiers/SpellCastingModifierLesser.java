package com.rcx.materialis.modifiers;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public class SpellCastingModifierLesser extends Modifier {

	@Override
	public int getPriority() {
		return -100; //after most other things
	}

	@Override
	public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
		super.addVolatileData(context, level, volatileData);
		volatileData.putBoolean(PsionizingRadiationModifier.SUPPRESS_TOOLCASTING, true);
		//volatileData.putBoolean(TinkerToolSocketable.CAN_LOOPCAST, true);
		//volatileData.putBoolean(TinkerToolSocketable.SHOW_PSI_BAR, true);
	}

	@Override
	public InteractionResult onToolUse(IToolStackView tool, int level, Level world, Player player, InteractionHand hand, EquipmentSlot slot) {
		/*if (enabled && !tool.isBroken()) {
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
					return InteractionResult.CONSUME;
				}
			}
		}*/
		return InteractionResult.PASS;
	}
}