package red.felnull.kimnarutree.data;

import net.minecraft.util.ResourceLocation;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.otyacraftengine.api.registries.OERegistries;

public class KNTWorldDatas {
    public static void register() {
        OERegistries.registrierPlayerData(new ResourceLocation(KimNaruTree.MODID, "client_sync_playerdata"), new ClientSyncPlayerWorldData());
    }
}
