package org.teamfelnull.kimnarutree.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;

import org.teamfelnull.kimnarutree.KimNaruTree;

import net.minecraft.server.MinecraftServer;

public class FileLoadUtil {
	public static Path getWorldSaveDataPath(MinecraftServer ms) {
		Path pth = ms.getActiveAnvilConverter().getFile(ms.getFolderName(), "test").getParentFile().toPath();
		return pth;
	}

	public static Path getKNTWorldSaveDataPath(MinecraftServer ms) {

		return getWorldSaveDataPath(ms).resolve(KimNaruTree.MODID);
	}

	public static Path getKNTPlayerDataPath(MinecraftServer ms) {

		return getKNTWorldSaveDataPath(ms).resolve("playerdata");
	}

	public static void txtMapReader(Path path, String name, Map<String, String> map) {

		map.clear();

		if (!path.resolve(name + ".txt").toFile().canExecute())
			return;

		try {
			FileReader fr = new FileReader(path.toFile() + "/" + name + ".txt");
			BufferedReader br = new BufferedReader(fr);
			String st;
			int cont = 0;
			while ((st = br.readLine()) != null) {
				cont++;
				if (!(cont == 1 || cont == 2)) {
					try {
						String[] fruit = st.split("=", 0);
						map.put(fruit[0], fruit[1]);
					} catch (Exception e) {

					}
				}
			}

			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			KimNaruTree.LOGGER.error("Failed File Write " + "Path :" + path.toString() + " File :" + name + ".txt  "
					+ e.getLocalizedMessage());
		} catch (IOException e) {
			KimNaruTree.LOGGER.error("Failed File Write " + "Path :" + path.toString() + " File :" + name + ".txt  "
					+ e.getLocalizedMessage());
		}

	}

	public static void txtMapWriter(Path path, String name, Map<String, String> map, String comment, boolean mkds) {

		if (mkds)
			path.toFile().mkdirs();

		try (FileWriter fw = new FileWriter(path.toFile() + "/" + name + ".txt", false)) {
			Properties properties = new Properties();
			properties.putAll(map);
			properties.store(fw, comment);
		} catch (IOException e) {
			KimNaruTree.LOGGER.error("Failed File Write " + "Path :" + path.toString() + " File :" + name + ".txt  "
					+ e.getLocalizedMessage());
		}

	}

}
