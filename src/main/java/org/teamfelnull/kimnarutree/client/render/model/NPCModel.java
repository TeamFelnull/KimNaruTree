package org.teamfelnull.kimnarutree.client.render.model;

import org.teamfelnull.kimnarutree.entity.NPCEntity;

import net.minecraft.client.renderer.entity.model.PlayerModel;

public class NPCModel<T extends NPCEntity> extends PlayerModel<T> {
	public NPCModel() {
		this(0.0f);

	}

	public NPCModel(float f) {
		super(f, false);
	}
}
