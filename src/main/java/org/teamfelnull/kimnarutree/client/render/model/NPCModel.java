package org.teamfelnull.kimnarutree.client.render.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.entity.model.IHeadToggle;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class NPCModel<T extends Entity> extends SegmentedModel<T> implements IHasHead, IHeadToggle {


	protected ModelRenderer head;
	protected ModelRenderer nose;
	protected ModelRenderer body;
	protected ModelRenderer bodyClothes;
	protected ModelRenderer arms;
	protected ModelRenderer rightLeg;
	protected ModelRenderer leftLeg;

	public NPCModel() {
		this(0.0f);

	}

	public NPCModel(float scale) {


		this.head = (new ModelRenderer(this)).setTextureSize(64, 64);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.setTextureOffset(0, 0).func_228301_a_(-4.0F, -10.0F, -4.0F, 8, 10, 8, scale);

		this.nose = (new ModelRenderer(this)).setTextureSize(64, 64);
		this.nose.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.nose.setTextureOffset(24, 0).func_228301_a_(-1.0F, -1.0F, -6.0F, 2, 4, 2, scale);
		this.head.addChild(this.nose);

		this.body = (new ModelRenderer(this)).setTextureSize(64, 64);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.setTextureOffset(16, 20).func_228301_a_(-4.0F, 0.0F, -3.0F, 8, 12, 6, scale);

		this.bodyClothes = (new ModelRenderer(this)).setTextureSize(64, 64);
		this.bodyClothes.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bodyClothes.setTextureOffset(0, 38).func_228301_a_(-4.0F, 0.0F, -3.0F, 8, 18, 6, scale + 0.5F);
		this.body.addChild(this.bodyClothes);

		this.arms = (new ModelRenderer(this)).setTextureSize(64, 64);
		this.arms.setRotationPoint(0.0F, 2.0F, 0.0F);
		this.arms.setTextureOffset(44, 22).func_228301_a_(-8.0F, -2.0F, -2.0F, 4, 8, 4, scale);
		this.arms.setTextureOffset(44, 22).func_228303_a_(4.0F, -2.0F, -2.0F, 4, 8, 4, scale, true);
		this.arms.setTextureOffset(40, 38).func_228301_a_(-4.0F, 2.0F, -2.0F, 8, 4, 4, scale);

		this.rightLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(64, 64);
		this.rightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		this.rightLeg.func_228301_a_(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);

		this.leftLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(64, 64);
		this.leftLeg.mirror = true;
		this.leftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
		this.leftLeg.func_228301_a_(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
	}

	@Override
	public Iterable<ModelRenderer> func_225601_a_() {
		return ImmutableList.of(this.head, this.body, this.arms, this.rightLeg,
				this.leftLeg);
	}

	@Override
	public ModelRenderer func_205072_a() {
		return this.head;
	}

	@Override
	public void func_217146_a(boolean p_217146_1_) {
		this.head.showModel = p_217146_1_;
	}

	@Override
	public void func_225597_a_(T entityIn, float limbSwing, float limbSwingAmount, float arg3, float netHeadYaw,
			float headPitch) {
		this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);

		this.arms.rotationPointY = 3.0F;
		this.arms.rotationPointZ = -1.0F;
		this.arms.rotateAngleX = -0.75F;

		this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
		this.rightLeg.rotateAngleY = 0.0F;

		this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount
				* 0.5F;
		this.leftLeg.rotateAngleY = 0.0F;


	}

}
