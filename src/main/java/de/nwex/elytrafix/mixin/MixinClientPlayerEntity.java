package de.nwex.elytrafix.mixin;

import com.mojang.brigadier.StringReader;
import de.nwex.elytrafix.ElytraFix;
import de.nwex.elytrafix.command.ClientCommandManager;
import de.nwex.elytrafix.interfaces.IEntity;
import de.nwex.elytrafix.interfaces.ILivingEntity;
import de.nwex.elytrafix.interfaces.IPlayerEntity;
import de.nwex.elytrafix.util.Chat;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity
{
    private final IEntity entity = ((IEntity) this);
    private final ILivingEntity livingEntity = ((ILivingEntity) this);
    private final IPlayerEntity playerEntity = ((IPlayerEntity) this);

    @Shadow
    public ClientPlayNetworkHandler networkHandler;

    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void tickMovement(CallbackInfo ci)
    {
        ItemStack itemStack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);

        //on ground and valid elytra equipped
        if(entity.onGround() && itemStack.getItem() == Items.ELYTRA && ElytraItem.isUsable(itemStack))
            ElytraFix.shouldElytraFly = false;

        if
        (
            !playerEntity.abilities.flying &&   //can't creative fly
                !entity.hasVehicle() &&             //isn't riding
                !livingEntity.isClimbing() &&       //isn't climbing
                ElytraFix.shouldElytraFly &&     //should fly
                !livingEntity.isFallFlying() &&     //isn't flying
                !entity.isTouchingWater()           //isn't in water
        )
        {
            Chat.print("ElytraFix", "Reactivating Elytra");

            //set flying locally
            playerEntity.method_23669();

            //send flying packet
            this.networkHandler.sendPacket(new ClientCommandC2SPacket((PlayerEntity) playerEntity, ClientCommandC2SPacket.Mode.START_FALL_FLYING));
        }
    }


    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void sendChatMessage(String message, CallbackInfo ci)
    {
        if(message.startsWith("/"))
        {
            StringReader reader = new StringReader(message);
            reader.skip();

            int cursor = reader.getCursor();

            String commandName = reader.canRead() ? reader.readUnquotedString() : "";
            reader.setCursor(cursor);

            if(ClientCommandManager.isClientSideCommand(commandName))
            {
                ClientCommandManager.executeCommand(reader, message);
                ci.cancel();
            }
        }
    }
}
