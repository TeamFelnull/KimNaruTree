package red.felnull.kimnarutree.money.bank;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.AbstractNBTBased;

public class Debt extends AbstractNBTBased {

    protected String bankName;
    protected String accountName;

    public static String AMOUNT = "Amount";

    public Debt(String bankName, String accountName) {
        super(Account.DEPOSIT);
        this.bankName = bankName;
        this.accountName = accountName;
    }

    @Override
    public CompoundNBT getParentNBT() {
        return new Account(bankName, accountName).getNBT();
    }

    @Override
    public CompoundNBT getDefaultNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putLong(AMOUNT, 0);
        return nbt;
    }

    public long getAmount(){
        return getNBT().getLong(AMOUNT);
    }

    public void setAmount(long balance){
        getNBT().putLong(AMOUNT, balance);
    }
}
