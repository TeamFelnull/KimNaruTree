package red.felnull.kimnarutree.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import red.felnull.kimnarutree.KimNaruTree;

public class KNTItemGroup {

    public static final ItemGroup MOD_TAB = new ItemGroup(KimNaruTree.MOD_ID) {
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(KNTItems.PICKY);
        }
    };
}
