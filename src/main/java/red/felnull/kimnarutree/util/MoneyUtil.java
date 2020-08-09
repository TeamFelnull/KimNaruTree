package red.felnull.kimnarutree.util;

import net.minecraft.util.text.TranslationTextComponent;
import red.felnull.kimnarutree.lib.OTHER;

import static red.felnull.kimnarutree.lib.TranslationUtil.kntTranslate;

public class MoneyUtil {
    public static TranslationTextComponent getDisplayAmount(long money) {
        return kntTranslate(OTHER.CURRENCY_UNIT, money);
    }
}
