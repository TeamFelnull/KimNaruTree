package red.felnull.kimnarutree.tileentity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.block.KNTBlocks;
import red.felnull.otyacraftengine.util.RegistryHelper;

public class KNTTileEntityTypes {
    @ObjectHolder(KimNaruTree.MODID + ":" + "test_npc_modoki")
    public static TileEntityType<TestNpcModokiTileEntity> TEST_NPC_MODOKI;

    public static void registerTileEntityType(IForgeRegistry<TileEntityType<?>> r) {
        RegistryHelper.registedTileEntityType(r, TestNpcModokiTileEntity::new, TEST_NPC_MODOKI, new ResourceLocation(KimNaruTree.MODID, "test_npc_modoki"), KNTBlocks.TEST_NPC_MODOKI);
    }
}
