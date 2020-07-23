package red.felnull.kimnarutree.data;

import net.minecraft.util.ResourceLocation;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.otyacraftengine.api.registries.OERegistries;

public class KNTWorldData {
    public static void register() {
        OERegistries.registrierWorldData(new ResourceLocation(KimNaruTree.MODID, "moneydata"), new MoneyWorldData());
        OERegistries.registrierWorldData(new ResourceLocation(KimNaruTree.MODID, "countrydata"), new CountryWorldData());
        OERegistries.registrierWorldData(new ResourceLocation(KimNaruTree.MODID, "bankdata"), new BankWorldData());
    }
}
