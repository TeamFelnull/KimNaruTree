package red.felnull.kimnarutree.data;

import net.minecraft.util.ResourceLocation;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.otyacraftengine.api.registries.OERegistries;

public class KNTWorldDatas {
    public static void register() {
        OERegistries.registrierWorldData(new ResourceLocation(KimNaruTree.MODID, "moneydata"), new MoneyWorldData());
    }
}
