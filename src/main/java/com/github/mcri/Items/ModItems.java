package com.github.mcri.items;

import com.github.mcri.scroll.ScrollUtil;
import com.github.mcri.utils.Registration;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public final class ModItems {
    public static final PitchForkItem PITCHFORK = new PitchForkItem(new Item.Settings().group(ItemGroup.TOOLS));
    public static final ScrollItem SCROLL = new ScrollItem(new Item.Settings().group(ModItemGroups.SCROLLS));

    /**
     * Registers all the items that have been pre-registered
     */
    public static void registerAll() {
        Registration.ReflectAllForRegistration(ModItems.class, Item.class, Registry.ITEM);

        ColorProviderRegistry.ITEM.register(new ItemColorProvider() {
            public int getColor(net.minecraft.item.ItemStack stack, int tintIndex) {
                if (tintIndex == 0) {
                    return ScrollUtil.getColor(stack);
                }
                return 0xFFFFFF;
            };
        }, SCROLL);
    }
}