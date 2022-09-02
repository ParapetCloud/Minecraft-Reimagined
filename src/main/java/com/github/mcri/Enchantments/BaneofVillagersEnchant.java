package com.github.mcri.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;

public class BaneofVillagersEnchant extends Enchantment {
    
    public BaneofVillagersEnchant() {
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
                other == ModEnchantments.BANE_AQUATICS ||
                other == ModEnchantments.BANE_ILLAGERS ||
                other == ModEnchantments.BANE_SWINES ||
                other == ModEnchantments.BANE_ENDER) {
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
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity livingEntity) {
            if (livingEntity instanceof VillagerEntity ||
                    livingEntity instanceof IronGolemEntity ||
                    livingEntity instanceof PlayerEntity ||
                    livingEntity instanceof SnowGolemEntity) {
                // retrieve user's base attack damage, to use in the final damage calculation
                float damage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)
                        + EnchantmentHelper.getAttackDamage(user.getMainHandStack(), user.getGroup());
                if (user instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) user;
                    target.damage(DamageSource.player(player), damage + (level * 2.5F));
                } else {
                    target.damage(DamageSource.mob(user), damage + (level * 2.5F));
                }
            }
        }

        super.onTargetDamaged(user, target, level);
    }
}
