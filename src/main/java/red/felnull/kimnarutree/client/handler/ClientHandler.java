package red.felnull.kimnarutree.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import red.felnull.kimnarutree.client.gui.screen.CountryCreateScreen;
import red.felnull.kimnarutree.data.country.Country;
import red.felnull.kimnarutree.data.KNTDatas;
import red.felnull.otyacraftengine.api.event.common.ResponseEvent;
import red.felnull.otyacraftengine.client.keys.OEKeyBindings;

public class ClientHandler {

    private static Minecraft mc = Minecraft.getInstance();


    @SubscribeEvent
    public static void onTick(TickEvent.ClientTickEvent e) {


    }

    @SubscribeEvent
    public static void onKey(InputEvent.KeyInputEvent e) {
        if (e.getKey() == OEKeyBindings.TEST.getKey().getKeyCode()) {
            mc.displayGuiScreen(new CountryCreateScreen(mc.currentScreen));
        }
    }

    @SubscribeEvent
    public static void onChatReceived(ClientChatReceivedEvent e) {
        if (e.getType() == ChatType.SYSTEM) {
            if (e.getMessage() instanceof TranslationTextComponent) {
                TranslationTextComponent me = (TranslationTextComponent) e.getMessage();
                if (me.getKey().contains("death") && !me.getKey().contains("rip")) {
                    e.setCanceled(true);
                }
            }
           /* if (e.getMessage().getString().contains("DataList: ")) {
                MessagePassbookHandler.oldChatComponent = e.getMessage();
            }*/
        }
    }

    @SubscribeEvent
    public static void onServerResponse(ResponseEvent.Server e) {
        if (e.getLocation().equals(KNTDatas.COUNTRY_SYNC)) {
            if (e.getId() == 0) {
                Country.clientNowCountry = new Country(e.getMessage());
            } else if (e.getId() == 1) {
                Country.clientNowCountry = null;
            }
        }
    }

}
