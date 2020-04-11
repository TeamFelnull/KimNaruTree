package org.teamfelnull.kimnarutree.entity;

import org.teamfelnull.kimnarutree.item.KNTItems;
import org.teamfelnull.kimnarutree.util.ItemUtil;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.INPC;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.OpenDoorGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class NPCEntity extends CreatureEntity implements INPC {
	public NonNullList<ItemStack> inventoryItems = NonNullList.withSize(36, ItemStack.EMPTY);
	public long money;
	public int armorchangeCooldwon;

	//0~8が自分用のスロット//9~26が材料用//27~35が商品棚
	protected NPCEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.setCanPickUpLoot(true);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new OpenDoorGoal(this, false));
		this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.6D));
		this.goalSelector.addGoal(3, new PanicGoal(this, 0.5D));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(5, new LookAtGoal(this, MobEntity.class, 8.0F));

	}

	@Override
	protected void dropInventory() {

		for (ItemStack item : inventoryItems) {
			this.entityDropItem(item);
		}
		for (ItemStack item : this.getArmorInventoryList()) {
			this.entityDropItem(item);
		}

		this.entityDropItem(this.getHeldItemMainhand());
		this.entityDropItem(this.getHeldItemOffhand());

	}

	@Override
	public void tick() {
		super.tick();
		this.setDropChance(EquipmentSlotType.HEAD, 0);
		this.setDropChance(EquipmentSlotType.CHEST, 0);
		this.setDropChance(EquipmentSlotType.LEGS, 0);
		this.setDropChance(EquipmentSlotType.FEET, 0);
		this.setDropChance(EquipmentSlotType.MAINHAND, 0);
		this.setDropChance(EquipmentSlotType.OFFHAND, 0);

		if (this.armorchangeCooldwon <= 0) {
			this.updateEquipment();
			armorchangeCooldwon = 200;
		} else {
			armorchangeCooldwon--;
		}
	}

	public void updateEquipment() {
		for (EquipmentSlotType slot : EquipmentSlotType.values()) {

			if (slot != EquipmentSlotType.MAINHAND && slot != EquipmentSlotType.OFFHAND) {

				NonNullList<ItemStack> mainlist = getMineItems();
				ItemStack best = ItemUtil.getBestArmor(this.getItemStackFromSlot(slot), mainlist, slot);
				if (!best.isEmpty() && this.getItemStackFromSlot(slot) != best) {

					this.playEquipSound(best);

					setMineItems(mainlist);
					this.setItemStackToSlot(slot, best);
				}
			}
		}
	}

	/*
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
	*/
	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;

	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getHeldItem(hand);

		if (itemstack.getItem() == KNTItems.PICKY) {

			if (!player.world.isRemote) {
				if (!player.isCrouching())
					player.sendMessage(new StringTextComponent("items=" + this.getMineItems()));
				else
					player.sendMessage(new StringTextComponent("aritems=" + this.getArmorInventoryList()));

			}
			return true;
		} else {

			this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(KNTItems.PICKYDED));

		}
		return super.processInteract(player, hand);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.money = compound.getLong("Money");
		this.armorchangeCooldwon = compound.getInt("ArmorChangeCooldwon");
		this.setCanPickUpLoot(true);
		this.inventoryItems = NonNullList.withSize(36, ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventoryItems);

	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putLong("Money", this.money);
		compound.putInt("ArmorChangeCooldwon", this.armorchangeCooldwon);
		ItemStackHelper.saveAllItems(compound, this.inventoryItems);
	}

	protected NonNullList<ItemStack> getItems() {
		return this.inventoryItems;
	}

	protected void setItems(NonNullList<ItemStack> itemsIn) {
		this.inventoryItems = itemsIn;
	}

	@Override
	protected void updateEquipmentIfNeeded(ItemEntity itemEntity) {
		ItemStack itemstack = itemEntity.getItem();
		NonNullList<ItemStack> mainlist = getMineItems();

		ItemStack pickitem = ItemUtil.addNonNullItem(mainlist, itemstack);

		if (itemstack.getCount() != pickitem.getCount()) {
			this.world.playSound((PlayerEntity) null, this.func_226277_ct_(), this.func_226278_cu_(),
					this.func_226281_cx_(), SoundEvents.ENTITY_ITEM_PICKUP, this.getSoundCategory(), 0.5F, 2);
		}

		itemEntity.setItem(pickitem);

		setMineItems(mainlist);

		if (itemEntity.getItem().isEmpty() || itemEntity.getItem().getCount() == 0) {
			itemEntity.remove();
		}
	}

	public NonNullList<ItemStack> getMineItems() {

		NonNullList<ItemStack> mainlist = NonNullList.withSize(9, ItemStack.EMPTY);

		for (int i = 0; i < mainlist.size(); i++) {
			mainlist.set(i, inventoryItems.get(i));
		}

		return mainlist;
	}

	public void setMineItems(NonNullList<ItemStack> itemsIn) {
		for (int i = 0; i < itemsIn.size(); i++) {
			inventoryItems.set(i, itemsIn.get(i));
		}
	}

	public NonNullList<ItemStack> getMaterialItems() {

		NonNullList<ItemStack> materiallist = NonNullList.withSize(18, ItemStack.EMPTY);

		for (int i = 0; i < materiallist.size(); i++) {
			materiallist.set(i, inventoryItems.get(i + 9));
		}

		return materiallist;
	}

	public void setMaterialItems(NonNullList<ItemStack> itemsIn) {
		for (int i = 0; i < itemsIn.size(); i++) {
			inventoryItems.set(i + 9, itemsIn.get(i));
		}
	}

	public NonNullList<ItemStack> getProductItems() {

		NonNullList<ItemStack> Productlist = NonNullList.withSize(9, ItemStack.EMPTY);

		for (int i = 0; i < Productlist.size(); i++) {
			Productlist.set(i, inventoryItems.get(i + 9 + 18));
		}

		return Productlist;
	}

	public void setProductItems(NonNullList<ItemStack> itemsIn) {
		for (int i = 0; i < itemsIn.size(); i++) {
			inventoryItems.set(i + 9 + 18, itemsIn.get(i));
		}
	}

}
