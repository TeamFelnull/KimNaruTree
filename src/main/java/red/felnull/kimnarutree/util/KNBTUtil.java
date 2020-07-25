package red.felnull.kimnarutree.util;

import net.minecraft.nbt.CompoundNBT;

import java.util.HashSet;
import java.util.Set;

public class KNBTUtil {
    public static Set<String> readStringSet(CompoundNBT tag) {
        Set<String> set = new HashSet<String>();
        for (String key : tag.keySet()) {
            set.add(tag.getString(key));
        }
        return set;
    }

    public static CompoundNBT writeStringSet(CompoundNBT tag, Set<String> set) {
        int c = 0;
        for (String st : set) {
            tag.putString(String.valueOf(c), st);
            c++;
        }
        return tag;
    }
}
