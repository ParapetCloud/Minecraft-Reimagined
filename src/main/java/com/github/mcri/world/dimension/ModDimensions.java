package com.github.mcri.world.dimension;

import com.github.mcri.MinecraftReimagined;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensions {
    public static final RegistryKey<World> MCRIDIM_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY,
    new Identifier(MinecraftReimagined.MOD_ID, "mcridim"));
    public static final RegistryKey<DimensionType> MCRIDIM_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY, MCRIDIM_DIMENSION_KEY.getValue());

    public static void register() {
        MinecraftReimagined.LOGGER.debug("Registering ModDimensions for " + MinecraftReimagined.MOD_ID);
    }
}
