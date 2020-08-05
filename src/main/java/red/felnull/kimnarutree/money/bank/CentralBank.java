package red.felnull.kimnarutree.money.bank;

public class CentralBank extends  Bank{

    public static String CENTRAL_BANK = "CentralBank";

    public CentralBank() {
        super(CENTRAL_BANK);
        setFund(Long.MAX_VALUE);
    }

}
