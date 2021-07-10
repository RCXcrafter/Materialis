package com.rcx.materialis.modifiers;

import java.util.Collection;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.Property;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.ChestType;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.RailShape;
import net.minecraft.state.properties.SlabType;
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
			World world = context.getLevel();
			BlockPos pos = context.getClickedPos();
			BlockState state = world.getBlockState(pos);
			if (context.getPlayer().isSecondaryUseActive() || !state.getBlock().canEntityDestroy(state, world, pos, context.getPlayer()) || state.getHarvestLevel() > tool.getStats().getInt(ToolStats.HARVEST_LEVEL) || !isRotatable(state, world, pos))
				return result;

			Direction face = context.getClickedFace();
			Rotation rotation = context.getClickedFace().getAxisDirection() == Direction.AxisDirection.POSITIVE ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90;
			for (Property<?> prop : state.getProperties()) {
				if (prop instanceof DirectionProperty) {
					Direction direction = state.getValue((DirectionProperty) prop);
					Direction newDirection = rotateDirection(direction, face.getAxis(), rotation);
					if (newDirection != direction) { //make sure something changed
						BlockState newState = null;
						int attempts = 0; //check if the block can even be rotated like this
						while (attempts < 3) {
							if (prop.getPossibleValues().contains(newDirection)) {
								newState = state.setValue((DirectionProperty) prop, newDirection);
								if (newState.canSurvive(world, pos))
									break;
							}
							newDirection = rotateDirection(newDirection, face.getAxis(), rotation); //try the next rotation
							attempts++;
						}
						if (prop.getPossibleValues().contains(newDirection) && newState.canSurvive(world, pos)) {
							world.setBlock(pos, newState, Constants.BlockFlags.DEFAULT_AND_RERENDER);
							result = ActionResultType.SUCCESS;
							ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
							break;
						}
					}
				}
				if (prop == BlockStateProperties.AXIS || prop == BlockStateProperties.HORIZONTAL_AXIS) {
					Direction.Axis axis = prop == BlockStateProperties.AXIS ? state.getValue(BlockStateProperties.AXIS) : state.getValue(BlockStateProperties.HORIZONTAL_AXIS);
					Direction.Axis newAxis = rotateAxis(axis, face.getAxis(), rotation);
					if (newAxis != axis) { //make sure something changed
						//check if the block can even be rotated like this
						if (prop.getPossibleValues().contains(newAxis)) {
							BlockState newState;
							if (prop == BlockStateProperties.AXIS)
								newState = state.setValue(BlockStateProperties.AXIS, newAxis);
							else
								newState = state.setValue(BlockStateProperties.HORIZONTAL_AXIS, newAxis);

							if (newState.canSurvive(world, pos)) {
								world.setBlock(pos, newState, Constants.BlockFlags.DEFAULT_AND_RERENDER);
								result = ActionResultType.SUCCESS;
								ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
								break;
							}
						}
					}
				}
				if (prop == BlockStateProperties.RAIL_SHAPE || prop == BlockStateProperties.RAIL_SHAPE_STRAIGHT) {
					RailShape shape = prop == BlockStateProperties.RAIL_SHAPE ? state.getValue(BlockStateProperties.RAIL_SHAPE) : state.getValue(BlockStateProperties.RAIL_SHAPE_STRAIGHT);
					RailShape newShape = rotateRail(shape, face.getAxis(), rotation);
					if (newShape != shape) { //make sure something changed
						//check if the block can even be rotated like this
						if (shouldRailBeRemoved(pos, world, newShape))
							newShape = rotateRail(shape, face.getAxis(), rotation.getRotated(Rotation.CLOCKWISE_180));

						if (!shouldRailBeRemoved(pos, world, newShape) && prop.getPossibleValues().contains(newShape)) {
							BlockState newState;
							if (prop == BlockStateProperties.RAIL_SHAPE)
								newState = state.setValue(BlockStateProperties.RAIL_SHAPE, newShape);
							else
								newState = state.setValue(BlockStateProperties.RAIL_SHAPE_STRAIGHT, newShape);

							world.setBlock(pos, newState, Constants.BlockFlags.DEFAULT_AND_RERENDER);
							result = ActionResultType.SUCCESS;
							ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
							break;

						}
					}
				}
				if (prop == BlockStateProperties.ROTATION_16) {
					int rotation16 = state.getValue(BlockStateProperties.ROTATION_16);
					rotation16 = (rotation16 + 1) % 16;
					world.setBlock(pos, state.setValue(BlockStateProperties.ROTATION_16, rotation16), Constants.BlockFlags.DEFAULT_AND_RERENDER);
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
					BlockState newState = state.setValue(BlockStateProperties.HALF, half);
					if (newState.canSurvive(world, pos)) {
						world.setBlock(pos, state.setValue(BlockStateProperties.HALF, half), Constants.BlockFlags.DEFAULT_AND_RERENDER);
						result = ActionResultType.SUCCESS;
						ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
						break;
					}
				}
				if (prop == BlockStateProperties.SLAB_TYPE) {
					SlabType half = state.getValue(BlockStateProperties.SLAB_TYPE);
					if (half == SlabType.DOUBLE) {
						if (half == SlabType.TOP)
							half = SlabType.BOTTOM;
						else if (half == SlabType.BOTTOM)
							half = SlabType.TOP;
						BlockState newState = state.setValue(BlockStateProperties.SLAB_TYPE, half);
						if (newState.canSurvive(world, pos)) {
							world.setBlock(pos, newState, Constants.BlockFlags.DEFAULT_AND_RERENDER);
							result = ActionResultType.SUCCESS;
							ToolDamageUtil.damage(tool, 1, context.getPlayer(), context.getItemInHand());
							break;
						}
					}
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

	public RailShape rotateRail(RailShape shape, Direction.Axis rotateOn, Rotation rotation) {
		switch (rotateOn) {
		case Y:
			switch(rotation) {
			case CLOCKWISE_180:
				switch(shape) {
				case ASCENDING_EAST:
					return RailShape.ASCENDING_WEST;
				case ASCENDING_WEST:
					return RailShape.ASCENDING_EAST;
				case ASCENDING_NORTH:
					return RailShape.ASCENDING_SOUTH;
				case ASCENDING_SOUTH:
					return RailShape.ASCENDING_NORTH;
				case SOUTH_EAST:
					return RailShape.NORTH_WEST;
				case SOUTH_WEST:
					return RailShape.NORTH_EAST;
				case NORTH_WEST:
					return RailShape.SOUTH_EAST;
				case NORTH_EAST:
					return RailShape.SOUTH_WEST;
				case NORTH_SOUTH:
				case EAST_WEST:
					return shape;
				}
			case COUNTERCLOCKWISE_90:
				switch(shape) {
				case ASCENDING_EAST:
					return RailShape.ASCENDING_NORTH;
				case ASCENDING_WEST:
					return RailShape.ASCENDING_SOUTH;
				case ASCENDING_NORTH:
					return RailShape.ASCENDING_WEST;
				case ASCENDING_SOUTH:
					return RailShape.ASCENDING_EAST;
				case SOUTH_EAST:
					return RailShape.NORTH_EAST;
				case SOUTH_WEST:
					return RailShape.SOUTH_EAST;
				case NORTH_WEST:
					return RailShape.SOUTH_WEST;
				case NORTH_EAST:
					return RailShape.NORTH_WEST;
				case NORTH_SOUTH:
					return RailShape.EAST_WEST;
				case EAST_WEST:
					return RailShape.NORTH_SOUTH;
				}
			case CLOCKWISE_90:
				switch(shape) {
				case ASCENDING_EAST:
					return RailShape.ASCENDING_SOUTH;
				case ASCENDING_WEST:
					return RailShape.ASCENDING_NORTH;
				case ASCENDING_NORTH:
					return RailShape.ASCENDING_EAST;
				case ASCENDING_SOUTH:
					return RailShape.ASCENDING_WEST;
				case SOUTH_EAST:
					return RailShape.SOUTH_WEST;
				case SOUTH_WEST:
					return RailShape.NORTH_WEST;
				case NORTH_WEST:
					return RailShape.NORTH_EAST;
				case NORTH_EAST:
					return RailShape.SOUTH_EAST;
				case NORTH_SOUTH:
					return RailShape.EAST_WEST;
				case EAST_WEST:
					return RailShape.NORTH_SOUTH;
				}
			default:
				return shape;
			}
		case X:
			switch(rotation) {
			case CLOCKWISE_90:
				switch(shape) {
				case ASCENDING_NORTH:
					return RailShape.NORTH_SOUTH;
				case ASCENDING_SOUTH:
					return RailShape.NORTH_SOUTH;
				case NORTH_SOUTH:
					return RailShape.ASCENDING_NORTH;
				default:
					return shape;
				}
			case COUNTERCLOCKWISE_90:
				switch(shape) {
				case ASCENDING_NORTH:
					return RailShape.NORTH_SOUTH;
				case ASCENDING_SOUTH:
					return RailShape.NORTH_SOUTH;
				case NORTH_SOUTH:
					return RailShape.ASCENDING_SOUTH;
				default:
					return shape;
				}
			default:
				return shape;
			}
		case Z:
			switch(rotation) {
			case CLOCKWISE_90:
				switch(shape) {
				case ASCENDING_EAST:
					return RailShape.EAST_WEST;
				case ASCENDING_WEST:
					return RailShape.EAST_WEST;
				case EAST_WEST:
					return RailShape.ASCENDING_EAST;
				default:
					return shape;
				}
			case COUNTERCLOCKWISE_90:
				switch(shape) {
				case ASCENDING_EAST:
					return RailShape.EAST_WEST;
				case ASCENDING_WEST:
					return RailShape.EAST_WEST;
				case EAST_WEST:
					return RailShape.ASCENDING_WEST;
				default:
					return shape;
				}
			default:
				return shape;
			}
		default:
			return shape;
		}
	}

	public static boolean isRotatable(BlockState state, World world, BlockPos pos) {
		if (pos.getY() >= 0 && pos.getY() <= world.getMaxBuildHeight() - 1 && world.getWorldBorder().isWithinBounds(pos)) {
			Collection<Property<?>> properties = state.getProperties();
			//make sure that multiblocks can't be rotated
			if (properties.contains(BlockStateProperties.DOUBLE_BLOCK_HALF)) //double tall blocks
				return false;
			if (properties.contains(BlockStateProperties.BED_PART)) //beds
				return false;
			if (properties.contains(BlockStateProperties.CHEST_TYPE) && state.getValue(BlockStateProperties.CHEST_TYPE) != ChestType.SINGLE) //double chests
				return false;
			if (properties.contains(BlockStateProperties.EXTENDED) && state.getValue(BlockStateProperties.EXTENDED)) //extended pistons
				return false;
			return true;
		}
		return false;
	}

	public static boolean shouldRailBeRemoved(BlockPos pos, World world, RailShape shape) {
		if (!Block.canSupportRigidBlock(world, pos.below())) {
			return true;
		} else {
			switch(shape) {
			case ASCENDING_EAST:
				return !Block.canSupportRigidBlock(world, pos.east());
			case ASCENDING_WEST:
				return !Block.canSupportRigidBlock(world, pos.west());
			case ASCENDING_NORTH:
				return !Block.canSupportRigidBlock(world, pos.north());
			case ASCENDING_SOUTH:
				return !Block.canSupportRigidBlock(world, pos.south());
			default:
				return false;
			}
		}
	}
}