package red.felnull.kimnarutree.entity.tile;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.block.KNTBlocks;
import red.felnull.kimnarutree.lib.resource.RESOURCE;
import red.felnull.kimnarutree.lib.resource.ResourceUtil;
import red.felnull.otyacraftengine.util.RegistryHelper;

public class KNTTileEntityTypes {
    @ObjectHolder(KimNaruTree.MOD_ID + ":" + "test_npc_modoki")
    public static TileEntityType<TestNpcModokiTileEntity> TEST_NPC_MODOKI;

    public static void registerTileEntityType(IForgeRegistry<TileEntityType<?>> r) {
        RegistryHelper.registedTileEntityType(r, TestNpcModokiTileEntity::new, TEST_NPC_MODOKI, ResourceUtil.kntResource(RESOURCE.TEST_NPC_MODOKI), KNTBlocks.TEST_NPC_MODOKI);
    }
}
