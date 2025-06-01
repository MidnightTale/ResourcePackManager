package com.magmaguy.resourcepackmanager;

import com.magmaguy.resourcepackmanager.commands.DataComplianceRequestCommand;
import com.magmaguy.resourcepackmanager.commands.ReloadCommand;
import com.magmaguy.resourcepackmanager.utils.VersionChecker;
import com.magmaguy.freeminecraftmodels.bukkit.Metrics;
import com.magmaguy.magmacore.MagmaCore;
import com.magmaguy.magmacore.command.CommandManager;
import com.magmaguy.magmacore.util.Logger;
import com.magmaguy.resourcepackmanager.autohost.AutoHost;
import com.magmaguy.resourcepackmanager.config.BlueprintFolder;
import com.magmaguy.resourcepackmanager.config.DataConfig;
import com.magmaguy.resourcepackmanager.config.DefaultConfig;
import com.magmaguy.resourcepackmanager.Listeners.ResourcePackGeneratedEvent;
import com.magmaguy.resourcepackmanager.mixer.Mix;
import com.magmaguy.resourcepackmanager.playermanager.PlayerManager;
import me.nahu.scheduler.wrapper.WrappedScheduler;
import me.nahu.scheduler.wrapper.WrappedSchedulerBuilder;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class ResourcePackManager extends JavaPlugin {

    public static ResourcePackManager instance;
    public final WrappedSchedulerBuilder schedulerBuilder = WrappedSchedulerBuilder.builder().plugin(instance);
    public final WrappedScheduler scheduler = schedulerBuilder.build();

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("\n" +
                "  ___  ___ ___ __  __                             \n" +
                " | _ \\/ __| _ \\  \\/  |__ _ _ _  __ _ __ _ ___ _ _ \n" +
                " |   /\\__ \\  _/ |\\/| / _` | ' \\/ _` / _` / -_) '_|\n" +
                " |_|_\\|___/_| |_|  |_\\__,_|_||_\\__,_\\__, \\___|_|  \n" +
                "                                    |___/         ");
        Bukkit.getLogger().info("ResourcePackManager v." + this.getDescription().getVersion());
        MagmaCore.onEnable();


        instance = this;
        new DefaultConfig();
        new DataConfig();
        BlueprintFolder.initialize();
        Mix.initialize();
        if (DefaultConfig.isAutoHost())
            Bukkit.getPluginManager().registerEvents(new PlayerManager(), this);
        CommandManager commandManager = new CommandManager(this, "resourcepackmanager");
        commandManager.registerCommand(new ReloadCommand()); // Disabled for Folia compatibility due to potential issues with plugin reloading.
        commandManager.registerCommand(new DataComplianceRequestCommand());
        if (Bukkit.getPluginManager().isPluginEnabled("FreeMinecraftModels")) Bukkit.getPluginManager().registerEvents(new ResourcePackGeneratedEvent(), this); // Disabled for Folia compatibility due to plugin reloading.
        Bukkit.getPluginManager().registerEvents(new VersionChecker.VersionCheckerEvents(), this);
        AutoHost.initialize();

        Metrics metrics = new Metrics(this, 22867);
        VersionChecker.checkPluginVersion();
    }

    @Override
    public void onLoad(){
        MagmaCore.createInstance(this);
    }

    @Override
    public void onDisable() {
        Logger.info("Disabling ResourcePackManager");
        HandlerList.unregisterAll(this);
        AutoHost.shutdown();
    }
}