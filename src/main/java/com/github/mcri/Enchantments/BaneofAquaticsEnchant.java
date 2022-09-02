package com.github.mcri.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;

public class BaneofAquaticsEnchant extends Enchantment {

    public BaneofAquaticsEnchant() {
        super(Enchantment.Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        Item item = stack.getItem();
        if (item instanceof AxeItem ||
            item instanceof SwordItem ||
            item instanceof TridentItem) {
            return true;
        }
        return super.isAcceptableItem(stack);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if (other == Enchantments.BANE_OF_ARTHROPODS ||
            other == ModEnchantments.BANE_SWINES ||
            other == ModEnchantments.BANE_VILLAGERS ||
            other == ModEnchantments.BANE_ENDER ||
            other == ModEnchantments.BANE_ILLAGERS) {
            return false;
        }
        return true;
    }

    @Override
    public int getMinPower(int level) {
        return level * 6;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        if (group == EntityGroup.AQUATIC) {
            return 2.5f * level;
        }
        else {
            return 0f;
        }
    }
}
