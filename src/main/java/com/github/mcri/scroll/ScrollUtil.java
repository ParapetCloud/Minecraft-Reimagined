package com.github.mcri.scroll;

import com.github.mcri.ModRegistry;
import com.github.mcri.effect.scroll.ScrollEffect;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class ScrollUtil {
    public static final String CUSTOM_SCROLL_EFFECTS_KEY = "CustomScrollEffects";
    public static final String CUSTOM_SCROLL_COLOR_KEY = "CustomScrollColor";
    public static final String SCROLL_KEY = "Scroll";
    private static final int DEFAULT_COLOR = 0xC60000;
    private static final Text NONE_TEXT;

    public static List<ScrollEffect> getScrollEffects(ItemStack stack) {
        return getScrollEffects(stack.getNbt());
    }

    public static List<ScrollEffect> getScrollEffects(Scroll scroll, Collection<ScrollEffect> custom) {
        List<ScrollEffect> list = Lists.newArrayList();
        list.addAll(scroll.getEffects());
        list.addAll(custom);
        return list;
    }

    public static List<ScrollEffect> getScrollEffects(@Nullable NbtCompound nbt) {
        List<ScrollEffect> list = Lists.newArrayList();
        list.addAll(getScroll(nbt).getEffects());
        getCustomScrollEffects(nbt, list);
        return list;
    }

    public static List<ScrollEffect> getCustomScrollEffects(ItemStack stack) {
        return getCustomScrollEffects(stack.getNbt());
    }

    public static List<ScrollEffect> getCustomScrollEffects(@Nullable NbtCompound nbt) {
        List<ScrollEffect> list = Lists.newArrayList();
        getCustomScrollEffects(nbt, list);
        return list;
    }

    public static void getCustomScrollEffects(@Nullable NbtCompound nbt, List<ScrollEffect> list) {
        if (nbt != null && nbt.contains(CUSTOM_SCROLL_EFFECTS_KEY, NbtElement.LIST_TYPE)) {
            NbtList nbtList = nbt.getList(CUSTOM_SCROLL_EFFECTS_KEY, NbtElement.COMPOUND_TYPE);

            for (int i = 0; i < nbtList.size(); ++i) {
                NbtCompound nbtCompound = nbtList.getCompound(i);
                ScrollEffect scrollEffect = ScrollEffect.fromNbt(nbtCompound);
                if (scrollEffect != null) {
                    list.add(scrollEffect);
                }
            }
        }

    }

    public static int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        if (nbtCompound != null && nbtCompound.contains(CUSTOM_SCROLL_COLOR_KEY, NbtElement.NUMBER_TYPE)) {
            return nbtCompound.getInt(CUSTOM_SCROLL_COLOR_KEY);
        }
        else {
            return getScroll(stack) == Scrolls.EMPTY ? DEFAULT_COLOR : getColor((Collection<ScrollEffect>) getScrollEffects(stack));
        }
    }

    public static int getColor(Scroll scroll) {
        return scroll == Scrolls.EMPTY ? DEFAULT_COLOR : getColor((Collection<ScrollEffect>) scroll.getEffects());
    }

    public static int getColor(Collection<ScrollEffect> effects) {
        if (effects.isEmpty()) {
            return DEFAULT_COLOR;
        }
        else {
            float r = 0.0F;
            float g = 0.0F;
            float b = 0.0F;
            int effectCount = 0;
            Iterator<ScrollEffect> effect = effects.iterator();

            while (effect.hasNext()) {
                ScrollEffect scrollEffect = (ScrollEffect) effect.next();
                int effectColor = scrollEffect.getColor();
                r += (float) (effectColor >> 16 & 255) / 255.0F;
                g += (float) (effectColor >> 8 & 255) / 255.0F;
                b += (float) (effectColor >> 0 & 255) / 255.0F;
                ++effectCount;
            }

            if (effectCount == 0) {
                return DEFAULT_COLOR;
            }
            else {
                r = r / (float) effectCount * 255.0F;
                g = g / (float) effectCount * 255.0F;
                b = b / (float) effectCount * 255.0F;
                return (int) r << 16 | (int) g << 8 | (int) b;
            }
        }
    }

    public static Scroll getScroll(ItemStack stack) {
        return getScroll(stack.getNbt());
    }

    public static Scroll getScroll(@Nullable NbtCompound compound) {
        return compound == null ? Scrolls.EMPTY : Scroll.byId(compound.getString(SCROLL_KEY));
    }

    public static ItemStack setScroll(ItemStack stack, Scroll scroll) {
        Identifier identifier = ModRegistry.SCROLL.getId(scroll);
        if (scroll == Scrolls.EMPTY) {
            stack.removeSubNbt(SCROLL_KEY);
        }
        else {
            stack.getOrCreateNbt().putString(SCROLL_KEY, identifier.toString());
        }

        return stack;
    }

    public static ItemStack setCustomScrollEffects(ItemStack stack, Collection<ScrollEffect> effects) {
        if (effects.isEmpty()) {
            return stack;
        }
        else {
            NbtCompound nbtCompound = stack.getOrCreateNbt();
            NbtList nbtList = nbtCompound.getList(CUSTOM_SCROLL_EFFECTS_KEY, NbtElement.LIST_TYPE);

            for (Iterator<ScrollEffect> effect = effects.iterator(); effect.hasNext();) {
                ScrollEffect scrollEffect = (ScrollEffect) effect.next();
                nbtList.add(scrollEffect.writeNbt(new NbtCompound()));
            }

            nbtCompound.put(CUSTOM_SCROLL_EFFECTS_KEY, nbtList);
            return stack;
        }
    }

    public static void buildTooltip(ItemStack stack, List<Text> toolTip) {
        
        List<ScrollEffect> effects = getScrollEffects(stack);

        if (effects.isEmpty()) {
            toolTip.add(NONE_TEXT);
        }
        else {
            for(Iterator<ScrollEffect> iter = effects.iterator(); iter.hasNext();) {
                ScrollEffect effect = iter.next();
                Identifier id = ModRegistry.SCROLL_EFFECT.getId(effect);
                toolTip.add(Text.translatable(id.toTranslationKey("scrollEffect")));
            }
        }
    }

    static {
        NONE_TEXT = Text.translatable("scrollEffect.none").formatted(Formatting.GRAY);
    }
}
