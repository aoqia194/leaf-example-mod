package com.example.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import zombie.core.Core;

import static com.example.ExampleMod.LOGGER;

@Mixin(Core.class)
public class ExampleMixin {
    @Inject(method = "getVersion", at = @At("RETURN"), cancellable = true)
    private void getVersion(CallbackInfoReturnable<String> cir) {
        LOGGER.println("Changing the internal game version.");
        cir.setReturnValue("43.123.69");
    }
}
