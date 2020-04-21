package de.nwex.elytrafix;

import de.nwex.elytrafix.util.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Formatting;

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
        ConfigManager.get();
    }
}
