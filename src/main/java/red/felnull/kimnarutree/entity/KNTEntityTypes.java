package red.felnull.kimnarutree.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.registries.IForgeRegistry;
import red.felnull.kimnarutree.KimNaruTree;

public class KNTEntityTypes {
    public static final EntityType<NPCEntity> NPC = EntityType.Builder.<NPCEntity>create(NPCEntity::new, EntityClassification.MISC).size(0.6F, 1.95F).build(KimNaruTree.MODID + ":npc");

    public static void registerEntity(IForgeRegistry<EntityType<?>> r) {

        r.register(NPC.setRegistryName(KimNaruTree.MODID, "npc"));
    }

    public static void regsterAttrubytes() {
        GlobalEntityTypeAttributes.put(NPC, NPCEntity.getAttributeModifier().func_233813_a_());
    }

}
