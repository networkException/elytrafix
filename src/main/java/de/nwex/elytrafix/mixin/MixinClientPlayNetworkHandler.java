package de.nwex.elytrafix.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import de.nwex.elytrafix.util.Chat;
import de.nwex.elytrafix.ElytraFix;
import de.nwex.elytrafix.command.ClientCommandManager;
import de.nwex.elytrafix.util.ConfigManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.play.CommandTreeS2CPacket;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandSource;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler
{
    @Shadow
    private CommandDispatcher<CommandSource> commandDispatcher;

    @Shadow
    private MinecraftClient client;

    private Boolean once = true;

    @Inject(method = "onGameJoin", at = @At("HEAD"))
    public void onGameJoin(GameJoinS2CPacket packet, CallbackInfo ci)
    {
        ElytraFix.shouldElytraFly = false;

        if(once)
        {
            once = false;

            new Thread(() ->
            {
                try
                {
                    Thread.sleep(100);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }

                if(client.player != null)
                {
                    Chat.print(ElytraFix.active ? "ElytraFix active" : "ElytraFix inactive");

                    ElytraFix.playerEntityId = client.player.getEntityId();
                }
            }).start();
        }
    }

    private void command(CommandDispatcher<ServerCommandSource> dispatcher)
    {
        ClientCommandManager.clearClientSideCommands();
        ClientCommandManager.addClientSideCommand("elytrafix");

        dispatcher.register(CommandManager.literal("elytrafix").executes((context) ->
        {
            ElytraFix.active = !ElytraFix.active;

            Chat.print("Toggle", ElytraFix.active ? "ElytraFix active" : "ElytraFix inactive");

            ConfigManager.set();

            return 1;
        }));
    }

    @SuppressWarnings("unchecked")
    @Inject(method = "<init>", at = @At("RETURN"))
    public void onInit(MinecraftClient mc, Screen screen, ClientConnection connection, GameProfile profile, CallbackInfo ci)
    {
        command((CommandDispatcher<ServerCommandSource>) (Object) commandDispatcher);
    }

    @SuppressWarnings("unchecked")
    @Inject(method = "onCommandTree", at = @At("TAIL"))
    public void onOnCommandTree(CommandTreeS2CPacket packet, CallbackInfo ci)
    {
        command((CommandDispatcher<ServerCommandSource>) (Object) commandDispatcher);
    }
}
