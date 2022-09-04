package com.github.mcri.scroll;

import com.github.mcri.ModRegistry;
import com.github.mcri.effect.scroll.ScrollEffects;
import com.github.mcri.utils.Registration;


public final class Scrolls {
    public static Scroll EMPTY = new Scroll();
    public static Scroll LIGHTNING = new Scroll(ScrollEffects.LIGHTNING);
    public static Scroll WITHERING = new Scroll(ScrollEffects.WITHERING);
    public static Scroll CLEANSING = new Scroll(ScrollEffects.CLEANSING);

    public static void registerAll() {
        Registration.ReflectAllForRegistration(Scrolls.class, Scroll.class, ModRegistry.SCROLL);
    }
}
