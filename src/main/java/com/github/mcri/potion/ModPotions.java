package com.github.mcri.potion;

import java.lang.reflect.Field;

import com.github.mcri.MinecraftReimagined;
import com.github.mcri.effect.ModStatusEffects;
import com.github.mcri.utils.PotionName;
import com.github.mcri.utils.Registration;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.registry.Registry;

/** @see Potions */
public final class ModPotions {
    private static final int SECONDS = 20;
    private static final int MINUTES = 60 * SECONDS;

    @PotionName(name = "Bad Luck")
    public static final Potion BAD_LUCK = new Potion(new StatusEffectInstance(StatusEffects.UNLUCK, 5 * MINUTES, 0));
    @PotionName(name = "Bad Luck")
    public static Potion LONG_BAD_LUCK;
    @PotionName(name = "Bad Luck")
    public static Potion STRONG_BAD_LUCK;
    @PotionName(name = "Blindness")
    public static final Potion BLINDNESS = new Potion(new StatusEffectInstance(StatusEffects.BLINDNESS, 90 * SECONDS, 0));
    @PotionName(name = "Blindness")
    public static Potion LONG_BLINDNESS;
    @PotionName(name = "Blindness")
    public static Potion STRONG_BLINDNESS;
    @PotionName(name = "Conduit Power")
    public static final Potion CONDUIT_POWER = new Potion(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 90 * SECONDS, 0));
    @PotionName(name = "Conduit Power")
    public static Potion LONG_CONDUIT_POWER;
    @PotionName(name = "Conduit Power")
    public static Potion STRONG_CONDUIT_POWER;
    @PotionName(name = "Darkness")
    public static final Potion DARKNESS = new Potion(new StatusEffectInstance(StatusEffects.DARKNESS, 90 * SECONDS, 0));
    @PotionName(name = "Darkness")
    public static Potion LONG_DARKNESS;
    @PotionName(name = "Darkness")
    public static Potion STRONG_DARKNESS;
    @PotionName(name = "Dolphin's Grace")
    public static final Potion DOLPHINS_GRACE = new Potion(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 90 * SECONDS, 0));
    @PotionName(name = "Dolphin's Grace")
    public static Potion LONG_DOLPHINS_GRACE;
    @PotionName(name = "Dolphin's Grace")
    public static Potion STRONG_DOLPHINS_GRACE;
    @PotionName(name = "Glowing")
    public static final Potion GLOWING = new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 90 * SECONDS, 0));
    @PotionName(name = "Glowing")
    public static Potion LONG_GLOWING;
    @PotionName(name = "Glowing")
    public static Potion STRONG_GLOWING;
    @PotionName(name = "Hunger")
    public static final Potion HUNGER = new Potion(new StatusEffectInstance(StatusEffects.HUNGER, 90 * SECONDS, 0));
    @PotionName(name = "Hunger")
    public static Potion LONG_HUNGER;
    @PotionName(name = "Hunger")
    public static Potion STRONG_HUNGER;
    @PotionName(name = "Inner Strength")
    public static final Potion INNER_STRENGTH = new Potion(new StatusEffectInstance(ModStatusEffects.INNER_STRENGTH, 3 * MINUTES, 0));
    @PotionName(name = "Inner Strength")
    public static Potion LONG_INNER_STRENGTH;
    @PotionName(name = "Inner Strength")
    public static Potion STRONG_INNER_STRENGTH;
    @PotionName(name = "Levitation")
    public static final Potion LEVITATION = new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 90 * SECONDS, 0));
    @PotionName(name = "Levitation")
    public static Potion LONG_LEVITATION;
    @PotionName(name = "Levitation")
    public static Potion STRONG_LEVITATION;
    @PotionName(name = "Luck")
    public static Potion LONG_LUCK = new Potion(new StatusEffectInstance(StatusEffects.LUCK, 10 * MINUTES, 0));
    @PotionName(name = "Luck")
    public static Potion STRONG_LUCK = new Potion(new StatusEffectInstance(StatusEffects.LUCK, 150 * SECONDS, 1));
    @PotionName(name = "Mining Fatigue")
    public static final Potion MINING_FATIGUE = new Potion(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 90 * SECONDS, 0));
    @PotionName(name = "Mining Fatigue")
    public static Potion LONG_MINING_FATIGUE;
    @PotionName(name = "Mining Fatigue")
    public static Potion STRONG_MINING_FATIGUE;
    @PotionName(name = "Saturation")
    public static final Potion SATURATION = new Potion(new StatusEffectInstance(StatusEffects.SATURATION, 90 * SECONDS, 0));
    @PotionName(name = "Saturation")
    public static Potion LONG_SATURATION;
    @PotionName(name = "Saturation")
    public static Potion STRONG_SATURATION;

    static {
        Field[] fields = ModPotions.class.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            Class<?> fieldType = field.getType();
            // check that they're static and an item and not a long/strong variant
            if (!field.canAccess(null) ||
                !Potion.class.isAssignableFrom(fieldType) ||
                field.getName().startsWith("LONG_") ||
                field.getName().startsWith("STRONG_")) {
                continue;
            }

            try {
                Field longField = ModPotions.class.getField("LONG_" + field.getName());

                StatusEffectInstance[] effects = new StatusEffectInstance[1];
                ((Potion) field.get(null)).getEffects().stream()
                    .map(effect -> Copy(effect, 2, 0))
                    .toList().toArray(effects);

                longField.set(null, new Potion(effects));
            }
            catch (Exception ex) {
                MinecraftReimagined.LOGGER.error("Failed to make long version");
            }

            try {
                Field strongField = ModPotions.class.getField("STRONG_" + field.getName());

                StatusEffectInstance[] effects = new StatusEffectInstance[1];
                ((Potion) field.get(null)).getEffects().stream()
                    .map(effect -> Copy(effect, 0.5, 1))
                    .toList().toArray(effects);

                strongField.set(null, new Potion(effects));
            }
            catch (Exception ex) {
                MinecraftReimagined.LOGGER.error("Failed to make strong version");
            }
        }
    }

    public static StatusEffectInstance Copy(StatusEffectInstance from, double durationMul, int amplifierAdd) {
        return new StatusEffectInstance(from.getEffectType(), (int) (from.getDuration() * durationMul), from.getAmplifier() + amplifierAdd);
    }

    public static void registerAll() {
        Registration.ReflectAllForRegistration(ModPotions.class, Potion.class, Registry.POTION);
    }
}
