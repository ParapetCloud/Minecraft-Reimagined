package com.github.mcri.effect.scroll;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class WitheringScrollEffect extends ScrollEffect {

    public WitheringScrollEffect() {
        super(0x202020);
    }

    @Override
    public boolean canUseAt(PlayerEntity user, Vec3d pos) {
        return true;
    }

    @Override
    public void useAt(PlayerEntity user, Vec3d pos, ServerWorld world) {
        BlockPos min = new BlockPos(pos).add(-2, -2, -2);
        BlockPos max = new BlockPos(pos).add(3, 3, 3);

        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, new Box(min, max), entity -> user != entity && user.canTarget(entity));
        
        world.spawnParticles(ParticleTypes.LARGE_SMOKE, pos.x, pos.y, pos.z, 500, 1, 1, 1, 0);

        for (Iterator<LivingEntity> iter = entities.iterator(); iter.hasNext();) {
            LivingEntity target = iter.next();

            target.setAttacker(user);
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 10 * 20, 0));
        }
    }
}
