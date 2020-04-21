package de.nwex.elytrafix.util;

import de.nwex.elytrafix.ElytraFix;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

public class ConfigManager
{
    public static void get()
    {
        File configFile = new File(FabricLoader.getInstance().getConfigDirectory() + File.separator + "elytrafix" + File.separator + "config.txt");

        if(!configFile.exists())
        {
            FileUtil.setTextContent(configFile, "true");
        }

        ElytraFix.active = Boolean.valueOf(FileUtil.getTextContent(configFile));
    }

    public static void set()
    {
        FileUtil.setTextContent(new File(FabricLoader.getInstance().getConfigDirectory() + File.separator + "elytrafix" + File.separator + "config.txt"), String.valueOf(ElytraFix.active));
    }
}
