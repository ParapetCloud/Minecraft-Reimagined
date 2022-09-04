package com.github.mcri.effect.scroll;

import javax.annotation.Nullable;

import com.github.mcri.ModRegistry;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public abstract class ScrollEffect {
    private int color;

    public ScrollEffect(int color) {
        this.color = color;
    }
    
    @Nullable
    public static ScrollEffect fromNbt(NbtCompound nbt) {
        int i = nbt.getInt("Id");
        ScrollEffect scrollEffect = ScrollEffect.byRawId(i);
        return scrollEffect;
    }
    
    @Nullable
    public static ScrollEffect byRawId(int rawId) {
        return (ScrollEffect) ModRegistry.SCROLL_EFFECT.get(rawId);
    }

    public static int getRawId(ScrollEffect effect) {
        return ModRegistry.SCROLL_EFFECT.getRawId(effect);
    }

    /**
     * Returns if the scroll effect can be applied to self
     * 
     * @param user
     *            The user of the scroll
     */
    public boolean canUseOnSelf(PlayerEntity user) {
        return false;
    }

    /**
     * Performs the action of using the scroll on the user. Called on server only
     * 
     * @param user
     *            The user of the scroll
     * @param world
     *            The world the user is in
     */
    public void useOnSelf(PlayerEntity user, ServerWorld world) {}

    /**
     * Returns if the scroll can be used on the other entity
     * 
     * @param user
     *            The user of the scroll
     * @param target
     *            The target of the scroll
     */
    public boolean canUseOnOther(PlayerEntity user, LivingEntity target) {
        return false;
    }

    /**
     * Performs the action of using the scroll on a specified entity. Called on server only
     * 
     * @param user
     *            The user of the scroll
     * @param target
     *            the target of the scroll
     * @param world
     *            the world the user is in
     */
    public void useOnOther(PlayerEntity user, LivingEntity target, ServerWorld world) {}

    /**
     * Returns if the scroll can be used at a specific location
     * 
     * @param user
     *            The user of the scroll
     * @param pos
     *            The position of the scrolls usage
     */
    public boolean canUseAt(PlayerEntity user, Vec3d pos) {
        return false;
    }

    /**
     * Performs the action of using the scroll at a specied location. Called on server only
     * 
     * @param user
     *            The user of the scroll
     * @param pos
     *            The position to activate the scroll
     * @param world
     *            The world the user is in
     */
    public void useAt(PlayerEntity user, Vec3d pos, ServerWorld world) {}

    public int getColor() {
        return color;
    }

    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putInt("Id", ScrollEffect.getRawId(this));
        return nbt;
    }
}
