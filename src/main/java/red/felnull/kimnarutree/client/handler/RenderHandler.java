package red.felnull.kimnarutree.client.handler;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import red.felnull.kimnarutree.data.country.Country;
import red.felnull.kimnarutree.data.KNTDatas;
import red.felnull.kimnarutree.item.CountryDebugStickItem;
import red.felnull.kimnarutree.item.KNTItems;
import red.felnull.otyacraftengine.api.event.client.RenderItemOverlayIntoGUIEvent;
import red.felnull.otyacraftengine.client.util.IKSGRenderUtil;
import red.felnull.otyacraftengine.client.util.IKSGTextureUtil;
import red.felnull.otyacraftengine.util.IkisugiMath;

import java.util.ArrayList;
import java.util.List;

public class RenderHandler {
    private static Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public static void onItemOverlay(RenderItemOverlayIntoGUIEvent e) {
        if (e.getStack().getItem() == KNTItems.COUNTRY_DEBUG_STICK) {
            CompoundNBT tag = e.getStack().getTag();
            if (tag != null && tag.contains(CountryDebugStickItem.FLAG_UUID) && tag.contains(CountryDebugStickItem.FLAG_WIDTH) && tag.contains(CountryDebugStickItem.FLAG_HEIGHT)) {
                MatrixStack ms = new MatrixStack();
                IKSGRenderUtil.matrixPush(ms);
                IKSGRenderUtil.matrixTranslatef(ms, 0, 0, e.getZLevel() + 200.0F);

                int size = 12;
                float w = tag.getInt(CountryDebugStickItem.FLAG_WIDTH);
                float h = tag.getInt(CountryDebugStickItem.FLAG_HEIGHT);

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

                IKSGRenderUtil.guiBindAndBlit(IKSGTextureUtil.getReceiveTexture(KNTDatas.WORLD_NATIONAL_FLAG, tag.getString(CountryDebugStickItem.FLAG_UUID)), ms, e.getX(), e.getY(), 0, 0, aw, ah, aw, ah);
                IKSGRenderUtil.matrixPop(ms);
            }
        }
    }

    @SubscribeEvent
    public static void onRender(RenderGameOverlayEvent e) {
        if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            int w = e.getWindow().getScaledWidth();
            int h = e.getWindow().getScaledHeight();
            IKSGRenderUtil.matrixPush(e.getMatrixStack());
            if (Country.clientNowCountry != null) {

                String contrySt = Country.clientNowCountry.getName();
                String representativeSt = I18n.format("message.country.representative", Country.clientNowCountry.getRepresentativePlayerName());
                String sizeSt = I18n.format("message.country.size", Country.clientNowCountry.getSize());

                List<String> displaySts = new ArrayList<String>();
                displaySts.add(contrySt);
                displaySts.add(representativeSt);
                displaySts.add(sizeSt);

                int[] disSizes = new int[displaySts.size()];
                for (int i = 0; i < displaySts.size(); i++) {
                    disSizes[i] = mc.fontRenderer.getStringWidth(displaySts.get(i));
                }

                int mostStr = IkisugiMath.mostInt(disSizes);
                int moziH = 10;
                int highStr = moziH * displaySts.size();

                int size = 32;
                float fw = Country.clientNowCountry.getFlagWidth();
                float fh = Country.clientNowCountry.getFlagHeight();
                int aw = 0;
                int ah = 0;
                if (fw == fh) {
                    aw = size;
                    ah = size;
                } else if (fw > fh) {
                    aw = size;
                    ah = (int) ((float) size * (fh / fw));
                } else if (fw < fh) {
                    aw = (int) ((float) size * (fw / fh));
                    ah = size;
                }
                int flagY = IkisugiMath.mostInt(ah, highStr);

                IKSGRenderUtil.guiBindAndBlit(IKSGTextureUtil.getReceiveTexture(KNTDatas.WORLD_NATIONAL_FLAG, Country.clientNowCountry.getFlagImageUUID()), e.getMatrixStack(), w - mostStr - 4 - aw, h - 3 - flagY, 0, 0, aw, ah, aw, ah);

                for (int i = 0; i < displaySts.size(); i++) {
                    mc.fontRenderer.func_238422_b_(e.getMatrixStack(), new StringTextComponent(displaySts.get(i)), w - mostStr - 2, h - 3 - flagY + moziH * i, 14737632);
                }

            } else {
                String tel = I18n.format("message.country.terranullius");
                mc.fontRenderer.func_238422_b_(e.getMatrixStack(), new StringTextComponent(tel), w - mc.fontRenderer.getStringWidth(tel) - 2, h - 9, 14737632);
            }
            IKSGRenderUtil.matrixPop(e.getMatrixStack());
        }
    }
}
