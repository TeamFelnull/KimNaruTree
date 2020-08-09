package red.felnull.kimnarutree.lib;

public enum RESOURCE implements IResourceEnum {
    TEST_NPC_MODOKI("test_npc_modoki"),
    NATIONAL_FLAG("national_flag"),
    COUNTRY_SYNC("country_sync"),
    ;

    private String key;

    RESOURCE(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
