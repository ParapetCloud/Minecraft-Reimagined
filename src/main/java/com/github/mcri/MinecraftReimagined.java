package com.github.mcri;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mcri.block.*;
import com.github.mcri.effect.*;
import com.github.mcri.effect.scroll.ScrollEffects;
import com.github.mcri.enchantments.ModEnchantments;
import com.github.mcri.items.ModItems;
import com.github.mcri.scroll.Scrolls;

public class MinecraftReimagined implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "mcri";
	
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Initializing...");

		ModItems.registerAll();
		ModEnchantments.registerAll();
		ModStatusEffects.registerAll();
		ModBlocks.registerAll();
		ScrollEffects.registerAll();
		Scrolls.registerAll();
	}
}
