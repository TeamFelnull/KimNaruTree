package red.felnull.kimnarutree.data.bank;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.AbstractNBTBased;
import red.felnull.kimnarutree.data.Knbt;
import red.felnull.kimnarutree.data.player.KNTPlayerData;

import java.util.Objects;

public class Debt extends AbstractNBTBased {

    protected String accountUUID;
    protected String bankUUID;

    public static String AMOUNT = "Amount";
    public static String REPAYMENT_AMOUNT = "RepaymentAmount";

    public Debt(String accountUUID, String bankUUID) {
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
        nbt.putLong(AMOUNT, 0);
        nbt.putLong(REPAYMENT_AMOUNT, 0);
        return nbt;
    }

    public void repay(){
        Deposit dep = new Deposit(accountUUID, bankUUID);
        long repaymentAmount = getRepaymentAmount();
        if(repaymentAmount >= dep.getBalance()){
            new KNTPlayerData(accountUUID).setCreditworthiness(810);
            return;
        }
        dep.addBalance(-getRepaymentAmount());
        addAmount(-getRepaymentAmount());
    }

    public long getAmount(){
        return getNBT().getLong(AMOUNT);
    }

    public void setAmount(long amount){
        getNBT().putLong(AMOUNT, amount);
    }

    public void addAmount(long balance){
        setAmount(getAmount() + balance);
    }

    public long getRepaymentAmount(){
        return getNBT().getLong(REPAYMENT_AMOUNT);
    }

    public void setRepaymentAmount(long repaymentAmount){
        getNBT().putLong(REPAYMENT_AMOUNT, repaymentAmount);
    }

    public long getInterest(){
        return getAmount() * new KNTPlayerData(accountUUID).getCreditworthiness();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Debt debt = (Debt) o;
        return Objects.equals(accountUUID, debt.accountUUID) &&
                Objects.equals(bankUUID, debt.bankUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountUUID, bankUUID);
    }
}
