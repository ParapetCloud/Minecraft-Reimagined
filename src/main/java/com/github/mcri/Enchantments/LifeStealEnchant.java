package com.github.mcri.Enchantments;

import com.github.mcri.IEnchantmentTimoutExt;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class LifeStealEnchant extends Enchantment {
    public static final Enchantment LIFESTEAL = new LifeStealEnchant();

    public static void Register() {
        Registry.register(Registry.ENCHANTMENT, new Identifier("mcri", "life_steal"), LIFESTEAL);
    }

    public LifeStealEnchant() {
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
        return 1;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        
        // players need to check that they aren't spamming
        IEnchantmentTimoutExt timeouts = (IEnchantmentTimoutExt)user;

        // cache the last time
        int lastLifeStealAttempt = timeouts.getLastLifeSteal();
        timeouts.setLastLifeSteal(user.age);

        // mobs and players need different timouts due to how they can attack
        if (user instanceof PlayerEntity player) {
            // for players we need to make sure they aren't spamming
            if (player.getAttackCooldownProgress(user.age - lastLifeStealAttempt) < 1) {
                return;
            }
        }
        else {
            // mobs only need a check to make sure they aren't getting multiple damages from sweeping edge
            if (user.age - lastLifeStealAttempt < 1) {
                return;
            }
        }

        user.heal(2);
        super.onTargetDamaged(user, target, level);
    }
}
