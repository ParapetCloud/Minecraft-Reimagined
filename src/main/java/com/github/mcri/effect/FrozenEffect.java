package com.github.mcri.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FrozenEffect extends StatusEffect {

    public FrozenEffect() {
      super(
        StatusEffectCategory.HARMFUL, // whether beneficial or harmful for entities
        0xE0FFFF); // color in RGB
    }
   
    // This method is called every tick to check whether it should apply the status effect or not
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
      // In our case, we just make it return true so that it applies the status effect every tick.
      return true;
    }
   
    // This method is called when it applies the status effect. We implement custom functionality here.
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
      if (!entity.world.isClient()) {
        entity.setFrozenTicks(150);
      }

      super.applyUpdateEffect(entity, amplifier);

    }
  }