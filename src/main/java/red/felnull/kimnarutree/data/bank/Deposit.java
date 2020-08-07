package red.felnull.kimnarutree.money.bank;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.AbstractNBTBased;

public class Deposit extends AbstractNBTBased {

    protected String bankName;
    protected String accountName;

    public static String BALANCE = "Balance";

    public Deposit(String bankName, String accountName) {
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
        nbt.putLong(BALANCE, 0);
        return nbt;
    }

    public long getBalance(){
        return getNBT().getLong(BALANCE);
    }

    public void setBalance(long balance){
        getNBT().putLong(BALANCE, balance);
    }

    public void addBalance(long balance){
        getNBT().putLong(BALANCE, getBalance() + balance);
    }

    public long getInterest(){
        return (long) (getBalance() * new Bank(bankName).getInterestRate());
    }
}
