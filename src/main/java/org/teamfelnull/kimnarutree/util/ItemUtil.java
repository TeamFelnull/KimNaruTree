package org.teamfelnull.kimnarutree.util;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemUtil {

	public static ItemStack addNonNullItem(NonNullList<ItemStack> itemlist, ItemStack item) {

		if (item.isEmpty())
			return ItemStack.EMPTY;

		ItemStack dropitem = item.copy();

		for (int i = 0; i < itemlist.size(); i++) {

			ItemStack stack = itemlist.get(i);

			if (stack.isEmpty()) {
				itemlist.set(i, dropitem);
				return ItemStack.EMPTY;
			}

			if (canStack(item, stack) && stack.getCount() <= stack.getMaxStackSize() - 1) {
				int ss = stack.getCount();
				int is = dropitem.getCount();

				stack.setCount(ss + is <= stack.getMaxStackSize() ? ss + is : stack.getMaxStackSize());
				dropitem.setCount(ss + is <= stack.getMaxStackSize() ? 0 : ss + is - stack.getMaxStackSize());

				if (dropitem.isEmpty())
					return ItemStack.EMPTY;
			}
		}
		return dropitem;

	}

	public static boolean canStack(ItemStack stack1, ItemStack stack2) {

		if (stack1.getItem() == stack2.getItem() && stack1.getTag() == stack2.getTag())
			return true;

		return false;
	}

	//防具評価値
	public static int getArmorEvaluation(ItemStack stack) {
		if (stack.isEmpty() || !(stack.getItem() instanceof ArmorItem))
			return 0;

		ArmorItem arm = (ArmorItem) stack.getItem();

		return arm.getDamageReduceAmount() + (stack.isEnchanted() ? 3 : 0);
	}

	public static ItemStack getBestArmor(NonNullList<ItemStack> stacks, EquipmentSlotType slot) {

		if (stacks.isEmpty())
			return ItemStack.EMPTY;

		ItemStack most = ItemStack.EMPTY;
		int num = -1;

		for (int i = 0; i < stacks.size(); i++) {
			ItemStack item = stacks.get(i);

			if (item.getItem() instanceof ArmorItem && ((ArmorItem) item.getItem()).getEquipmentSlot() == slot) {

				if (most.isEmpty()) {
					most = item;
				} else {
					ArmorItem arm = (ArmorItem) item.getItem();
					ArmorItem marm = (ArmorItem) most.getItem();

					int ah = arm.getDamageReduceAmount();
					int mah = marm.getDamageReduceAmount();

					if (ah != mah && ah >= mah) {
						most = item;
						num = i;
					}
				}
			}
		}
		if (num >= 0)
			stacks.set(num, ItemStack.EMPTY);

		return most;
	}
}
