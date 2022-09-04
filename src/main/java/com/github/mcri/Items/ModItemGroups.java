package com.github.mcri.items;

import com.github.mcri.MinecraftReimagined;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public final class ModItemGroups {
    public static final ItemGroup SCROLLS = FabricItemGroupBuilder.create(new Identifier(MinecraftReimagined.MOD_ID, "scrolls"))
        .icon(() -> new ItemStack(ModItems.SCROLL))
        .build();

}
