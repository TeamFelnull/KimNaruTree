package red.felnull.kimnarutree.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.TranslationTextComponent;
import red.felnull.otyacraftengine.client.gui.screen.IkisugiScreen;

public class CountryCreateScreen extends IkisugiScreen {
    private final Screen lastScreen;

    public CountryCreateScreen(Screen screen) {
        super(new TranslationTextComponent("countrycreate.title"));
        this.lastScreen = screen;
    }

    @Override
    public void initByIKSG() {
        this.addWidgetByIKSG(new Button(this.getWidthByIKSG() / 2 - 155 + 160, this.getHeightByIKSG() - 29, 150, 20, KNTDialogTexts.CRATE, (ac) -> {
            this.getMinecraft().displayGuiScreen(null);
        }));
        this.addWidgetByIKSG(new Button(this.getWidthByIKSG() / 2 - 155, this.getHeightByIKSG() - 29, 150, 20, DialogTexts.field_240633_d_, (p_213125_1_) -> {
            this.getMinecraft().displayGuiScreen(lastScreen);
        }));
    }

    @Override
    public void renderByIKSG(MatrixStack matrix, int mouseX, int mouseY, float parTick) {
        this.renderBackgroundByIKSG(matrix);
        this.drawCenterStringByIKSG(matrix, this.field_230704_d_, getWidthByIKSG() / 2, 15, 16777215);
        super.renderByIKSG(matrix, mouseX, mouseY, parTick);
    }
}
