package red.felnull.kimnarutree.block;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.item.KNTItemGroup;

public class KNTBlocks {

    public static Block TEST_BLOCK = newBlock("test_block", Material.ROCK, SoundType.WOOD, 1.0f, 2.0f);
    public static Block DENNIS_POSED = newOrnamentBlock("dennis_posed", Material.ROCK, SoundType.STONE, 1.0f, 2.0f);
    //public static Block NOTE_PC = new NotePCBlock(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(1f, 0.5f)).setRegistryName(KimNaruTree.MODID, "note_pc");
    public static Block TEST_NPC_MODOKI = new TestNpcModokiBlock(AbstractBlock.Properties.create(Material.ROCK)).setRegistryName(KimNaruTree.MODID, "test_npc_modoki");

    public static void registerBlock(IForgeRegistry<Block> r) {
        registryBlock(r, TEST_BLOCK);
        registryBlock(r, DENNIS_POSED);
        registryBlock(r, TEST_NPC_MODOKI);
    }

    public static void registerItem(IForgeRegistry<Item> r) {
        registryBlockItem(r, TEST_BLOCK);
        registryBlockItem(r, DENNIS_POSED);
        registryBlockItem(r, TEST_NPC_MODOKI);
    }

    private static Block newOrnamentBlock(String name, Material materialIn, SoundType sound, float hardness, float resistance) {
        return new OrnamentBlock(AbstractBlock.Properties.create(materialIn).sound(sound).hardnessAndResistance(hardness, resistance)).setRegistryName(KimNaruTree.MODID, name);
    }

    private static Block newBlock(String name, Material materialIn, SoundType sound, float hardness, float resistance) {
        return new Block(AbstractBlock.Properties.create(materialIn).sound(sound).hardnessAndResistance(hardness, resistance)).setRegistryName(KimNaruTree.MODID, name);
    }

    private static void registryBlock(IForgeRegistry<Block> r, Block b) {
        KimNaruTree.LOGGER.info("Registering Block : " + b.getRegistryName());
        r.register(b);
    }

    private static void registryBlockItem(IForgeRegistry<Item> r, Block b) {
        KimNaruTree.LOGGER.info("Registering BlockItem : " + b.getRegistryName());
        r.register(new BlockItem(b, new Item.Properties().group(KNTItemGroup.MOD_TAB)).setRegistryName(b.getRegistryName()));
    }
}
