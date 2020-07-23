package red.felnull.kimnarutree.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.otyacraftengine.client.gui.IkisugiDialogTexts;
import red.felnull.otyacraftengine.client.gui.screen.IkisugiScreen;
import red.felnull.otyacraftengine.client.util.RenderHelper;
import red.felnull.otyacraftengine.client.util.TextureHelper;
import red.felnull.otyacraftengine.util.FileLoadHelper;
import red.felnull.otyacraftengine.util.PlayerHelper;

import java.nio.file.Path;
import java.util.List;

public class CountryCreateScreen extends IkisugiScreen {

    protected static final ResourceLocation TEST_NATIONAL_FLAG = new ResourceLocation(KimNaruTree.MODID, "textures/gui/test_national_flag.png");

    private final Screen lastScreen;

    private TextFieldWidget countryNameField;
    private Button btnCreate;
    private String contryName;
    private byte[] flagImage;


    public CountryCreateScreen(Screen screen) {
        super(new TranslationTextComponent("countrycreate.title"));
        this.lastScreen = screen;
    }

    @Override
    public void initByIKSG() {
        btnCreate = this.addWidgetByIKSG(new Button(this.getWidthByIKSG() / 2 - 155 + 160, this.getHeightByIKSG() - 29, 150, 20, IkisugiDialogTexts.CRATE, (ac) -> {
            this.getMinecraft().displayGuiScreen(null);
        }));
        this.addWidgetByIKSG(new Button(this.getWidthByIKSG() / 2 - 155, this.getHeightByIKSG() - 29, 150, 20, IkisugiDialogTexts.CANCEL, (p_213125_1_) -> {
            this.getMinecraft().displayGuiScreen(lastScreen);
        }));
        this.countryNameField = this.addWidgetByIKSG(new TextFieldWidget(this.field_230712_o_, this.field_230708_k_ / 2 - 100, 60, 200, 20, new TranslationTextComponent("selectWorld.enterName")));
        this.countryNameField.setText(I18n.format("countrycreate.defaltName", PlayerHelper.getUserName(getMinecraft().player)));
        this.countryNameField.setResponder((string) -> {
            this.contryName = string;
            this.btnCreate.field_230693_o_ = !this.countryNameField.getText().isEmpty();
        });
        this.setFocusedDefault(this.countryNameField);
    }

    @Override
    public void tickByIKSG() {
        this.countryNameField.tick();
    }

    @Override
    public void renderByIKSG(MatrixStack matrix, int mouseX, int mouseY, float parTick) {
        this.renderBackgroundByIKSG(matrix);
        this.drawCenterStringByIKSG(matrix, getTitleByIKSG(), getWidthByIKSG() / 2, 15, 16777215);
        super.renderByIKSG(matrix, mouseX, mouseY, parTick);
        this.func_238476_c_(matrix, this.field_230712_o_, I18n.format("countrycreate.enterName"), this.getWidthByIKSG() / 2 - 100, 47, -6250336);
        this.func_238476_c_(matrix, this.field_230712_o_, I18n.format("countrycreate.enterFlag"), this.getWidthByIKSG() / 2 - 150, 97, -6250336);


        ResourceLocation loc = TEST_NATIONAL_FLAG;

        if (flagImage != null) {
            loc = TextureHelper.getPictureImageTexture(flagImage);
        }
        RenderHelper.guiBindAndBlit(loc, matrix, this.getWidthByIKSG() / 2 - 150, 110, 0, 0, 256 / 3, 171 / 3, 256 / 3, 171 / 3);

        this.countryNameField.func_230430_a_(matrix, mouseX, mouseY, parTick);

    }

    @Override
    public void func_230476_a_(List<Path> dragFiles) {
        if (dragFiles.size() == 1) {
            flagImage = FileLoadHelper.fileBytesReader(dragFiles.get(0));
        }
    }


}
