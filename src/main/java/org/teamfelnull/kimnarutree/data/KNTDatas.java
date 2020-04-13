package org.teamfelnull.kimnarutree.data;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;

public class KNTDatas {
	//プレイヤーデータ、ワールドデーターのkimnarutree\playerdataにそれぞれUUIDで保存される
	public static Map<String, Map<String, String>> SAVED_PLAYER_DATA = new HashMap<String, Map<String, String>>();
	//プレイヤーデータ、再起されるたびに初期化される
	public static Map<String, Map<String, String>> UNSAVED_PLAYER_DATA = new HashMap<String, Map<String, String>>();
	//ベースアイテム価値データー、ディレクトリのkimunarutree\baseitemworth.jsonに保存される
	public static Map<Item, Long> BASE_ITEM_WORTH_DATA = new HashMap<Item, Long>();
}
