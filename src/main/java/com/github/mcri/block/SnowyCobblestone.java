package com.github.mcri.block;

import com.github.mcri.MinecraftReimagined;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SnowyCobblestone extends Block {
    public static final SnowyCobblestone SNOWY_COBBLESTONE = new SnowyCobblestone(
            FabricBlockSettings.of(Material.STONE).strength(2.0f).sounds(BlockSoundGroup.STONE).requiresTool());

    public static void Register() {
        Registry.register(Registry.BLOCK, new Identifier(MinecraftReimagined.MOD_ID, "snowy_cobblestone"),
                SNOWY_COBBLESTONE);
        Registry.register(Registry.ITEM, new Identifier(MinecraftReimagined.MOD_ID, "snowy_cobblestone"),
                new BlockItem(SNOWY_COBBLESTONE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }

    public SnowyCobblestone(Settings settings) {
        super(settings);
    }
}
