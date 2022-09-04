package com.github.mcri.items;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.github.mcri.ModRegistry;
import com.github.mcri.effect.scroll.ScrollEffect;
import com.github.mcri.scroll.Scroll;
import com.github.mcri.scroll.ScrollUtil;
import com.github.mcri.scroll.Scrolls;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;

public class ScrollItem extends Item {
    public ScrollItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        boolean used = false;

        HitResult result = user.raycast(8, 0, true);

        List<ScrollEffect> effects = ScrollUtil.getScrollEffects(itemStack);

        switch (result.getType()) {
            case BLOCK:
                BlockHitResult blockHit = (BlockHitResult) result;

                BlockPos doublePos = blockHit.getBlockPos().multiply(2).add(1, 1, 1).offset(blockHit.getSide(), 1);
                Vec3d pos = new Vec3d(doublePos.getX() / 2.0, doublePos.getY() / 2.0, doublePos.getZ() / 2.0);

                for (int i = 0; i < effects.size(); ++i) {
                    used |= useScrollAtIfPossible(user, pos, effects.get(i));
                }
                break;
            case ENTITY:
                EntityHitResult entityHit = (EntityHitResult) result;
                Entity entity = entityHit.getEntity();

                if (entity instanceof LivingEntity target) {
                    for (int i = 0; i < effects.size(); ++i) {
                        used |= useScrollOnOtherIfPossible(user, target, effects.get(i));
                    }
                }
                break;
            default:
                break;
        }

        if (used) {
            onScrollUsed(user, itemStack);
            return TypedActionResult.success(itemStack, world.isClient());
        }
        else {
            for (int i = 0; i < effects.size(); ++i) {
                used |= useScrollOnSelfIfPossible(user, effects.get(i));
            }

            if (used) {
                onScrollUsed(user, itemStack);
                return TypedActionResult.success(itemStack, world.isClient());
            }
        }

        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        boolean used = false;
        List<ScrollEffect> effects = ScrollUtil.getScrollEffects(stack);

        //try to use it on the entity
        for (int i = 0; i < effects.size(); ++i) {
            used |= useScrollOnOtherIfPossible(user, entity, effects.get(i));
        }

        if (used) {
            onScrollUsed(user, stack);
            return ActionResult.SUCCESS;
        }

        //try to use it where the entity is
        for (int i = 0; i < effects.size(); ++i) {
            used |= useScrollAtIfPossible(user, entity.getPos(), effects.get(i));
        }

        if (used) {
            onScrollUsed(user, stack);
            return ActionResult.SUCCESS;
        }

        //try to use it on the user
        for (int i = 0; i < effects.size(); ++i) {
            used |= useScrollOnSelfIfPossible(user, effects.get(i));
        }

        if (used) {
            onScrollUsed(user, stack);
            return ActionResult.SUCCESS;
        }

        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        return super.getTooltipData(stack);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return ScrollUtil.getScroll(stack).finishTranslationKey(this.getTranslationKey() + ".effect.");
    }
    
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        ScrollUtil.buildTooltip(stack, tooltip);
    }
    
    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (this.isIn(group)) {
            Iterator<Scroll> scrollIter = ModRegistry.SCROLL.iterator();

            while (scrollIter.hasNext()) {
                Scroll scroll = scrollIter.next();
                if (scroll != Scrolls.EMPTY) {
                    stacks.add(ScrollUtil.setScroll(new ItemStack(this), scroll));
                }
            }
        }

    }

    private void onScrollUsed(PlayerEntity user, ItemStack itemStack) {
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        user.getItemCooldownManager().set(itemStack.getItem(), 40);
        if (!user.isCreative()) {
            itemStack.decrement(1);
        }
    }

    private boolean useScrollOnSelfIfPossible(PlayerEntity user, ScrollEffect effect) {
        if (effect.canUseOnSelf(user)) {
            if (user.world instanceof ServerWorld world) {
                effect.useOnSelf(user, world);
            }
            return true;
        }
        return false;
    }

    private boolean useScrollOnOtherIfPossible(PlayerEntity user, LivingEntity target, ScrollEffect effect) {
        if (effect.canUseOnOther(user, target)) {
            if (user.world instanceof ServerWorld world) {
                effect.useOnOther(user, target, world);
            }
            return true;
        }
        return false;
    }

    private boolean useScrollAtIfPossible(PlayerEntity user, Vec3d pos, ScrollEffect effect) {
        if (effect.canUseAt(user, pos)) {
            if (user.world instanceof ServerWorld world) {
                effect.useAt(user, pos, world);
            }
            return true;
        }
        return false;
    }
}
