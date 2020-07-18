package red.felnull.kimnarutree.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.otyacraftengine.util.RegistryHelper;

public class KNTContainerTypes {
    @ObjectHolder(KimNaruTree.MODID + ":" + "test_npc_modoki")
    public static ContainerType<TestNpcModokiContainer> TEST_NPC_MODOKI;

    public static void registerContainerType(IForgeRegistry<ContainerType<?>> r) {

        RegistryHelper.registedContainerType(r, TEST_NPC_MODOKI, (windowId, inv, data) -> {
            return new TestNpcModokiContainer(windowId, inv, data.readBlockPos());
        }, new ResourceLocation(KimNaruTree.MODID, "test_npc_modoki"));

    }
}
