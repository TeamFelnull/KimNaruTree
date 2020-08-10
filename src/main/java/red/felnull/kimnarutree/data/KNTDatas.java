package red.felnull.kimnarutree.data;

import net.minecraft.util.ResourceLocation;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.lib.resource.RESOURCE;
import red.felnull.kimnarutree.lib.resource.ResourceUtil;
import red.felnull.otyacraftengine.api.registries.OERegistries;

public class KNTDatas {

    public static final String PATH = KimNaruTree.MOD_ID + "\\national_flags";

    public static final ResourceLocation WORLD_NATIONAL_FLAG = ResourceUtil.kntResource(RESOURCE.NATIONAL_FLAG);
    public static final ResourceLocation COUNTRY_SYNC = ResourceUtil.kntResource(RESOURCE.COUNTRY_SYNC);

    public static void init() {
        OERegistries.registrierServerRecevedPath(WORLD_NATIONAL_FLAG, PATH);
        OERegistries.registrierTextuerSendPath(WORLD_NATIONAL_FLAG, PATH);
    }
}
