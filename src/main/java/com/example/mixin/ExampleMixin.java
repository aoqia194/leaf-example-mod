package com.example.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zombie.Lua.LuaEventManager;

@Mixin(LuaEventManager.class)
public class ExampleMixin {
    @Inject(method = "triggerEvent", at = @At("HEAD"))
    private static void triggerEvent(String event, CallbackInfo ci) {
        if (!event.equals("OnRenderTick") && !event.equals("OnPreUIDraw") &&
            !event.equals("OnPostUIDraw")) {
            System.out.printf("ExampleMixin -> triggerEvent(%s) called.%n", event);
        }
    }
}
