package red.felnull.kimnarutree.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import red.felnull.kimnarutree.client.gui.screen.CountryCreateScreen;
import red.felnull.otyacraftengine.client.keys.OEKeyBindings;

public class ClientHandler {

    private static Minecraft mc = Minecraft.getInstance();

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
}
