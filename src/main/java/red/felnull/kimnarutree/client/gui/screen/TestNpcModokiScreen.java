package red.felnull.kimnarutree.client.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.container.TestNpcModokiContainer;
import red.felnull.otyacraftengine.client.gui.screen.AbstractIkisugiContainerScreen;

public class TestNpcModokiScreen extends AbstractIkisugiContainerScreen<TestNpcModokiContainer> {
    protected static final ResourceLocation SB_GUI_TEXTURE = new ResourceLocation(KimNaruTree.MODID, "textures/gui/npc_test.png");

    public TestNpcModokiScreen(TestNpcModokiContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public ResourceLocation getBackGrandTextuer() {
        return SB_GUI_TEXTURE;
    }

    @Override
    public void initByIKSG() {
        super.initByIKSG();

        this.addWidgetByIKSG(new Button(this.getWidthByIKSG() / 2 - 100, 24 * 2, 98, 20, new TranslationTextComponent("fml.menu.mods"), button -> {
            Minecraft.getInstance().player.sendStatusMessage(new StringTextComponent("test"), false);
        }));

    }
}
