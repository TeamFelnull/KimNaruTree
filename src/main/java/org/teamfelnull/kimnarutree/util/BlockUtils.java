package org.teamfelnull.kimnarutree.util;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUtils {
	public static boolean isSky(World worldIn, BlockPos posIn) {
		BlockPos pos = posIn;
		while (pos.getY() < 255) {
			pos = pos.up();
			double d0 = Math.max(
					worldIn.getBlockState(pos).getCollisionShape(worldIn, pos).max(Direction.Axis.Y,
							pos.getX() + 0.5d - (double) pos.getX(), pos.getZ() + 0.5d - (double) pos.getZ()),
					(double) worldIn.getFluidState(pos).func_215679_a(worldIn, pos));
			if (d0 > 0.0D) {
				return false;
			}
		}

		return true;
	}
}
