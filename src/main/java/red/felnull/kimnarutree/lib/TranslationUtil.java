package red.felnull.kimnarutree.lib;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TranslationTextComponent;

public class TranslationUtil {

    public static TranslationTextComponent kntTranslate(String key, Object... objects){
        return new TranslationTextComponent(key, objects);
    }

    public static TranslationTextComponent kntTranslate(ITranslationEnum langEnum, Object... objects){
        return kntTranslate(langEnum.getKey(), objects);
    }

    public static String string(ITranslationEnum langEnum){
        return langEnum.getKey();
    }

    public static String i18n(ITranslationEnum langEnum){
        return I18n.format(string(langEnum));
    }

    public static String i18n(ITranslationEnum langEnum, Object... parameters){
        return I18n.format(string(langEnum), parameters);
    }
}
