package com.github.mcri.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class BaneofEnderEffect extends StatusEffect {

  public BaneofEnderEffect() {
    super(
        StatusEffectCategory.HARMFUL,
        0x4B0082); // color in RGB
  }

  @Override
  public boolean canApplyUpdateEffect(int duration, int amplifier) {
    return true;
  }
}