package red.felnull.kimnarutree.money.bank;

import red.felnull.kimnarutree.data.Knbt;

import java.util.HashMap;
import java.util.Set;

public class Bank {
    public static Set<String> nameSet() {
        return Knbt.of("bankdata").keySet();
    }

    public static long getLoan(String name) {
        return Knbt.ofBank(name).getLong("loan");
    }

    public static long getReserve(String name) {
        return Knbt.ofBank(name).getLong("reserve");
    }

    public static float getInterestRate(String name) {
        return Knbt.ofBank(name).getFloat("interestRate");
    }

    public static HashMap<String, Long> getDepositMap(String name){
        HashMap<String, Long> map = new HashMap<>();
        for(String key : Knbt.ofBankDeposit(name).keySet()) map.put(key, getDeposit(name, key));
        return map;
    }

    public static long getDeposit(String name, String uuid){
        return Knbt.ofBankDeposit(name).getLong(uuid);
    }

    public static HashMap<String, Long> getDebtMap(String name){
        HashMap<String, Long> map = new HashMap<>();
        for(String key : Knbt.ofBankDebt(name).keySet()) map.put(key, getDebt(name, key));
        return map;
    }

    public static long getDebt(String name, String uuid){
        return Knbt.ofBankDebt(name).getLong(uuid);
    }

    public static float getReserveDepositRate(String name) {
        return Knbt.ofBank(name).getFloat("reserveDepositRate");
    }

    public void addLoan(String name, long loan) {
        Knbt.ofBank(name).putLong("loan", getLoan(name) + loan);
    }

    public void addReserve(String name, long reserve) {
        Knbt.ofBank(name).putLong("reserve", getReserve(name) + reserve);
    }

    public void addDebt(String name, String uuid, long amount) {
        Knbt.ofBankDebt(name).putLong(uuid, amount);
    }

    public void paybackDebt(String name, String uuid, long amount) {
        Knbt.ofBankDebt(name).putLong(uuid, getDebt(name, uuid) - amount);
    }

    public void addDeposit(String name, String uuid, long amount){
        Knbt.ofBankDeposit(name).putLong(uuid, amount);
        long reserve = (long) (amount * getReserveDepositRate(name));
        addReserve(name, reserve);
        addLoan(name, amount - reserve);
    }

    public void withdrawDeposit(String name, String uuid, long amount){
        long depositResult = getDeposit(name, uuid) - amount;
        if(depositResult >= 0) Knbt.ofBankDeposit(name).putLong(uuid, depositResult);
        long reserveResult = getReserve(name)-amount;
        if(reserveResult >= 0) addReserve(name, reserveResult);
    }
}
