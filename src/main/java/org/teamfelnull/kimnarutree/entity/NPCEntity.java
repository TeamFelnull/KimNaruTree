package org.teamfelnull.kimnarutree.entity;

import org.teamfelnull.kimnarutree.advancements.TestTrigger;
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
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
	public NonNullList<ItemStack> inventoryItems = NonNullList.withSize(36, ItemStack.EMPTY);

	//0~8が自分用のスロット//9~26が材料用//27~35が商品棚
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
		for (ItemStack item : this.getArmorInventoryList()) {
			this.entityDropItem(item);
		}
	}

	@Override
	public void tick() {
		super.tick();
		this.setDropChance(EquipmentSlotType.HEAD, 0);
		this.setDropChance(EquipmentSlotType.CHEST, 0);
		this.setDropChance(EquipmentSlotType.LEGS, 0);
		this.setDropChance(EquipmentSlotType.FEET, 0);
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

			if (!player.world.isRemote) {
				player.sendMessage(new StringTextComponent("items=" + this.getArmorInventoryList()));
				TestTrigger.INSTANCE.trigger((ServerPlayerEntity) player);
			}
			return true;
		} else {

			this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(Items.DIAMOND_HELMET));
			this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
			this.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS));
			this.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(Items.DIAMOND_BOOTS));

		}
		this.tick();
		return super.processInteract(player, hand);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.money = compound.getLong("Money");

		this.inventoryItems = NonNullList.withSize(36, ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventoryItems);

	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putLong("Money", this.money);

		ItemStackHelper.saveAllItems(compound, this.inventoryItems);
	}

	protected NonNullList<ItemStack> getItems() {
		return this.inventoryItems;
	}

	protected void setItems(NonNullList<ItemStack> itemsIn) {
		this.inventoryItems = itemsIn;
	}

	protected NonNullList<ItemStack> getMineItems() {

		NonNullList<ItemStack> mainlist = NonNullList.withSize(9, ItemStack.EMPTY);

		for (int i = 0; i < mainlist.size(); i++) {
			mainlist.set(i, inventoryItems.get(i));
		}

		return mainlist;
	}

	protected void setMineItems(NonNullList<ItemStack> itemsIn) {
		for (int i = 0; i < itemsIn.size(); i++) {
			inventoryItems.set(i, itemsIn.get(i));
		}
	}

	protected NonNullList<ItemStack> getMaterialItems() {

		NonNullList<ItemStack> materiallist = NonNullList.withSize(18, ItemStack.EMPTY);

		for (int i = 0; i < materiallist.size(); i++) {
			materiallist.set(i, inventoryItems.get(i + 9));
		}

		return materiallist;
	}

	protected void setMaterialItems(NonNullList<ItemStack> itemsIn) {
		for (int i = 0; i < itemsIn.size(); i++) {
			inventoryItems.set(i + 9, itemsIn.get(i));
		}
	}

	protected NonNullList<ItemStack> getProductItems() {

		NonNullList<ItemStack> Productlist = NonNullList.withSize(9, ItemStack.EMPTY);

		for (int i = 0; i < Productlist.size(); i++) {
			Productlist.set(i, inventoryItems.get(i + 9 + 18));
		}

		return Productlist;
	}

	protected void setProductItems(NonNullList<ItemStack> itemsIn) {
		for (int i = 0; i < itemsIn.size(); i++) {
			inventoryItems.set(i + 9 + 18, itemsIn.get(i));
		}
	}

}
