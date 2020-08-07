package red.felnull.kimnarutree.money.bank;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.Knbt;
import red.felnull.kimnarutree.data.AbstractNBTBased;

import java.util.HashMap;
import java.util.Set;

public class Bank extends AbstractNBTBased {

    public static String FUND = "Fund";
    public static String INTEREST_RATE = "InterestRate";
    public static String PLAYER_ACCOUNTS = "PlayerAccounts";
    public static String BANK_ACCOUNTS = "BankAccounts";

    public Bank(String name) {
        super(name);
    }

    @Override
    public CompoundNBT getParentNBT() {
        return Knbt.getBanks();
    }

    @Override
    public CompoundNBT getDefaultNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putLong(FUND, 0);
        nbt.putFloat(INTEREST_RATE, 0.01F);
        nbt.put(PLAYER_ACCOUNTS, new PlayerAccount(this.name, dummy).getDefaultNBT());
        nbt.put(BANK_ACCOUNTS, new BankAccount(this.name, dummy).getDefaultNBT());
        return nbt;
    }

    public long getFund(){
        return getNBT().getLong(FUND);
    }

    public  float getInterestRate() {
        return getNBT().getFloat(INTEREST_RATE);
    }

    public PlayerAccount getPlayerAccountOf(String name){
        return new PlayerAccount(this.name, name);
    }

    public BankAccount getBankAccountOf(String name){
        return new BankAccount(this.name, name);
    }

    public void setFund(long fund){
        getNBT().putLong(FUND, fund);
    }

    public void setInterestRate(float  interestRate){
        getNBT().putFloat(INTEREST_RATE,  interestRate);
    }

    public void addFund(long fund){
        getNBT().putLong(FUND, getFund() + fund);
    }

    public void addPlayerAccount(String name){
        getNBT().put(PLAYER_ACCOUNTS, new PlayerAccount(this.name, name).getDefaultNBT());
    }

    public void addBankAccount(String name){
        getNBT().put(BANK_ACCOUNTS, new BankAccount(this.name, name).getDefaultNBT());
    }

    public void executePerDay(){
        for(String uuid : getNBT().getCompound(PLAYER_ACCOUNTS).keySet()){
            getPlayerAccountOf(uuid).calcInterest();
            getPlayerAccountOf(uuid).getDebt().repay();
        }
        for(String bankName : getNBT().getCompound(BANK_ACCOUNTS).keySet()){
            getPlayerAccountOf(bankName).calcInterest();
            getPlayerAccountOf(bankName).getDebt().repay();
        }
    }
}
