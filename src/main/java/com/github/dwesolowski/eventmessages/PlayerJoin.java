package com.github.dwesolowski.eventmessages;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.Random;

public class PlayerJoin implements Listener {

    private int counter = 0;
    private final EventMessages plugin;

    PlayerJoin(EventMessages pl) {
        this.plugin = pl;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        final String join = this.plugin.getConfig().getString("Join").replace("{player_name}", p.getName());
        boolean random = this.plugin.getConfig().getBoolean("random-mode");

        if (this.counter == this.plugin.getConfig().getConfigurationSection("Messages").getKeys(false).size()) {
            this.counter = 0;
        }
        if (random) {
            this.counter = new Random().nextInt(this.plugin.getConfig().getConfigurationSection("Messages").getKeys(false).size());
        }

        final List<String> Message = this.plugin.getConfig().getStringList("Messages." + this.counter + ".Message");
        for (final String s : Message) {
            final String msg = s.replace("{player_name}", p.getName());
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }
        ++this.counter;
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', join));
    }
}