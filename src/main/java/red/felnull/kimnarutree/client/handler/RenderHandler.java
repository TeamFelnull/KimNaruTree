package red.felnull.kimnarutree.client.handler;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import red.felnull.kimnarutree.country.Country;
import red.felnull.kimnarutree.data.KNTDatas;
import red.felnull.kimnarutree.item.KNTItems;
import red.felnull.otyacraftengine.api.event.client.RenderItemOverlayIntoGUIEvent;
import red.felnull.otyacraftengine.client.util.IKSGRenderUtil;
import red.felnull.otyacraftengine.client.util.IKSGTextureUtil;

public class RenderHandler {
    private static Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public static void onItemOverlay(RenderItemOverlayIntoGUIEvent e) {
        if (e.getStack().getItem() == KNTItems.COUNTRY_DEBUG_STICK) {
            CompoundNBT tag = e.getStack().getTag();
            if (tag != null && tag.contains("CountryFlag") && tag.contains("FlagWidth") && tag.contains("FlagHeight")) {
                MatrixStack ms = new MatrixStack();
                IKSGRenderUtil.matrixPush(ms);
                IKSGRenderUtil.matrixTranslatef(ms, 0, 0, e.getZLevel() + 200.0F);

                int size = 12;
                float w = tag.getInt("FlagWidth");
                float h = tag.getInt("FlagHeight");

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

                IKSGRenderUtil.guiBindAndBlit(IKSGTextureUtil.getReceiveTexture(KNTDatas.WORLD_NATIONAL_FLAG, tag.getString("CountryFlag")), ms, e.getX(), e.getY(), 0, 0, aw, ah, aw, ah);
                IKSGRenderUtil.matrixPop(ms);
            }
        }
    }

    @SubscribeEvent
    public static void onRender(RenderGameOverlayEvent e) {
        if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            int w = e.getWindow().getScaledWidth();
            int h = e.getWindow().getScaledHeight();

            String contrySt = Country.clientNowCountry != null ? Country.clientNowCountry.getName() : I18n.format("message.country.terranullius");
            mc.fontRenderer.func_238422_b_(e.getMatrixStack(), new StringTextComponent(contrySt), w - mc.fontRenderer.getStringWidth(contrySt) - 2, h - 9, 14737632);

        }
    }
}
