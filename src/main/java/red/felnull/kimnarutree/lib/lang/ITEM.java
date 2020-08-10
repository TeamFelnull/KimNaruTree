package red.felnull.kimnarutree.lib.lang;

public enum ITEM implements ITranslationEnum {
    TEST_ITEM("test_item"),
    PICKY("picky"),
    PICKY_DED("picky_ded"),
    NPC_SPAWN_EGG("npc_spawn_egg"),
    PASSBOOK("passbook"),
    COUNTRY_DEBUG_STICK("country_debug_stick"),
    NATIONAL_FLAG("national_flag"),
    ;

    private String key;

    ITEM(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
