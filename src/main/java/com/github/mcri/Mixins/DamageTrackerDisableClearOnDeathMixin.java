package com.github.mcri.mixins;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageRecord;
import net.minecraft.entity.damage.DamageTracker;

@Mixin(value = DamageTracker.class)
public abstract class DamageTrackerDisableClearOnDeathMixin {
    @Accessor
    abstract boolean getHasDamage();
    @Accessor
    abstract void setHasDamage(boolean val);
    @Accessor(value = "entity")
    abstract LivingEntity getEntity2();
    @Accessor
    abstract int getAgeOnLastDamage();
    @Accessor
    abstract void setAgeOnLastDamage(int val);
    @Accessor
    abstract boolean getRecentlyAttacked();
    @Accessor
    abstract void setRecentlyAttacked(boolean val);
    @Accessor
    abstract List<DamageRecord> getRecentDamage();


    @Inject(method="update", at = @At(value = "HEAD"), cancellable = true)
    public void updateNoClearOnDeath(CallbackInfo cir) {
        int i = getRecentlyAttacked() ? 300 : 100;
        if (getHasDamage() && getEntity2().age - getAgeOnLastDamage() > i) {
            boolean bl = getRecentlyAttacked();
            setHasDamage(false);
            setRecentlyAttacked(false);
            setAgeOnLastDamage(getEntity2().age);
            if (bl) {
                getEntity2().endCombat();
            }

            getRecentDamage().clear();
        }
        cir.cancel();
    }
}
