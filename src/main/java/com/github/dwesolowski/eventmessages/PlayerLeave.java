package com.github.dwesolowski.eventmessages;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    private final EventMessages plugin;

    PlayerLeave(EventMessages pl) {
        this.plugin = pl;
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        final String quit = this.plugin.getConfig().getString("Quit").replace("{player_name}", p.getName());
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', quit));
    }
}