package red.felnull.kimnarutree.money.bank;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.AbstractNBTBased;
import red.felnull.kimnarutree.money.zengin.Creditworthiness;

public class Debt extends AbstractNBTBased {

    protected String bankName;
    protected String accountName;

    public static String AMOUNT = "Amount";
    public static String REPAYMENT_AMOUNT = "RepaymentAmount";

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
        nbt.putLong(REPAYMENT_AMOUNT, 0);
        return nbt;
    }

    public long getAmount(){
        return getNBT().getLong(AMOUNT);
    }

    public long getRepaymentAmount(){
        return getNBT().getLong(REPAYMENT_AMOUNT);
    }

    public void setAmount(long amount){
        getNBT().putLong(AMOUNT, amount);
    }

    public void setRepaymentAmount(long repaymentAmount){
        getNBT().putLong(REPAYMENT_AMOUNT, repaymentAmount);
    }

    public void addAmount(long balance){
        getNBT().putLong(AMOUNT, getAmount() + balance);
    }

    public long getInterest(){
        return (long) (getAmount() * new Creditworthiness().getCreditworthinessOf(name));
    }

    public void repay(){
        Deposit dep = new Account(bankName, name).getDeposit();
        long repaymentAmount = -getRepaymentAmount();
        if(repaymentAmount >= dep.getBalance()){
            Creditworthiness.setCreditworthinessOf(name, 1145141919810L);
            return;
        }
        dep.addBalance(-getRepaymentAmount());
    }
}
