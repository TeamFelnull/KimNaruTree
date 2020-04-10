package org.teamfelnull.kimnarutree.advancements;

import org.teamfelnull.kimnarutree.KimNaruTree;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class TestTrigger implements ICriterionTrigger<Instance> {

	public static final ResourceLocation id = new ResourceLocation(KimNaruTree.MODID, "test");

	public static final TestTrigger INSTANCE = new TestTrigger();

	public TestTrigger() {

	}

	@Override
	public Instance deserializeInstance(JsonObject var1, JsonDeserializationContext var2) {

		return null;
	}

	@Override
	public ResourceLocation getId() {

		return id;
	}

	@Override
	public void addListener(PlayerAdvancements var1, Listener<Instance> var2) {

	}

	@Override
	public void removeListener(PlayerAdvancements var1, Listener<Instance> var2) {

	}

	@Override
	public void removeAllListeners(PlayerAdvancements var1) {

	}

}

class Instance implements ICriterionInstance {

	Instance(ItemPredicate count, EntityPredicate user, Boolean desu) {

	}

	@Override
	public ResourceLocation getId() {

		return TestTrigger.id;
	}

	boolean test(ItemStack stack, ServerPlayerEntity entity) {
		return true;
	}
}