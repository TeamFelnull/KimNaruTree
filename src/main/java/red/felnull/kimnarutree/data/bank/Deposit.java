package red.felnull.kimnarutree.data.bank;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.AbstractNBTBased;
import red.felnull.kimnarutree.data.Knbt;

import java.util.Objects;

public class Deposit extends AbstractNBTBased {

    protected String accountUUID;
    protected String bankUUID;

    public static String BALANCE = "Balance";

    public Deposit(String accountUUID, String bankUUID) {
        super(Account.DEPOSIT);
        this.accountUUID = accountUUID;
        this.bankUUID = bankUUID;
    }

    @Override
    public CompoundNBT getParentNBT() {
        return Knbt.Bank().get(bankUUID).getCompound(Bank.ACCOUNTS).getCompound(accountUUID);
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
        setBalance(getBalance() + balance);
    }

    public long getInterest(){
        return (long) (getBalance() * new Bank(bankUUID).getInterestRate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Deposit deposit = (Deposit) o;
        return Objects.equals(accountUUID, deposit.accountUUID) &&
                Objects.equals(bankUUID, deposit.bankUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountUUID, bankUUID);
    }
}
