package red.felnull.kimnarutree.data;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.money.bank.Bank;
import red.felnull.kimnarutree.money.bank.CentralBank;
import red.felnull.kimnarutree.util.StringFormatter;
import red.felnull.otyacraftengine.data.WorldData;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BankWorldData extends WorldData {
    @Override
    public Path getSavedFolderPath() {
        return Paths.get(StringFormatter.dat(Knbt.BANK_DATA));
    }

    @Override
    public CompoundNBT getInitialNBT(CompoundNBT nbt) {
        return new CentralBank().getDefaultNBT();
    }
}
