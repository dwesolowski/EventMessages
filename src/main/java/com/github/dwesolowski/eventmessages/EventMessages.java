package com.github.dwesolowski.eventmessages;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EventMessages extends JavaPlugin {

    @Override
    public void onEnable() {
        registerConfig();
        registerEvents();
        registerMetrics();
    }

    @Override
    public void onDisable() {
        getServer().getPluginManager().disablePlugin(this);
    }

    private void registerConfig() {
        saveDefaultConfig();
        saveConfig();
    }

    private void registerEvents() {
        final PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(this),this);
        pm.registerEvents(new PlayerLeave(this),this);
    }

    private void registerMetrics() {
        final MetricsLite metrics = new MetricsLite(this);
    }
}