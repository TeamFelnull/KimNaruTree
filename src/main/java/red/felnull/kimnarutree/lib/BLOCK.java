package red.felnull.kimnarutree.lib;

public enum BLOCK implements ITranslationEnum{
    /*KNT*/
    TEST_BLOCK("test_block"),
    DENNIS_POSED("dennis_posed"),
    NOTE_PC("note_pc"),
    TEST_NPC_MODOKI("test_npc_modoki"),
    ;

    private String key;

    BLOCK(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
