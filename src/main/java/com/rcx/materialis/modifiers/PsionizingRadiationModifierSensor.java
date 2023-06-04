package com.rcx.materialis.modifiers;

import java.util.List;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.compat.TinkerToolSocketable;
import com.rcx.materialis.util.ITintingModifier;
import com.rcx.materialis.util.MaterialisUtil;

import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.common.MinecraftForge;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.api.exosuit.IExosuitSensor;
import vazkii.psi.api.exosuit.PsiArmorEvent;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.core.handler.PlayerDataHandler.PlayerData;
import vazkii.psi.common.item.ItemCAD;

public class PsionizingRadiationModifierSensor extends Modifier implements ITintingModifier {

	public static final ResourceLocation SENSOR = new ResourceLocation(Materialis.modID, "sensor");

	public PsionizingRadiationModifierSensor() {
		if (PsionizingRadiationModifier.enabled)
			MinecraftForge.EVENT_BUS.addListener(this::onPsiArmorEvent);
	}

	@Override
	public int getPriority() {
		return 200; //before most other things
	}

	@Override
	public void onRemoved(IToolStackView tool) {
		tool.getPersistentData().remove(SENSOR);
	}

	@Override
	public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
		MaterialisUtil.addToVolatileInt(PsionizingRadiationModifier.RADIATION_LEVEL, volatileData, level);
	}

	public void onPsiArmorEvent(PsiArmorEvent event) {
		if (!event.getPlayer().isSpectator()) {
			for (int i = 0; i < 4; i++) {
				ItemStack armor = event.getPlayer().getInventory().armor.get(i);
				ToolStack armorTool = ToolStack.copyFrom(armor);
				if (armorTool.getDefinition() != ToolDefinition.EMPTY && !armorTool.isBroken() && armorTool.getModifierLevel(this) > 0 && armorTool.getPersistentData().contains(SENSOR, Tag.TAG_COMPOUND) && !armorTool.getVolatileData().getBoolean(PsionizingRadiationModifier.SUPPRESS_TOOLCASTING)) {
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
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		if (PsionizingRadiationModifier.enabled) {
			ItemStack sensor = ItemStack.of(tool.getPersistentData().getCompound(PsionizingRadiationModifierSensor.SENSOR));
			if (!sensor.isEmpty() && sensor.getItem() instanceof IExosuitSensor) {
				tooltip.add(new TranslatableComponent(((IExosuitSensor) sensor.getItem()).getEventType(sensor)));
			}
		}
	}

	@Override
	public Component getDisplayName(IToolStackView tool, int level) {
		if (PsionizingRadiationModifier.enabled) {
			ItemStack sensor = ItemStack.of(tool.getPersistentData().getCompound(PsionizingRadiationModifierSensor.SENSOR));
			if (!sensor.isEmpty() && sensor.getItem() instanceof IExosuitSensor) {
				return new TranslatableComponent(getTranslationKey()).withStyle(style -> style.withColor(TextColor.fromRgb(((IExosuitSensor) sensor.getItem()).getColor(sensor))));
			}
		}
		return super.getDisplayName(tool, level);
	}

	@Override
	public int getTint(IToolStackView tool) {
		if (PsionizingRadiationModifier.enabled && tool.getPersistentData().contains(PsionizingRadiationModifierSensor.SENSOR, Tag.TAG_COMPOUND)) {
			ItemStack stack = ItemStack.of(tool.getPersistentData().getCompound(PsionizingRadiationModifierSensor.SENSOR));
			if (stack.getItem() instanceof IExosuitSensor)
				return ((IExosuitSensor) stack.getItem()).getColor(stack);
		}
		return 0x82190A;
	};

	@Override
	public int getLuminosity(IToolStackView tool) {
		return 15;
	};
}