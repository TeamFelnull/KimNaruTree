package red.felnull.kimnarutree.data.country;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.Knbt;
import red.felnull.kimnarutree.util.StringFormatter;
import red.felnull.otyacraftengine.data.WorldData;

import java.nio.file.Path;

public class CountryWorldData extends WorldData {
    @Override
    public Path getSavedFolderPath() {
        return StringFormatter.dat(Knbt.COUNTRY_DATA);
    }

    @Override
    public CompoundNBT getInitialNBT(CompoundNBT tag) {
        return tag;
    }
}
