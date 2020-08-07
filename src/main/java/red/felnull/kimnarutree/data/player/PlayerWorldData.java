package red.felnull.kimnarutree.data.player;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.Knbt;
import red.felnull.kimnarutree.util.StringFormatter;
import red.felnull.otyacraftengine.data.WorldData;

import java.nio.file.Path;

public class PlayerWorldData extends WorldData {
    @Override
    public Path getSavedFolderPath() {
        return StringFormatter.dat(Knbt.PLAYER_DATA);
    }

    @Override
    public CompoundNBT getInitialNBT(CompoundNBT tag) {
        return tag;
    }
}
