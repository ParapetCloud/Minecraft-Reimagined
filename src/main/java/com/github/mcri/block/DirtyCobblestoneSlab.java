package com.github.mcri.block;

import com.github.mcri.MinecraftReimagined;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DirtyCobblestoneSlab extends SlabBlock {
    public static final DirtyCobblestoneSlab DIRTY_COBBLESTONE_SLAB = new DirtyCobblestoneSlab(
            FabricBlockSettings.of(Material.STONE).strength(2.0f).sounds(BlockSoundGroup.STONE).requiresTool());

    public static void Register() {
        Registry.register(Registry.BLOCK, new Identifier(MinecraftReimagined.MOD_ID, "dirty_cobblestone_slab"),
                DIRTY_COBBLESTONE_SLAB);
        Registry.register(Registry.ITEM, new Identifier(MinecraftReimagined.MOD_ID, "dirty_cobblestone_slab"),
                new BlockItem(DIRTY_COBBLESTONE_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }

    public DirtyCobblestoneSlab(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState) ((BlockState) this.getDefaultState().with(TYPE, SlabType.BOTTOM))
                .with(WATERLOGGED, false));
    }
}
