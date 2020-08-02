package red.felnull.kimnarutree.data;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.otyacraftengine.data.WorldData;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MoneyWorldData extends WorldData {
    @Override
    public Path getSavedFolderPath() {
        return Paths.get(KimNaruTree.MODID + "\\moneydata.dat");
    }

    @Override
    public CompoundNBT getInitialNBT(CompoundNBT tag) {
        return tag;
    }
}
