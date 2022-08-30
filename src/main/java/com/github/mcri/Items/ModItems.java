package com.github.mcri.Items;

import com.github.mcri.MinecraftReimagined;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.TridentItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems extends TridentItem {

    public static final Item PITCHFORK = new Item(new Item.Settings().group(ItemGroup.TOOLS));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(MinecraftReimagined.MOD_ID, "pitchfork"), PITCHFORK);
    }

    public ModItems (Item.Settings settings) {
        super(settings);
    }
}