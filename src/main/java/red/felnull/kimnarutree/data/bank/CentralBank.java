package red.felnull.kimnarutree.data.bank;

import red.felnull.kimnarutree.data.Knbt;

public class CentralBank extends  Bank{

    public static final String CENTRAL_BANK_UUID = "114514-1919-810";
    public static final String CENTRAL_BANK_NAME = "CentralBank";

    public CentralBank() {
        super(CENTRAL_BANK_UUID);
    }

    public static void register(){
        Bank bank = new Bank(CENTRAL_BANK_UUID);

        Knbt.Bank().register(bank);
        bank.setName(CENTRAL_BANK_NAME);
    }

    public static CentralBank getCentralBank(){
        Bank central = getBankByUUID(CENTRAL_BANK_UUID);

        return central instanceof CentralBank ? (CentralBank) central : null;
    }

    public static void IKSGFund(){
        getCentralBank().setFund(Long.MAX_VALUE);
    }

}
