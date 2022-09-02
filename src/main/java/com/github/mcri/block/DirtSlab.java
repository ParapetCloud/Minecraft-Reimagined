package com.github.mcri.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;

public class DirtSlab extends SlabBlock {
    public DirtSlab(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState) ((BlockState) this.getDefaultState().with(TYPE, SlabType.BOTTOM)).with(WATERLOGGED, false));
    }
}
