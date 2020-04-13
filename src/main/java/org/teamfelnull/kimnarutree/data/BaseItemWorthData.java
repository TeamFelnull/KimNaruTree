package org.teamfelnull.kimnarutree.data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.registries.KNTRegistries;
import org.teamfelnull.kimnarutree.util.FileHelper;
import org.teamfelnull.kimnarutree.util.StringHelper;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class BaseItemWorthData {
	//アイテムのベース価値を取得
	public static long getData(Item item) {
		if (!KNTDatas.BASE_ITEM_WORTH_DATA.containsKey(item)) {
			return 0;
		}
		return KNTDatas.BASE_ITEM_WORTH_DATA.get(item);
	}

	public static void setData(Item item, long baseworth) {
		KNTDatas.BASE_ITEM_WORTH_DATA.put(item, baseworth);
	}

	//特定のアイテムのベース価値を読み込むプレイヤーに渡す
	public static void read() {
		KNTDatas.BASE_ITEM_WORTH_DATA.clear();
		Path path = FileHelper.getOptionDataPath();

		try (JsonReader reader = new JsonReader(new FileReader(path + "/baseitemworth.json"))) {
			reader.beginObject();
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (ForgeRegistries.ITEMS.containsKey(new ResourceLocation(name))) {
					setData(StringHelper.convertItemFromString(name), reader.nextLong());
				} else {
					reader.skipValue();
				}
			}
			reader.endObject();
			reader.close();

		} catch (FileNotFoundException jsonparseexception) {
			KimNaruTree.LOGGER
					.error("Failed File Read " + "Path :" + path.toString() + " File :baseitemworth.json");
		} catch (IOException ioexception) {
			KimNaruTree.LOGGER
					.error("Failed File Read " + "Path :" + path.toString() + " File :baseitemworth.json");
		}
	}

	//初期データを書き込む
	public static void write() {
		Path path = FileHelper.getOptionDataPath();
		path.toFile().mkdirs();
		try (JsonWriter writer = new JsonWriter(new FileWriter(path + "/baseitemworth.json"))) {
			writer.beginObject();
			for (Item item : ForgeRegistries.ITEMS) {

				long v = 0;

				if (KNTDatas.BASE_ITEM_WORTH_DATA.containsKey(item)) {
					v = getData(item);
				} else {
					if (KNTRegistries.DEFALT_BASE_ITEM_WORTH.containsKey(item)) {
						v = KNTRegistries.DEFALT_BASE_ITEM_WORTH.get(item);
					}
				}
				writer.setIndent(" ");
				writer.name(StringHelper.convertStringFromItem(item)).value(v);

			}
			writer.endObject();
		} catch (IOException e) {
			KimNaruTree.LOGGER
					.error("Failed File Write " + "Path :" + path.toString() + " File :baseitemworth.json");
		}
	}
}
