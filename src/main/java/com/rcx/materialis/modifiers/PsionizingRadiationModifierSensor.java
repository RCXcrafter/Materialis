package com.rcx.materialis.modifiers;

import java.util.List;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.compat.TinkerToolSocketable;
import com.rcx.materialis.util.MaterialisUtil;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants.NBT;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.utils.TooltipFlag;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.api.exosuit.IExosuitSensor;
import vazkii.psi.api.exosuit.PsiArmorEvent;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.core.handler.PlayerDataHandler.PlayerData;
import vazkii.psi.common.item.ItemCAD;

public class PsionizingRadiationModifierSensor extends Modifier {

	public static final ResourceLocation SENSOR = new ResourceLocation(Materialis.modID, "sensor");

	public PsionizingRadiationModifierSensor() {
		super(0xEA523C);
		if (PsionizingRadiationModifier.enabled)
			MinecraftForge.EVENT_BUS.addListener(this::onPsiArmorEvent);
	}

	@Override
	public int getPriority() {
		return 200; //before most other things
	}

	@Override
	public void onRemoved(IModifierToolStack tool) {
		tool.getPersistentData().remove(SENSOR);
	}

	@Override
	public void addVolatileData(Item item, ToolDefinition toolDefinition, StatsNBT baseStats, IModDataReadOnly persistentData, int level, ModDataNBT volatileData) {
		MaterialisUtil.addToVolatileInt(PsionizingRadiationModifier.RADIATION_LEVEL, volatileData, level);
	}

	public void onPsiArmorEvent(PsiArmorEvent event) {
		if (!event.getPlayer().isSpectator()) {
			for (int i = 0; i < 4; i++) {
				ItemStack armor = event.getPlayer().inventory.armor.get(i);
				ToolStack armorTool = ToolStack.copyFrom(armor);
				if (armorTool.getDefinition() != ToolDefinition.EMPTY && !armorTool.isBroken() && armorTool.getModifierLevel(this) > 0 && armorTool.getPersistentData().contains(SENSOR, NBT.TAG_COMPOUND) && !armorTool.getVolatileData().getBoolean(PsionizingRadiationModifier.SUPPRESS_TOOLCASTING)) {
					ItemStack sensor = ItemStack.of(armorTool.getPersistentData().getCompound(PsionizingRadiationModifierSensor.SENSOR));
					if (sensor.isEmpty() || !(sensor.getItem() instanceof IExosuitSensor) || !((IExosuitSensor) sensor.getItem()).getEventType(sensor).equals(event.type)) {
						return;
					}

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
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, TooltipFlag flag) {
		if (PsionizingRadiationModifier.enabled) {
			ItemStack sensor = ItemStack.of(tool.getPersistentData().getCompound(PsionizingRadiationModifierSensor.SENSOR));
			if (!sensor.isEmpty() && sensor.getItem() instanceof IExosuitSensor) {
				tooltip.add(new TranslationTextComponent(((IExosuitSensor) sensor.getItem()).getEventType(sensor)));
			}
		}
	}

	@Override
	public ITextComponent getDisplayName(IModifierToolStack tool, int level) {
		if (PsionizingRadiationModifier.enabled) {
			ItemStack sensor = ItemStack.of(tool.getPersistentData().getCompound(PsionizingRadiationModifierSensor.SENSOR));
			if (!sensor.isEmpty() && sensor.getItem() instanceof IExosuitSensor) {
				return new TranslationTextComponent(getTranslationKey()).withStyle(style -> style.withColor(Color.fromRgb(((IExosuitSensor) sensor.getItem()).getColor(sensor))));
			}
		}
		return super.getDisplayName(tool, level);
	}
}