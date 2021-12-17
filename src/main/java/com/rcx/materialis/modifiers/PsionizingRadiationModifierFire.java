package com.rcx.materialis.modifiers;

import com.rcx.materialis.compat.TinkerToolSocketable;
import com.rcx.materialis.item.ExosuitModelArmorItem;
import com.rcx.materialis.util.MaterialisUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.core.handler.PlayerDataHandler.PlayerData;
import vazkii.psi.common.item.ItemCAD;
import vazkii.psi.common.item.base.ModItems;

public class PsionizingRadiationModifierFire extends Modifier {

	public PsionizingRadiationModifierFire() {
		super(0xB6A9E7);
	}

	@Override
	public int getPriority() {
		return 200; //before most other things
	}

	@Override
	public void addVolatileData(Item item, ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, int level, ModDataNBT volatileData) {
		MaterialisUtil.addToVolatileInt(PsionizingRadiationModifier.RADIATION_LEVEL, volatileData, level);
		if (PsionizingRadiationModifier.enabled)
			volatileData.put(ExosuitModelArmorItem.SENSOR, new ItemStack(ModItems.exosuitSensorHeat).serializeNBT());
	}

	@Override
	public void onAttacked(IModifierToolStack tool, int level, EquipmentContext context, EquipmentSlotType slotType, DamageSource source, float amount, boolean isDirectDamage) {
		if (PsionizingRadiationModifier.enabled && !tool.isBroken() && context.getEntity() != null && context.getEntity() instanceof PlayerEntity && !tool.getVolatileData().getBoolean(PsionizingRadiationModifier.SUPPRESS_TOOLCASTING) && source.isFire()) {
			ItemStack toolStack = context.getEntity().getItemBySlot(slotType);
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();
			PlayerEntity player = (PlayerEntity) context.getEntity();
			PlayerData data = PlayerDataHandler.get(player);
			ItemStack playerCad = PsiAPI.getPlayerCAD(player);

			if (!playerCad.isEmpty()) {
				int timesCast = tool.getPersistentData().getInt(TinkerToolSocketable.TIMES_CAST);
				ItemStack bullet = ISocketable.socketable(toolStack).getSelectedBullet();
				final ItemStack finalTool = toolStack;
				ItemCAD.cast(player.getCommandSenderWorld(), player, data, bullet, playerCad, 5, 0, 0.025F, (SpellContext spellContext) -> {
					spellContext.tool = finalTool;
					if (source.getEntity() instanceof LivingEntity)
						spellContext.attackingEntity = (LivingEntity) source.getEntity();
					spellContext.damageTaken = amount;
					spellContext.loopcastIndex = timesCast;
				});
				tool.getPersistentData().putInt(TinkerToolSocketable.TIMES_CAST, timesCast + 1);
			}
		}
	}
}