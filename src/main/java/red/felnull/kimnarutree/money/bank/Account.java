package red.felnull.kimnarutree.money.bank;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.AbstractNBTBased;

public class Account extends AbstractNBTBased {

    protected String bankName;

    public static String DEPOSIT = "Deposit";
    public static String DEBT = "Debt";

    public Account(String bankName, String name) {
        super(name);
        this.bankName = bankName;
    }

    @Override
    public CompoundNBT getParentNBT() {
        return new Bank(bankName).getNBT();
    }

    @Override
    public CompoundNBT getDefaultNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.put(DEPOSIT, new Deposit(bankName, name).getDefaultNBT());
        nbt.put(DEBT, new Debt(bankName, name).getDefaultNBT());
        return nbt;
    }

    public Deposit getDeposit(){
        return new Deposit(bankName, name);
    }

    public Debt getDebt(){
        return new Debt(bankName, name);
    }

    public void calcInterest(){
        Deposit dep = getDeposit();
        Debt deb = getDebt();
        dep.addBalance(dep.getInterest());
        deb.addAmount(deb.getInterest());
    }
}
