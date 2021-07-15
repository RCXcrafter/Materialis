package com.rcx.materialis.modifiers;

import java.util.List;

import com.rcx.materialis.Materialis;
import com.rcx.materialis.compat.TinkerToolSocketable;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.recipe.tinkerstation.ValidatedResult;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModDataReadOnly;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.utils.BlockSideHitListener;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.core.handler.PlayerDataHandler.PlayerData;
import vazkii.psi.common.item.ItemCAD;

public class PsionizingRadiationModifier extends Modifier {

	boolean enabled = ModList.get().isLoaded("psi");
	static final ValidatedResult SLOT_NOT_EMPTY = ValidatedResult.failure(Util.makeDescriptionId("recipe", new ResourceLocation(Materialis.modID, "remove_modifier.spell_slot_not_empty")));

	public PsionizingRadiationModifier() {
		super(0xB6A9E7);
	}

	@Override
	public int getPriority() {
		return 200; //before most other things
	}

	@Override
	public ValidatedResult validate(IModifierToolStack tool, int level) {
		//check if there are still spells in the sockets that are being removed
		if (enabled && tool instanceof ToolStack) {
			for (int l = level; l < ISocketable.MAX_ASSEMBLER_SLOTS; l++) {
				if (tool.getPersistentData().contains(TinkerToolSocketable.SPELL_SLOTS[l], NBT.TAG_COMPOUND))
					return SLOT_NOT_EMPTY;
			}
		}
		//remove tags if modifier is removed
		if (level == 0) {
			tool.getPersistentData().remove(TinkerToolSocketable.SELECTED_SPELL);
		}
		return ValidatedResult.PASS;
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
	}

	@Override
	public Boolean removeBlock(IModifierToolStack tool, int level, ToolHarvestContext context) {
		if (enabled && !tool.isBroken() && context.getPlayer() != null) {
			//level 2 unlocks aoe harvest casting
			if (context.isAOE() && level < 2) {
				return null;
			}
			ItemStack toolStack = context.getPlayer().getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();
			if (!TinkerTags.Items.HARVEST_PRIMARY.getValues().contains(toolStack.getItem()))
				return null;
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
		}
		return null;
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (enabled && !tool.isBroken() && context.getPlayerAttacker() != null && context.getLivingTarget() != null) {
			//level 2 unlocks aoe attack casting
			if (context.isExtraAttack() && level < 2) {
				return 0;
			}
			ItemStack toolStack = context.getPlayerAttacker().getMainHandItem();
			if (tool instanceof ToolStack)
				toolStack = ((ToolStack) tool).createStack();
			if (!TinkerTags.Items.MELEE_PRIMARY.getValues().contains(toolStack.getItem()))
				return 0;
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
		}
		return 0;
	}

	@Override
	public void addInformation(IModifierToolStack tool, int level, List<ITextComponent> tooltip, boolean isAdvanced, boolean detailed) {
		if (enabled && tool instanceof ToolStack) {
			ITextComponent componentName = ISocketable.getSocketedItemName(((ToolStack) tool).createStack(), "psimisc.none");
			tooltip.add(new TranslationTextComponent("psimisc.spell_selected", componentName));
		}
	}
}