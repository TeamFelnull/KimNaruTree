package red.felnull.kimnarutree.client.render.entity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import red.felnull.kimnarutree.client.render.model.NPCModel;
import red.felnull.kimnarutree.entity.NPCEntity;
import red.felnull.otyacraftengine.client.util.IKSGTextureUtil;

public class NPCRenderer extends BipedRenderer<NPCEntity, NPCModel<NPCEntity>> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(
            "textures/entity/steve.png");

    public NPCRenderer(EntityRendererManager renderManagerIn) {

        this(renderManagerIn, new NPCModel<NPCEntity>(), 0.5f);
    }


    public NPCRenderer(EntityRendererManager renderManagerIn, NPCModel<NPCEntity> modelBipedIn, float shadowSize) {
        super(renderManagerIn, modelBipedIn, shadowSize);

        this.addLayer(new BipedArmorLayer<>(this, new BipedModel<>(0.5F), new BipedModel<>(1.0F)));
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new HeadLayer<>(this));
        this.addLayer(new ElytraLayer<>(this));

    }

    public ResourceLocation getEntityTexture(NPCEntity entity) {

        if (entity.getCustomName() != null) {
            String name = entity.getCustomName().getString();
            return IKSGTextureUtil.getPlayerSkinTexture(name);
        }
        return TEXTURES;
    }
}