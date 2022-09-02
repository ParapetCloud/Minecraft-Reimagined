package com.github.mcri.block;

import com.github.mcri.MinecraftReimagined;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class DirtSlabBlock extends SlabBlock {
    public static final DirtSlabBlock DIRT_SLAB_BLOCK = new DirtSlabBlock(
            FabricBlockSettings.of(Material.SOIL).strength(0.5f).sounds(BlockSoundGroup.GRAVEL));

    public static void Register() {
        Registry.register(Registry.BLOCK, new Identifier(MinecraftReimagined.MOD_ID, "dirt_slab_block"),
                DIRT_SLAB_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MinecraftReimagined.MOD_ID, "dirt_slab_block"),
                new BlockItem(DIRT_SLAB_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }

    public DirtSlabBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.getDefaultState().with(TYPE, SlabType.BOTTOM)).with(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        SlabType slabType = state.get(TYPE);
        switch (slabType) {
            case DOUBLE: {
                return VoxelShapes.fullCube();
            }
            case TOP: {
                return TOP_SHAPE;
            }
        }
        return BOTTOM_SHAPE;
    }

    private static BlockSoundGroup modifyBlockSoundGroup(BlockSoundGroup soundGroup) {
        return BlockSoundGroup.GRASS;
    }
}
