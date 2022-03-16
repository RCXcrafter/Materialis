package com.rcx.materialis.modifiers;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.impl.SingleUseModifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ThermalWrenchingModifier extends SingleUseModifier {

	boolean enabled = ModList.get().isLoaded("thermal");

	@Override
	public InteractionResult beforeBlockUse(IToolStackView tool, int level, UseOnContext context, EquipmentSlot slot) {
		/*if (enabled && !tool.isBroken() && context.getPlayer() != null) {
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
				return InteractionResult.SUCCESS;
			}
		}*/
		return InteractionResult.PASS;
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		/*if (enabled) {
			LivingEntity target = context.getLivingTarget();
			if (target != null && context.isFullyCharged() && target.isAlive()) {
				target.addEffect(new MobEffectInstance(CoreReferences.WRENCHED, 60, 0, false, false));
			}
		}*/
		return 0;
	}
}