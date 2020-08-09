package red.felnull.kimnarutree.handler;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import red.felnull.kimnarutree.block.KNTBlocks;
import red.felnull.kimnarutree.entity.container.KNTContainerTypes;
import red.felnull.kimnarutree.entity.KNTEntityTypes;
import red.felnull.kimnarutree.item.KNTItems;
import red.felnull.kimnarutree.entity.tile.KNTTileEntityTypes;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> e) {
        KNTItems.registerItem(e.getRegistry());
        KNTBlocks.registerItem(e.getRegistry());
    }

    @SubscribeEvent
    public static void onBlockRegistry(final RegistryEvent.Register<Block> e) {
        KNTBlocks.registerBlock(e.getRegistry());
    }

    @SubscribeEvent
    public static void onTileEntityTypeRegistry(final RegistryEvent.Register<TileEntityType<?>> e) {
        KNTTileEntityTypes.registerTileEntityType(e.getRegistry());
    }

    @SubscribeEvent
    public static void onContainerTypeRegistry(final RegistryEvent.Register<ContainerType<?>> e) {
        KNTContainerTypes.registerContainerType(e.getRegistry());
    }

    @SubscribeEvent
    public static void onEntityRegistry(final RegistryEvent.Register<EntityType<?>> e) {
        KNTEntityTypes.registerEntity(e.getRegistry());
    }


}
