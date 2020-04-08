package org.teamfelnull.kimnarutree.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class NPCEntity extends CreatureEntity {

	protected NPCEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);

	}

	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}
}
