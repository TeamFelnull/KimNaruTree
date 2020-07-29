package red.felnull.kimnarutree.data;

import net.minecraft.util.ResourceLocation;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.otyacraftengine.api.registries.OERegistries;

public class KNTDatas {
    public static final ResourceLocation WORLD_NATIONAL_FLAG = new ResourceLocation(KimNaruTree.MODID, "national_flag");

    public static void init() {
        OERegistries.registrierServerRecevedPath(WORLD_NATIONAL_FLAG, KimNaruTree.MODID + "\\nationalflags");
    }
}
