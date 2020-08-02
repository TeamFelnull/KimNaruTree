package red.felnull.kimnarutree.data;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.otyacraftengine.data.WorldData;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BankWorldData extends WorldData {
    @Override
    public Path getSavedFolderPath() {
        return Paths.get(KimNaruTree.MODID + "\\bankdata.dat");
    }

    @Override
    public CompoundNBT getInitialNBT(CompoundNBT tagIn) {
        CompoundNBT central = new CompoundNBT();
        central.putString("name", "central_bank");
        central.putLong("loan", -1);
        central.putLong("reserve", -1);
        central.putFloat("interestRate", 0.01F);
        central.putFloat("reserveDepositRate", 0.1F);
        CompoundNBT deposit = new CompoundNBT();
        deposit.putLong("none", 0);
        central.put("deposits", deposit);
        CompoundNBT debts = new CompoundNBT();
        debts.putLong("none", 0);
        central.put("debts", debts);
        tagIn.put("central", central);
        return tagIn;
    }
}
