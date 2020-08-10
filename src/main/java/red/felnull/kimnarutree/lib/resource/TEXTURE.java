package red.felnull.kimnarutree.lib.resource;

public enum TEXTURE implements IResourceEnum {
    /*NOT KNT*/
    ENTITY_STEVE("textures/entity/steve.png"),
    /*KNT*/
    GUI_NPC_TEST("textures/gui/npc_test.png"),
    ;

    private String key;

    TEXTURE(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}