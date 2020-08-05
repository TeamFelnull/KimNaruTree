package red.felnull.kimnarutree.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.money.bank.Bank;
import red.felnull.otyacraftengine.data.WorldDataManager;

public class Knbt {
    public static String BANK_DATA = "bank_data";
    public static String MONEY_DATA = "money_data";
    public static String COUNTRY_DATA = "country_data";

    public static CompoundNBT get(String name){
        return WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, name));
    }

    public static CompoundNBT getBank(String name){
        return Knbt.get(BANK_DATA).getCompound(name);
    }

    public static CompoundNBT getBanks(){
        return Knbt.get(BANK_DATA);
    }

    public static CompoundNBT getMoney(String name){
        return Knbt.get(MONEY_DATA).getCompound(name);
    }

    public static CompoundNBT getCountry(String name){
        return Knbt.get(COUNTRY_DATA).getCompound(name);
    }

    public static void addBank(String name, CompoundNBT nbt){
        Knbt.get(BANK_DATA).put(name, nbt);
    }

    public static void addMoney(String name, CompoundNBT nbt){
        Knbt.get(MONEY_DATA).put(name, nbt);
    }

    public static void addCountry(String name, CompoundNBT nbt){
        Knbt.get(COUNTRY_DATA).put(name, nbt);
    }

    public static void removeBank(String name){
        Knbt.get(BANK_DATA).remove(name);
    }

    public static void removeMoney(String name){
        Knbt.get(MONEY_DATA).remove(name);
    }

    public static void removeCountry(String name){
        Knbt.get(COUNTRY_DATA).remove(name);
    }

}
