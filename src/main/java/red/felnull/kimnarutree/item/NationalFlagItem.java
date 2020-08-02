package red.felnull.kimnarutree.item;

import net.minecraft.item.Item;

public class NationalFlagItem extends Item {
    public NationalFlagItem(Properties properties) {
        super(properties/*.setISTER(() -> NationalFlagRenderer::new)*/);
    }
}
