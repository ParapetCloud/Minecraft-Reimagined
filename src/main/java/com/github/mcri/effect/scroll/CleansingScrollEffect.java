package com.github.mcri.effect.scroll;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class CleansingScrollEffect extends ScrollEffect {

    public CleansingScrollEffect() {
        super(0xFF7ED3);
    }

    @Override
    public boolean canUseOnSelf(PlayerEntity user) {
        return true;
    }

    @Override
    public void useOnSelf(PlayerEntity user, ServerWorld world) {
        user.clearStatusEffects();
    }
}
