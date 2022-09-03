package org.kayteam.playerrequestapi.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.kayteam.playerrequestapi.PlayerRequestManager;

import java.util.UUID;

public class PlayerQuitListener implements Listener {

    private final PlayerRequestManager playerRequestManager;

    public PlayerQuitListener(PlayerRequestManager playerRequestManager) {

        this.playerRequestManager = playerRequestManager;

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        // Get Player
        Player player = event.getPlayer();
        // Getting the Player UUID
        UUID uuid = player.getUniqueId();
        // Removing Requests from PlayerRequestManager
        playerRequestManager.removeRequests(uuid);

    }

}