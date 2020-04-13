package org.teamfelnull.kimnarutree.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.teamfelnull.kimnarutree.KimNaruTree;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.server.MinecraftServer;

public class FileInteractor {

	private File file;

	public static FileInteractor newFile(MinecraftServer ms, String path) {
		File file = new File(FileHelper.getWorldSaveDataPath(ms) + path);
		FileHelper.exists(file);
		return new FileInteractor(file);
	}
	public FileInteractor(File file) {
		this.file = file;
	}

	private static CompoundNBT nbtOut;

	public CompoundNBT read() {
		ReadThread thread = new ReadThread(file);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			KimNaruTree.LOGGER.error("Could not join Thread ", e);
		}
		return nbtOut;
	}

	private static class ReadThread extends Thread{

		private File file;

		public ReadThread(File fileIn) {
			this.file = fileIn;
		}

		public void run() {
			try {
				FileInputStream stream = new FileInputStream(file);
				CompoundNBT nbt = CompressedStreamTools.readCompressed(stream);
				nbtOut = nbt;
			} catch (IOException ioexception) {
	    		KimNaruTree.LOGGER.error("Could not load data ", ioexception);
	    	}
		}

	}

	public void write(CompoundNBT nbt) {
		WriteThread thread = new WriteThread(this.file, nbt);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			KimNaruTree.LOGGER.error("Could not join Thread ", e);
		}
	}

	private static class WriteThread extends Thread{

		private File file;
		private CompoundNBT nbt;

		public WriteThread(File fileIn, CompoundNBT nbtIn) {
			this.file = fileIn;
			this.nbt = nbtIn;
		}

		public void run() {
	        try {
	    		FileOutputStream stream = new FileOutputStream(file);
	            CompressedStreamTools.writeCompressed(nbt, stream);
	         } catch (IOException ioexception) {
	        	 KimNaruTree.LOGGER.error("Could not save data ", ioexception);
	         }
		}

	}
}
