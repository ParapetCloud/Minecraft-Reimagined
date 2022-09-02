package com.github.mcri.Mixins;

import net.minecraft.entity.mob.ShulkerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.mcri.StatusEffects.BaneofEnderEffect;

@Mixin(value = ShulkerEntity.class)
public abstract class ShulkerDisableTPMixin {
    @Inject(method = { "tryTeleport()Z" }, at = @At(value = "HEAD"), cancellable = true)
    private void teleportDisable(CallbackInfoReturnable<Boolean> cir) {
        //Mixin method of accessing .this, to check the status effect
        if (((ShulkerEntity) (Object) this).hasStatusEffect(BaneofEnderEffect.ENDERBANEEFFECT)) {
            cir.setReturnValue(false);
        }
    }
}

