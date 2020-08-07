package red.felnull.kimnarutree.data.territory;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.Knbt;
import red.felnull.kimnarutree.util.StringFormatter;
import red.felnull.otyacraftengine.data.WorldData;

import java.nio.file.Path;

public class TerritoryWorldData extends WorldData {
    @Override
    public Path getSavedFolderPath() {
        return StringFormatter.dat(Knbt.TERRITORY_DATA);
    }

    @Override
    public CompoundNBT getInitialNBT(CompoundNBT tag) {
        return tag;
    }
}
