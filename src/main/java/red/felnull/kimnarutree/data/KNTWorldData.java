package red.felnull.kimnarutree.data;

import net.minecraft.util.ResourceLocation;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.otyacraftengine.api.registries.OERegistries;

public class KNTWorldData {
    public static void register() {
        OERegistries.registrierWorldData(new ResourceLocation(KimNaruTree.MODID, Knbt.MONEY_DATA), new MoneyWorldData());
        OERegistries.registrierWorldData(new ResourceLocation(KimNaruTree.MODID, Knbt.COUNTRY_DATA), new CountryWorldData());
        OERegistries.registrierWorldData(new ResourceLocation(KimNaruTree.MODID, Knbt.BANK_DATA), new BankWorldData());
    }
}
