package dev.pvphud.mixin;

import dev.pvphud.tracker.CombatTracker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow private int attackCooldown;

    /** Air swings (crosshair MISS). Entity swings are handled in the interaction-manager mixin. */
    @Inject(method = "doAttack", at = @At("HEAD"))
    private void pvphud$onSwing(CallbackInfoReturnable<Boolean> cir) {
        MinecraftClient mc = (MinecraftClient) (Object) this;
        if (attackCooldown > 0 || mc.player == null || mc.crosshairTarget == null) return;
        if (mc.crosshairTarget.getType() == HitResult.Type.MISS) CombatTracker.I.onMiss(mc.player);
    }
}
