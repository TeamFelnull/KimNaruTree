package red.felnull.kimnarutree.lib;

public enum ADVANCEMENT implements IResourceEnum {
    WALLET_0("root"),
    WALLET_MAN("wallet_10000"),
    WALLET_100MAN("wallet_1000000"),
    WALLET_1000MAN("wallet_10000000"),
    WALLET_5000MAN("wallet_50000000"),
    WALLET_OKU("wallet_100000000"),
    WALLET_5OKU("wallet_500000000"),
    WALLET_10OKU("wallet_1000000000"),
    WALLET_100OKU("wallet_10000000000"),
    FUNERAL_0("funeral_0"),
    FUNERAL_500("funeral_500"),
    FUNERAL_100MAN("funeral_1000000"),
    FUNERAL_3000MAN("funeral_30000000"),
    FUNERAL_10OKU("funeral_1000000000"),
    ;

    private String key;

    ADVANCEMENT(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
