package red.felnull.kimnarutree.data;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.bank.CentralBank;
import red.felnull.kimnarutree.util.StringFormatter;
import red.felnull.otyacraftengine.data.WorldData;

import java.nio.file.Path;
import java.nio.file.Paths;

import static red.felnull.kimnarutree.data.bank.CentralBank.CENTRAL_BANK;

public class BankWorldData extends WorldData {
    @Override
    public Path getSavedFolderPath() {
        return Paths.get(StringFormatter.dat(Knbt.BANK_DATA));
    }

    @Override
    public CompoundNBT getInitialNBT(CompoundNBT nbt) {
        nbt.put(CENTRAL_BANK, new CentralBank(CENTRAL_BANK).getDefaultNBT());
        return nbt;
    }
}
