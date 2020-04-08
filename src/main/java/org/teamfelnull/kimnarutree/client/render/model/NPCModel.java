package org.teamfelnull.kimnarutree.client.render.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.entity.model.IHeadToggle;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.util.math.MathHelper;

public class NPCModel<T extends Entity> extends EntityModel<T> implements IHasHead, IHeadToggle {
	protected final RendererModel villagerHead;

	public NPCModel() {
		this.villagerHead = (new RendererModel(this)).setTextureSize(64, 64);
		this.villagerHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.villagerHead.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 6.4f);

	}

	@Override
	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.villagerHead.render(scale);

	}

	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch, float scaleFactor) {
		boolean flag = false;
		if (entityIn instanceof AbstractVillagerEntity) {
			flag = ((AbstractVillagerEntity) entityIn).getShakeHeadTicks() > 0;
		}

		this.villagerHead.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.villagerHead.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		if (flag) {
			this.villagerHead.rotateAngleZ = 0.3F * MathHelper.sin(0.45F * ageInTicks);
			this.villagerHead.rotateAngleX = 0.4F;
		} else {
			this.villagerHead.rotateAngleZ = 0.0F;
		}

	}

	public RendererModel func_205072_a() {
		return this.villagerHead;
	}

	public void func_217146_a(boolean p_217146_1_) {
		this.villagerHead.showModel = p_217146_1_;

	}

}
