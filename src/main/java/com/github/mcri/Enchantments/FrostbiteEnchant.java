package com.github.mcri.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import com.github.mcri.StatusEffects.FrozenEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FrostbiteEnchant extends Enchantment {
    public static final FrostbiteEnchant Instance = new FrostbiteEnchant();

    public static void Register() {
        Registry.register(Registry.ENCHANTMENT, new Identifier("mcri", "frostbite"), Instance);
    }

    public FrostbiteEnchant() {
        super(Enchantment.Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(new StatusEffectInstance(FrozenEffect.FROZEN, 4 * 20 * level, level));
        }

        super.onTargetDamaged(user, target, level);
    }
}