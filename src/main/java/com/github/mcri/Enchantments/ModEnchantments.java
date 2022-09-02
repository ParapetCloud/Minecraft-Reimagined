package com.github.mcri.enchantments;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.github.mcri.MinecraftReimagined;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
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

    /**
     * Registers all the enchantments that have been registered
     */
    public static void registerAll() {
        Set<String> keys = delayedRegisters.keySet();

        for (Iterator<String> iter = keys.iterator(); iter.hasNext();) {
            String key = iter.next();
            Registry.register(Registry.ENCHANTMENT, new Identifier(MinecraftReimagined.MOD_ID, key), delayedRegisters.get(key));
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Black magic section below
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private static HashMap<String, Enchantment> delayedRegisters = new HashMap<String, Enchantment>();

    static {
        // get all the fields in this class
        Field[] fields = ModEnchantments.class.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            try {
                // check that they're static and an enchantment
                if (!fields[i].canAccess(null) || fields[i].getType().getSuperclass() != Enchantment.class) {
                    continue;
                }

                MinecraftReimagined.LOGGER.debug("registering '" + fields[i].getName() + "'");

                // set them to an instance of an object and get ready for delayed registering
                fields[i].set(null, delayedRegister(fields[i].getName().toLowerCase(), fields[i].getType()));
            }
            catch (Exception ex) {
                MinecraftReimagined.LOGGER.error("Failed to register enchantment '" + fields[i].getName().toLowerCase() + "'");
            }
        }
    }

    /**
     * sets an enchantment to be registered later
     * 
     * @param <T>
     *            the enchantment type
     * @param id
     *            the id of the enchantment
     * @param type
     *            the class of the enchantment
     * @return an instance of the enchantment to be registered later
     */
    private static Enchantment delayedRegister(String id, Class<?> type) {
        try {
            // create a new instance of the enchantment with the default constructor
            Enchantment instance = (Enchantment)type.getDeclaredConstructor().newInstance();
            // register it for future registration
            delayedRegisters.put(id, instance);
            return instance;
        }
        catch (Exception ex) {
            MinecraftReimagined.LOGGER.error("Failed to register enchantment '" + id + "' (" + type.getName() + ")");
            return null;
        }
    }
}
