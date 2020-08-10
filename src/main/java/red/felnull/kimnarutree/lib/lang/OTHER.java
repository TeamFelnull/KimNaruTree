package red.felnull.kimnarutree.lib.lang;

public enum OTHER implements ITranslationEnum {
    CURRENCY_UNIT("money.currency_unit"),
    CONTAINER_NPC("container.npc"),
    ;

    private String key;

    OTHER(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
