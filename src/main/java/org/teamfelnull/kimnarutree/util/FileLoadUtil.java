package org.teamfelnull.kimnarutree.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Map;

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
				if (cont != 1) {
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

	public static void txtMapWriter(Path path, String name, Map<String, String> map, String coment, boolean mkds) {

		if (mkds)
			path.toFile().mkdirs();

		try {
			FileWriter fw = new FileWriter(path.toFile() + "/" + name + ".txt", false);
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			pw.println(coment);
			for (String key : map.keySet()) {
				pw.println(key + "=" + map.get(key));
			}
			pw.close();
		} catch (IOException e) {
			KimNaruTree.LOGGER.error("Failed File Write " + "Path :" + path.toString() + " File :" + name + ".txt  "
					+ e.getLocalizedMessage());
		}

	}

}
