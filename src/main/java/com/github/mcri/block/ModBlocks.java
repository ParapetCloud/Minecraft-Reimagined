package com.github.mcri.block;

import com.github.mcri.utils.BlockItemSettings;
import com.github.mcri.utils.Registration;
import com.github.mcri.utils.BlockItemSettings.Group;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public final class ModBlocks {
    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final DirtSlab DIRT_SLAB = new DirtSlab(
        FabricBlockSettings.of(Material.SOIL).strength(0.5f).sounds(BlockSoundGroup.GRAVEL));

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final DirtyCobblestone DIRTY_COBBLESTONE = new DirtyCobblestone(
        FabricBlockSettings.of(Material.STONE).strength(1.0f).sounds(BlockSoundGroup.STONE).requiresTool());

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final DirtyCobblestoneSlab DIRTY_COBBLESTONE_SLAB = new DirtyCobblestoneSlab(
        FabricBlockSettings.of(Material.STONE).strength(2.0f).sounds(BlockSoundGroup.STONE).requiresTool());

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final DirtyCobblestoneStairs DIRTY_COBBLESTONE_STAIRS = new DirtyCobblestoneStairs(
        DIRTY_COBBLESTONE.getDefaultState(),
        FabricBlockSettings.of(Material.STONE).strength(2.0f).sounds(BlockSoundGroup.STONE).requiresTool());

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final DirtyCobblestoneWall DIRTY_COBBLESTONE_WALL = new DirtyCobblestoneWall(
        FabricBlockSettings.copy(DIRTY_COBBLESTONE).requiresTool());

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final SnowyCobblestone SNOWY_COBBLESTONE = new SnowyCobblestone(
        FabricBlockSettings.of(Material.STONE).strength(2.0f).sounds(BlockSoundGroup.STONE).requiresTool());

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final SnowyCobblestoneSlab SNOWY_COBBLESTONE_SLAB = new SnowyCobblestoneSlab(
        FabricBlockSettings.of(Material.STONE).strength(2.0f).sounds(BlockSoundGroup.STONE).requiresTool());

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final SnowyCobblestoneStairs SNOWY_COBBLESTONE_STAIRS = new SnowyCobblestoneStairs(
        SNOWY_COBBLESTONE.getDefaultState(),
        FabricBlockSettings.of(Material.STONE).strength(2.0f).sounds(BlockSoundGroup.STONE).requiresTool());

    @BlockItemSettings(group = Group.BUILDING_BLOCKS)
    public static final SnowyCobblestoneWall SNOWY_COBBLESTONE_WALL = new SnowyCobblestoneWall(
        FabricBlockSettings.copy(DIRTY_COBBLESTONE).requiresTool());

    /**
     * Registers all the effects that have been pre-registered
     */
    public static void registerAll() {
        Registration.ReflectAllForRegistration(ModBlocks.class, Block.class, Registry.BLOCK);
    }
}
