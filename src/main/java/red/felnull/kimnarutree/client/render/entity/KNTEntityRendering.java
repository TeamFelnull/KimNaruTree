package red.felnull.kimnarutree.client.render.entity;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import red.felnull.kimnarutree.entity.KNTEntityTypes;

public class KNTEntityRendering {
    public static void registerRendering() {
        RenderingRegistry.registerEntityRenderingHandler(KNTEntityTypes.NPC, NPCRenderer::new);
    }
}
