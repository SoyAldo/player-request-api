package org.kayteam.playerrequestapi.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.kayteam.playerrequestapi.PlayerRequestManager;

import java.util.UUID;

public class PlayerJoinListener implements Listener {

    private final PlayerRequestManager playerRequestManager;

    public PlayerJoinListener(PlayerRequestManager playerRequestManager) {

        this.playerRequestManager = playerRequestManager;

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        // Get Player
        Player player = event.getPlayer();
        // Getting the Player UUID
        UUID uuid = player.getUniqueId();
        // Adding Requests to PlayerRequestManager
        playerRequestManager.addRequests(uuid);

    }

}