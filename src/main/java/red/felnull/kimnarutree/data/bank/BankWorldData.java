package red.felnull.kimnarutree.data.bank;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.Knbt;
import red.felnull.kimnarutree.util.StringFormatter;
import red.felnull.otyacraftengine.data.WorldData;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BankWorldData extends WorldData {
    @Override
    public Path getSavedFolderPath() {
        return StringFormatter.dat(Knbt.BANK_DATA);
    }

    @Override
    public CompoundNBT getInitialNBT(CompoundNBT tag) {
        return tag;
    }
}
