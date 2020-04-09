package org.teamfelnull.kimnarutree.entity;

import org.teamfelnull.kimnarutree.item.KNTItems;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.INPC;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class NPCEntity extends CreatureEntity implements INPC {

	public long money;
	public NonNullList<ItemStack> inventoryItems = NonNullList.withSize(9, ItemStack.EMPTY);

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

	@Override
	protected void dropInventory() {

		for (ItemStack item : inventoryItems) {
			this.entityDropItem(item);
		}

	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_WANDERING_TRADER_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_WANDERING_TRADER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_WANDERING_TRADER_DEATH;
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getHeldItem(hand);

		if (itemstack.getItem() == KNTItems.PICKY) {

			if (!player.world.isRemote)
				player.sendMessage(new StringTextComponent("items=" + this.inventoryItems));

			return true;
		} else {

			if (!itemstack.isEmpty()) {
				if (this.addItem(itemstack.copy()))
					itemstack.shrink(itemstack.getCount());
				return true;
			}
		}

		return super.processInteract(player, hand);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.money = compound.getLong("Money");

		this.inventoryItems = NonNullList.withSize(9, ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventoryItems);

	}

	public boolean addItem(ItemStack stack) {
		for (int i = 0; i < inventoryItems.size(); i++) {

			if (inventoryItems.get(i).isEmpty()) {
				inventoryItems.set(i, stack);
				return true;
			}
		}
		return false;
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putLong("Money", this.money);

		ItemStackHelper.saveAllItems(compound, this.inventoryItems);
	}

}
