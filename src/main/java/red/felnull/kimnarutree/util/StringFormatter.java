package red.felnull.kimnarutree.util;

import net.minecraft.util.math.ChunkPos;
import red.felnull.kimnarutree.KimNaruTree;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class StringFormatter {
    public static Path dat(String name) {
        return Paths.get(KimNaruTree.MOD_ID + "\\" + name + ".dat");
    }

    public static String chunk(ChunkPos pos) {
        return pos.x + ":" + pos.z;
    }

    public static String chunk(int x, int z) {
        return x + ":" + z;
    }

    public static String join(String... strings) {
        StringBuffer sb = new StringBuffer();
        Arrays.stream(strings).forEach(sb::append);
        return sb.toString();
    }
}