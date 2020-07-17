package red.felnull.kimnarutree.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.container.TestNpcModokiContainer;
import red.felnull.otyacraftengine.client.util.RenderHelper;

import javax.annotation.Nullable;

public class TestNpcModokiScreen extends ContainerScreen<TestNpcModokiContainer> {
    protected static final ResourceLocation SB_GUI_TEXTURE = new ResourceLocation(KimNaruTree.MODID, "textures/gui/npc_test.png");

    public TestNpcModokiScreen(TestNpcModokiContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void func_230430_a_(MatrixStack maxt, int p_render_1_, int p_render_2_, float p_render_3_) {
        this.func_230446_a_(maxt);
        super.func_230430_a_(maxt, p_render_1_, p_render_2_, p_render_3_);
        this.func_230459_a_(maxt, p_render_1_, p_render_2_);
    }

    @Override
    protected void func_230450_a_(MatrixStack maxt, float partialTicks, int mouseX, int mouseY) {
        RenderHelper.matrixPush(maxt);
        int xs = (this.field_230708_k_ - this.xSize) / 2;
        int ys = (this.field_230709_l_ - this.ySize) / 2;
        RenderHelper.guiBindAndBlit(SB_GUI_TEXTURE, maxt, xs, ys, 0, 0, this.xSize, this.ySize, 256, 256);
        RenderHelper.matrixPop(maxt);
    }

}
