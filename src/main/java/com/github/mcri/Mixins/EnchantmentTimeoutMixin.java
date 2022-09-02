package com.github.mcri.mixins;

import org.spongepowered.asm.mixin.Mixin;

import com.github.mcri.IEnchantmentTimoutExt;

import net.minecraft.entity.LivingEntity;

@Mixin(LivingEntity.class)
public abstract class EnchantmentTimeoutMixin implements IEnchantmentTimoutExt {
    private int lastLifeSteal = 0;

    public int getLastLifeSteal() {
        return lastLifeSteal;
    }

    public void setLastLifeSteal(int last) {
        lastLifeSteal = last;
    }
}
