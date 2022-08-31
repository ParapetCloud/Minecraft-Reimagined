package com.github.mcri.Mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.util.hit.HitResult;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.mcri.StatusEffects.BaneofEnderEffect;

@Mixin(value = EnderPearlEntity.class)
public abstract class EnderpearlDisableTPMixin {
    @Inject(method = { "onCollision()V" }, at = @At(value = "HEAD"), cancellable = true)
    private void teleportDisable(HitResult hitResult, CallbackInfo cir) {
        Entity thrower = ((EnderPearlEntity) (Object) this).getOwner();
        LivingEntity thrower2 = (LivingEntity)thrower;
        if (thrower2.hasStatusEffect(BaneofEnderEffect.ENDERBANEEFFECT)) {
            ((EnderPearlEntity) (Object) this).discard();
            cir.cancel();
        }
    }
}

