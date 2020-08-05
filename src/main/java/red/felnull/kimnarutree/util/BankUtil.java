package red.felnull.kimnarutree.util;

import red.felnull.kimnarutree.data.Knbt;

import java.util.Set;

public class BankUtil {
    public static Set<String> getNameSet() {
        return Knbt.get(Knbt.BANK_DATA).keySet();
    }
}
