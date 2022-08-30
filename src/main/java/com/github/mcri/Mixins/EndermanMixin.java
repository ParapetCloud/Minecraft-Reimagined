package com.github.mcri.Mixins;

import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.mcri.StatusEffects.BaneofEnderEffect;

@Mixin(value = EndermanEntity.class)
public abstract class EndermanMixin {
    @Inject(method = { "teleportTo(DDD)Z" }, at = @At(value = "HEAD"), cancellable = true)
    private void teleportDisable(double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        if (((EndermanEntity) (Object) this).getStatusEffects() == BaneofEnderEffect.ENDERBANEEFFECT) {
            
            cir.setReturnValue(false);
        }
    }
}