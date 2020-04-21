package de.nwex.elytrafix;

import de.nwex.elytrafix.util.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Formatting;

import java.io.File;

public class ElytraFix implements ClientModInitializer
{
    public static Boolean shouldElytraFly = false;
    public static Integer playerEntityId = 0;
    public static Boolean active;

    public static Formatting BASE = Formatting.GRAY;
    public static Formatting ACCENT = Formatting.GREEN;
    public static Formatting HIGHLIGHT = Formatting.WHITE;
    public static Formatting DARK = Formatting.DARK_GRAY;
    public static Formatting WARN = Formatting.YELLOW;
    public static Formatting ERROR = Formatting.RED;

    @Override
    public void onInitializeClient()
    {
        File configDir = new File(FabricLoader.getInstance().getConfigDirectory(), "elytrafix");

        //noinspection ResultOfMethodCallIgnored
        configDir.mkdirs();

        ConfigManager.get();
    }
}
