package org.teamfelnull.kimnarutree.client.render.entity;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.client.render.model.NPCModel;
import org.teamfelnull.kimnarutree.entity.NPCEntity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.util.ResourceLocation;

public class NPCRenderer extends MobRenderer<NPCEntity, NPCModel<NPCEntity>> {

	private static final ResourceLocation textuer = new ResourceLocation(KimNaruTree.MODID,
			"textures/entity/npc/npc.png");

	public NPCRenderer(EntityRendererManager p_i50961_1_) {
		super(p_i50961_1_, new NPCModel<>(), 0.5f);
		this.addLayer(new HeadLayer<>(this));
		//   this.addLayer(new BipedArmorLayer<>(this, p_i50974_3_, p_i50974_4_));
	}

	@Override
	public ResourceLocation getEntityTexture(NPCEntity entity) {
		return textuer;

	}

	protected void preRenderCallback(NPCEntity entitylivingbaseIn, MatrixStack p_225620_2_, float partialTickTime) {

		float f = 0.9375F;

		if (entitylivingbaseIn.isChild()) {
			f = (float) ((double) f * 0.5D);
			this.shadowSize = 0.25F;
		} else {
			this.shadowSize = 0.5F;
		}

		p_225620_2_.func_227862_a_(f, f, f);
	}
}
