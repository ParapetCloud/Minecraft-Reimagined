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

public class SnowyCobblestoneWall extends WallBlock {
    public static final SnowyCobblestoneWall SNOWY_COBBLESTONE_WALL = new SnowyCobblestoneWall(
            FabricBlockSettings.copy(DirtyCobblestone.DIRTY_COBBLESTONE).requiresTool());

    public static void Register() {
        Registry.register(Registry.BLOCK,
                new Identifier(MinecraftReimagined.MOD_ID, "snowy_cobblestone_wall"),
                SNOWY_COBBLESTONE_WALL);
        Registry.register(Registry.ITEM, new Identifier(MinecraftReimagined.MOD_ID, "snowy_cobblestone_wall"),
                new BlockItem(SNOWY_COBBLESTONE_WALL,
                        new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }

    public SnowyCobblestoneWall(AbstractBlock.Settings settings) {
        super(settings);
    }
}
