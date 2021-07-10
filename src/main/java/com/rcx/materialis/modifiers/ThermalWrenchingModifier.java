package com.rcx.materialis.modifiers;

import cofh.lib.block.IDismantleable;
import cofh.lib.util.Utils;
import cofh.lib.util.references.CoreReferences;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.helper.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class ThermalWrenchingModifier extends SingleUseModifier {

	boolean enabled = ModList.get().isLoaded("thermal");

	public ThermalWrenchingModifier() {
		super(0x0755D7);
	}

	@Override
	public ActionResultType beforeBlockUse(IModifierToolStack tool, int level, ItemUseContext context) {
		if (enabled && !tool.isBroken() && context.getPlayer() != null) {
			World world = context.getLevel();
			BlockPos pos = context.getClickedPos();
			BlockState state = context.getLevel().getBlockState(context.getClickedPos());
			Block block = state.getBlock();
			if (context.getPlayer().isSecondaryUseActive() && block.canEntityDestroy(state, world, pos, context.getPlayer()) && block instanceof IDismantleable && ((IDismantleable) block).canDismantle(world, pos, state, context.getPlayer())) {
				if (Utils.isServerWorld(world)) {
					BlockRayTraceResult target = new BlockRayTraceResult(context.getClickLocation(), context.getClickedFace(), context.getClickedPos(), context.isInside());
					((IDismantleable) block).dismantleBlock(world, pos, state, target, context.getPlayer(), false);
				}
				ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
				return ActionResultType.SUCCESS;
			} 
		}
		return ActionResultType.PASS;
	}

	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (enabled) {
			LivingEntity target = context.getLivingTarget();
			if (target != null && context.isFullyCharged() && target.isAlive()) {
				target.addEffect(new EffectInstance(CoreReferences.WRENCHED, 60, 0, false, false));
			}
		}
		return 0;
	}
}