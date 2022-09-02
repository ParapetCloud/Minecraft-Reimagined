package com.github.mcri.block;

import com.github.mcri.MinecraftReimagined;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DirtyCobblestoneWall extends WallBlock {
    public static final DirtyCobblestoneWall DIRTY_COBBLESTONE_WALL = new DirtyCobblestoneWall(
            FabricBlockSettings.copy(DirtyCobblestone.DIRTY_COBBLESTONE).requiresTool());

    public static void Register() {
        Registry.register(Registry.BLOCK,
                new Identifier(MinecraftReimagined.MOD_ID, "dirty_cobblestone_wall"),
                DIRTY_COBBLESTONE_WALL);
        Registry.register(Registry.ITEM, new Identifier(MinecraftReimagined.MOD_ID, "dirty_cobblestone_wall"),
                new BlockItem(DIRTY_COBBLESTONE_WALL,
                        new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }

    public DirtyCobblestoneWall(AbstractBlock.Settings settings) {
        super(settings);
    }
}
