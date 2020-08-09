package red.felnull.kimnarutree.lib;

public enum MESSAGE implements ITranslationEnum{
    /*KNT*/
    RIP("message.rip"),
    COUNTRY_SET("message.country.set"),
    COUNTRY_TERRA_NULLIUS("message.country.terra_nullius"),
    COUNTRY_TERRITORY("message.country.territory"),
    COUNTRY_REPRESENTATIVE("message.country.representative"),
    COUNTRY_SIZE("message.country.size"),
    COUNTRY_BELONG_TO("message.country.belong_to"),
    COUNTRY_NOTIFICATION("message.country.notification"),
    ;

    private String key;

    MESSAGE(String key){
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
