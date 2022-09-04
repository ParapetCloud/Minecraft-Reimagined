package com.github.mcri.effect.scroll;

import com.github.mcri.ModRegistry;
import com.github.mcri.utils.Registration;

public final class ScrollEffects {
    public static LightningScrollEffect LIGHTNING;
    public static WitheringScrollEffect WITHERING;
    public static CleansingScrollEffect CLEANSING;

    public static void registerAll() {
        Registration.ReflectAllForRegistration(ScrollEffects.class, ScrollEffect.class, ModRegistry.SCROLL_EFFECT);
    }
}
