package com.github.mcri.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import com.github.mcri.StatusEffects.FrozenEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FrostbiteEnchant extends Enchantment {
    public static final FrostbiteEnchant FROSTBITE = new FrostbiteEnchant();

    public static void Register() {
        Registry.register(Registry.ENCHANTMENT, new Identifier("mcri", "frostbite"), FROSTBITE);
    }

    public FrostbiteEnchant() {
        super(Enchantment.Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
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
    protected boolean canAccept(Enchantment other) {
        if (other == Enchantments.FIRE_ASPECT || other == PoisonAspectEnchant.POISON || other == WitheringEnchant.WITHERING) {
            return false;
        }
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity livingEntity) {
            livingEntity.extinguish();
            livingEntity.addStatusEffect(new StatusEffectInstance(FrozenEffect.FROZEN, 4 * 20 * level, level));
            ((ServerWorld) target.world).spawnParticles(ParticleTypes.SNOWFLAKE, target.getX(), target.getBodyY(1D),
                    target.getZ(), 10, .3, .4, .3, 0.0D);
        }

        super.onTargetDamaged(user, target, level);
    }
}