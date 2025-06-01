package com.magmaguy.resourcepackmanager.Listeners;

import com.magmaguy.resourcepackmanager.commands.ReloadCommand;
import com.magmaguy.freeminecraftmodels.events.ResourcePackGenerationEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ResourcePackGeneratedEvent implements Listener {
    @EventHandler
    public void onResourcePackGenerated(ResourcePackGenerationEvent event) {
        // ReloadCommand.reloadPlugin(Bukkit.getConsoleSender()); // Disabled for Folia compatibility as plugin reloading can cause issues.
    }
}
