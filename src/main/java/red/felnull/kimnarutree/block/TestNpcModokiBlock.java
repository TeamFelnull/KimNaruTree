package red.felnull.kimnarutree.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import red.felnull.kimnarutree.tileentity.TestNpcModokiTileEntity;

import javax.annotation.Nullable;

public class TestNpcModokiBlock extends Block {
    public TestNpcModokiBlock(Properties properties) {
        super(properties);
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
