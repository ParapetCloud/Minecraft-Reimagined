package com.github.mcri.block;

import com.github.mcri.utils.BlockItemSettings;
import com.github.mcri.utils.Registration;
import com.github.mcri.utils.BlockItemSettings.Group;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public final class ModBlocks {
    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final SlabBlock DIRT_SLAB = new SlabBlock(
            FabricBlockSettings.of(Material.SOIL).strength(0.5f).sounds(BlockSoundGroup.GRAVEL));

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final Block DIRTY_COBBLESTONE = new Block(
            FabricBlockSettings.of(Material.STONE).strength(1.0f).sounds(BlockSoundGroup.STONE).requiresTool());

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final SlabBlock DIRTY_COBBLESTONE_SLAB = new SlabBlock(
            FabricBlockSettings.copy(DIRTY_COBBLESTONE));

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final StairsBlock DIRTY_COBBLESTONE_STAIRS = new StairsBlock(
            DIRTY_COBBLESTONE.getDefaultState(),
            FabricBlockSettings.copy(DIRTY_COBBLESTONE));

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final WallBlock DIRTY_COBBLESTONE_WALL = new WallBlock(
            FabricBlockSettings.copy(DIRTY_COBBLESTONE).requiresTool());

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final Block SNOWY_COBBLESTONE = new Block(
            FabricBlockSettings.of(Material.STONE).strength(2.0f).sounds(BlockSoundGroup.STONE).requiresTool());

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final SlabBlock SNOWY_COBBLESTONE_SLAB = new SlabBlock(
            FabricBlockSettings.copy(SNOWY_COBBLESTONE));

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final StairsBlock SNOWY_COBBLESTONE_STAIRS = new StairsBlock(
            SNOWY_COBBLESTONE.getDefaultState(),
            FabricBlockSettings.copy(SNOWY_COBBLESTONE));

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final WallBlock SNOWY_COBBLESTONE_WALL = new WallBlock(
            FabricBlockSettings.copy(SNOWY_COBBLESTONE));

    /**
     * Registers all the effects that have been pre-registered
     */
    public static void registerAll() {
        Registration.ReflectAllForRegistration(ModBlocks.class, Block.class, Registry.BLOCK);
    }
}
