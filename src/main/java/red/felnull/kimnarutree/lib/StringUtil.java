package red.felnull.kimnarutree.lib;

import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class StringUtil {

    public static IFormattableTextComponent colorText(String msg, TextFormatting color){
        return new StringTextComponent(msg).func_240699_a_(color);
    }
}
