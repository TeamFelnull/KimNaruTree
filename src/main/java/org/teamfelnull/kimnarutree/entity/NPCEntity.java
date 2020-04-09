package org.teamfelnull.kimnarutree.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class NPCEntity extends CreatureEntity {

	protected NPCEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);

	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 0.5D));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.35D));
		this.goalSelector.addGoal(3, new LookAtGoal(this, MobEntity.class, 8.0F));
		this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_WANDERING_TRADER_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_WANDERING_TRADER_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_WANDERING_TRADER_DEATH;
	}

	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}
}
