package org.teamfelnull.kimnarutree.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class StringHelper {

	public static long convertLongFromString(String st) {
		try {
			return Long.parseLong(st.replace(" ", ""));
		} catch (Exception e) {
			return 0;
		}
	}

	public static String convertStringFromLong(long lo) {
		return ((Long) lo).toString();
	}

	public static List<Integer> convertIntListFromString(String st) {
		List<Integer> li = new ArrayList<Integer>();
		for (String str : st.split(",", -1)) {
			li.add(convertIntFromString(str));
		}
		return li;
	}

	public static String convertStringFromIntList(List<Integer> list) {
		return StringUtils.join(list, ",");
	}

	public static int convertIntFromString(String st) {
		try {
			return Integer.parseInt(st.replace(" ", ""));
		} catch (Exception e) {
			return 0;
		}
	}

	public static String convertStringFromInt(int lo) {
		return lo + "";
	}

	public static Item convertItemFromString(String st) {
		return ForgeRegistries.ITEMS.getValue(new ResourceLocation(st));
	}

	public static String convertStringFromItem(Item item) {
		return item.getRegistryName().toString();
	}

}
