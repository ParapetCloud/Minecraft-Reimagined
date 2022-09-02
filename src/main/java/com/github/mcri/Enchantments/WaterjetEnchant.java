package com.github.mcri.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class WaterjetEnchant extends Enchantment {
    
    public WaterjetEnchant() {
        super(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.TRIDENT, new EquipmentSlot[0]);
    }
}
