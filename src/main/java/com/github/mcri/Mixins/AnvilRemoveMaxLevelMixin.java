package com.github.mcri.Mixins;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilRemoveMaxLevelMixin extends ForgingScreenHandler {
    public AnvilRemoveMaxLevelMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Accessor
    abstract Property getLevelCost();

    @Accessor
    abstract String getNewItemName();

    @Accessor("repairItemUsage")
    abstract void setRepairItemUsage(int repairItemUsage);

    @Inject(method = { "updateResult" }, at = @At(value = "HEAD"), cancellable = true)
    public void updateResultNoMaxLevel(CallbackInfo cir) {
        ItemStack inputStackLeft = this.input.getStack(0);
        this.getLevelCost().set(1);
        int i = 0;
        int baseRepairCost = 0;
        if (inputStackLeft.isEmpty()) {
            this.output.setStack(0, ItemStack.EMPTY);
            this.getLevelCost().set(0);
        }
        else {
            ItemStack inputStackLeftCopy = inputStackLeft.copy();
            ItemStack inputStackRight = this.input.getStack(1);
            Map<Enchantment, Integer> leftEnchantLevels = EnchantmentHelper.get(inputStackLeftCopy);
            baseRepairCost += inputStackLeft.getRepairCost() + (inputStackRight.isEmpty() ? 0 : inputStackRight.getRepairCost());
            this.setRepairItemUsage(0);
            if (!inputStackRight.isEmpty()) {
                boolean isEnchantedBook = inputStackRight.isOf(Items.ENCHANTED_BOOK) && !EnchantedBookItem.getEnchantmentNbt(inputStackRight).isEmpty();
                int l;
                int m;
                int n;
                if (inputStackLeftCopy.isDamageable() && inputStackLeftCopy.getItem().canRepair(inputStackLeft, inputStackRight)) {
                    // repair
                    l = Math.min(inputStackLeftCopy.getDamage(), inputStackLeftCopy.getMaxDamage() / 4);
                    if (l <= 0) {
                        this.output.setStack(0, ItemStack.EMPTY);
                        this.getLevelCost().set(0);
                        cir.cancel();
                        return;
                    }

                    for (m = 0; l > 0 && m < inputStackRight.getCount(); ++m) {
                        n = inputStackLeftCopy.getDamage() - l;
                        inputStackLeftCopy.setDamage(n);
                        ++i;
                        l = Math.min(inputStackLeftCopy.getDamage(), inputStackLeftCopy.getMaxDamage() / 4);
                    }

                    this.setRepairItemUsage(m);
                }
                else {
                    // enchant
                    if (!isEnchantedBook && (!inputStackLeftCopy.isOf(inputStackRight.getItem()) || !inputStackLeftCopy.isDamageable())) {
                        this.output.setStack(0, ItemStack.EMPTY);
                        this.getLevelCost().set(0);
                        cir.cancel();
                        return;
                    }

                    if (inputStackLeftCopy.isDamageable() && !isEnchantedBook) {
                        l = inputStackLeft.getMaxDamage() - inputStackLeft.getDamage();
                        m = inputStackRight.getMaxDamage() - inputStackRight.getDamage();
                        n = m + inputStackLeftCopy.getMaxDamage() * 12 / 100;
                        int o = l + n;
                        int p = inputStackLeftCopy.getMaxDamage() - o;
                        if (p < 0) {
                            p = 0;
                        }

                        if (p < inputStackLeftCopy.getDamage()) {
                            inputStackLeftCopy.setDamage(p);
                            i += 2;
                        }
                    }

                    Map<Enchantment, Integer> rightEnchantLevels = EnchantmentHelper.get(inputStackRight);
                    boolean hasCombinableEnchant = false;
                    boolean hasUncombinableEnchant = false;
                    Iterator enchantIter = rightEnchantLevels.keySet().iterator();

                    label155: while (true) {
                        Enchantment enchantment;

                        //get the next (not null) enchantment or exit when we have none left
                        do {
                            if (!enchantIter.hasNext()) {
                                if (hasUncombinableEnchant && !hasCombinableEnchant) {
                                    // clear the output if there are only non-combinable enchantments
                                    this.output.setStack(0, ItemStack.EMPTY);
                                    this.getLevelCost().set(0);
                                    cir.cancel();
                                    return;
                                }
                                break label155;
                            }

                            enchantment = (Enchantment) enchantIter.next();
                        } while (enchantment == null);

                        int leftLevel = (Integer) leftEnchantLevels.getOrDefault(enchantment, 0);
                        int rightLevel = (Integer) rightEnchantLevels.get(enchantment);
                        rightLevel = leftLevel == rightLevel ? rightLevel + 1 : Math.max(rightLevel, leftLevel);
                        boolean canCombine = enchantment.isAcceptableItem(inputStackLeft);
                        if (this.player.getAbilities().creativeMode || inputStackLeft.isOf(Items.ENCHANTED_BOOK)) {
                            canCombine = true;
                        }

                        Iterator var17 = leftEnchantLevels.keySet().iterator();

                        while (var17.hasNext()) {
                            Enchantment enchantment2 = (Enchantment) var17.next();
                            if (enchantment2 != enchantment && !enchantment.canCombine(enchantment2)) {
                                canCombine = false;
                                ++i;
                            }
                        }

                        if (!canCombine) {
                            hasUncombinableEnchant = true;
                        }
                        else {
                            hasCombinableEnchant = true;
                            if (rightLevel > enchantment.getMaxLevel()) {
                                rightLevel = enchantment.getMaxLevel();
                            }

                            leftEnchantLevels.put(enchantment, rightLevel);
                            int s = 0;
                            switch (enchantment.getRarity()) {
                                case COMMON:
                                    s = 1;
                                    break;
                                case UNCOMMON:
                                    s = 2;
                                    break;
                                case RARE:
                                    s = 4;
                                    break;
                                case VERY_RARE:
                                    s = 8;
                            }

                            if (isEnchantedBook) {
                                s = Math.max(1, s / 2);
                            }

                            i += s * rightLevel;
                            if (inputStackLeft.getCount() > 1) {
                                i = 40;
                            }
                        }
                    }
                }
            }

            int nameChangeCost = 0;
            if (StringUtils.isBlank(this.getNewItemName())) {
                if (inputStackLeft.hasCustomName()) {
                    nameChangeCost = 1;
                    i += nameChangeCost;
                    inputStackLeftCopy.removeCustomName();
                }
            }
            else if (!this.getNewItemName().equals(inputStackLeft.getName().getString())) {
                nameChangeCost = 1;
                i += nameChangeCost;
                inputStackLeftCopy.setCustomName(Text.literal(this.getNewItemName()));
            }

            this.getLevelCost().set(inputStackRight.isEmpty() ? i : baseRepairCost + i);
            if (i <= 0) {
                inputStackLeftCopy = ItemStack.EMPTY;
            }

            if (nameChangeCost == i && nameChangeCost > 0 && this.getLevelCost().get() >= 40) {
                this.getLevelCost().set(39);
            }

            // here lies the one thing I had to do for this stupid function.
            // may it rest in eternal suffering

            if (!inputStackLeftCopy.isEmpty()) {
                int t = inputStackLeftCopy.getRepairCost();
                if (!inputStackRight.isEmpty() && t < inputStackRight.getRepairCost()) {
                    t = inputStackRight.getRepairCost();
                }

                if (nameChangeCost != i || nameChangeCost == 0) {
                    t = AnvilScreenHandler.getNextCost(t);
                }

                inputStackLeftCopy.setRepairCost(t);
                EnchantmentHelper.set(leftEnchantLevels, inputStackLeftCopy);
            }

            this.output.setStack(0, inputStackLeftCopy);
            ((AnvilScreenHandler) (Object) this).sendContentUpdates();
            cir.cancel();
        }
    }
}
