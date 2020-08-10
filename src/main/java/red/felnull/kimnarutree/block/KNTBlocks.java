package red.felnull.kimnarutree.block;


import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.item.KNTItemGroup;
import red.felnull.kimnarutree.lib.lang.BLOCK;

import static red.felnull.kimnarutree.lib.lang.TranslationUtil.kntTranslate;

public class KNTBlocks {

    public static Block TEST_BLOCK = KNTBlock.instance(BLOCK.TEST_BLOCK, Material.ROCK, SoundType.WOOD, 1.0f, 2.0f);
    public static Block DENNIS_POSED = OrnamentBlock.instance(BLOCK.DENNIS_POSED, Material.ROCK, SoundType.STONE, 1.0f, 2.0f);
    public static Block NOTE_PC = NotePCBlock.instance(BLOCK.NOTE_PC, Material.IRON, SoundType.METAL, 1f, 0.5f);
    public static Block TEST_NPC_MODOKI = TestNpcModokiBlock.instance(BLOCK.TEST_NPC_MODOKI);

    public static final String REGISTERING_BLOCK = "Registering Block : ";
    public static final String REGISTERING_BLOCK_ITEM= "Registering BlockItem : ";

    public static void registerBlock(IForgeRegistry<Block> r) {
        registryBlock(r, TEST_BLOCK);
        registryBlock(r, DENNIS_POSED);
        registryBlock(r, NOTE_PC);
        registryBlock(r, TEST_NPC_MODOKI);
    }

    public static void registerItem(IForgeRegistry<Item> r) {
        registryBlockItem(r, TEST_BLOCK);
        registryBlockItem(r, DENNIS_POSED);
        registryBlockItem(r, NOTE_PC);
        registryBlockItem(r, TEST_NPC_MODOKI);
    }

    private static void registryBlock(IForgeRegistry<Block> r, Block b) {
        KimNaruTree.LOGGER.info(REGISTERING_BLOCK + b.getRegistryName());
        r.register(b);
    }

    private static void registryBlockItem(IForgeRegistry<Item> r, Block b) {
        KimNaruTree.LOGGER.info(REGISTERING_BLOCK_ITEM + b.getRegistryName());
        r.register(new BlockItem(b, new Item.Properties().group(KNTItemGroup.MOD_TAB)).setRegistryName(b.getRegistryName()));
    }
}
