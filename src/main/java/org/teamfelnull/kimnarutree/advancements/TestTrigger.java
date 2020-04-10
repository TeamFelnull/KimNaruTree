package org.teamfelnull.kimnarutree.advancements;

import org.teamfelnull.kimnarutree.KimNaruTree;
import org.teamfelnull.kimnarutree.advancements.TestTrigger.Instance;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.DistancePredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

public class TestTrigger extends AbstractCriterionTrigger<Instance>
{
    private static final ResourceLocation ID = new ResourceLocation(KimNaruTree.MODID, "picky");
    public static final TestTrigger INSTANCE = new TestTrigger();

    public ResourceLocation getId() {
        return ID;
    }

    public Instance deserializeInstance(final JsonObject json, final JsonDeserializationContext context) {
        final DistancePredicate distance = DistancePredicate.deserialize(json.get("distance"));
        final MinMaxBounds.IntBound duration = MinMaxBounds.IntBound.fromJson(json.get("duration"));
        return new Instance(distance, duration);
    }

    public void trigger(final ServerPlayerEntity player) {
        this.func_227070_a_(player.getAdvancements(), instance -> instance.test(player));
    }

    public static class Instance extends CriterionInstance
    {
        private final DistancePredicate distance;
        private final MinMaxBounds.IntBound duration;

        public Instance(final DistancePredicate distance, final MinMaxBounds.IntBound duraiton) {
            super(TestTrigger.ID);
            this.distance = distance;
            this.duration = duraiton;
        }

        public static Instance forDistance(final DistancePredicate distance) {
            return new Instance(distance, MinMaxBounds.IntBound.UNBOUNDED);
        }

        public boolean test(final ServerPlayerEntity player) {
        	return true;
            //return this.distance.test(vector.x, vector.y, vector.z, player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_()) && this.duration.test(distance);
        }

        public JsonElement serialize() {
            final JsonObject json = new JsonObject();
            json.add("distance", this.distance.serialize());
            json.add("duration", this.duration.serialize());
            return (JsonElement)json;
        }
    }
}