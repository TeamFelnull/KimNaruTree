package red.felnull.kimnarutree.money.bank;

import red.felnull.kimnarutree.data.Knbt;

public class CentralBank extends  Bank{

    public static String CENTRAL_BANK = "CentralBank";

    public CentralBank(String name) {
        super(name);
    }

    public static void IKSGFund(){
        new CentralBank(CENTRAL_BANK).setFund(Long.MAX_VALUE);
    }

}
