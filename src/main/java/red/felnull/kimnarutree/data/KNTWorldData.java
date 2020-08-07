package red.felnull.kimnarutree.data;

import net.minecraft.util.ResourceLocation;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.data.bank.BankWorldData;
import red.felnull.kimnarutree.data.country.CountryWorldData;
import red.felnull.kimnarutree.data.player.PlayerWorldData;
import red.felnull.kimnarutree.data.territory.TerritoryWorldData;
import red.felnull.otyacraftengine.api.registries.OERegistries;

public class KNTWorldData {
    public static void register() {
        OERegistries.registrierWorldData(new ResourceLocation(KimNaruTree.MODID, Knbt.BANK_DATA), new BankWorldData());
        OERegistries.registrierWorldData(new ResourceLocation(KimNaruTree.MODID, Knbt.PLAYER_DATA), new PlayerWorldData());
        OERegistries.registrierWorldData(new ResourceLocation(KimNaruTree.MODID, Knbt.COUNTRY_DATA), new CountryWorldData());
        OERegistries.registrierWorldData(new ResourceLocation(KimNaruTree.MODID, Knbt.TERRITORY_DATA), new TerritoryWorldData());
    }
}
