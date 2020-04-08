package org.teamfelnull.kimnarutree.util;

import java.util.ArrayList;
import java.util.List;

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

		String o = "";
		int co = 0;
		for (int n : list) {
			o += convertStringFromInt(n);
			co++;
			if (co != list.size())
				o += ",";

		}

		return o;
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
}
