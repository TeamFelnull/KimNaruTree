package red.felnull.kimnarutree.data.bank;

import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.Knbt;
import red.felnull.kimnarutree.data.AbstractNBTBased;
import red.felnull.otyacraftengine.util.StringHelper;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Bank extends AbstractNBTBased {

    public static final String ACCOUNTS = "Accounts";
    public static final String NAME = "Name";
    public static final String FUND = "Fund";
    public static final String INTEREST_RATE = "InterestRate";

    public Bank() {
        super(UUID.randomUUID().toString());
    }

    protected Bank(String uuid) {
        super(uuid);
    }

    @Override
    public CompoundNBT getParentNBT() {
        return Knbt.Bank().getNBTs();
    }

    @Override
    public CompoundNBT getDefaultNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString(NAME, "");
        nbt.putLong(FUND, 0);
        nbt.putFloat(INTEREST_RATE, 0.01F);
        return nbt;
    }

    public static void register(String name, String uuid){
        Bank bank = new Bank(uuid);

        Knbt.Bank().register(bank);
        bank.setName(name);

        Account.register(dummy, uuid);
    }

    public void executePerDay(){
        getAccountSet().forEach( account -> {
            account.calcInterest();
            account.getDebt().repay();
        });
    }

    public static Bank getBankByUUID(String uuid){
        if (!Knbt.Bank().getNBTs().contains(uuid))
            return null;

        return new Bank(uuid);
    }

//
//    public static Bank getBankByName(String name){
//        String resultUUID = null;
//
//        for (String uuid : Knbt.Bank().getNBTs().keySet()) {
//            if (getBankByUUID(uuid).getName().equals(name))
//                resultUUID = uuid;
//        }
//
//        return getBankByUUID(resultUUID);
//    }

    public Set<Account> getAccountSet(){
        HashSet<Account> set = new HashSet<>();
        getNBT().getCompound(ACCOUNTS).keySet().forEach( uuid -> set.add(new Account(uuid, getKey())));

        return set;
    }

    public void addAccount(String uuid){
        CompoundNBT accTag = new CompoundNBT();
        accTag.put(uuid, new Account(uuid, getKey()).getDefaultNBT());
        getNBT().put(ACCOUNTS, accTag);
    }

    public String getName(){
        return getNBT().getString(NAME);
    }

    public void setName(String name){
        getNBT().putString(NAME, name);
    }

    public long getFund(){
        return getNBT().getLong(FUND);
    }

    public void setFund(long fund){
        getNBT().putLong(FUND, fund);
    }

    public void addFund(long fund){
        setFund(getFund() + fund);
    }

    public  float getInterestRate() {
        return getNBT().getFloat(INTEREST_RATE);
    }

    public void setInterestRate(float  interestRate){
        getNBT().putFloat(INTEREST_RATE,  interestRate);
    }

    public Account getAccount(String uuid){
        return new Account(uuid, getKey());
    }
}
