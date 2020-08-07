package red.felnull.kimnarutree.data;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.util.StringFormatter;
import red.felnull.otyacraftengine.data.WorldData;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CountryWorldData extends WorldData {
    @Override
    public Path getSavedFolderPath() {
        return Paths.get(StringFormatter.dat(Knbt.COUNTRY_DATA));
    }

    @Override
    public CompoundNBT getInitialNBT(CompoundNBT tag) {
        tag.put("countrys", new CompoundNBT());
        tag.put("players", new CompoundNBT());
        tag.put("territorys", new CompoundNBT());
        return tag;
    }
}
