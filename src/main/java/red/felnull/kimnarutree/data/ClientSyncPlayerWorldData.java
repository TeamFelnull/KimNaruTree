package red.felnull.kimnarutree.data;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.otyacraftengine.data.PlayerWorldData;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientSyncPlayerWorldData extends PlayerWorldData {
    @Override
    public boolean isClientSincble() {
        return true;
    }

    @Override
    public Path getSavedFolderPath() {
        return Paths.get(KimNaruTree.MODID + "\\clientsyncplayerdata");
    }

    @Override
    public CompoundNBT getDefaltNBT(CompoundNBT tag) {
        tag.putLong("money_balance", 0);
        return tag;
    }
}
