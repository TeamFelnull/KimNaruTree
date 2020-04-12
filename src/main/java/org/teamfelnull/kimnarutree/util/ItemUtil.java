package org.teamfelnull.kimnarutree.util;

import org.teamfelnull.kimnarutree.item.Evaluations;

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

	public static ItemStack getBestArmor(ItemStack eq, NonNullList<ItemStack> itemlist, EquipmentSlotType slot) {

		if (itemlist.isEmpty())
			return eq;

		ItemStack most = eq;
		int changeslot = -1;
		for (int i = 0; i < itemlist.size(); i++) {
			ItemStack stack = itemlist.get(i);
			if (stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getEquipmentSlot() == slot) {

				int stackAE = Evaluations.getArmorEvaluation(stack);
				int mostAE = Evaluations.getArmorEvaluation(most);

				if (stackAE != mostAE && stackAE >= mostAE) {
					most = stack;
					changeslot = i;
				}
			}
		}
		if (changeslot != -1) {
			itemlist.set(changeslot, eq);
		}

		return most;
	}
}
