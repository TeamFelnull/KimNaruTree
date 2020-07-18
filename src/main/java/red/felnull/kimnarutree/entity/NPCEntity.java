package red.felnull.kimnarutree.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.INPC;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class NPCEntity extends CreatureEntity implements INPC {
    private static final DataParameter<Integer> SHAKE_HEAD_TICKS = EntityDataManager.createKey(NPCEntity.class, DataSerializers.VARINT);

    protected NPCEntity(EntityType<? extends CreatureEntity> p_i48575_1_, World p_i48575_2_) {
        super(p_i48575_1_, p_i48575_2_);
    }

    public static AttributeModifierMap.MutableAttribute getAttributeModifier() {
        return MobEntity.func_233666_p_().func_233815_a_(Attributes.field_233818_a_, 30.0D).func_233815_a_(Attributes.field_233819_b_, 20.0D).func_233815_a_(Attributes.field_233821_d_, 0.25D).func_233815_a_(Attributes.field_233823_f_, 6.0D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(SHAKE_HEAD_TICKS, 0);

    }

    public int getShakeHeadTicks() {
        return this.dataManager.get(SHAKE_HEAD_TICKS);
    }

    public void setShakeHeadTicks(int val) {
        this.dataManager.set(SHAKE_HEAD_TICKS, val);
    }

    private void shakeHead() {
        this.setShakeHeadTicks(40);
        if (!this.world.isRemote()) {
            this.playSound(SoundEvents.ENTITY_VILLAGER_NO, this.getSoundVolume(), this.getSoundPitch());
        }

    }

    @Override
    public void tick() {
        super.tick();
        if (this.getShakeHeadTicks() > 0) {
            this.setShakeHeadTicks(this.getShakeHeadTicks() - 1);
        }
    }

    @Override
    public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        if (!this.world.isRemote) {
            this.shakeHead();
        }
        return ActionResultType.func_233537_a_(this.world.isRemote);
    }
}
