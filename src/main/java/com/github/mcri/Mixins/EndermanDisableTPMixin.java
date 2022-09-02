package com.github.mcri.mixins;

import net.minecraft.entity.mob.EndermanEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.mcri.effect.ModStatusEffects;

@Mixin(value = EndermanEntity.class)
public abstract class EndermanDisableTPMixin {
    @Inject(method = { "teleportTo(DDD)Z" }, at = @At(value = "HEAD"), cancellable = true)
    private void teleportDisable(double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        //Mixin method of accessing .this, to check the status effect
        if (((EndermanEntity) (Object) this).hasStatusEffect(ModStatusEffects.ENDERBANE_EFFECT)) {
            cir.setReturnValue(false);
        }
    }
    @Inject(method = { "teleportRandomly()Z"}, at = @At(value = "HEAD"), cancellable = true)
    private void randomTeleportDisable(CallbackInfoReturnable<Boolean> cir) {
        //Mixin method of accessing .this, to check the status effect
        if (((EndermanEntity) (Object) this).hasStatusEffect(ModStatusEffects.ENDERBANE_EFFECT)) {
            cir.setReturnValue(false);
        }
    }
}

