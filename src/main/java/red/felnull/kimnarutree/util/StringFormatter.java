package red.felnull.kimnarutree.util;

import net.minecraft.util.math.ChunkPos;
import red.felnull.kimnarutree.KimNaruTree;

import java.nio.file.Path;
import java.nio.file.Paths;

public class StringFormatter {
    public static Path dat(String name){
        return Paths.get(KimNaruTree.MODID + "\\" + name + ".dat");
    }

    public static String chunk(ChunkPos pos){
        return pos.x + ":" + pos.z;
    }

    public static String chunk(int x, int z){
        return x + ":" + z;
    }
}
