package com.github.mcri;

import com.github.mcri.effect.scroll.ScrollEffect;
import com.github.mcri.scroll.Scroll;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class ModRegistry {
    public static Registry<ScrollEffect> SCROLL_EFFECT = FabricRegistryBuilder.createSimple(ScrollEffect.class, new Identifier(MinecraftReimagined.MOD_ID, "scroll_effects")).buildAndRegister();
    public static Registry<Scroll> SCROLL = FabricRegistryBuilder.createSimple(Scroll.class, new Identifier(MinecraftReimagined.MOD_ID, "scrolls")).buildAndRegister();
}
