package de.nwex.elytrafix.mixin;

import de.nwex.elytrafix.ElytraFix;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.util.PacketByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientCommandC2SPacket.class)
public class MixinClientCommandC2SPacket
{
    @Shadow private int entityId;
    @Shadow private ClientCommandC2SPacket.Mode mode;

    @Inject(method = "write", at = @At("HEAD"))
    public void write(PacketByteBuf packetByteBuf, CallbackInfo ci)
    {
        if(entityId == ElytraFix.playerEntityId && mode.equals(ClientCommandC2SPacket.Mode.START_FALL_FLYING))
        {
            ElytraFix.shouldElytraFly = true;
        }
    }
}
