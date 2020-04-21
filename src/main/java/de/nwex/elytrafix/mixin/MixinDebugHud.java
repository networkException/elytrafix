package de.nwex.elytrafix.mixin;

import de.nwex.elytrafix.ElytraFix;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(DebugHud.class)
public class MixinDebugHud
{
    @Inject(method = "getLeftText", at = @At("RETURN"), cancellable = true)
    private void getLeftText(CallbackInfoReturnable<List<String>> ci)
    {
        List<String> leftText = ci.getReturnValue();

        leftText.add((ElytraFix.active ? "ElytraFix active " : "ElytraFix inactive ") + (ElytraFix.shouldElytraFly ? "(fallFlying)" : "(onGround)"));

        ci.setReturnValue(leftText);
    }
}
