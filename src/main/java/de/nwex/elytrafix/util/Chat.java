package de.nwex.elytrafix.util;

import de.nwex.elytrafix.ElytraFix;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class Chat
{
    public static void print(Text prefix, Text message)
    {
        Text chat = new LiteralText("")
            .append(new LiteralText("[").formatted(ElytraFix.DARK))
            .append(new LiteralText("networkTools").formatted(ElytraFix.ACCENT))
            .append(new LiteralText("] ").formatted(ElytraFix.DARK))
            .append(prefix.formatted(ElytraFix.BASE))
            .append(new LiteralText(" > ").formatted(ElytraFix.ACCENT))
            .append(message.formatted(ElytraFix.BASE));

        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(chat);
    }

    public static void print(String prefix, String message)
    {
        Text chat = new LiteralText("")
            .append(new LiteralText("[").formatted(ElytraFix.DARK))
            .append(new LiteralText("networkTools").formatted(ElytraFix.ACCENT))
            .append(new LiteralText("] ").formatted(ElytraFix.DARK))
            .append(new LiteralText(prefix).formatted(ElytraFix.BASE))
            .append(new LiteralText(" > ").formatted(ElytraFix.ACCENT))
            .append(new LiteralText(message).formatted(ElytraFix.BASE));

        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(chat);
    }

    public static void print(String message)
    {
        print("Log", message);
    }

    public static void warn(String prefix, String message)
    {
        Text chat = new LiteralText("")
            .append(new LiteralText("[").formatted(ElytraFix.DARK))
            .append(new LiteralText("networkTools").formatted(ElytraFix.ACCENT))
            .append(new LiteralText("] ").formatted(ElytraFix.DARK))
            .append(new LiteralText(prefix).formatted(ElytraFix.WARN))
            .append(new LiteralText(" > ").formatted(ElytraFix.ACCENT))
            .append(new LiteralText(message).formatted(ElytraFix.BASE));

        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(chat);
    }

    public static void warn(String message)
    {
        print("Warn", message);
    }

    public static void error(Text prefix, Text message)
    {
        Text chat = new LiteralText("")
            .append(new LiteralText("[").formatted(ElytraFix.DARK))
            .append(new LiteralText("networkTools").formatted(ElytraFix.ACCENT))
            .append(new LiteralText("] ").formatted(ElytraFix.DARK))
            .append(prefix.formatted(ElytraFix.ERROR))
            .append(new LiteralText(" > ").formatted(ElytraFix.ACCENT))
            .append(message.formatted(ElytraFix.BASE));

        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(chat);
    }

    public static void error(String prefix, String message)
    {
        Text chat = new LiteralText("")
            .append(new LiteralText("[").formatted(ElytraFix.DARK))
            .append(new LiteralText("networkTools").formatted(ElytraFix.ACCENT))
            .append(new LiteralText("] ").formatted(ElytraFix.DARK))
            .append(new LiteralText(prefix).formatted(ElytraFix.ERROR))
            .append(new LiteralText(" > ").formatted(ElytraFix.ACCENT))
            .append(new LiteralText(message).formatted(ElytraFix.BASE));

        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(chat);
    }

    public static void error(String message)
    {
        print("Error", message);
    }
}
