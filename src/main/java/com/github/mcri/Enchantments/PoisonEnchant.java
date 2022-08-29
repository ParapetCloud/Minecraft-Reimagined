package com.github.mcri.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PoisonEnchant extends Enchantment {
    public static final Enchantment POISON = new PoisonEnchant();

    public static void Register() {
        Registry.register(Registry.ENCHANTMENT, new Identifier("mcri", "poison"), POISON);
    }

    public PoisonEnchant() {
        super(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if (other == Enchantments.FIRE_ASPECT || other == FrostbiteEnchant.FROSTBITE || other == WitheringEnchant.WITHERING) {
            return false;
        }
        return true;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 5 * 20, level - 1));
        }

        super.onTargetDamaged(user, target, level);
    }
}
