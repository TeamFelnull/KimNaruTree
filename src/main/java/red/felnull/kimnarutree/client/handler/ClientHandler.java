package red.felnull.kimnarutree.client.handler;

import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientHandler {
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
