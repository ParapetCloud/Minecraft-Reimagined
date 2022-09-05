package com.github.mcri.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class InnerStengthEffect extends StatusEffect {
    public InnerStengthEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x770076);
        addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "1E42E5A1-3298-4161-992B-B3849C2B45CB", 12, Operation.ADDITION);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(DamageSource.MAGIC, 1.0F);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int divider = Math.max(20 >> amplifier, 1);

        return duration % divider == 0;
    }
}
