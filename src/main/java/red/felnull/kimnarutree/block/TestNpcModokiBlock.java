package red.felnull.kimnarutree.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.lib.lang.ITranslationEnum;
import red.felnull.kimnarutree.entity.tile.TestNpcModokiTileEntity;

import javax.annotation.Nullable;

public class TestNpcModokiBlock extends KNTBlock {
    public TestNpcModokiBlock(Properties properties) {
        super(properties);
    }

    public static Block instance(ITranslationEnum klang, Material material, SoundType sound, float hardness, float resistance){
        return new TestNpcModokiBlock(AbstractBlock.Properties.create(material).sound(sound).hardnessAndResistance(hardness, resistance)).setRegistryName(KimNaruTree.MOD_ID, klang.getKey());
    }

    @Override
    public ActionResultType onBlockActivated(BlockState p_225533_1_, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
        if (!worldIn.isRemote) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (!(tileentity instanceof TestNpcModokiTileEntity))
                return ActionResultType.PASS;
            //       playerIn.openContainer((INamedContainerProvider) tileentity);
            NetworkHooks.openGui((ServerPlayerEntity) playerIn, (INamedContainerProvider) tileentity, pos);
        }
        return ActionResultType.SUCCESS;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TestNpcModokiTileEntity();
    }

    @Override
    public final boolean hasTileEntity(BlockState state) {
        return true;
    }
}
