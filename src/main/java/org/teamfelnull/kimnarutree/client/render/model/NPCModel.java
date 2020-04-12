package org.teamfelnull.kimnarutree.client.render.model;

import org.teamfelnull.kimnarutree.entity.NPCEntity;

import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.math.MathHelper;

public class NPCModel<T extends NPCEntity> extends PlayerModel<T> {
	public NPCModel() {
		this(0.0f);

	}

	public NPCModel(float f) {
		super(f, false);
	}

	@Override
	public void func_225597_a_(T p_225597_1_, float p_225597_2_, float p_225597_3_,
			float p_225597_4_,
			float p_225597_5_, float p_225597_6_) {
		super.func_225597_a_(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
		boolean sflag = false;
		if (p_225597_1_ instanceof NPCEntity) {
			sflag = ((NPCEntity) p_225597_1_).getShakeHeadTicks() > 0;
		}
		if (sflag) {
			this.bipedHead.rotateAngleZ = 0.3F * MathHelper.sin(0.45F * p_225597_4_);
			this.bipedHead.rotateAngleX = 0.4F;
			this.bipedHeadwear.rotateAngleZ = 0.3F * MathHelper.sin(0.45F * p_225597_4_);
			this.bipedHeadwear.rotateAngleX = 0.4F;
		} else {
			this.bipedHead.rotateAngleZ = 0.0F;
			this.bipedHeadwear.rotateAngleZ = 0.0F;
		}

	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		this.bipedLeftArm.rotateAngleZ = limbSwing;
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
	}
}
