package com.github.mcri.enchantments;

import com.github.mcri.StatusEffects.BaneofEnderEffect;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;

public class BaneofEnderEnchant extends Enchantment {

    public BaneofEnderEnchant() {
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
                other == ModEnchantments.BANE_VILLAGERS ||
                other == ModEnchantments.BANE_SWINES ||
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
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity livingEntity) {
            if (livingEntity instanceof EndermanEntity ||
                    livingEntity instanceof EndermiteEntity ||
                    livingEntity instanceof ShulkerEntity ||
                    livingEntity instanceof EnderDragonEntity) {
                // retrieve user's base attack damage, to use in the final damage calculation
                float damage = (float) user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)
                        + EnchantmentHelper.getAttackDamage(user.getMainHandStack(), user.getGroup());
                if (user instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) user;
                    target.damage(DamageSource.player(player), damage + (level * 2.5F));
                } else {
                    target.damage(DamageSource.mob(user), damage + (level * 2.5F));
                }
                livingEntity.addStatusEffect(
                        new StatusEffectInstance(BaneofEnderEffect.ENDERBANEEFFECT, 1 * 10 * level, level));
            }
        }

        super.onTargetDamaged(user, target, level);
    }
}
