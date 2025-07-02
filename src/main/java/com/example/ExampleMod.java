package com.example;

import dev.aoqia.leaf.api.ModInitializer;

import zombie.debug.DebugLog;
import zombie.debug.DebugLogStream;

public class ExampleMod implements ModInitializer {
    public static final String MOD_ID = "modid";
    public static final DebugLogStream LOGGER = DebugLog.Mod;

    public static final String GAME_VERSION_CHANGED_EVENT = "GameVersionChanged";

    @Override
    public void onInitialize() {
        LOGGER.debugln("[%s] %s", MOD_ID, "Hello Leaf World!!! >w<");
    }
}
