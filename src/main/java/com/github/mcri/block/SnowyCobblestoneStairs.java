package com.github.mcri.block;

import com.github.mcri.MinecraftReimagined;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SnowyCobblestoneStairs extends StairsBlock {
        public static final SnowyCobblestoneStairs SNOWY_COBBLESTONE_STAIRS = new SnowyCobblestoneStairs(
                        SnowyCobblestone.SNOWY_COBBLESTONE.getDefaultState(),
                        FabricBlockSettings.of(Material.STONE).strength(2.0f).sounds(BlockSoundGroup.STONE)
                                        .requiresTool());

        public static void Register() {
                Registry.register(Registry.BLOCK,
                                new Identifier(MinecraftReimagined.MOD_ID, "snowy_cobblestone_stairs"),
                                SNOWY_COBBLESTONE_STAIRS);
                Registry.register(Registry.ITEM, new Identifier(MinecraftReimagined.MOD_ID, "snowy_cobblestone_stairs"),
                                new BlockItem(SNOWY_COBBLESTONE_STAIRS,
                                                new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        }

        public SnowyCobblestoneStairs(BlockState baseBlockState, Settings settings) {
                super(baseBlockState, settings);
        }
}
