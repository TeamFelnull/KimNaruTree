package red.felnull.kimnarutree.tileentity;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.block.KNTBlocks;

import java.util.function.Supplier;

public class KNTTileEntityTypes {
    @ObjectHolder(KimNaruTree.MODID + ":" + "test_npc_modoki")
    public static TileEntityType<TestNpcModokiTileEntity> TEST_NPC_MODOKI;

    public static void registerTileEntityType(IForgeRegistry<TileEntityType<?>> r) {
        registryTileEntityType(r, TestNpcModokiTileEntity::new, TEST_NPC_MODOKI, "test_npc_modoki", KNTBlocks.TEST_NPC_MODOKI);
    }

    private static void registryTileEntityType(IForgeRegistry<TileEntityType<?>> r, Supplier<? extends TileEntity> factoryIn, TileEntityType<?> te, String name, Block... blocks) {
        te = TileEntityType.Builder.create(factoryIn, blocks).build(null).setRegistryName(new ResourceLocation(KimNaruTree.MODID, name));
        r.register(te);
    }
}
