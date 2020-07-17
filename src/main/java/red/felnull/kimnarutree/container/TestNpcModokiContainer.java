package red.felnull.kimnarutree.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class TestNpcModokiContainer extends Container {
    public BlockPos pos;

    public TestNpcModokiContainer(int windowId, PlayerInventory playerInventory, BlockPos pos) {
        super(KNTContainerTypes.TEST_NPC_MODOKI, windowId);
        this.pos = pos;
        for (int k = 0; k < 3; ++k) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for (int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 142));
        }

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
