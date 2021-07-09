package com.rcx.materialis.modifiers;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.Property;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import slimeknights.tconstruct.library.modifiers.SingleUseModifier;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class WrenchingModifier extends SingleUseModifier {

	private final int priority;

	public WrenchingModifier(int color, int priority) {
		super(color);
		this.priority = priority;
	}

	@Override
	public int getPriority() {
		return priority;
	}

	@Override
	public boolean shouldDisplay(boolean advanced) {
		return priority > Short.MIN_VALUE;
	}

	@Override
	public ActionResultType beforeBlockUse(IModifierToolStack tool, int level, ItemUseContext context) {
		ActionResultType result = ActionResultType.PASS;
		if (!tool.isBroken() && context.getPlayer() != null) {
			BlockState state = context.getLevel().getBlockState(context.getClickedPos());
			if (context.getPlayer().isSecondaryUseActive() || !state.getBlock().canEntityDestroy(state, context.getLevel(), context.getClickedPos(), context.getPlayer()) || state.getHarvestLevel() > tool.getStats().getInt(ToolStats.HARVEST_LEVEL) || !isRotatable(state, context.getLevel(), context.getClickedPos(), true))
				return result;

			Direction face = context.getClickedFace();
			Rotation rotation = context.getClickedFace().getAxisDirection() == Direction.AxisDirection.POSITIVE ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90;
			for (Property<?> prop : state.getProperties()) {
				if (prop instanceof DirectionProperty) {
					Direction direction = state.getValue((DirectionProperty) prop);
					Direction newDirection = rotateDirection(direction, face.getAxis(), rotation);
					if (newDirection == direction) //make sure something changed
						continue;

					int attempts = 1; //check if the block can even be rotated like this
					while (!prop.getPossibleValues().contains(newDirection) && attempts < 3) {
						newDirection = rotateDirection(direction, face.getAxis(), rotation.getRotated(rotation)); //try the next rotation
						attempts++;
					}
					if (prop.getPossibleValues().contains(newDirection)) {
						context.getLevel().setBlock(context.getClickedPos(), state.setValue((DirectionProperty) prop, newDirection), Constants.BlockFlags.DEFAULT_AND_RERENDER);
						result = ActionResultType.SUCCESS;
						ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
						break;
					}
				}
				if (prop == BlockStateProperties.AXIS || prop == BlockStateProperties.HORIZONTAL_AXIS) {
					Direction.Axis axis = prop == BlockStateProperties.AXIS ? state.getValue(BlockStateProperties.AXIS) : state.getValue(BlockStateProperties.HORIZONTAL_AXIS);
					Direction.Axis newAxis = rotateAxis(axis, face.getAxis(), rotation);
					if (newAxis == axis) //make sure something changed
						continue;

					//check if the block can even be rotated like this
					if (prop.getPossibleValues().contains(newAxis)) {
						if (prop == BlockStateProperties.AXIS)
							context.getLevel().setBlock(context.getClickedPos(), state.setValue(BlockStateProperties.AXIS, newAxis), Constants.BlockFlags.DEFAULT_AND_RERENDER);
						else
							context.getLevel().setBlock(context.getClickedPos(), state.setValue(BlockStateProperties.HORIZONTAL_AXIS, newAxis), Constants.BlockFlags.DEFAULT_AND_RERENDER);
						result = ActionResultType.SUCCESS;
						ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
						break;
					}
				}
				if (prop == BlockStateProperties.ROTATION_16) {
					int rotation16 = state.getValue(BlockStateProperties.ROTATION_16);
					rotation16 = (rotation16 + 1) % 16;
					context.getLevel().setBlock(context.getClickedPos(), state.setValue(BlockStateProperties.ROTATION_16, rotation16), Constants.BlockFlags.DEFAULT_AND_RERENDER);
					result = ActionResultType.SUCCESS;
					ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
					break;
				}
				if (prop == BlockStateProperties.HALF) {
					Half half = state.getValue(BlockStateProperties.HALF);
					if (half == Half.TOP)
						half = Half.BOTTOM;
					else
						half = Half.TOP;
					context.getLevel().setBlock(context.getClickedPos(), state.setValue(BlockStateProperties.HALF, half), Constants.BlockFlags.DEFAULT_AND_RERENDER);
					result = ActionResultType.SUCCESS;
					ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
					break;
				}
			}
		}
		return result;
	}

	public static Direction rotateDirection(Direction direction, Direction.Axis rotateOn, Rotation rotation) {
		if (direction.getAxis() == rotateOn)
			return direction;

		switch (rotation) {
		case CLOCKWISE_180:
			return direction.getOpposite();
		case COUNTERCLOCKWISE_90:
			switch (rotateOn) {
			case X:
				switch (direction) {
				case DOWN: return Direction.NORTH;
				case UP: return Direction.SOUTH;
				case NORTH: return Direction.UP;
				case SOUTH: return Direction.DOWN;
				default: return direction;
				}
			case Y:
				switch (direction) {
				case NORTH: return Direction.WEST;
				case SOUTH: return Direction.EAST;
				case WEST: return Direction.SOUTH;
				case EAST: return Direction.NORTH;
				default: return direction;
				}
			case Z:
				switch (direction) {
				case DOWN: return Direction.EAST;
				case UP: return Direction.WEST;
				case WEST: return Direction.DOWN;
				case EAST: return Direction.UP;
				default: return direction;
				}
			}
		case CLOCKWISE_90:
			switch (rotateOn) {
			case X:
				switch (direction) {
				case DOWN: return Direction.SOUTH;
				case UP: return Direction.NORTH;
				case NORTH: return Direction.DOWN;
				case SOUTH: return Direction.UP;
				default: return direction;
				}
			case Y:
				switch (direction) {
				case NORTH: return Direction.EAST;
				case SOUTH: return Direction.WEST;
				case WEST: return Direction.NORTH;
				case EAST: return Direction.SOUTH;
				default: return direction;
				}
			case Z:
				switch (direction) {
				case DOWN: return Direction.WEST;
				case UP: return Direction.EAST;
				case WEST: return Direction.UP;
				case EAST: return Direction.DOWN;
				default: return direction;
				}
			}
		default:
			return direction;
		}
	}

	public static Direction.Axis rotateAxis(Direction.Axis axis, Direction.Axis rotateOn, Rotation rotation) {
		switch (rotation) {
		case CLOCKWISE_180:
			return axis;
		case COUNTERCLOCKWISE_90:
		case CLOCKWISE_90:
			switch (rotateOn) {
			case X:
				switch (axis) {
				case Y: return Direction.Axis.Z;
				case Z: return Direction.Axis.Y;
				default: return axis;
				}
			case Y:
				switch (axis) {
				case X: return Direction.Axis.Z;
				case Z: return Direction.Axis.X;
				default: return axis;
				}
			case Z:
				switch (axis) {
				case X: return Direction.Axis.Y;
				case Y: return Direction.Axis.X;
				default: return axis;
				}
			}
		default:
			return axis;
		}
	}

	//modified version of {@link PistonBlock#isPushable(BlockState World BlockPos Direction boolean Direction) }
	public static boolean isRotatable(BlockState p_185646_0_, World p_185646_1_, BlockPos p_185646_2_, boolean p_185646_4_) {
		if (p_185646_2_.getY() >= 0 && p_185646_2_.getY() <= p_185646_1_.getMaxBuildHeight() - 1 && p_185646_1_.getWorldBorder().isWithinBounds(p_185646_2_)) {
			if (p_185646_0_.isAir()) {
				return true;
			} else if (!p_185646_0_.is(Blocks.OBSIDIAN) && !p_185646_0_.is(Blocks.CRYING_OBSIDIAN) && !p_185646_0_.is(Blocks.RESPAWN_ANCHOR)) {
				if (!p_185646_0_.is(Blocks.PISTON) && !p_185646_0_.is(Blocks.STICKY_PISTON)) {
					if (p_185646_0_.getDestroySpeed(p_185646_1_, p_185646_2_) == -1.0F) {
						return false;
					}
					switch(p_185646_0_.getPistonPushReaction()) {
					case BLOCK:
						return false;
					case DESTROY:
						return p_185646_4_;
					case PUSH_ONLY:
						return true;
					}
				} else if (p_185646_0_.getValue(PistonBlock.EXTENDED)) {
					return false;
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}