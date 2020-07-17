package red.felnull.kimnarutree.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import red.felnull.kimnarutree.KimNaruTree;

public class KNTContainerTypes {
    @ObjectHolder(KimNaruTree.MODID + ":" + "test_npc_modoki")
    public static ContainerType<TestNpcModokiContainer> TEST_NPC_MODOKI;

    public static void registerContainerType(IForgeRegistry<ContainerType<?>> r) {
        TEST_NPC_MODOKI = (ContainerType<TestNpcModokiContainer>) IForgeContainerType.create((windowId, inv, data) -> {
            return new TestNpcModokiContainer(windowId, inv, data.readBlockPos());
        }).setRegistryName(new ResourceLocation(KimNaruTree.MODID, "test_npc_modoki"));
        r.register(TEST_NPC_MODOKI);
    }
}
