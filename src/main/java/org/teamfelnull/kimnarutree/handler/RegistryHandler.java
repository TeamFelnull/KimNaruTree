package org.teamfelnull.kimnarutree.handler;

import org.teamfelnull.kimnarutree.block.KNTBlocks;
import org.teamfelnull.kimnarutree.entity.KNTEntitys;
import org.teamfelnull.kimnarutree.item.KNTItems;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
	public static void onEntityRegistry(final RegistryEvent.Register<EntityType<?>> e) {
		KNTEntitys.registerEntity(e.getRegistry());
	}
}
