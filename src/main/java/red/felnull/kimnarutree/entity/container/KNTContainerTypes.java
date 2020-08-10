package red.felnull.kimnarutree.entity.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.lib.resource.RESOURCE;
import red.felnull.kimnarutree.lib.resource.ResourceUtil;
import red.felnull.otyacraftengine.util.RegistryHelper;

public class KNTContainerTypes {
    @ObjectHolder(KimNaruTree.MOD_ID + ":" + "test_npc_modoki")
    public static ContainerType<TestNpcModokiContainer> TEST_NPC_MODOKI;

    public static void registerContainerType(IForgeRegistry<ContainerType<?>> r) {

        RegistryHelper.registedContainerType(r, TEST_NPC_MODOKI, (windowId, inv, data) -> {
            return new TestNpcModokiContainer(windowId, inv, data.readBlockPos());
        }, ResourceUtil.kntResource(RESOURCE.TEST_NPC_MODOKI));

    }
}
