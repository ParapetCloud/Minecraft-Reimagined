package com.github.mcri.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class LevitationEnchant extends Enchantment {
    public static final LevitationEnchant LEVITATION = new LevitationEnchant();

    public static void Register() {
        Registry.register(Registry.ENCHANTMENT, new Identifier("mcri", "levitation"), LEVITATION);
    }

    public LevitationEnchant() {
        super(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        Item item = stack.getItem();
        if (item instanceof AxeItem || item instanceof SwordItem) {
            return true;
        }
        return super.isAcceptableItem(stack);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 1 * 20, level - 1));
        }

        super.onTargetDamaged(user, target, level);
    }
}
