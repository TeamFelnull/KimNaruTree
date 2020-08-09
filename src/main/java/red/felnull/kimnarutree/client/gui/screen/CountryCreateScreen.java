package red.felnull.kimnarutree.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.data.country.Country;
import red.felnull.kimnarutree.lib.GUI;
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

import static red.felnull.kimnarutree.lib.TranslationUtil.*;

public class CountryCreateScreen extends IkisugiScreen {

    public static final ResourceLocation TEST_NATIONAL_FLAG = new ResourceLocation(KimNaruTree.MOD_ID, "textures/gui/test_national_flag.png");
    private final Screen lastScreen;
    protected Button btnCreate;
    protected byte[] flagImage;
    protected boolean loading = false;
    protected String loadingErr;
    protected int flagWidth;
    protected int flagHeight;
    protected boolean hasSetCountryName;
    protected boolean hasSetFlagImage;
    private String countryName;
    private TextFieldWidget countryNameField;

    public CountryCreateScreen(Screen screen) {
        super(kntTranslate(GUI.COUNTRY_CREATE_TITLE));
        this.lastScreen = screen;
        flagWidth = 256;
        flagHeight = 171;
    }

    @Override
    public void initByIKSG() {
        btnCreate = addWidgetByIKSG(new Button(getWidthByIKSG() / 2 - 155 + 160, getHeightByIKSG() - 29, 150, 20, IkisugiDialogTexts.CRATE, ac -> {
                    Country.sendCreateRequest(countryName, flagImage);
            getMinecraft().displayGuiScreen(null);
        }));
        addWidgetByIKSG(new Button(getWidthByIKSG() / 2 - 155, getHeightByIKSG() - 29, 150, 20, IkisugiDialogTexts.CANCEL, p_213125_1_ ->
                getMinecraft().displayGuiScreen(lastScreen)));
        countryNameField = addWidgetByIKSG(new TextFieldWidget(field_230712_o_, field_230708_k_ / 2 - 100, 60, 200, 20, kntTranslate(GUI.SELECT_WORLD_ENTER_NAME)));
        countryNameField.setText(i18n((GUI.COUNTRY_CREATE_DEFAULT_NAME), PlayerHelper.getUserName(getMinecraft().player)));
        countryNameField.setResponder((string) -> {
            countryName = string;
            hasSetCountryName = !countryNameField.getText().isEmpty();
            btnCreate.field_230693_o_ = hasSetCountryName && hasSetFlagImage;
        });
        countryName = countryNameField.getText();
        hasSetCountryName = !countryName.isEmpty();
        btnCreate.field_230693_o_ = hasSetCountryName && hasSetFlagImage;

        setFocusedDefault(countryNameField);
    }

    @Override
    public void tickByIKSG() {
        countryNameField.tick();
    }

    @Override
    public void renderByIKSG(MatrixStack matrix, int mouseX, int mouseY, float parTick) {
        renderBackgroundByIKSG(matrix);
        drawCenterStringByIKSG(matrix, getTitleByIKSG(), getWidthByIKSG() / 2, 15, 16777215);
        super.renderByIKSG(matrix, mouseX, mouseY, parTick);
        drawStringByIKSG(matrix, field_230712_o_, i18n(GUI.COUNTRY_CREATE_ENTER_NAME), getWidthByIKSG() / 2 - 100, 47, -6250336);
        drawStringByIKSG(matrix, field_230712_o_, i18n(GUI.COUNTRY_CREATE_ENTER_FLAG), getWidthByIKSG() / 2 - 150, 87, -6250336);
        drawStringByIKSG(matrix, field_230712_o_, i18n(GUI.COUNTRY_CREATE_DROP_INFO), getWidthByIKSG() / 2 - 150, 97, -6250336);

        ResourceLocation loc = TEST_NATIONAL_FLAG;

        if (flagImage != null) {
            loc = IKSGTextureUtil.getPictureImageTexture(flagImage);
        }
        IKSGRenderUtil.matrixPush(matrix);
        RenderSystem.enableBlend();
        IKSGRenderUtil.guiBindAndBlit(loc, matrix, getWidthByIKSG() / 2 - 150, 110, 0, 0, flagWidth / 3, flagHeight / 3, flagWidth / 3, flagHeight / 3);
        IKSGRenderUtil.matrixPop(matrix);

        if (loading) {
            IKSGRenderUtil.matrixPush(matrix);
            RenderSystem.enableBlend();
            IKSGRenderUtil.guiBindAndBlit(IKSGTextureUtil.getLoadingIconTextuer(), matrix, getWidthByIKSG() / 2 - 150 + 256 / 3 + 5, 97 + 10, 0, 0, 8, 8, 8, 8);
            IKSGRenderUtil.matrixPop(matrix);
            drawStringByIKSG(matrix, field_230712_o_, i18n(GUI.COUNTRY_CREATE_LOADING_IMAGE), getWidthByIKSG() / 2 - 150 + 256 / 3 + 15, 97 + 10, -6250336);
        }

        if (loadingErr != null) {
            drawStringByIKSG(matrix, field_230712_o_, i18n((GUI.COUNTRY_CREATE_ERR_LOADING), loadingErr), getWidthByIKSG() / 2 - 150 + 256 / 3 + 3, 97 + 10, -6250336);
        }

        countryNameField.func_230430_a_(matrix, mouseX, mouseY, parTick);
    }

    @Override
    public void dropAndDragByIKSG(List<Path> dragFiles) {
        if (dragFiles.size() == 1 && !loading) {
            loadingErr = null;
            LoadingThread lt = new LoadingThread(this, dragFiles.get(0));
            lt.start();
        } else if (dragFiles.size() != 1) {
            loadingErr = i18n(GUI.COUNTRY_CREATE_ERR_MULTIPLE_FILES);
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
                screen.loadingErr = i18n(GUI.COUNTRY_CREATE_ERR_NO_IMAGE);
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
            screen.flagWidth = outbfi.getWidth();
            screen.flagHeight = outbfi.getHeight();
            screen.flagImage = PictuerUtil.geByteImage(outbfi);
            if (screen.flagImage == null) {
                screen.flagWidth = 256;
                screen.flagHeight = 171;
            }
            screen.loadingErr = null;
        } catch (Exception ex) {
            screen.loadingErr = ex.toString();
        }
        screen.hasSetFlagImage = screen.flagImage != null;
        screen.btnCreate.field_230693_o_ = screen.hasSetCountryName && screen.hasSetFlagImage;
        screen.loading = false;
    }
}
