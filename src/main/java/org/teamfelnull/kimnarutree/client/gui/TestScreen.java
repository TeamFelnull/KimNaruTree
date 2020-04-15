package org.teamfelnull.kimnarutree.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class TestScreen extends Screen {
	public static final ITextComponent title = new TranslationTextComponent("test.screen");
	public static final ResourceLocation INVENTORY_BACKGROUND = new ResourceLocation(
			"textures/gui/container/inventory.png");

	public TestScreen() {
		super(title);
	}

	@Override
	public boolean isPauseScreen() {

		return false;
	}

	@Override
	public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(INVENTORY_BACKGROUND);
	}
}
