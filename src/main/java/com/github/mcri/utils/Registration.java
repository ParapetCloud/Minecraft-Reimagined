package com.github.mcri.utils;

import java.lang.reflect.Field;

import com.github.mcri.MinecraftReimagined;
import com.github.mcri.utils.BlockItemSettings.Group;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class Registration {

    @SuppressWarnings("unchecked")
    public static <T> void ReflectAllForRegistration(Class<?> scan, Class<T> inheritsFrom, Registry<T> registry) {
        // get all the fields in this class
        Field[] fields = scan.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            try {
                Class<?> fieldType = fields[i].getType();
                // check that they're static and an item
                if (!fields[i].canAccess(null) || !inheritsFrom.isAssignableFrom(fieldType)) {
                    continue;
                }

                MinecraftReimagined.LOGGER.debug("registering " + inheritsFrom.getSimpleName() + " '" + fields[i].getName() + "'");

                T instance = (T) fields[i].get(null);

                if (instance == null) {
                    instance = (T) fieldType.getDeclaredConstructor().newInstance();
                    fields[i].set(null, instance);
                }

                // set them to an instance of an object and get ready for delayed registering
                Identifier id = new Identifier(MinecraftReimagined.MOD_ID, fields[i].getName().toLowerCase());
                Registry.register(registry, id, instance);

                if (Block.class.isAssignableFrom(inheritsFrom)) {
                    // for blocks we need to register the items too!
                    BlockItemSettings settings = fields[i].getAnnotation(BlockItemSettings.class);

                    if (settings == null) {
                        MinecraftReimagined.LOGGER.error("Failed to create item for block '" + fields[i].getName() + "'. No blockItemSettings annotation was found");
                        continue;
                    }

                    Registry.register(Registry.ITEM, id, new BlockItem((Block)instance, new FabricItemSettings().group(getGroupFor(settings.group()))));
                }
            }
            catch (Exception ex) {
                MinecraftReimagined.LOGGER.error("Failed to register " + inheritsFrom.getName() + " '" + fields[i].getName().toLowerCase() + "'\n" + ex.toString());
            }
        }
    }

    public static ItemGroup getGroupFor(Group group) {
        switch (group) {
            case BREWING:
                return ItemGroup.BREWING;
            case BUILDING_BLOCKS:
                return ItemGroup.BUILDING_BLOCKS;
            case COMBAT:
                return ItemGroup.COMBAT;
            case DECORATIONS:
                return ItemGroup.DECORATIONS;
            case FOOD:
                return ItemGroup.FOOD;
            case HOTBAR:
                return ItemGroup.HOTBAR;
            case INVENTORY:
                return ItemGroup.INVENTORY;
            case MATERIALS:
                return ItemGroup.MATERIALS;
            case REDSTONE:
                return ItemGroup.REDSTONE;
            case SEARCH:
                return ItemGroup.SEARCH;
            case TOOLS:
                return ItemGroup.TOOLS;
            case TRANSPORTATION:
                return ItemGroup.TRANSPORTATION;
            case MISC:
            default:
                return ItemGroup.MISC;
        }
    }
}
