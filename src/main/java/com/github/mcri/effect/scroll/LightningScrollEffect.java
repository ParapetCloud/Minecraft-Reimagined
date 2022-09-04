package com.github.mcri.effect.scroll;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class LightningScrollEffect extends ScrollEffect {
    public LightningScrollEffect() {
        super(0x30D0D0);
    }

    @Override
    public boolean canUseAt(PlayerEntity user, Vec3d pos) {
        return true;
    }

    @Override
    public void useAt(PlayerEntity user, Vec3d pos, ServerWorld world) {
        LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(world);
        lightningEntity.refreshPositionAfterTeleport(pos);
        lightningEntity.setCosmetic(false);
        world.spawnEntity(lightningEntity);
    }
}
