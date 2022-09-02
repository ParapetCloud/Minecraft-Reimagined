package com.github.mcri.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BlockItemSettings {
    public enum Group {
        BUILDING_BLOCKS,
        DECORATIONS,
        REDSTONE,
        TRANSPORTATION,
        MISC,
        SEARCH,
        FOOD,
        TOOLS,
        COMBAT,
        BREWING,
        MATERIALS,
        HOTBAR,
        INVENTORY,
    }

    Group group();
}
