package com.github.mcri.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.IllusionerEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BaneofIllagersEnchant extends Enchantment {
    public static final BaneofIllagersEnchant BANEILLAGERS = new BaneofIllagersEnchant();

    public static void Register() {
        Registry.register(Registry.ENCHANTMENT, new Identifier("mcri", "bane_illagers"), BANEILLAGERS);
    }

    public BaneofIllagersEnchant() {
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
                other == BaneofAquaticsEnchant.BANEAQUATICS ||
                other == BaneofVillagersEnchant.BANEVILLAGERS ||
                other == BaneofSwinesEnchant.BANESWINES ||
                other == BaneofEnderEnchant.BANEENDER) {
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
            if (livingEntity instanceof VindicatorEntity ||
                    livingEntity instanceof VexEntity ||
                    livingEntity instanceof RavagerEntity ||
                    livingEntity instanceof WitchEntity ||
                    livingEntity instanceof EvokerEntity ||
                    livingEntity instanceof IllusionerEntity ||
                    livingEntity instanceof PillagerEntity) {
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
