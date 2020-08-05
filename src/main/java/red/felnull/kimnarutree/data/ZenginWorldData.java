package red.felnull.kimnarutree.data;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.money.zengin.Creditworthiness;
import red.felnull.kimnarutree.util.StringFormatter;
import red.felnull.otyacraftengine.data.WorldData;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZenginWorldData extends WorldData {
    @Override
    public Path getSavedFolderPath() {
        return Paths.get(StringFormatter.dat(Knbt.ZENGIN_DATA));
    }

    @Override
    public CompoundNBT getInitialNBT(CompoundNBT compoundNBT) {
        return new Creditworthiness().getDefaultNBT();
    }
}
