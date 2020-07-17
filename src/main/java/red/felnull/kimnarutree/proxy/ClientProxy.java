package red.felnull.kimnarutree.proxy;


import red.felnull.kimnarutree.client.gui.screen.KNTScrennContainerFactorys;

public class ClientProxy extends CommonProxy {
    public static void clientInit() {
        KNTScrennContainerFactorys.registerFactories();
    }

    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void posInit() {
        super.posInit();
    }


}
