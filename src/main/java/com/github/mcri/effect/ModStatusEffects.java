package com.github.mcri.effect;

import com.github.mcri.utils.Registration;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.registry.Registry;

public final class ModStatusEffects {
    public static FrozenEffect FROZEN;
    public static BaneofEnderEffect ENDERBANE_EFFECT;
    public static InnerStengthEffect INNER_STRENGTH;

    /**
     * Registers all the effects that have been pre-registered
     */
    public static void registerAll() {
        Registration.ReflectAllForRegistration(ModStatusEffects.class, StatusEffect.class, Registry.STATUS_EFFECT);
    }
}
