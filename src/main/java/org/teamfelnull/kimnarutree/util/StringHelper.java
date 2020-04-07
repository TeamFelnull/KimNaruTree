package org.teamfelnull.kimnarutree.util;

public class StringHelper {
	public static long convertLongFromString(String st) {
		try {
			return Long.parseLong(st);
		} catch (Exception e) {
			return 0;
		}
	}

	public static String convertStringFromLong(long lo) {

		return ((Long) lo).toString();
	}
}
