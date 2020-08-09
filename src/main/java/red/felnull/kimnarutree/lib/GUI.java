package red.felnull.kimnarutree.lib;

public enum GUI implements ITranslationEnum{
    /*KNT*/
    COUNTRY_CREATE_TITLE("gui.country.create.title"),
    SELECT_WORLD_ENTER_NAME("gui.select.world.enter_name"),
    COUNTRY_CREATE_DEFAULT_NAME("gui.country.create.default_name"),
    COUNTRY_CREATE_ENTER_NAME("gui.country.create.enter_name"),
    COUNTRY_CREATE_ENTER_FLAG("gui.country.create.enter_flag"),
    COUNTRY_CREATE_DROP_INFO("gui.country.create.drop_info"),
    COUNTRY_CREATE_LOADING_IMAGE("gui.country.create.loading_image"),
    COUNTRY_CREATE_ERR_LOADING("gui.country.create.err.loading"),
    COUNTRY_CREATE_ERR_MULTIPLE_FILES("country.create.err.multiple_files"),
    COUNTRY_CREATE_ERR_NO_IMAGE("gui.country.create.err.no_image"),
    COUNTRY_MENU_TITLE("gui.country.menu.title"),
    ;

    private String key;

    GUI(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
