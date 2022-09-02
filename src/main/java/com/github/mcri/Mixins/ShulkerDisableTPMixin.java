package com.github.mcri.mixins;

import net.minecraft.entity.mob.ShulkerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.mcri.effect.ModStatusEffects;

@Mixin(value = ShulkerEntity.class)
public abstract class ShulkerDisableTPMixin {
    @Inject(method = { "tryTeleport()Z" }, at = @At(value = "HEAD"), cancellable = true)
    private void teleportDisable(CallbackInfoReturnable<Boolean> cir) {
        //Mixin method of accessing .this, to check the status effect
        if (((ShulkerEntity) (Object) this).hasStatusEffect(ModStatusEffects.ENDERBANE_EFFECT)) {
            cir.setReturnValue(false);
        }
    }
}

