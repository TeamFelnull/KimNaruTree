package red.felnull.kimnarutree.money.bank;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.AbstractNBTBased;
import red.felnull.kimnarutree.data.Knbt;

public class PlayerAccount extends Account {

    public PlayerAccount(String bankName, String name) {
        super(bankName, name);
    }
}
