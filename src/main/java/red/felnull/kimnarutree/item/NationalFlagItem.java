package red.felnull.kimnarutree.item;

import net.minecraft.item.Item;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.lib.ITranslationEnum;

public class NationalFlagItem extends KNTItem {
    public NationalFlagItem(Properties properties) {
        super(properties/*.setISTER(() -> NationalFlagRenderer::new)*/);
    }

    public static Item instance(ITranslationEnum klang, int maxStackSize){
        return new NationalFlagItem(new Item.Properties().maxStackSize(maxStackSize).group(KNTItemGroup.MOD_TAB)).setRegistryName(KimNaruTree.MOD_ID, klang.getKey());
    }
}
