package org.teamfelnull.kimnarutree.util;

import org.apache.commons.lang3.StringUtils;

import net.minecraftforge.fml.ModList;
public class ModUtil {

	public static String getModVersion(String modid) {
		try {
			return ModList.get().getModContainerById(modid)
					.map(modContainer -> modContainer.getModInfo().getVersion()).orElse(null).toString();
		} catch (Exception e) {
			return "Error!!";
		}
	}

	public static String getModName(String modid) {
		try {
			return ModList.get().getModContainerById(modid).map(modContainer -> modContainer.getModInfo().getDisplayName())
				.orElse(StringUtils.capitalize(modid));
		} catch (Exception e) {
			return "Error!!";
		}
	}
}
