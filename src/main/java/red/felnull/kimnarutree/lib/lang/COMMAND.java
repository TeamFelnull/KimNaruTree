package red.felnull.kimnarutree.lib.lang;

public enum COMMAND implements ITranslationEnum {
    /*KNT*/
    MONEY_SHOW_ME("command.money.show.me"),
    MONEY_SHOW_PLAYER("command.money.show.player"),
    MONEY_ADD("command.money.add"),
    MONEY_SET("command.money.set"),
    ;

    private String key;

    COMMAND(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
