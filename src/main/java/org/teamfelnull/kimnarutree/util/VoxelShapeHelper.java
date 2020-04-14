package org.teamfelnull.kimnarutree.util;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;

public class VoxelShapeHelper {

	public static VoxelShape makeCuboidShaoe90(double x1, double y1, double z1, double x2, double y2, double z2) {
		return Block.makeCuboidShape(z1, y1, x1, z2, y2, x2);
	}

	public static VoxelShape makeCuboidShaoe180(double x1, double y1, double z1, double x2, double y2, double z2) {
		return Block.makeCuboidShape(16 - x1, y1, 16 - z1, 16 - x2, y2, 16 - z2);
	}

	public static VoxelShape makeCuboidShaoe270(double x1, double y1, double z1, double x2, double y2, double z2) {
		return makeCuboidShaoe180(z1, y1, x1, z2, y2, x2);
	}

}
