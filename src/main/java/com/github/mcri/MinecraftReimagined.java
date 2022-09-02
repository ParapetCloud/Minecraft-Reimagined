package com.github.mcri;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mcri.Enchantments.*;
import com.github.mcri.StatusEffects.*;
import com.github.mcri.block.*;
import com.github.mcri.Items.ModItems;

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

		ModItems.registerItems();

		LOGGER.info("Initializing...");
		// Enchantments
		FrostbiteEnchant.Register();
		LifeStealEnchant.Register();
		PoisonAspectEnchant.Register();
		WitheringEnchant.Register();
		MountedEnchant.Register();
		LevitationEnchant.Register();
		BaneofAquaticsEnchant.Register();
		BaneofEnderEnchant.Register();
		BaneofSwinesEnchant.Register();
		BaneofVillagersEnchant.Register();
		BaneofIllagersEnchant.Register();
		ClearSkiesEnchant.Register();
		NoSkiesEnchant.Register();
		DarkSkiesEnchant.Register();
		StormySkiesEnchant.Register();

		// Status effects
		FrozenEffect.Register();
		BaneofEnderEffect.Register();

		// Blocks
		DirtSlab.Register();
		DirtyCobblestone.Register();
		SnowyCobblestone.Register();
		DirtyCobblestoneSlab.Register();
		SnowyCobblestoneSlab.Register();
		DirtyCobblestoneStairs.Register();
		SnowyCobblestoneStairs.Register();
		DirtyCobblestoneWall.Register();
		SnowyCobblestoneWall.Register();
	}
}
