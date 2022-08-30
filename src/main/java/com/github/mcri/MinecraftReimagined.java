package com.github.mcri;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mcri.Enchantments.*;
import com.github.mcri.StatusEffects.*;

public class MinecraftReimagined implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("mcri");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Initializing...");
		// Enchantments
		FrostbiteEnchant.Register();
		LifeStealEnchant.Register();
		PoisonEnchant.Register();
		WitheringEnchant.Register();
		MountedEnchant.Register();
		LevitationEnchant.Register();
		BaneofAquaticsEnchant.Register();
		BaneofEnderEnchant.Register();
		BaneofSwinesEnchant.Register();
		BaneofVillagersEnchant.Register();
		BaneofIllagersEnchant.Register();

		// Status effects
		FrozenEffect.Register();
		BaneofEnderEffect.Register();
	}
}
