package red.felnull.kimnarutree.tileentity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import red.felnull.kimnarutree.container.TestNpcModokiContainer;
import red.felnull.otyacraftengine.tileentity.IClientSyncbleTileEntity;
import red.felnull.otyacraftengine.tileentity.IkisugiTileEntity;

import javax.annotation.Nullable;

public class TestNpcModokiTileEntity extends IkisugiTileEntity implements INamedContainerProvider, IClientSyncbleTileEntity, ITickableTileEntity {


    public TestNpcModokiTileEntity() {
        super(KNTTileEntityTypes.TEST_NPC_MODOKI);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.npc");
    }

    @Override
    public void tick() {
        this.syncble(this);
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory player, PlayerEntity playerEntity) {
        return new TestNpcModokiContainer(id, player, this.getPos());
    }

}
