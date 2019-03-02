package com.github.dwesolowski.eventmessages;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private final EventMessages plugin;

    PlayerJoin(EventMessages pl) {
        this.plugin = pl;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        final String join = this.plugin.getConfig().getString("Join").replace("{player_name}", p.getName());
        for (final String messages : this.plugin.getConfig().getStringList("Message")) {
            final String msg = messages.replace("{player_name}", p.getName());
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', join));
    }
}