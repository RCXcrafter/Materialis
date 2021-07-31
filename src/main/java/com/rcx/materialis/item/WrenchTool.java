package com.rcx.materialis.item;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ToolType;
import slimeknights.tconstruct.library.tools.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ToolHarvestLogic;
import slimeknights.tconstruct.library.tools.item.ToolItem;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

/**
 * Tool with metal and other player made blocks harvest
 */
public class WrenchTool extends ToolItem {

	public static final ToolType TOOL_TYPE = ToolType.get("wrench");
	public static final ImmutableSet<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(Material.DECORATION, Material.BUILDABLE_GLASS, Material.SHULKER_SHELL, Material.GLASS, Material.METAL, Material.HEAVY_METAL, Material.PISTON);
	public static final ToolHarvestLogic HARVEST_LOGIC = new HarvestLogic();

	public WrenchTool(Properties properties, ToolDefinition toolDefinition) {
		super(properties, toolDefinition);
	}

	@Override
	public int getHarvestLevel(ItemStack stack, ToolType toolClass, @Nullable PlayerEntity player, @Nullable BlockState state) {
		//wrench is not an existing tool type, so check for effectiveness instead
		if (state == null || canHarvestBlock(stack, state)) { //canHarvestBlock takes care of checking if the tool is broken
			return ToolStack.from(stack).getStats().getInt(ToolStats.HARVEST_LEVEL);
		}
		return -1;
	}

	@Override
	public ToolHarvestLogic getToolHarvestLogic() {
		return HARVEST_LOGIC;
	}

	/** Harvest logic for wrenches */
	public static class HarvestLogic extends ToolHarvestLogic {

		@Override
		public boolean isEffectiveAgainst(IModifierToolStack tool, ItemStack stack, BlockState state) {
			return EFFECTIVE_MATERIALS.contains(state.getMaterial()) || super.isEffectiveAgainst(tool, stack, state);
		}
	}
}