package red.felnull.kimnarutree.client.gui.screen;

import net.minecraft.client.gui.ScreenManager;
import red.felnull.kimnarutree.container.KNTContainerTypes;

public class KNTScrennContainerFactorys {
    public static void registerFactories() {
        ScreenManager.registerFactory(KNTContainerTypes.TEST_NPC_MODOKI, TestNpcModokiScreen::new);
    }
}
