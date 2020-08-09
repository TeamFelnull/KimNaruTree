package red.felnull.kimnarutree.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.lib.ITranslationEnum;

public class KNTBlock extends Block {
    public KNTBlock(Properties properties) {
        super(properties);
    }

    public static Block instance(ITranslationEnum klang){
        return instance(klang, Material.ROCK);
    }

    public static Block instance(ITranslationEnum klang, Material material){
        return instance(klang, material, SoundType.STONE);
    }


    public static Block instance(ITranslationEnum klang, Material material, SoundType sound){
        return instance(klang, material, sound, 1.0F);
    }

    public static Block instance(ITranslationEnum klang, Material material, SoundType sound, float hardness){
        return instance(klang, material, sound, hardness, 1.0F);
    }

    public static Block instance(ITranslationEnum klang, Material material, SoundType sound, float hardness, float resistance){
        return new KNTBlock(AbstractBlock.Properties.create(material).sound(sound).hardnessAndResistance(hardness, resistance)).setRegistryName(KimNaruTree.MOD_ID, klang.getKey());
    }
}
