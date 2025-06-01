package com.magmaguy.resourcepackmanager.commands;

import com.magmaguy.magmacore.command.AdvancedCommand;
import com.magmaguy.magmacore.command.CommandData;
import com.magmaguy.magmacore.util.Logger;
import com.magmaguy.resourcepackmanager.ResourcePackManager;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ReloadCommand extends AdvancedCommand {
    public ReloadCommand() {
        super(List.of("reload"));
        setDescription("Reloads the plugin");
        setPermission("resourcepackmanager.*");
        setUsage("/fmm reload");
    }

    public static void reloadPlugin(CommandSender sender) {
        ResourcePackManager.instance.onDisable();
        ResourcePackManager.instance.onEnable();
        Logger.sendMessage(sender, "Reloaded the plugin!");
    }

    @Override
    public void execute(CommandData commandData) {
        ResourcePackManager.instance.onDisable();
        ResourcePackManager.instance.onEnable();
        Logger.sendMessage(commandData.getCommandSender(), "Reloaded the plugin!");
    }
}