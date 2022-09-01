package com.github.mcri.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NoSkiesEnchant extends Enchantment {
    public static final Enchantment NOSKIES = new NoSkiesEnchant();

    public static void Register() {
        Registry.register(Registry.ENCHANTMENT, new Identifier("mcri", "no_skies"), NOSKIES);
    }

    public NoSkiesEnchant() {
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
        if (other == ClearSkiesEnchant.CLEARSKIES ||
                other == DarkSkiesEnchant.DARKSKIES ||
                other == StormySkiesEnchant.STORMYSKIES) {
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
        return 3;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (!user.world.isSkyVisible(user.getBlockPos())) {
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

        super.onTargetDamaged(user, target, level);
    }
}
