package com.github.mcri.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.mcri.enchantments.ModEnchantments;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

@Mixin(value = TridentEntity.class)
public abstract class TridentWaterjetMixin extends PersistentProjectileEntity {

    @Accessor
    abstract ItemStack getTridentStack();

    public TridentWaterjetMixin(World world, LivingEntity owner, ItemStack stack) {
        super(EntityType.TRIDENT, owner, world);
    }

    @Inject(method = { "onEntityHit" }, at = @At(value = "TAIL"))
    private void waterJet(EntityHitResult entityHitResult, CallbackInfo cir) {
        if (EnchantmentHelper.getLevel(ModEnchantments.Waterjet, this.getTridentStack()) > 0) {
            Entity target = entityHitResult.getEntity();

            target.addVelocity(0, 1, 0);
            if (target.world instanceof ServerWorld world) {
                world.spawnParticles(ParticleTypes.DRIPPING_DRIPSTONE_WATER, target.getX(), target.getY(), target.getZ(), 500, 0.3, 4, 0.3, 0);
            }
        }
    }
}
