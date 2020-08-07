package red.felnull.kimnarutree.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.data.country.Country;
import red.felnull.otyacraftengine.client.gui.IkisugiDialogTexts;
import red.felnull.otyacraftengine.client.gui.screen.IkisugiScreen;
import red.felnull.otyacraftengine.client.util.IKSGRenderUtil;
import red.felnull.otyacraftengine.client.util.IKSGTextureUtil;
import red.felnull.otyacraftengine.util.PictuerUtil;
import red.felnull.otyacraftengine.util.PlayerHelper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.List;

public class CountryCreateScreen extends IkisugiScreen {

    public static final ResourceLocation TEST_NATIONAL_FLAG = new ResourceLocation(KimNaruTree.MODID, "textures/gui/test_national_flag.png");
    private final Screen lastScreen;
    protected Button btnCreate;
    protected byte[] flagImage;
    protected boolean loading = false;
    protected String loadingErr;
    protected int widthFlag;
    protected int heightFlag;
    protected boolean createFlag1;
    protected boolean createFlag2;
    private TextFieldWidget countryNameField;
    private String countryName;

    public CountryCreateScreen(Screen screen) {
        super(new TranslationTextComponent("countrycreate.title"));
        this.lastScreen = screen;
        widthFlag = 256;
        heightFlag = 171;
    }

    @Override
    public void initByIKSG() {
        btnCreate = this.addWidgetByIKSG(new Button(this.getWidthByIKSG() / 2 - 155 + 160, this.getHeightByIKSG() - 29, 150, 20, IkisugiDialogTexts.CRATE, (ac) -> {
            Country.sendCreateRequest(countryName, flagImage);
            this.getMinecraft().displayGuiScreen(null);
        }));
        this.addWidgetByIKSG(new Button(this.getWidthByIKSG() / 2 - 155, this.getHeightByIKSG() - 29, 150, 20, IkisugiDialogTexts.CANCEL, (p_213125_1_) -> {
            this.getMinecraft().displayGuiScreen(lastScreen);
        }));
        this.countryNameField = this.addWidgetByIKSG(new TextFieldWidget(this.field_230712_o_, this.field_230708_k_ / 2 - 100, 60, 200, 20, new TranslationTextComponent("selectWorld.enterName")));
        this.countryNameField.setText(I18n.format("countrycreate.defaltName", PlayerHelper.getUserName(getMinecraft().player)));
        this.countryNameField.setResponder((string) -> {
            this.countryName = string;
            createFlag1 = !this.countryNameField.getText().isEmpty();
            this.btnCreate.field_230693_o_ = createFlag1 && createFlag2;
        });
        createFlag1 = !this.countryNameField.getText().isEmpty();
        this.btnCreate.field_230693_o_ = createFlag1 && createFlag2;
        countryName = this.countryNameField.getText();

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
        this.drawStringByIKSG(matrix, this.field_230712_o_, I18n.format("countrycreate.enterName"), this.getWidthByIKSG() / 2 - 100, 47, -6250336);
        this.drawStringByIKSG(matrix, this.field_230712_o_, I18n.format("countrycreate.enterFlag"), this.getWidthByIKSG() / 2 - 150, 87, -6250336);
        this.drawStringByIKSG(matrix, this.field_230712_o_, I18n.format("countrycreate.dropInfo"), this.getWidthByIKSG() / 2 - 150, 97, -6250336);

        ResourceLocation loc = TEST_NATIONAL_FLAG;

        if (flagImage != null) {
            loc = IKSGTextureUtil.getPictureImageTexture(flagImage);
        }
        IKSGRenderUtil.matrixPush(matrix);
        RenderSystem.enableBlend();
        IKSGRenderUtil.guiBindAndBlit(loc, matrix, this.getWidthByIKSG() / 2 - 150, 110, 0, 0, widthFlag / 3, heightFlag / 3, widthFlag / 3, heightFlag / 3);
        IKSGRenderUtil.matrixPop(matrix);

        if (loading) {
            IKSGRenderUtil.matrixPush(matrix);
            RenderSystem.enableBlend();
            IKSGRenderUtil.guiBindAndBlit(IKSGTextureUtil.getLoadingIconTextuer(), matrix, this.getWidthByIKSG() / 2 - 150 + 256 / 3 + 5, 97 + 10, 0, 0, 8, 8, 8, 8);
            IKSGRenderUtil.matrixPop(matrix);
            this.drawStringByIKSG(matrix, this.field_230712_o_, I18n.format("countrycreate.loadingImage"), this.getWidthByIKSG() / 2 - 150 + 256 / 3 + 15, 97 + 10, -6250336);
        }

        if (loadingErr != null) {
            this.drawStringByIKSG(matrix, this.field_230712_o_, I18n.format("countrycreate.loadingErr", loadingErr), this.getWidthByIKSG() / 2 - 150 + 256 / 3 + 3, 97 + 10, -6250336);
        }

        this.countryNameField.func_230430_a_(matrix, mouseX, mouseY, parTick);

    }

    @Override
    public void dropAndDragByIKSG(List<Path> dragFiles) {
        if (dragFiles.size() == 1 && !loading) {
            loadingErr = null;
            LoadingThread lt = new LoadingThread(this, dragFiles.get(0));
            lt.start();
        } else if (dragFiles.size() != 1) {
            loadingErr = I18n.format("countrycreate.err.multiplefiles");
        }
    }

    @Override
    public void onCloseByIKSG() {
        super.onCloseByIKSG();
    }
}

class LoadingThread extends Thread {
    private CountryCreateScreen screen;
    private Path path;

    public LoadingThread(CountryCreateScreen screen, Path path) {
        this.screen = screen;
        this.path = path;
    }

    public void run() {
        screen.loading = true;
        try {
            BufferedImage bfi = PictuerUtil.getBffImage(path);
            if (bfi == null) {
                screen.loadingErr = I18n.format("countrycreate.err.noimage");
                screen.loading = false;
                return;
            }
            int size = 256;
            float w = bfi.getWidth();
            float h = bfi.getHeight();

            int aw = 0;
            int ah = 0;

            if (w == h) {
                aw = size;
                ah = size;
            } else if (w > h) {
                aw = size;
                ah = (int) ((float) size * (h / w));
            } else if (w < h) {
                aw = (int) ((float) size * (w / h));
                ah = size;
            }
            BufferedImage outbfi = new BufferedImage(aw, ah, bfi.getType());
            outbfi.createGraphics().drawImage(bfi.getScaledInstance(aw, ah, Image.SCALE_AREA_AVERAGING), 0, 0, aw, ah, null);
            screen.widthFlag = outbfi.getWidth();
            screen.heightFlag = outbfi.getHeight();
            screen.flagImage = PictuerUtil.geByteImage(outbfi);
            if (screen.flagImage == null) {
                screen.widthFlag = 256;
                screen.heightFlag = 171;
            }
            screen.loadingErr = null;
        } catch (Exception ex) {
            screen.loadingErr = ex.toString();
        }
        screen.createFlag2 = screen.flagImage != null;
        screen.btnCreate.field_230693_o_ = screen.createFlag1 && screen.createFlag2;
        screen.loading = false;
    }
}
