package red.felnull.kimnarutree.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import red.felnull.otyacraftengine.container.IkisugiContainer;

public class TestNpcModokiContainer extends IkisugiContainer {

    public TestNpcModokiContainer(int windowId, PlayerInventory playerInventory, BlockPos pos) {
        super(KNTContainerTypes.TEST_NPC_MODOKI, windowId);

    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {

        return ItemStack.EMPTY;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
