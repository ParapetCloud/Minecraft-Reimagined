package com.github.mcri.StatusEffects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BaneofEnderEffect extends StatusEffect {

  public static final StatusEffect ENDERBANEEFFECT = new BaneofEnderEffect();

  public static void Register() {
    Registry.register(Registry.STATUS_EFFECT, new Identifier("mcri", "enderbane_effect"), ENDERBANEEFFECT);
  }

  public BaneofEnderEffect() {
    super(
        StatusEffectCategory.HARMFUL,
        0x4B0082); // color in RGB
  }

  @Override
  public boolean canApplyUpdateEffect(int duration, int amplifier) {
    return true;
  }

  @Override
  public void applyUpdateEffect(LivingEntity entity, int amplifier) {
    if (entity instanceof EndermanEntity || entity instanceof EndermiteEntity || entity instanceof ShulkerEntity) {

    }

    super.applyUpdateEffect(entity, amplifier);
  }
}