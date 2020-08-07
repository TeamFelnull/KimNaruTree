package red.felnull.kimnarutree.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.otyacraftengine.data.WorldDataManager;

public class Knbt {

    public String DATA;

    public static String BANK_DATA = "bank_data";
    public static String ZENGIN_DATA = "zengin_data";
    public static String PLAYER_DATA = "player_data";
    public static String COUNTRY_DATA = "country_data";
    public static String TERRITORY_DATA = "territory_data";

    private Knbt(String data){
        DATA = data;
    }

    private static CompoundNBT getNBTsOf(String key){
        return WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, key));
    }

    public CompoundNBT getNBTs(){
        return Knbt.getNBTsOf(DATA);
    }

    public CompoundNBT get(String key){
        return getNBTs().getCompound(key);
    }

    public void register(AbstractNBTBased nbtClass){
        CompoundNBT nbt = nbtClass.getDefaultNBT();
        getNBTs().put(nbtClass.getKey(), nbt);
    }

    public void remove(String key){
        getNBTs().remove(key);
    }

    public static Knbt Bank(){
        return new Knbt(BANK_DATA);
    }

    public static Knbt Zengin(){
        return new Knbt(ZENGIN_DATA);
    }

    public static Knbt PlayerData(){
        return new Knbt(PLAYER_DATA);
    }

    public static Knbt Country(){
        return new Knbt(COUNTRY_DATA);
    }

    public static Knbt Territory(){
        return new Knbt(TERRITORY_DATA);
    }
}