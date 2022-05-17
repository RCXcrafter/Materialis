package com.rcx.materialis.modifiers;

import cofh.lib.block.IDismantleable;
import cofh.lib.util.Utils;
import cofh.lib.util.references.CoreReferences;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ThermalWrenchingModifier extends NoLevelsModifier {

	boolean enabled = ModList.get().isLoaded("cofh_core");

	@Override
	public InteractionResult beforeBlockUse(IToolStackView tool, int level, UseOnContext context, EquipmentSlot slot) {
		if (enabled && !tool.isBroken() && context.getPlayer() != null) {
			Level world = context.getLevel();
			BlockPos pos = context.getClickedPos();
			BlockState state = context.getLevel().getBlockState(context.getClickedPos());
			Block block = state.getBlock();
			if (context.getPlayer().isSecondaryUseActive() && block.canEntityDestroy(state, world, pos, context.getPlayer()) && block instanceof IDismantleable && ((IDismantleable) block).canDismantle(world, pos, state, context.getPlayer())) {
				if (Utils.isServerWorld(world)) {
					BlockHitResult target = new BlockHitResult(context.getClickLocation(), context.getClickedFace(), context.getClickedPos(), context.isInside());
					((IDismantleable) block).dismantleBlock(world, pos, state, target, context.getPlayer(), false);
				}
				ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		if (enabled) {
			LivingEntity target = context.getLivingTarget();
			if (target != null && context.isFullyCharged() && target.isAlive()) {
				target.addEffect(new MobEffectInstance(CoreReferences.WRENCHED, 60, 0, false, false));
			}
		}
		return 0;
	}
}