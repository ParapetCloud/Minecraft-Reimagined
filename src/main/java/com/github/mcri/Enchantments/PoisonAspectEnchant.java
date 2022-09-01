package com.github.mcri.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PoisonAspectEnchant extends Enchantment {
    public static final PoisonAspectEnchant POISON = new PoisonAspectEnchant();

    public static void Register() {
        Registry.register(Registry.ENCHANTMENT, new Identifier("mcri", "poison_aspect"), POISON);
    }

    public PoisonAspectEnchant() {
        super(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        Item item = stack.getItem();
        if (item instanceof AxeItem || item instanceof TridentItem) {
            return true;
        }
        return super.isAcceptableItem(stack);
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
            StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.POISON, 5 * 20, level - 1);

            livingEntity.addStatusEffect(statusEffectInstance);

            if (user.world instanceof ServerWorld world) {
                world.spawnParticles(ParticleTypes.SNEEZE, target.getX(), target.getBodyY(0.5), target.getZ(), 5, 0, 0, 0, 0.1);
            }
        }

        super.onTargetDamaged(user, target, level);
    }
}
