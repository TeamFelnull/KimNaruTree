package org.teamfelnull.kimnarutree.util;

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

			if (item.getItem() == stack.getItem() && stack.getCount() <= stack.getMaxStackSize() - 1) {
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

}
