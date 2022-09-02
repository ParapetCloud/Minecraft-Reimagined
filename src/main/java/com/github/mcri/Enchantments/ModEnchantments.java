package com.github.mcri.enchantments;

import com.github.mcri.utils.Registration;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;

public final class ModEnchantments {
    // Banes
    public static BaneofAquaticsEnchant BANE_AQUATICS;
    public static BaneofEnderEnchant BANE_ENDER;
    public static BaneofIllagersEnchant BANE_ILLAGERS;
    public static BaneofSwinesEnchant BANE_SWINES;
    public static BaneofVillagersEnchant BANE_VILLAGERS;

    // Skies
    public static ClearSkiesEnchant CLEAR_SKIES;
    public static DarkSkiesEnchant DARK_SKIES;
    public static NoSkiesEnchant NO_SKIES;
    public static StormySkiesEnchant STORMY_SKIES;

    // Aspects
    public static PoisonAspectEnchant POISON_ASPECT;
    public static WitheringEnchant WITHERING;

    // Other
    public static FrostbiteEnchant FROSTBITE;
    public static LevitationEnchant LEVITATION;
    public static LifeStealEnchant LIFESTEAL;
    public static LightningEnchant LIGHTNING;
    public static MountedEnchant MOUNTED;
    public static WaterjetEnchant Waterjet;

    /**
     * Registers all the enchantments that have been pre-registered
     */
    public static void registerAll() {
        Registration.ReflectAllForRegistration(ModEnchantments.class, Enchantment.class, Registry.ENCHANTMENT);
    }
}
