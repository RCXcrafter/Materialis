package com.rcx.materialis.modifiers;

import java.util.List;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.util.MaterialisUtil;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.utils.TooltipKey;

public class PsionizingRadiationModifierSensor extends Modifier {

	public static final ResourceLocation SENSOR = new ResourceLocation(Materialis.modID, "sensor");

	public PsionizingRadiationModifierSensor() {
		//if (PsionizingRadiationModifier.enabled)
			//MinecraftForge.EVENT_BUS.addListener(this::onPsiArmorEvent);
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

	/*public void onPsiArmorEvent(PsiArmorEvent event) {
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
	}*/

	@Override
	public void addInformation(IToolStackView tool, int level, Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
		/*if (PsionizingRadiationModifier.enabled) {
			ItemStack sensor = ItemStack.of(tool.getPersistentData().getCompound(PsionizingRadiationModifierSensor.SENSOR));
			if (!sensor.isEmpty() && sensor.getItem() instanceof IExosuitSensor) {
				tooltip.add(new TranslationTextComponent(((IExosuitSensor) sensor.getItem()).getEventType(sensor)));
			}
		}*/
	}

	@Override
	public Component getDisplayName(IToolStackView tool, int level) {
		/*if (PsionizingRadiationModifier.enabled) {
			ItemStack sensor = ItemStack.of(tool.getPersistentData().getCompound(PsionizingRadiationModifierSensor.SENSOR));
			if (!sensor.isEmpty() && sensor.getItem() instanceof IExosuitSensor) {
				return new TranslatableComponent(getTranslationKey()).withStyle(style -> style.withColor(TextColor.fromRgb(((IExosuitSensor) sensor.getItem()).getColor(sensor))));
			}
		}*/
		return super.getDisplayName(tool, level);
	}
}