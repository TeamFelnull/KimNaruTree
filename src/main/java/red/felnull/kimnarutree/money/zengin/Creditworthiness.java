package red.felnull.kimnarutree.money.zengin;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.AbstractNBTBased;
import red.felnull.kimnarutree.data.Knbt;

public class Creditworthiness extends AbstractNBTBased {

    public static String CREDITWORTHINESS = "Creditworthiness";

    public Creditworthiness() {
        super(CREDITWORTHINESS);
    }

    @Override
    public CompoundNBT getParentNBT() {
        return Knbt.getZengin();
    }

    @Override
    public CompoundNBT getDefaultNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putLong(dummy, 0);
        return nbt;
    }

    public static long getCreditworthinessOf(String name){
        return new Creditworthiness().getNBT().getLong(name);
    }

    public static void setCreditworthinessOf(String name, long amount){
        new Creditworthiness().getNBT().putLong(name, amount);
    }
}
