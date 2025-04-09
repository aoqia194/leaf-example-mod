package com.example.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import zombie.Lua.LuaEventManager;
import zombie.network.GameServer;

import static com.example.ExampleMod.LOGGER;

@Mixin(LuaEventManager.class)
public class ExampleMixin {
    @Inject(method = "triggerEvent", at = @At("HEAD"))
    private static void triggerEvent(String event, CallbackInfo ci) {
        // Only capture events if executed on the dedicated server.
        // if (!GameServer.bServer) {
        //     return;
        // }

        // Filter out spammy events.
        if (!event.equals("OnRenderTick") && !event.equals("OnPreUIDraw") &&
            !event.equals("OnPostUIDraw") && !event.equals("OnPostRender")) {
            LOGGER.println("[ExampleMixin] triggerEvent called with args (%s).", event);
        }
    }
}
