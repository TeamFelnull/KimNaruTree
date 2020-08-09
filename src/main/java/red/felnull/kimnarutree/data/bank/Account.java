package red.felnull.kimnarutree.data.bank;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.AbstractNBTBased;
import red.felnull.kimnarutree.data.Knbt;

public class Account extends AbstractNBTBased {

    protected String bankUUID;

    public static final String DEPOSIT = "Deposit";
    public static final String DEBT = "Debt";

    public Account(String accountUUID, String bankUUID) {
        super(accountUUID);
        this.bankUUID = bankUUID;
    }

    @Override
    public CompoundNBT getParentNBT() {
        return Knbt.Bank().get(bankUUID).getCompound(Bank.ACCOUNTS);
    }

    @Override
    public CompoundNBT getDefaultNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.put(DEPOSIT, new Deposit(getKey(), bankUUID).getDefaultNBT());
        nbt.put(DEBT, new Debt(getKey(), bankUUID).getDefaultNBT());
        return nbt;
    }

    public static void register(String accountUUID, String bankUUID){
        Account acc = new Account(accountUUID, bankUUID);

        Knbt.Bank().get(Bank.ACCOUNTS).put(accountUUID, acc.getDefaultNBT());
    }

    public void calcInterest(){
        Deposit dep = getDeposit();
        Debt deb = getDebt();
        dep.addBalance(dep.getInterest());
        deb.addAmount(deb.getInterest());
    }

    public Deposit getDeposit(){
        return new Deposit(getKey(), bankUUID);
    }

    public Debt getDebt(){
        return new Debt(getKey(), bankUUID);
    }
}
