package org.teamfelnull.kimnarutree.data;

import java.util.HashMap;
import java.util.Map;

public class UnSavedPlayerData {
	//UUID、ステータス名、ステータス、例:プレイヤーUUID、fps,81
	public static void setData(String plname, String statename, String state) {
		if (!KNTDatas.UNSAVED_PLAYER_DATA.containsKey(plname)) {
			KNTDatas.UNSAVED_PLAYER_DATA.put(plname, new HashMap<String, String>());
		}
		KNTDatas.UNSAVED_PLAYER_DATA.get(plname).put(statename, state);
	}

	//プレイヤーデーター取得
	public static String getData(String uuid, String statename) {
		if (!KNTDatas.UNSAVED_PLAYER_DATA.containsKey(uuid)) {
			return null;
		}
		Map<String, String> plmap = KNTDatas.UNSAVED_PLAYER_DATA.get(uuid);
		if (!plmap.containsKey(statename)) {
			return null;
		}
		return plmap.get(statename);
	}
}
