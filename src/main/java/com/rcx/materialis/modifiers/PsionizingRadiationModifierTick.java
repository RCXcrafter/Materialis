package com.rcx.materialis.modifiers;

import com.rcx.materialis.compat.TinkerToolSocketable;
import com.rcx.materialis.util.MaterialisUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.core.handler.PlayerDataHandler.PlayerData;
import vazkii.psi.common.item.ItemCAD;

public class PsionizingRadiationModifierTick extends Modifier {


	public PsionizingRadiationModifierTick() {
		super(0xB6A9E7);
	}

	@Override
	public int getPriority() {
		return 200; //before most other things
	}

	@Override
	public void addVolatileData(Item item, ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, int level, ModDataNBT volatileData) {
		MaterialisUtil.addToVolatileInt(PsionizingRadiationModifier.RADIATION_LEVEL, volatileData, level);
	}

	@Override
	public void onInventoryTick(IModifierToolStack tool, int level, World world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
		if (isCorrectSlot && PsionizingRadiationModifier.enabled && !tool.isBroken() && holder != null && holder instanceof PlayerEntity && !tool.getVolatileData().getBoolean(PsionizingRadiationModifier.SUPPRESS_TOOLCASTING)) {
			PlayerEntity player = (PlayerEntity) holder;
			PlayerData data = PlayerDataHandler.get(player);
			ItemStack playerCad = PsiAPI.getPlayerCAD(player);

			if (!playerCad.isEmpty()) {
				int timesCast = tool.getPersistentData().getInt(TinkerToolSocketable.TIMES_CAST);
				ItemStack bullet = ISocketable.socketable(stack).getSelectedBullet();
				ItemCAD.cast(player.getCommandSenderWorld(), player, data, bullet, playerCad, 0, 0, 0F, (SpellContext spellContext) -> {
					spellContext.tool = stack;
					spellContext.loopcastIndex = timesCast;
				});
				tool.getPersistentData().putInt(TinkerToolSocketable.TIMES_CAST, timesCast + 1);
			}
		}
	}
}