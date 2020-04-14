package org.teamfelnull.kimnarutree.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class NotePCBlock extends Block {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
	private static final VoxelShape CLOSE_NORTH_AXIS_AABB = Block.makeCuboidShape(2, 0, 0, 13, 1, 16);
	private static final VoxelShape CLOSE_SOUTH_AXIS_AABB = Block.makeCuboidShape(3, 0, 0, 14, 1, 16);
	private static final VoxelShape CLOSE_EAST_AXIS_AABB = Block.makeCuboidShape(0, 0, 2, 16, 1, 13);
	private static final VoxelShape CLOSE_WEST_AXIS_AABB = Block.makeCuboidShape(0, 0, 3, 16, 1, 14);

	private static final VoxelShape OPEN_NORTH_PART1 = Block.makeCuboidShape(3, 0, 0, 13, 0.5d, 16);
	private static final VoxelShape OPEN_NORTH_PART2 = Block.makeCuboidShape(2, 0, 0, 3, 1, 16);
	private static final VoxelShape OPEN_NORTH_PART3 = Block.makeCuboidShape(3, 0.5d, 0, 3.5d, 2d, 16);
	private static final VoxelShape OPEN_NORTH_PART4 = Block.makeCuboidShape(2.5d, 1, 0, 3, 3, 16);
	private static final VoxelShape OPEN_NORTH_PART5 = Block.makeCuboidShape(2, 2, 0, 2.5d, 4, 16);
	private static final VoxelShape OPEN_NORTH_PART6 = Block.makeCuboidShape(1.5d, 3, 0, 2, 5, 16);
	private static final VoxelShape OPEN_NORTH_PART7 = Block.makeCuboidShape(1, 4, 0, 1.5d, 6, 16);
	private static final VoxelShape OPEN_NORTH_PART8 = Block.makeCuboidShape(0.5d, 5, 0, 1, 7, 16);
	private static final VoxelShape OPEN_NORTH_PART9 = Block.makeCuboidShape(0, 6, 0, 0.5d, 8, 16);
	private static final VoxelShape OPEN_NORTH_PART10 = Block.makeCuboidShape(-0.5d, 7, 0, 0, 9, 16);
	private static final VoxelShape OPEN_NORTH_PART11 = Block.makeCuboidShape(-1, 8, 0, -0.5d, 9.3d, 16);
	private static final VoxelShape OPEN_NORTH_AXIS_AABB = VoxelShapes.or(OPEN_NORTH_PART1, OPEN_NORTH_PART2,
			OPEN_NORTH_PART3, OPEN_NORTH_PART4, OPEN_NORTH_PART5, OPEN_NORTH_PART6, OPEN_NORTH_PART7, OPEN_NORTH_PART8,
			OPEN_NORTH_PART9, OPEN_NORTH_PART10, OPEN_NORTH_PART11);

	private static final VoxelShape OPEN_SOUTH_PART1 = makeCuboidShaoe180(3, 0, 0, 13, 0.5d, 16);
	private static final VoxelShape OPEN_SOUTH_PART2 = makeCuboidShaoe180(2, 0, 0, 3, 1, 16);
	private static final VoxelShape OPEN_SOUTH_PART3 = makeCuboidShaoe180(3, 0.5d, 0, 3.5d, 2d, 16);
	private static final VoxelShape OPEN_SOUTH_PART4 = makeCuboidShaoe180(2.5d, 1, 0, 3, 3, 16);
	private static final VoxelShape OPEN_SOUTH_PART5 = makeCuboidShaoe180(2, 2, 0, 2.5d, 4, 16);
	private static final VoxelShape OPEN_SOUTH_PART6 = makeCuboidShaoe180(1.5d, 3, 0, 2, 5, 16);
	private static final VoxelShape OPEN_SOUTH_PART7 = makeCuboidShaoe180(1, 4, 0, 1.5d, 6, 16);
	private static final VoxelShape OPEN_SOUTH_PART8 = makeCuboidShaoe180(0.5d, 5, 0, 1, 7, 16);
	private static final VoxelShape OPEN_SOUTH_PART9 = makeCuboidShaoe180(0, 6, 0, 0.5d, 8, 16);
	private static final VoxelShape OPEN_SOUTH_PART10 = makeCuboidShaoe180(-0.5d, 7, 0, 0, 9, 16);
	private static final VoxelShape OPEN_SOUTH_PART11 = makeCuboidShaoe180(-1, 8, 0, -0.5d, 9.3d, 16);
	private static final VoxelShape OPEN_SOUTH_AXIS_AABB = VoxelShapes.or(OPEN_SOUTH_PART1, OPEN_SOUTH_PART2,
			OPEN_SOUTH_PART3, OPEN_SOUTH_PART4, OPEN_SOUTH_PART5, OPEN_SOUTH_PART6, OPEN_SOUTH_PART7, OPEN_SOUTH_PART8,
			OPEN_SOUTH_PART9, OPEN_SOUTH_PART10, OPEN_SOUTH_PART11);

	private static final VoxelShape OPEN_EAST_PART1 = makeCuboidShaoe270(13, 0, 0, 3, 0.5d, 16);
	private static final VoxelShape OPEN_EAST_PART2 = makeCuboidShaoe270(14, 0, 0, 13, 1, 16);
	private static final VoxelShape OPEN_EAST_PART3 = makeCuboidShaoe270(13, 0.5d, 0, 12.5d, 2d, 16);
	private static final VoxelShape OPEN_EAST_PART4 = makeCuboidShaoe270(13.5d, 1, 0, 13, 3, 16);
	private static final VoxelShape OPEN_EAST_PART5 = makeCuboidShaoe270(14, 2, 0, 13.5d, 4, 16);
	private static final VoxelShape OPEN_EAST_PART6 = makeCuboidShaoe270(14.5d, 3, 0, 14, 5, 16);
	private static final VoxelShape OPEN_EAST_PART7 = makeCuboidShaoe270(15, 4, 0, 14.5d, 6, 16);
	private static final VoxelShape OPEN_EAST_PART8 = makeCuboidShaoe270(15.5d, 5, 0, 15, 7, 16);
	private static final VoxelShape OPEN_EAST_PART9 = makeCuboidShaoe270(16, 6, 0, 15.5d, 8, 16);
	private static final VoxelShape OPEN_EAST_PART10 = makeCuboidShaoe270(16.5d, 7, 0, 16, 9, 16);
	private static final VoxelShape OPEN_EAST_PART11 = makeCuboidShaoe270(17, 8, 0, 16.5d, 9.3d, 16);
	private static final VoxelShape OPEN_EAST_AXIS_AABB = VoxelShapes.or(OPEN_EAST_PART1, OPEN_EAST_PART2,
			OPEN_EAST_PART3, OPEN_EAST_PART4, OPEN_EAST_PART5, OPEN_EAST_PART6, OPEN_EAST_PART7, OPEN_EAST_PART8,
			OPEN_EAST_PART9, OPEN_EAST_PART10, OPEN_EAST_PART11);

	private static final VoxelShape OPEN_WEST_PART1 = makeCuboidShaoe90(13, 0, 0, 3, 0.5d, 16);
	private static final VoxelShape OPEN_WEST_PART2 = makeCuboidShaoe90(14, 0, 0, 13, 1, 16);
	private static final VoxelShape OPEN_WEST_PART3 = makeCuboidShaoe90(13, 0.5d, 0, 12.5d, 2d, 16);
	private static final VoxelShape OPEN_WEST_PART4 = makeCuboidShaoe90(13.5d, 1, 0, 13, 3, 16);
	private static final VoxelShape OPEN_WEST_PART5 = makeCuboidShaoe90(14, 2, 0, 13.5d, 4, 16);
	private static final VoxelShape OPEN_WEST_PART6 = makeCuboidShaoe90(14.5d, 3, 0, 14, 5, 16);
	private static final VoxelShape OPEN_WEST_PART7 = makeCuboidShaoe90(15, 4, 0, 14.5d, 6, 16);
	private static final VoxelShape OPEN_WEST_PART8 = makeCuboidShaoe90(15.5d, 5, 0, 15, 7, 16);
	private static final VoxelShape OPEN_WEST_PART9 = makeCuboidShaoe90(16, 6, 0, 15.5d, 8, 16);
	private static final VoxelShape OPEN_WEST_PART10 = makeCuboidShaoe90(16.5d, 7, 0, 16, 9, 16);
	private static final VoxelShape OPEN_WEST_PART11 = makeCuboidShaoe90(17, 8, 0, 16.5d, 9.3d, 16);
	private static final VoxelShape OPEN_WEST_AXIS_AABB = VoxelShapes.or(OPEN_WEST_PART1, OPEN_WEST_PART2,
			OPEN_WEST_PART3, OPEN_WEST_PART4, OPEN_WEST_PART5, OPEN_WEST_PART6, OPEN_WEST_PART7, OPEN_WEST_PART8,
			OPEN_WEST_PART9, OPEN_WEST_PART10, OPEN_WEST_PART11);

	public NotePCBlock(Properties properties) {
		super(properties);
		this.setDefaultState(
				this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(OPEN, Boolean.valueOf(false)));
	}

	private static VoxelShape makeCuboidShaoe90(double x1, double y1, double z1, double x2, double y2, double z2) {
		return Block.makeCuboidShape(z1, y1, x1, z2, y2, x2);
	}

	private static VoxelShape makeCuboidShaoe180(double x1, double y1, double z1, double x2, double y2, double z2) {
		return Block.makeCuboidShape(16 - x1, y1, 16 - z1, 16 - x2, y2, 16 - z2);
	}

	private static VoxelShape makeCuboidShaoe270(double x1, double y1, double z1, double x2, double y2, double z2) {
		return makeCuboidShaoe180(z1, y1, x1, z2, y2, x2);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {

		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().rotateY());
	}

	@Override
	public ActionResultType func_225533_a_(BlockState stateIn, World worldIn, BlockPos pos,
			PlayerEntity playerIn, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {

		if (playerIn.isCrouching()) {
			stateIn = stateIn.cycle(OPEN);
			worldIn.setBlockState(pos, stateIn, 2);
			worldIn.playEvent(playerIn, 1037, pos, 0);
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		return facing == Direction.DOWN && !this.isValidPosition(stateIn, worldIn, currentPos)
				? Blocks.AIR.getDefaultState()
				: super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return func_220055_a(worldIn, pos.down(), Direction.UP);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(FACING);

		if (state.get(OPEN)) {
			switch (direction) {
			case NORTH:
				return OPEN_NORTH_AXIS_AABB;
			case SOUTH:
				return OPEN_SOUTH_AXIS_AABB;
			case EAST:
				return OPEN_EAST_AXIS_AABB;
			case WEST:
				return OPEN_WEST_AXIS_AABB;
			default:
				return OPEN_NORTH_AXIS_AABB;
			}
		} else {
			switch (direction) {
			case NORTH:
				return CLOSE_NORTH_AXIS_AABB;
			case SOUTH:
				return CLOSE_SOUTH_AXIS_AABB;
			case EAST:
				return CLOSE_EAST_AXIS_AABB;
			case WEST:
				return CLOSE_WEST_AXIS_AABB;
			default:
				return CLOSE_NORTH_AXIS_AABB;
			}

		}
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, OPEN);
	}

}
