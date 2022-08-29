package com.github.mcri.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.passive.DonkeyEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.MuleEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MountedEnchant extends Enchantment {
    public static final Enchantment MOUNTED = new MountedEnchant();

    public static void Register() {
        Registry.register(Registry.ENCHANTMENT, new Identifier("mcri", "mounted"), MOUNTED);
    }

    public MountedEnchant() {
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
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxPower(int level) {
        return level * 4;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity livingEntity) {
            if (user.getVehicle() instanceof HorseEntity ||
                    user.getVehicle() instanceof MuleEntity ||
                    user.getVehicle() instanceof DonkeyEntity ||
                    user.getVehicle() instanceof LlamaEntity ||
                    user.getVehicle() instanceof PigEntity ||
                    user.getVehicle() instanceof RavagerEntity ||
                    user.getVehicle() instanceof PigEntity ||
                    user.getVehicle() instanceof MinecartEntity) {
                // retrieve user's base attack damage, to use in the final damage calculation
                float damage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)
                        + EnchantmentHelper.getAttackDamage(user.getMainHandStack(), user.getGroup());
                target.damage(DamageSource.mob(user), damage + (level * 0.5F));
            }
        }

        super.onTargetDamaged(user, target, level);
    }
}
