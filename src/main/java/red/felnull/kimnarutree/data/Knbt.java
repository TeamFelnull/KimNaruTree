package red.felnull.kimnarutree.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.otyacraftengine.data.WorldDataManager;

public class Knbt {
    public static CompoundNBT of(String name) {
        return WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, name));
    }

    public static CompoundNBT ofBank(String name) {
        return WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "bankdata")).getCompound(name);
    }

    public static CompoundNBT ofBankDebt(String name) {
        return WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "bankdata")).getCompound(name).getCompound("debts");
    }

    public static CompoundNBT ofBankDeposit(String name) {
        return WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "bankdata")).getCompound(name).getCompound("deposits");
    }

    public static CompoundNBT ofMoney(String name) {
        return WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "moneydata")).getCompound(name);
    }

    public static CompoundNBT ofCountry(String name) {
        return WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "countrydata")).getCompound(name);
    }
}
