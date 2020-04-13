package org.teamfelnull.kimnarutree.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.teamfelnull.kimnarutree.KimNaruTree;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;

public class FileHelper{

	public static Path getOptionDataPath() {
		File file = new File("kimunarutree");
		return file.toPath();
	}

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

	public static boolean exists(File file) {
		if(!file.exists()) {
			try {
				File dir = new File(file.getParent());
				dir.mkdirs();
				dir.createNewFile();
				file.createNewFile();
				//NBTに何もないと読めないため、test値をあらかじめ追加
				FileInteractor init = new FileInteractor(file);
				CompoundNBT nbt = new CompoundNBT();
				nbt.putInt("init", 1);
				init.write(nbt);
				return true;
			} catch (IOException e) {
				KimNaruTree.LOGGER.error("Could not create file ", e);
			}
		}
		return false;
	}
}
