package com.github.mcri.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BaneofIllagersEnchant extends Enchantment {
    public static final Enchantment BANEILLAGERS = new BaneofIllagersEnchant();

    public static void Register() {
        Registry.register(Registry.ENCHANTMENT, new Identifier("mcri", "baneofillagers"), BANEILLAGERS);
    }

    public BaneofIllagersEnchant() {
        super(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        if (group == EntityGroup.ILLAGER){
            return 1 + (0.2f * level);
        } else {
            return 0f;
        }
    }
}
