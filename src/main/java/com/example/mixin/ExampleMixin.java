package com.example.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import zombie.core.Core;

@Mixin(Core.class)
public class ExampleMixin {
    @Inject(method = "getDebug", at = @At("HEAD"), cancellable = true)
    private void getDebug(CallbackInfoReturnable<Boolean> cir) {
        System.out.println("ExampleMixin -> Forcing getDebug().");
        cir.setReturnValue(true);
    }
}
