package org.teamfelnull.kimnarutree.client.render.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.entity.model.IHeadToggle;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class NPCModel<T extends Entity> extends EntityModel<T> implements IHasHead, IHeadToggle {

	protected RendererModel head;
	protected RendererModel nose;
	protected RendererModel body;
	protected RendererModel arms;
	protected RendererModel rightLeg;
	protected RendererModel leftLeg;

	public NPCModel() {
		this(0.0f);
	}

	public NPCModel(float scale) {
		this.head = (new RendererModel(this)).setTextureSize(64, 64);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, scale);

		this.nose = (new RendererModel(this)).setTextureSize(64, 64);
		this.nose.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.nose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, scale);
		this.head.addChild(this.nose);

		this.body = (new RendererModel(this)).setTextureSize(64, 64);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.setTextureOffset(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, scale);

		this.arms = (new RendererModel(this)).setTextureSize(64, 64);
		this.arms.setRotationPoint(0.0F, 2.0F, 0.0F);
		this.arms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, scale);
		this.arms.setTextureOffset(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, scale, true);
		this.arms.setTextureOffset(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, scale);

		this.rightLeg = (new RendererModel(this, 0, 22)).setTextureSize(64, 64);
		this.rightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);

		this.leftLeg = (new RendererModel(this, 0, 22)).setTextureSize(64, 64);
		this.leftLeg.mirror = true;
		this.leftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
		this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
	}

	@Override
	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw,float headPitch, float scale) {

		this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.head.render(scale);
		this.body.render(scale);
		this.arms.render(scale);
		this.rightLeg.render(scale);
		this.leftLeg.render(scale);
	}

	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch, float scaleFactor) {

		this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);

		this.arms.rotationPointY = 3.0F;
		this.arms.rotationPointZ = -1.0F;
		this.arms.rotateAngleX = -0.75F;

		this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
		this.rightLeg.rotateAngleY = 0.0F;

		this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount* 0.5F;
		this.leftLeg.rotateAngleY = 0.0F;
	}

	public RendererModel func_205072_a() {
		return this.head;
	}

	@Override
	public void func_217146_a(boolean p_217146_1_) {
		this.head.showModel = p_217146_1_;
	}
}
