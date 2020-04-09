package org.teamfelnull.kimnarutree.entity;

import org.teamfelnull.kimnarutree.KimNaruTree;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.IForgeRegistry;

public class KNTEntitys {

	public static final EntityType<NPCEntity> NPC = EntityType.Builder
			.<NPCEntity> create(NPCEntity::new, EntityClassification.MISC).size(0.6F, 1.95F)
			.build(KimNaruTree.MODID + ":npc");

	public static void registerEntity(IForgeRegistry<EntityType<?>> r) {
		r.register(NPC.setRegistryName(KimNaruTree.MODID, "npc"));
	}
}
